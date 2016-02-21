package cn.com.nisong;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.com.nisong.db.DataHandlerProvider;
import cn.com.nisong.db.IDataHandler;
import cn.com.nisong.db.JsonDataParser;


@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Main  implements CommandLineRunner{

	@Value("${data.db}")
	private String db;
	
	@Value("${data.json.file}")
	private String jsonData;
	
	@Resource
	private DataHandlerProvider dataHandlerProvider;
	
	 public static void main(String[] args)  {
	        SpringApplication.run(Main.class, args);
	    }

	
	@Override
	public void run(String... args) throws Exception {
		 IDataHandler dataHandler=dataHandlerProvider.build(db);
			
		 dataHandler.save( JsonDataParser.parseJson(jsonData));
		
	}
	 
}
