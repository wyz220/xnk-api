/**
 * 
 */
package com.xnk.service.config;

import java.util.HashMap;
import java.util.Map;




/**
 * 系统配置
 * @author master by 2019.04.18
 *
 */
public class SysConfig {

	//测评评论项
	public static Map<Integer, String> commentSeqMap = new HashMap<Integer, String>();
	
	
	static {
		try{
			init();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void init() {
		commentSeqMap.put(1, "脚感磨合");
		commentSeqMap.put(2, "外观设计");
		commentSeqMap.put(3, "做工");
		commentSeqMap.put(4, "包裹性");
		commentSeqMap.put(5, "支撑性");
		commentSeqMap.put(6, "灵活性");
		commentSeqMap.put(7, "透气性");
		commentSeqMap.put(8, "抓地力");
		commentSeqMap.put(9, "耐磨性");
		commentSeqMap.put(10, "缓震性");
	}

}
