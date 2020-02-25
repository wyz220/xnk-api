package com.xnk.service.provider.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xnk.service.provider.utils.EnumUtils.C_ENVIRONMENT;


/**
 * @desc 本类用户获取properties文件中对应key的value值
 */
public class Global {
	
	private static Logger logger = LoggerFactory.getLogger(Global.class);
	/**
	 * 无法直接获取此实例
	 */
	private static Global global = new Global();
	
	public static Global getInstance(){
		return global;
	}
	/**
	 * 属性文件的缓存map 使用ConcurrentMap保证线程安全
	 */
	private static ConcurrentMap<String, Properties> propMap = new ConcurrentHashMap<String, Properties>();
	/**
	 * 属性值的缓存map 使用ConcurrentMap保证线程安全
	 */
	private static ConcurrentMap<String, String> valMap = new ConcurrentHashMap<String, String>();
	/**
	 * 当前properties文件名
	 */
	private static final String DEFAULT_CONFIG_FILE = "application.properties";  
	/**
	 * 根据fileName获取当前资源文件对应的Properties对象
	 */
	public Properties getPropFromProperties(String fileName) throws Exception{
		//先从静态的map中获取prop文件
		Properties prop = propMap.get(fileName);
		if(prop != null){
			return prop;
		}
		String filePath = null;
		String configPath = System.getProperty("configurePath");
		logger.info("***********configPath***************"+configPath);
		if(MacUtils.getOSName() != null
				&& MacUtils.getOSName().startsWith(
						C_ENVIRONMENT.LINUX.value)){
			if (configPath == null) {
				logger.info("**********LINUX system*********");
				filePath = this.getClass().getClassLoader().getResource(fileName)
						.getPath();
				logger.info("*************filePath before*************" + filePath);
				filePath = java.net.URLDecoder.decode(filePath,"utf-8");
				logger.info("*************filePath after*************" + filePath);
				filePath = removeJarName(filePath);
				logger.info("*************filePath*************"+filePath);
			} else {
				filePath = configPath + File.separator + fileName;
			}
		}else{
			if (configPath == null) {
				logger.info("**********window system*********");
				filePath = this.getClass().getClassLoader().getResource(fileName)
						.getPath();
				filePath = java.net.URLDecoder.decode(filePath,"utf-8");
				logger.info("*************filePath*************"+filePath);
			} else {
				filePath = configPath + File.separator + fileName;
			}
		}
		prop = new Properties();
		//Properties的load方法，需要传入对应路径的文件流对象
		prop.load(new FileInputStream(new File(filePath)));
		propMap.put(fileName, prop);
		return prop;
	}
	/**
	 * 改写方法，根据当前已知的文件名和key获取对应的value
	 */
	public static String getConfig(String key){
		Properties prop = null;
		try {
			prop = global.getPropFromProperties(DEFAULT_CONFIG_FILE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		key = key.trim();
		if(!valMap.containsKey(key)){
			if(prop.getProperty(key) != null){
				valMap.put(key, prop.getProperty(key));
			}
		}
		return valMap.get(key); 
	}
	
    public static String getAdminPath(){
    	return getConfig("adminPath");
    }
    
    public static String getUIPath() {
    	return getConfig("uiPath");
    }
    
    public static String removeJarName(String s){
    	String[]  str  = StringUtils.split(s,"/");
    	String result = "/";
    	for(String sb : str){
			if(StringUtils.contains(sb, "jar") || StringUtils.contains(sb, "file")){
				continue;
			}
			result = result + sb + "/";
		}
    	result = StringUtils.removeEnd(result, "/");
    	return result;
    }
    
    public static void main(String[] args) {
		System.out.println(removeJarName("file:/usr/local/cluster/service/loginService/msht-handle-provider-0.0.1-SNAPSHOT.jar!/application.properties"));
	}
}
