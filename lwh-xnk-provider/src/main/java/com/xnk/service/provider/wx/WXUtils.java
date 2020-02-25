package com.xnk.service.provider.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.xnk.service.provider.config.SysConfig;


public class WXUtils {

	public  static final Logger logger = LoggerFactory.getLogger(WXUtils.class);
	/**
	 * 获取用户授权URL
	 * @return
	 */
	public static String getAuthUrl(String appid,String url,String redirectUri) {
		url = url.replace("APPID", appid);
		url = url.replace("REDIRECT_URI",redirectUri);
		logger.info(String.format("getAuthUrl<===%s",url));
		return url;
	}
	
	/**
     * 统一下单
     * @param name
     *            商品名称
     * @param total_fee
     *            价格（单位：分）
     * @param ip
     *            ip
	 * @param wxConfig 
     */
    public static Map<String, String> payUnifiedorder(
        String name,
        int total_fee,
        String ip,
        String out_trade_no,
        String notify_url,
        String nonce_str,
        String openId) {
    	
        UnifiedorderPayReqDate unifiedorderPayReqDate = new UnifiedorderPayReqDate(SysConfig.WX_APP_ID,
        		SysConfig.WX_MERCHATID, "", name, "", "", out_trade_no, "", total_fee, ip, "", "", "", notify_url,
                "JSAPI", "", nonce_str, openId,SysConfig.WX_MERCHATKEY);

        String postDataXML = unifiedorderPayReqDate.toXml();
        postDataXML = postDataXML + "\n";
        logger.debug(String.format("postDataXML=%s",postDataXML));
        try {
            String res = HttpUtils.postXml(SysConfig.WX_PAY_URL, postDataXML);
            logger.debug(String.format("HttpUtils.postXml=%s",res));
            Map<String, String> map = XMLParser.getMapFromXML(res);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
public static String getOpenId(String code) {
		
		String url = SysConfig.WX_OPEN_ID_URL;
		
		url = url.replace("APPID",SysConfig.WX_APP_ID);
		url = url.replace("APPSECRET", SysConfig.WX_APP_SECRET);
		if(code!=null){
			url = url.replace("CODE", code);
		}
		
		logger.info(String.format("getOpenId===>url=%s",url));
	    
		JSONObject json = httpRequest(url, "GET", null);
		String openid = "";
		try {
			if (json != null) {
				if(json.containsKey("openid")){
					openid = json.getString("openid");
				}
				logger.info("json parse");
				Set set = json.keySet();
				for (Object key : set) {
					logger.info(String.format("%s=%s", key, json.get(key)));
				}
				logger.info("json parse end");
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		logger.info(String.format("getOpenId<===%s",openid));
		return openid;
		 
	}

/**
 * 发起https请求并获取结果
 * 
 * @param requestUrl
 *            请求地址
 * @param requestMethod
 *            请求方式（GET、POST）
 * @param outputStr
 *            提交的数据
 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
 */
public static JSONObject httpRequest(String requestUrl,
		String requestMethod, String outputStr) {
	JSONObject jsonObject = null;
	StringBuffer buffer = new StringBuffer();
	try {
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();

		URL url = new URL(requestUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
				.openConnection();
		httpUrlConn.setSSLSocketFactory(ssf);

		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false);
		// 设置请求方式（GET/POST）
		httpUrlConn.setRequestMethod(requestMethod);

		if ("GET".equalsIgnoreCase(requestMethod))
			httpUrlConn.connect();

		// 当有数据需要提交时
		if (null != outputStr) {
			OutputStream outputStream = httpUrlConn.getOutputStream();
			// 注意编码格式，防止中文乱码
			outputStream.write(outputStr.getBytes("UTF-8"));
			outputStream.close();
		}

		// 将返回的输入流转换成字符串
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(
				inputStreamReader);

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// 释放资源
		inputStream.close();
		inputStream = null;
		httpUrlConn.disconnect();
		
		logger.debug("return value ==>"+buffer.toString());
		jsonObject = (JSONObject)JSONObject.parse(buffer.toString());;
	} catch (ConnectException ce) {
		ce.printStackTrace();
		logger.error("Weixin server connection timed out.");
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(String.format("https request error:%s",e.getCause().getMessage()));
	}
	return jsonObject;
}
}
