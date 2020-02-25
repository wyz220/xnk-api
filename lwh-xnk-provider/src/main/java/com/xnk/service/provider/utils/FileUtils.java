package com.xnk.service.provider.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件工具类
 * 
 * @author wuyanzhong
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	public static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);

		}

		return stream;
	}

	public static List<String> readTxtFile(String filePath) {
		List<String> fileLines = new ArrayList<String>();
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					fileLines.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
 
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return fileLines;
	}
	

}
