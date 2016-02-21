package cn.com.nisong.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.com.nisong.RMDBMappingHolder;
import cn.com.nisong.util.DateUtil;

@Service("mysqlDataHandler")
public class MysqlDataHandler implements IDataHandler {
	private static Logger logger = LoggerFactory
			.getLogger(MysqlDataHandler.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${data.mapping.file}")
	String mappingFile;
	
	@Override
	public void save(Map<String, List<Map>> jsonDataMap) {

		StringBuffer sb = new StringBuffer("INSERT INTO ");

		final Map<String, String> columnDataTypeMap = RMDBMappingHolder.getInstance(mappingFile)
				.getColumnDataTypeMap();
		Set<String> cols = columnDataTypeMap.keySet();
		final ArrayList<String> colsList = new ArrayList<String>(cols.size());
		for (String col : cols) {
			colsList.add(col);
		}

		Set entrySet = jsonDataMap.entrySet();
		if (entrySet != null) {
			Iterator it = entrySet.iterator();
			if (it.hasNext()) {
				Entry entry = (Entry) it.next();
				String table = (String) entry.getKey();
				// 待插入的数据
				final List<Map> dataList = (List<Map>) entry.getValue();

				logger.debug("table:{}", table);

				sb.append("`");
				sb.append(table);
				sb.append("`(");

				StringBuffer sb2 = new StringBuffer();
				StringBuffer sb3 = new StringBuffer();
				for (String col : colsList) {
					sb2.append("`");
					sb2.append(col);
					sb2.append("`,");

					sb3.append("?,");

				}
				// 去掉最后一个逗号
				String colsString = sb2.toString();
				colsString = colsString.substring(0,
						colsString.lastIndexOf(","));
				sb.append(colsString);
				sb.append(") VALUES (");

				// 去掉最后一个逗号
				String valuesString = sb3.toString();
				valuesString = valuesString.substring(0,
						valuesString.lastIndexOf(","));
				sb.append(valuesString);
				
				sb.append(")");

				String insertSql = sb.toString();
				logger.info("Sql from insert data:{}"+insertSql);
				jdbcTemplate.batchUpdate(insertSql,
						new BatchPreparedStatementSetter() {

							@Override
							public void setValues(PreparedStatement ps, int i)
									throws SQLException {
								// 批量插入ֵ
								Map record = dataList.get(i);

								int colLength = colsList.size();
								for (int j = 0; j < colLength; j++) {

									String column = colsList.get(j);
									String dataType = columnDataTypeMap
											.get(column);

									Object value = record.get(column);

									if ("int".equalsIgnoreCase(dataType)) {
										Integer te = 0;
										
										try {
											te = (Integer) value;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											te = 0;
										}

										ps.setInt(j+1, te.intValue());

									} else if ("varchar".equalsIgnoreCase(dataType)) {

										
										ps.setString(j+1, String.valueOf(value));
										
									} else if ("double".equalsIgnoreCase(dataType)) {
										Double te = 0.0;
										
										try {
											te = (Double) value;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											te = 0.0;
										}

										ps.setDouble(j+1, te.doubleValue());
									} else if ("date".equalsIgnoreCase(dataType)) {
										Date te = DateUtil
												.parseToSqlDate((String) value);
										ps.setDate(j+1, te);
									} else if ("timestamp".equalsIgnoreCase(dataType)) {
										Timestamp te = DateUtil
												.parseToSqlDateTime((String) value);
										ps.setTimestamp(j, te);
									} else if ("bigint".equalsIgnoreCase(dataType)) {
										Long te = 0L;
										try {
											te = (Long) value;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											te = 0L;
										}

										ps.setLong(j+1, te.longValue());
									} else {
										logger.error(
												"Unkown data type for column {}. ",
												column);
										throw new RuntimeException(
												"Unkown data type for column "
														+ column + ".");
									}

								}

							}

							@Override
							public int getBatchSize() {
								// TODO Auto-generated method stub
								return dataList.size();
							}
						});

			}

		}

	}

}
