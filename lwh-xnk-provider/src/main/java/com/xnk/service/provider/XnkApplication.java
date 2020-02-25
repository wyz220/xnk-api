package com.xnk.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xnk.service.provider.utils.ConfigUtil;


/**
 * 
 * @Description:  启动类
 */
/**
 * springboot入口类,此类需要在所有用到的package上层
 * exclude = {DataSourceAutoConfiguration.class}
 * 禁用springboot默认加载的application.properties单数据源配置
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
public class XnkApplication {

	protected static Logger logger = LoggerFactory
			.getLogger(XnkApplication.class);
		
	public static void main(String[] args) {
		try {
		logger.info("鞋凝客接口服务群启动......start");
		SpringApplication.run(XnkApplication.class, args);
		logger.info("鞋凝客接口服务群启动中......end");
		ConfigUtil.init("wx.properties");
		} catch (Exception e) {
			logger.error("启动出错",e);
		}
			
		
	}
}
