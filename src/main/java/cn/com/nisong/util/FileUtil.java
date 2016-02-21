package cn.com.nisong.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static String read(String path) {

		File file = new File(path);
		return read(file);
	}

	public static String read(File file) {

		BufferedReader reader = null;

		StringBuffer data = new StringBuffer();
		
		try {
			reader = new BufferedReader(new FileReader(file));
		
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				data.append(temp);
			}
		} catch (FileNotFoundException e) {
			logger.error("File not found:{}", file, e);
		} catch (IOException e) {
			logger.error("Failed to read file :{}", file, e);
		} finally {

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Failed to read file :{}", file, e);
				}
			}
		}
		return data.toString();
	}
}
