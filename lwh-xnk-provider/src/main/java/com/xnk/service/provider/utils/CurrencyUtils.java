package com.xnk.service.provider.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算， 这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入。
 * @author lindaofen
 *
 */
public final class CurrencyUtils {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 2;

	// 这个类不能实例化
	private CurrencyUtils() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static Double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的比较运算。
	 * 
	 * @param v1
	 *            第一个数
	 * @param v2
	 *            第二个数
	 * @return 两个参数大小比较的结果
	 *             0:两数相等, 1:第一个数大于第二个数, -1:第一个数小于第二个数
	 */
	public static int compare(double v1, double v2) {
		BigDecimal val1 = new BigDecimal(Double.toString(v1));
		BigDecimal val2 = new BigDecimal(Double.toString(v2));
		int result = 0;
		if (val1.compareTo(val2) < 0) {  
	        result = -1;  
	    }  
	    if (val1.compareTo(val2) == 0) {  
	        result = 0;  
	    }  
	    if (val1.compareTo(val2) > 0) {  
	        result = 1;  
	    }  
	    return result;
	}
	
	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double compare(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static String removeDecimalRounding(Double price){
		if (price == null){
			return "0";
		}
		BigDecimal big  = new BigDecimal(price);
		return big.setScale(0,BigDecimal.ROUND_DOWN).toString();
	}
	
	/**
	 * 将金额转换为以分为单位，没有小数点
	 * @param value 金额
	 * @return
	 */
	public static String convertAmountToMin(double value){
		double temp = mul(value, 100);
		int intVal = (int) temp;
		return String.valueOf(intVal);
	}
	
	public static double convertAmountToYuan(double value){
		double temp  = mul(value, 0.01);
		return temp;
	}
	
	public static String formatAmount(double amount, int scale){
		BigDecimal b = new BigDecimal(amount);
		return b.setScale(scale, BigDecimal.ROUND_DOWN).toString();
	}
	public static double format(double value, int scale){
		BigDecimal b = new BigDecimal(value);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double div1(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2).doubleValue();

	}
	
	public static Double mul_format(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		double value = b1.multiply(b2).doubleValue();
		return format(value, 0);
	}
	
	public static double removeDecimalRounding(double value,  int scale){
		BigDecimal big  = new BigDecimal(value);
		return big.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args) throws ParseException{
		System.out.println(convertAmountToMin(17.49));
		System.out.println(convertAmountToMin(32.45));
		System.out.println(convertAmountToMin(32.85));
		
		System.out.println(convertAmountToYuan(1749));
		System.out.println(convertAmountToYuan(3245));
		System.out.println(convertAmountToYuan(3285));
		
		Date date = DateUtils.parseDate("20170627102020","yyyyMMddHHmmss");
		System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(mul_format(3.555,1,0));
		
		System.out.println(CurrencyUtils.mul(CurrencyUtils.div(2, 7), 100));
		
		System.out.println(format(3.12345678,6));
        
	}
}