package cn.com.nisong.db;

import java.util.List;
import java.util.Map;

public interface IDataHandler {

	public void save(Map<String,List<Map>> jsonDataMap);
	
}
