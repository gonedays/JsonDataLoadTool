package cn.com.nisong;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RMDBMappingHolder {
	
	private static  Logger logger=LoggerFactory.getLogger(RMDBMappingHolder.class);
	
	private volatile static RMDBMappingHolder instance ;
	
	private Map<String,String> columnDataTypeMap=null;
		
	
	private RMDBMappingHolder(String xmlFile){
		
		try {
			columnDataTypeMap=parseXML(xmlFile);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("Failed to parse xml file {}",xmlFile,e);
			columnDataTypeMap=new HashMap<String,String>();
		}
	}
	 
	public static RMDBMappingHolder getInstance(String xmlFile) {
		if (instance == null) {
			synchronized (RMDBMappingHolder.class) {
				if (instance == null) {
					instance = new RMDBMappingHolder(xmlFile);
				}
			}
		}
		return instance;

	}

	public Map<String, String> getColumnDataTypeMap() {
		return columnDataTypeMap;
	}

	/**
	 * 解析XML，读取列和数据类型的映射关系
	 * 
	 *
	
	 *      <columns>
	 *      		<columnName>dataType</columnName>
	 *      	    <columnName>dataType</columnName>
	 *      		<columnName>dataType</columnName>
	 *      </columns> 
	
	 * 
	 * @param createNewTable
	 * @param fileName
	 * @throws DocumentException 
	 */
	private  Map<String,String> parseXML(String fileName) throws DocumentException {

		SAXReader saxReader = new SAXReader();
		Map<String,String> columnDataTypeMap=new HashMap<String,String>();
		try {
			Document document = saxReader.read(RMDBMappingHolder.class.getClassLoader().getResourceAsStream(fileName));
			
			Element columns = document.getRootElement();
			Iterator it = columns.elementIterator();
			while (it.hasNext()) {				
				Element column = (Element) it.next();	
				
				columnDataTypeMap.put(column.getName(), column.getText());
				
			}
			return columnDataTypeMap;
		} catch (DocumentException e) {
			logger.error("Failed to parse xml file:{}",fileName, e);
			throw e ;
		}
		
	}
	
//	public static void main(String[] args) {
//
//		Map map = null;
//		try {
//			map = RMDBMappingHolder.parseXML("mapping.xml");
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(map);
//	}
}
