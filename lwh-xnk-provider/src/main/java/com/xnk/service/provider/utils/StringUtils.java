package com.xnk.service.provider.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


/**
 * 
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static final String BASE1 = "ABCDEFGHIJKLMLOPQRSTUVWXYZabcdefghijklmlopqrstuvwxyz1234567890 _+";
	public static final String BASE2 = "abcdefghijklmlopqrstuvwxyz1234567890";

	public static String format(double amount) {
		DecimalFormat df = new java.text.DecimalFormat("#.0");
		return df.format(amount);
	}
	
	public static String generateRandomStr(int length) { 
	    String base = "0123456789";  
	    Random random = new Random();  
	    StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int number = random.nextInt(base.length());  
	        sb.append(base.charAt(number));  
	    }  
	    return sb.toString();  
	 } 
	
	public static String generateRandomStr(String base,int length) { 
	    Random random = new Random();  
	    StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int number = random.nextInt(base.length());  
	        sb.append(base.charAt(number));  
	    }  
	    return sb.toString();  
	 }  
	
	public static String generateOrderNo() { 
		String timeStr = DateUtils.getDate("yyMMddHHmmss");
		//String timeStr = "";
		String randomStr = generateRandomStr(4);
	    return timeStr + randomStr;  
	 } 
	
	public static double calculateOutWaterQuantity(int unitPrice, double amount,int quipOutWaterQuanity, int equipOutWaterTime){
		//计算每秒出水量
		double avgOutWaterQuantity = CurrencyUtils.div(quipOutWaterQuanity, equipOutWaterTime,6);
		//unitPrice单价，1元出水多少秒
		//计算出水总时间
		double totalTime = CurrencyUtils.mul(unitPrice, amount);
		
		return CurrencyUtils.mul(avgOutWaterQuantity, totalTime);
	}
	
	public static Short calculateOutWaterTime(int unitPrice, double amount){
		//unitPrice单价，1元出水多少秒
		//计算出水总时间
		double totalTime = CurrencyUtils.mul(unitPrice, amount);
		BigDecimal b = new BigDecimal(totalTime);
		return b.setScale(0,   RoundingMode.HALF_UP).shortValue();
	}
	
	public static double calculateOutWaterQuantity(int totalTime,int quipOutWaterQuanity, int equipOutWaterTime){
		//计算每秒出水量
		double avgOutWaterQuantity = (quipOutWaterQuanity * 1.0) / equipOutWaterTime;
		double saleWaterQuantity = CurrencyUtils.mul(avgOutWaterQuantity, totalTime);
		
		return CurrencyUtils.format(saleWaterQuantity, 2);
	}
	
	public static double calculateOutWaterQuantity(double unitPrice,double amount){
		//通过金额计算水量，默认单价0.3元1升
		double saleWaterQuantity = CurrencyUtils.div(amount, unitPrice);
		
		return CurrencyUtils.format(saleWaterQuantity, 2);
	}
	
	public static String generateFileName() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + ".jpg";
	}
	
	public static String fillCardNumber(String cardNum) { 
	    StringBuffer cardNumber = new StringBuffer();
	    int size = 10 - cardNum.length();
	    if (size > 0){
	    	for (int i = 0; i < size; i++){
	    		cardNumber.append(0);
	    	}
	    }
	    cardNumber.append(cardNum);
	    return cardNumber.toString();
	 } 
	
	public static int[] splitCardNumber(String cardNum) { 
	    int[] cardNumbers = new int[2];
	    String cardType = cardNum.substring(0, 2);
	    String cardNumber = cardNum.substring(2, cardNum.length());
	    cardNumbers[0] = Integer.valueOf(cardType);
	    cardNumbers[1] = Integer.valueOf(cardNumber);
	    return cardNumbers;
	 }
	
	public static double convertGPSLocation(String str){
		//20° 1' 10.18"
		int angleSymbolIndex = str.indexOf("°");
		int minuteSymbolIndex = str.indexOf("'");
		int secondSymbolIndex = str.indexOf('"');
		String angle = trim(str.substring(0,angleSymbolIndex));
		String minute = trim(str.substring(angleSymbolIndex + 1, minuteSymbolIndex));
		String second = trim(str.substring(minuteSymbolIndex + 1, secondSymbolIndex));
		double angleValue = Double.valueOf(angle);
		double minuteValue = Double.valueOf(minute);
		double secondValue = Double.valueOf(second);
		
		double value = angleValue + CurrencyUtils.div(minuteValue,60,6) + CurrencyUtils.div(secondValue,3600,6);
		return value;
	}
	
	public static int getTotalPage(long count, int pageSize) {
        long totalPage = 0;
        if (0 == count % pageSize) {
            if (0 != count) {
                totalPage = count / pageSize;
            }
        } else {
            totalPage = count / pageSize + 1;
        }
        return new Long(totalPage).intValue();
	}
	
	public static void main(String[] args){

/*		System.out.println(format(32.4));
		System.out.println(format(32.0));
		System.out.println(format(32.5));
		System.out.println(DateUtils.getDate("yyMMddHHmmss"));
		System.out.println(calculateOutWaterQuantity(29,1.5,5,44));
		
		System.out.println(calculateOutWaterTime(29, 1.5));
		System.out.println(convertGPSLocation("20° 1' 10.18\""));
		System.out.println(convertGPSLocation("110° 17' 54.96\""));
		

		System.out.println(calculateOutWaterQuantity(44,5,44));
		
		System.out.println(5/44.0);
		System.out.println(CurrencyUtils.div(5, 44,6));
		
		System.out.println("--" + calculateOutWaterQuantity(18,5,28));
		System.out.println("--" + calculateOutWaterQuantity(48,5,28));
		
		System.out.println(calculateOutWaterQuantity(0.3, 1.55));
		System.out.println(calculateOutWaterQuantity(0.3, 1.74));
		
		System.out.println(fillCardNumber("100000364"));
		
		double avgOutWaterQuantity = (5 * 1.0) / 36;
		//7.5L总出水时间
		double totalOutWaterTime = Double.valueOf(7.5) / avgOutWaterQuantity;
		//计算1元打水时间
		int unitPrice = CurrencyUtils.mul_format(1 / 2.25, totalOutWaterTime,0).intValue();
		System.out.println("unitPrice=" + unitPrice);
		
		System.out.println(CurrencyUtils.mul_format(1 / 1.5, 36,0).intValue());
		System.out.println(CurrencyUtils.mul_format(1 / 1.5, 28,0).intValue());
		System.out.println(1 / 2.25);

		System.out.println(splitCardNumber("0100000550"));*/
		
/*		Map<String, String> map = new HashMap<String,String>();
		for (int i = 0; i < 10000; i++){
			String orderNo = generateOrderNo();
			System.out.println(i+1 + "-Generate order number: " + orderNo);
			if (map.containsKey(orderNo)){
				System.out.println("The order number already exists,orderNo=" + orderNo);
				break;
			}
			map.put(orderNo, orderNo);
		}*/
		
		for (int i = 0; i < 10; i++){
			System.out.println(generateRandomStr(BASE2,12));
		}
		
	}

}
