/**
 * 
 */
package com.xnk.service.provider.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.xnk.service.provider.utils.FileUtils;



/**
 * 系统配置
 * @author master by 2019.04.18
 *
 */
public class SysConfig {


	public static String SECURITY_SIGN_KEY;
	public static String SECURITY_ENCRYPT_KEY;
	
	public static String WX_IP;
    public static String WX_MERCHATID;
    public static String WX_MERCHATKEY;
    public static String WX_APP_ID;
    public static String WX_APP_SECRET;
    public static String WX_DOMAIN;
    public static String WX_CODE_URL;
    public static String WX_REDIRECT_URI;
    public static String WX_NOTIFY_URL;
    public static String WX_AUTH_URL;
    public static String WX_OPEN_ID_URL;
    public static String WX_PAY_URL;
    
    public static Map<Integer, String> commonStatusMap = new HashMap<Integer, String>();
	
    public static Map<Integer, String> basketBallPostionMap = new HashMap<Integer, String>();
    public static Map<Integer, String> halfCourtMap = new HashMap<Integer, String>();
    public static Map<Integer, String> stackNoBallStateMap = new HashMap<Integer, String>();
	
    public static Map<Integer, String> dribbleUseMap = new HashMap<Integer, String>();
    public static Map<Integer, String> shangLanUseMap = new HashMap<Integer, String>();
    public static Map<Integer, String> touLanUseMap = new HashMap<Integer, String>();
    public static Map<Integer, String> attackMap = new HashMap<Integer, String>();
    public static Map<Integer, String> defendMap = new HashMap<Integer, String>();
    public static Map<Integer, String> nutCapMap = new HashMap<Integer, String>();
	
    public static Map<Integer, String> footMap = new HashMap<Integer, String>();
    public static Map<Integer, String> boundMap = new HashMap<Integer, String>();
    public static Map<Integer, String> instepMap = new HashMap<Integer, String>();
    public static Map<Integer, String> physicalFunctionMap = new HashMap<Integer, String>();
	
    public static Map<Integer, String> shoeTypeMap = new HashMap<Integer, String>();
	public static Map<Integer, String> isPartialMap = new HashMap<Integer, String>();
	public static Map<Integer, String> shoeSizeMap = new HashMap<Integer, String>();
	public static Map<Integer, String> sockTypeMap = new HashMap<Integer, String>();
	public static Map<Integer, String> fieldTypeMap = new HashMap<Integer, String>();
	
	//测评推荐序
	public static Map<Integer, String> writeEvaluationRecomMap = new HashMap<Integer, String>();
	
	//测评满足条件列表
	public static Map<Integer, String> conditionEvaluationMap = new HashMap<Integer, String>();
	
	static {

		try{
			InputStream in  = FileUtils.getResourceAsStream("application.properties");
			Properties props = new Properties();
			props.load(in);
			init(props);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void init(Properties props) {

		SECURITY_SIGN_KEY = props.getProperty("security.sign.key");
		SECURITY_ENCRYPT_KEY = props.getProperty("security.encrypt.key");
		
		
		WX_IP = props.getProperty("weixin.ip");
		WX_MERCHATID = props.getProperty("weixin.merchatId");
		WX_MERCHATKEY = props.getProperty("weixin.merchatIdKey");
		WX_APP_ID = props.getProperty("weixin.appId");
		WX_APP_SECRET = props.getProperty("weixin.appSecret");
		WX_DOMAIN = props.getProperty("weixin.domain");
		WX_CODE_URL = props.getProperty("weixin.codeUrl");
		WX_REDIRECT_URI = props.getProperty("weixin.rediretUri");
		WX_NOTIFY_URL = props.getProperty("weixin.notifyUrl");
		WX_AUTH_URL = props.getProperty("weixin.authUrl");
		WX_OPEN_ID_URL = props.getProperty("weixin.openIdUrl");
		WX_PAY_URL = props.getProperty("weixin.payUrl");

		commonStatusMap.put(0, "无效");
		commonStatusMap.put(1, "有效");

		// 篮球上位置
		basketBallPostionMap.put(0, "控球后卫");
		basketBallPostionMap.put(1, "得分后卫");
		basketBallPostionMap.put(2, "小前锋");
		basketBallPostionMap.put(3, "大前锋");
		basketBallPostionMap.put(4, "中锋");

		halfCourtMap.put(0, "半场多");
		halfCourtMap.put(1, "全场多");
		halfCourtMap.put(2, "半场与全场次数差不多");

		stackNoBallStateMap.put(0, "为队友无球掩护");
		stackNoBallStateMap.put(1, "落位后原地看队友进攻待接球进攻");
		stackNoBallStateMap.put(2, "积极跑位接球进攻");
		stackNoBallStateMap.put(3, "落位到内线要球");
		stackNoBallStateMap.put(4, "冲抢进攻篮板");
		stackNoBallStateMap.put(5, "卡位抢进攻篮板");

		dribbleUseMap.put(0, "单边手运球");
		dribbleUseMap.put(1, "胯下运球");
		dribbleUseMap.put(2, "体前变向运球");
		dribbleUseMap.put(3, "转身运球");
		dribbleUseMap.put(4, "背后运球");

		shangLanUseMap.put(0, "标准三步上篮");
		shangLanUseMap.put(1, "跳步上篮");
		shangLanUseMap.put(2, "欧洲步");

		touLanUseMap.put(0, "原地投篮");
		touLanUseMap.put(1, "原地跳投");
		touLanUseMap.put(2, "前倾跳投");
		touLanUseMap.put(3, "后仰跳投");

		attackMap.put(0, "经常主动出击抢断");
		attackMap.put(1, "不怎么抢断");

		defendMap.put(0, "冲抢篮板");
		defendMap.put(1, "卡位抢篮板");
		defendMap.put(2, "不怎么抢篮板");

		nutCapMap.put(0, "经常有盖帽表现");
		nutCapMap.put(1, "不怎么盖帽");

		footMap.put(0, "正常足");
		footMap.put(1, "前掌宽");
		footMap.put(2, "前掌很宽");
		footMap.put(3, "前掌窄");
		footMap.put(4, "前掌很窄");

		boundMap.put(0, "正常足");
		boundMap.put(1, "高足弓");
		boundMap.put(2, "扁平足");

		instepMap.put(0, "正常脚背");
		instepMap.put(1, "高脚背");
		instepMap.put(2, "低脚背");

		physicalFunctionMap.put(0, "正常状态");
		physicalFunctionMap.put(1, "状态比平时好");
		physicalFunctionMap.put(2, "状态超常");
		physicalFunctionMap.put(3, "状态比平时差");
		physicalFunctionMap.put(4, "状态超差");
		
		shoeTypeMap.put(0, "篮球鞋");
		shoeTypeMap.put(1, "跑步鞋");

		isPartialMap.put(0, "偏大");
		isPartialMap.put(1, "偏大很多");
		isPartialMap.put(2, "正常码");
		isPartialMap.put(3, "偏小");
		isPartialMap.put(4, "偏小很多");
		
		shoeSizeMap.put(0, "前掌偏宽很多");
		shoeSizeMap.put(1, "前掌偏宽");
		shoeSizeMap.put(2, "正常宽度");
		shoeSizeMap.put(3, "前掌偏窄");
		shoeSizeMap.put(4, "前掌偏窄很多");
		
		sockTypeMap.put(0, "薄袜子");
		sockTypeMap.put(1, "厚袜子");
		sockTypeMap.put(2, "薄袜子和厚袜子");
	
		fieldTypeMap.put(0, "木地板");
		fieldTypeMap.put(1, "光滑水泥地");
		fieldTypeMap.put(2, "细颗粒水泥地");
		fieldTypeMap.put(3, "大颗粒水泥地");
		fieldTypeMap.put(4, "橡胶地板");
		fieldTypeMap.put(5, "镂空方格橡胶地板");
		
		
		writeEvaluationRecomMap.put(1, "很有收获");
		writeEvaluationRecomMap.put(2, "很有帮助");
		writeEvaluationRecomMap.put(3, "观点独特");
		writeEvaluationRecomMap.put(4, "文字精准");
		writeEvaluationRecomMap.put(5, "可读性强");
		writeEvaluationRecomMap.put(6, "图拍摄赞");
		writeEvaluationRecomMap.put(7, "图制作赞");

		conditionEvaluationMap.put(1, "满足条件1");
		conditionEvaluationMap.put(2, "满足条件2");
		conditionEvaluationMap.put(3, "满足条件3");
		conditionEvaluationMap.put(4, "满足条件4");
		conditionEvaluationMap.put(5, "满足条件5");

		
	}

}
