package cn.com.nisong.db;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class DataHandlerProvider {

	private static final String MYSQL_DATAHANDLER = "mysql";
	private static final String GF_DATAHANDLER = "gemfire";

	@Resource(name = "mysqlDataHandler")
	private IDataHandler mysqlDataHandler;

	@Resource(name = "gemfireDataHandler")
	// @Autowired
	// @Qualifier("gemfireDataHandler")
	private IDataHandler gemfireDataHandler;

	
	
	public IDataHandler build(String name) {

		if (name.equalsIgnoreCase(MYSQL_DATAHANDLER)) {

			return mysqlDataHandler;
		} else if (name.equalsIgnoreCase(GF_DATAHANDLER)) {
			return gemfireDataHandler;
		} else {
			return null;
		}

	}

}
