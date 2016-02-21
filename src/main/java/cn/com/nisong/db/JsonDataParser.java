package cn.com.nisong.db;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.nisong.util.FileUtil;

import com.alibaba.fastjson.JSON;

public class JsonDataParser {
	private static  Logger logger=LoggerFactory.getLogger(JsonDataParser.class);
	public static Map<String,List<Map>> parseJson(String jsonFile){
		
		Map<String,List<Map>> map=new HashMap<String,List<Map>>();
		
		File file=new File(JsonDataParser.class.getClassLoader().getResource(jsonFile).getFile());
		String jsonString=FileUtil.read(file);
		map=JSON.parseObject(jsonString,Map.class );
		
		return map;
		
	}
	
	
	public static void main(String[] args) {
		
		Map<String,List<Map>> map=JsonDataParser.parseJson("data.json");
		Set entrySet=map.entrySet();
		if(entrySet!=null){
			Iterator it=entrySet.iterator();
			if(it.hasNext())
			{
				Entry entry=(Entry)it.next();
				String table=(String)entry.getKey();
				List<Map> list=(List<Map>)entry.getValue();
				System.out.println("table:"+table);
				logger.debug("table:{}",table);
				for(Map m:list){	
					System.out.println(m);
					logger.debug(m.toString());
					
				}
				
			}
			
		}
		
	}
	
}
