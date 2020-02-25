package com.xnk.service.provider.utils;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.alibaba.fastjson.JSON;


/**
 * Des 对称加密解密 
 * @author lindaofen
 */
public class DesUtil {  

    /** 加密、解密key. */  
    private static final String PASSWORD_CRYPT_KEY = "wtoip2016!@#IWELOAKGTTIC32";  
    /** 加密算法,可用 DES,DESede,Blowfish. */  
    private final static String ALGORITHM = "DES";  
    
    /**

     * @param args
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {

				
		//数据加密密钥
		String  signKey= "www.xnk.com2019!@#";
		//签名加密密钥
		String  encryptKey= "www.xnk.com2020@@#";
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("type", "1");
		treeMap.put("childType", "0");
		treeMap.put("source", "1");
		treeMap.put("userId", "5");
		treeMap.put("evaluationId", "220");
		treeMap.put("amount", "3.0");
		treeMap.put("payType", "1");

		String encData = JSON.toJSONString(treeMap);
		System.out.println("encData: " + encData);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		
		String sign = MD5.sign(buffer.toString(), signKey, "UTF-8");
		System.out.println("sign: " + sign);
		
		
		String extParams = DesUtil.encrypt(encData,encryptKey);
		System.out.println("length1():: " + extParams.length() + "  extParams: " + extParams);
		
		
		//接口报文:
		// http://120.25.195.173:17080/rwapi/member/registered?extParams=109817536A51096205023D57711D1FCDAA7A4153D6AEF521822F7C3CE69FFE8E698E79BB72553D8BB76B14FCC88CF8BB&sign=d16f68af53e4426432b36b20d5d66ee4
		
		//sendUpdateMemberInfo();//巡检端  补充用户信息
		
		//sendrecharge();
		
		//sendDjrecharge();
		
		sendDjPayOrder();
		
		//sendDeveryOrder();
		
		//cancelDjOrder();
		
		/*String res = DesUtil.decrypt(extParams,encryptKey);
		System.out.println("res():: " + res.length() + "  res: " + res);
		*/
		
	}  
    
	private static void sendUpdateMemberInfo() {

		System.err.println("sendDjrecharge====");
		//数据加密密钥
		String encryptKey = "www.mssb.com2017!@#";
		//签名加密密钥
		String signKey = "www.msht.com2017v1!@#";
		
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("account", "0100000364");
		treeMap.put("phone", "18808949991");
		treeMap.put("address", "海甸岛");
		treeMap.put("fullName", "老张");
		
		
		String encData = JSON.toJSONString(treeMap);
		System.out.println("encData: " + encData);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		
		String sign = MD5.sign(buffer.toString(), signKey, "UTF-8");
		System.out.println("sign: " + sign);
		
		
		String extParams;
		try {
			extParams = DesUtil.encrypt(encData,encryptKey);
			System.out.println("  extParams: =>" + extParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	}

	private static void cancelDjOrder() {

		System.err.println("cancelDjOrder====");
		//数据加密密钥
		String encryptKey = "www.mssb.com2017!@#";
		//签名加密密钥
		String signKey = "www.msht.com2017v1!@#";
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("orderId", "219");
		
		String encData = JSON.toJSONString(treeMap);
		System.out.println("encData: " + encData);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		
		String sign = MD5.sign(buffer.toString(), signKey, "UTF-8");
		System.out.println("sign: " + sign);
		
		
		String extParams;
		try {
			extParams = DesUtil.encrypt(encData,encryptKey);
			System.out.println("  extParams: =>" + extParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**接口报文:
		 * http://localhost:7777/msb-retailwater-api/order/recharge?extParams=C3A54E3CB63FDA72227D1EA4F1DE412E89D0D6C14347D85CD738DBA4F6217183F3C252E37D644E0451861266EFA4C519C395C459399557D585F46F8FE84E81A4C53F47812AD7B923CE96E2E031D991C50827C07D7AF246ED5251933AF9D6E8CC502975CD5BF99055D697D53431767D2F5BCABE7FDFFC7D5720FD121D1BA4EA9FEA67AAB0860AE90656B5E99EF1E690A9&sign=a0a2efd54c3ab150e4afc4381efe29fa
		 */
		
		
		/*String res = DesUtil.decrypt(extParams,encryptKey);
		System.out.println("res():: " + res.length() + "  res: " + res);
		*/
		
	
	
	}

	private static void sendDeveryOrder() {
		System.err.println("sendDjrecharge====");
		//数据加密密钥
		String encryptKey = "www.mssb.com2017!@#";
		//签名加密密钥
		String signKey = "www.msht.com2017v1!@#";
		
		List<DjRechargeChildVo> child = new ArrayList<DjRechargeChildVo>();
		DjRechargeChildVo a1 = new DjRechargeChildVo();
		a1.setId(1L);
		a1.setNum(20);
		
		DjRechargeChildVo a2 = new DjRechargeChildVo();
		a2.setId(2L);
		a2.setNum(30);
		child.add(a1);
		child.add(a2);
		
		String jsonString = JSON.toJSONString(child);
		System.err.println(jsonString);
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("account", "18808969661");
		treeMap.put("addressId", "213");
		treeMap.put("childList", jsonString);//专桶
		System.err.println(jsonString);
		
		String encData = JSON.toJSONString(treeMap);
		System.out.println("encData: " + encData);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		
		String sign = MD5.sign(buffer.toString(), signKey, "UTF-8");
		System.out.println("sign: " + sign);
		
		
		String extParams;
		try {
			extParams = DesUtil.encrypt(encData,encryptKey);
			System.out.println("  extParams: =>" + extParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**接口报文:
		 * http://localhost:7777/msb-retailwater-api/order/recharge?extParams=C3A54E3CB63FDA72227D1EA4F1DE412E89D0D6C14347D85CD738DBA4F6217183F3C252E37D644E0451861266EFA4C519C395C459399557D585F46F8FE84E81A4C53F47812AD7B923CE96E2E031D991C50827C07D7AF246ED5251933AF9D6E8CC502975CD5BF99055D697D53431767D2F5BCABE7FDFFC7D5720FD121D1BA4EA9FEA67AAB0860AE90656B5E99EF1E690A9&sign=a0a2efd54c3ab150e4afc4381efe29fa
		 */
		
		
		/*String res = DesUtil.decrypt(extParams,encryptKey);
		System.out.println("res():: " + res.length() + "  res: " + res);
		*/
		
	
	}

	private static void sendDjPayOrder() {

		System.err.println("sendDjPayOrder====");
		//数据加密密钥
		//数据加密密钥
				String  signKey= "www.xnk.com2019!@#";
				//签名加密密钥
				String  encryptKey= "www.xnk.com2020@@#";
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		
		treeMap.put("orderId", "1");
		treeMap.put("type", 1+"");
		treeMap.put("channel", 1+"");
		treeMap.put("amount",3.0+"");
		
		String encData = JSON.toJSONString(treeMap);
		System.out.println("encData: " + encData);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		
		String sign = MD5.sign(buffer.toString(), signKey, "UTF-8");
		System.out.println("sign: " + sign);
		
		
		String extParams;
		try {
			extParams = DesUtil.encrypt(encData,encryptKey);
			System.out.println("  extParams: =>" + extParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**接口报文:
		 * http://localhost:7777/msb-retailwater-api/order/recharge?extParams=C3A54E3CB63FDA72227D1EA4F1DE412E89D0D6C14347D85CD738DBA4F6217183F3C252E37D644E0451861266EFA4C519C395C459399557D585F46F8FE84E81A4C53F47812AD7B923CE96E2E031D991C50827C07D7AF246ED5251933AF9D6E8CC502975CD5BF99055D697D53431767D2F5BCABE7FDFFC7D5720FD121D1BA4EA9FEA67AAB0860AE90656B5E99EF1E690A9&sign=a0a2efd54c3ab150e4afc4381efe29fa
		 */
		
		
		/*String res = DesUtil.decrypt(extParams,encryptKey);
		System.out.println("res():: " + res.length() + "  res: " + res);
		*/
		
	
	
	}

	public static class DjRechargeChildVo {

		private Long id;//规格标识
		private int num;//购买桶数量
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		
		
	}

	public static void  sendDjrecharge(){
		System.err.println("sendDjrecharge====");
		//数据加密密钥
		String encryptKey = "www.mssb.com2017!@#";
		//签名加密密钥
		String signKey = "www.msht.com2017v1!@#";
		
		List<DjRechargeChildVo> child = new ArrayList<DjRechargeChildVo>();
		DjRechargeChildVo a1 = new DjRechargeChildVo();
		a1.setId(1L);
		a1.setNum(20);
		DjRechargeChildVo a2 = new DjRechargeChildVo();
		a2.setId(2L);
		a2.setNum(30);
		child.add(a1);
		child.add(a2);
		String jsonString = JSON.toJSONString(child);
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("account", "18808969661");
		treeMap.put("payFee", "500");
		treeMap.put("payType", "8");//
		treeMap.put("payTime", "2019-05-13 17:00:00");
		treeMap.put("packId", "1");//套餐id
		//treeMap.put("childList", jsonString);//专桶
		System.err.println(jsonString);
		
		String encData = JSON.toJSONString(treeMap);
		System.out.println("encData: " + encData);
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		String sign = MD5.sign(buffer.toString(), signKey, "UTF-8");
		System.out.println("sign: " + sign);
		
		
		String extParams;
		try {
			extParams = DesUtil.encrypt(encData,encryptKey);
			System.out.println("  extParams: =>" + extParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**接口报文:
		 * http://localhost:7777/msb-retailwater-api/order/recharge?extParams=C3A54E3CB63FDA72227D1EA4F1DE412E89D0D6C14347D85CD738DBA4F6217183F3C252E37D644E0451861266EFA4C519C395C459399557D585F46F8FE84E81A4C53F47812AD7B923CE96E2E031D991C50827C07D7AF246ED5251933AF9D6E8CC502975CD5BF99055D697D53431767D2F5BCABE7FDFFC7D5720FD121D1BA4EA9FEA67AAB0860AE90656B5E99EF1E690A9&sign=a0a2efd54c3ab150e4afc4381efe29fa
		 */
		
		
		/*String res = DesUtil.decrypt(extParams,encryptKey);
		System.out.println("res():: " + res.length() + "  res: " + res);
		*/
		
	
	}
	
	public static void  sendrecharge(){
		System.err.println("sendrecharge====");
		//数据加密密钥
		String encryptKey = "www.mssb.com2017!@#";
		//签名加密密钥
		String signKey = "www.msht.com2017v1!@#";
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("type", "1");//1 民生报  2ic
		treeMap.put("account", "18808969661");
		treeMap.put("payFee", "800");
		treeMap.put("giveFee", "500");
		treeMap.put("payType", "18");//
		treeMap.put("payTime", "2019-05-13 17:00:00");
		treeMap.put("packId", "21");//套餐id
		//treeMap.put("childType", "0");//5 季度卡  6年卡
		treeMap.put("orderFrom", "0");
		
		//treeMap.put("payFee", "300");
		//treeMap.put("giveFee", "50");
		//treeMap.put("childType", "0");
		
		String encData = JSON.toJSONString(treeMap);
		System.out.println("encData: " + encData);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		
		String sign = MD5.sign(buffer.toString(), signKey, "UTF-8");
		System.out.println("sign: " + sign);
		
		
		String extParams;
		try {
			extParams = DesUtil.encrypt(encData,encryptKey);
			System.out.println("  extParams: =>" + extParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**接口报文:
		 * http://localhost:7777/msb-retailwater-api/order/recharge?extParams=C3A54E3CB63FDA72227D1EA4F1DE412E89D0D6C14347D85CD738DBA4F6217183F3C252E37D644E0451861266EFA4C519C395C459399557D585F46F8FE84E81A4C53F47812AD7B923CE96E2E031D991C50827C07D7AF246ED5251933AF9D6E8CC502975CD5BF99055D697D53431767D2F5BCABE7FDFFC7D5720FD121D1BA4EA9FEA67AAB0860AE90656B5E99EF1E690A9&sign=a0a2efd54c3ab150e4afc4381efe29fa
		 */
		
		
		/*String res = DesUtil.decrypt(extParams,encryptKey);
		System.out.println("res():: " + res.length() + "  res: " + res);
		*/
		
	
	}
      
    /** 
     * 对数据进行DES加密. 
     * @param data 待进行DES加密的数据 
     * @return 返回经过DES加密后的数据 
     * @throws Exception 
     * @author 
     */  
    public final static String decrypt(String data) throws Exception {  
        return new String(decrypt(hex2byte(data.getBytes()),  
                PASSWORD_CRYPT_KEY.getBytes()));  
    }  
    /** 
     * 对用DES加密过的数据进行解密. 
     * @param data DES加密数据 
     * @return 返回解密后的数据 
     * @throws Exception 
     * @author 
     */  
    public final static String encrypt(String data) throws Exception  {  
        return byte2hex(encrypt(data.getBytes(), PASSWORD_CRYPT_KEY  
                .getBytes()));  
    }  
      
    /** 
     * 用指定的key对数据进行DES加密. 
     * @param data 待加密的数据 
     * @param key DES加密的key 
     * @return 返回DES加密后的数据 
     * @throws Exception 
     * @author 
     */  
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {  
        // DES算法要求有一个可信任的随机数源  
    	SecureRandom sr = new SecureRandom();  
        // 从原始密匙数据创建DESKeySpec对象  
        DESKeySpec dks = new DESKeySpec(key);  
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成  
        // 一个SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
        SecretKey securekey = keyFactory.generateSecret(dks);  
        // Cipher对象实际完成加密操作  
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  
        // 用密匙初始化Cipher对象  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
        // 现在，获取数据并加密  
        // 正式执行加密操作  
        return cipher.doFinal(data);  
    }  
    /** 
     * 用指定的key对数据进行DES解密. 
     * @param data 待解密的数据 
     * @param key DES解密的key 
     * @return 返回DES解密后的数据 
     * @throws Exception 
     * @author
     */  
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {  
        // DES算法要求有一个可信任的随机数源  
        SecureRandom sr = new SecureRandom();  
        // 从原始密匙数据创建一个DESKeySpec对象  
        DESKeySpec dks = new DESKeySpec(key);  
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成  
        // 一个SecretKey对象  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
        SecretKey securekey = keyFactory.generateSecret(dks);  
        // Cipher对象实际完成解密操作  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        // 用密匙初始化Cipher对象  
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);  
        // 现在，获取数据并解密  
        // 正式执行解密操作  
        return cipher.doFinal(data);  
    }  
    public static byte[] hex2byte(byte[] b) {  
        if ((b.length % 2) != 0)  
            throw new IllegalArgumentException("长度不是偶数");  
        byte[] b2 = new byte[b.length / 2];  
        for (int n = 0; n < b.length; n += 2) {  
            String item = new String(b, n, 2);  
            b2[n / 2] = (byte) Integer.parseInt(item, 16);  
        }  
        return b2;  
    }  
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1)  
                hs = hs + "0" + stmp;  
            else  
                hs = hs + stmp;  
        }  
        return hs.toUpperCase();  
    }  
    
    /** 
     * 对用DES加密过的数据进行解密. 
     * @param data DES加密数据 
     * @return 返回解密后的数据 
     * @throws Exception 
     * @author 
     */  
    public static String encrypt(String data,String key) throws Exception  {  
        return byte2hex(encrypt(data.getBytes(), key  
                .getBytes()));  
    }  
    
    public static String decrypt(String data,String key) throws Exception  {  
        return new String(decrypt(hex2byte(data.getBytes()),  
        		key.getBytes()));  
    }
    
    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
    
    public static String encryptDES(String encryptString, String encryptKey)
                 throws Exception {
/*             IvParameterSpec zeroIv = new IvParameterSpec(iv);
             SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
             Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
             cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
            return byte2hex(encryptedData);*/
            
    	IvParameterSpec zeroIv = new IvParameterSpec(iv);
            // DES算法要求有一个可信任的随机数源  
            SecureRandom sr = new SecureRandom();  
            // 从原始密匙数据创建DESKeySpec对象  
            DESKeySpec dks = new DESKeySpec(encryptKey.getBytes());  
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成  
            // 一个SecretKey对象  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
            SecretKey securekey = keyFactory.generateSecret(dks);  
            // Cipher对象实际完成加密操作  
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");  
            // 用密匙初始化Cipher对象  
            cipher.init(Cipher.ENCRYPT_MODE, securekey);  
            // 现在，获取数据并加密  
            // 正式执行加密操作  
            return byte2hex(cipher.doFinal(encryptString.getBytes()));  
        }
    

      
}  