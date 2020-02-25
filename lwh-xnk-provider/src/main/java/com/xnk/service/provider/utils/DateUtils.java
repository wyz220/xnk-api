package com.xnk.service.provider.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
		"yyyyMMdd","yyyyMMddHHmmss"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 得到昨天日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getLastDate() {
		return DateFormatUtils.format(addDays(new Date(), -1) , "yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static int getHour() {
		return Integer.parseInt(formatDate(new Date(), "HH"));
	}
	
	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到某天的字符串 格式（dd）
	 */
	public static String getDay(String oneDate) {
		return formatDate(parseDate(oneDate), "dd");
	}
	
	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 得到某天的星期字符串 格式（E）星期几
	 */
	public static String getWeek(String oneDate) {
		return formatDate(parseDate(oneDate), "E");
	}	
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

	/**
	 * 转换秒数为时间（天,时:分:秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatTime(Double seconds){
		Integer day = (int) (seconds/(24*60*60));
		Integer hour = (int)  (seconds/(60*60)-day*24);
		Integer min = (int)  ((seconds/(60))-day*24*60-hour*60);
		Integer s = (int)  (seconds-day*24*60*60-hour*60*60-min*60);
		return (day>0?day+",":"")+hour+":"+min+":"+s;
    }
    
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 获取两个日期之间的秒数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getSecondsOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / 1000;
	}	

	/**
	 * 加减天数
	 * @param date
	 * @param days
     * @return
     */
	public static Date addDays(Date date, int days) {
		return new Date(date.getTime() + days*24L*60*60*1000);
	}

	@SuppressWarnings("deprecation")
	public static String firstDayOfMonth(String oneDate){
		Date getDate = parseDate(oneDate);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getDate.getYear());
		stringBuilder.append("-");
		stringBuilder.append(getDate.getMonth());
		stringBuilder.append("-01");
		return stringBuilder.toString();
	}
	
	
	public static Date getPreDay(){
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		ca.setTime(new Date()); //设置时间为当前时间 
		ca.add(Calendar.DATE, -1); 
		return ca.getTime();
	}
	
	public static Date getThisWeekMonday(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        // 获得当前日期是一个星期的第几天  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);  
        if (1 == dayWeek) {  
            cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);  
        // 获得当前日期是一个星期的第几天  
        int day = cal.get(Calendar.DAY_OF_WEEK);  
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
        return cal.getTime();  
    }  
	
	public static String getThisWeekFirst(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();  
        cale.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return sdf.format(cale.getTime());
	}
	
	public static String getThisWeekLast(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
        cale.add(Calendar.WEEK_OF_YEAR, 1);  
        cale.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
        return sdf.format(cale.getTime());  
	}
	
	public static String getThisMonthFirst(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        return sdf.format(cale.getTime());
	}
	
	public static String getThisMonthLast(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);  
        cale.set(Calendar.DAY_OF_MONTH, 0);  
        return sdf.format(cale.getTime());  
	}
	
	public static String getThisYearFirst(){
		Calendar cale = Calendar.getInstance();  
		cale.setTime(new Date());
        return cale.get(Calendar.YEAR) + "-01";
	}
	
	public static String getThisYearLast(){
		Calendar cale = Calendar.getInstance();  
		cale.setTime(new Date());
        return cale.get(Calendar.YEAR) + "-12";
	}
	
	public static String getYearMonth() {
		return getDate("yyyy-MM");
	}
	
	public static String getMonthFirst(int month){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, month);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        return sdf.format(cale.getTime());
	}
	
	public static int getRemainTimeSecondOfWeek(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int weekIndex = cal.get(Calendar.DAY_OF_WEEK);
		int days = 7 - weekIndex + 1;
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int currentSecondOfDay = (hour*3600) + (minute * 60) + second;
		int secondDiff = (24 * 3600) - currentSecondOfDay;
		int remainTimeSecond = (days * 24 * 3600) + secondDiff;
		return remainTimeSecond;
	}
	
	 public static void main(String[] args) {  
         /*System.out.println(getThisWeekFirst() + "---" + getThisWeekLast());
         System.out.println(getThisMonthFirst() + "---" + getThisMonthLast());
         System.out.println(getThisYearFirst() + "---" + getThisYearLast());
         System.out.println(getYearMonth());
         
         System.out.println(getMonthFirst(-2));*/
         
         System.out.println(getRemainTimeSecondOfWeek());
         //System.out.println(7 - getWeek());
		 
		 System.err.println(addDays(new Date(),90));//Thu Aug 08 11:58:06 CST 2019
		 
		 System.err.println(getDistanceOfTwoDate(new Date(),new Date()));;
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 
			 String br = "2019-05-21";
			 String er = "2019-05-22";
			 
			Date b = sdf.parse(br);
			Date e = sdf.parse(er);
			 
			Date f = new Date();
			
			String now = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
			Date curr = DateUtils.parseDate(now, "yyyy-MM-dd");
			
				
				String begin = DateUtils.formatDate(b, "yyyy-MM-dd");
				String end = DateUtils.formatDate(e, "yyyy-MM-dd");
				Date beginTime = DateUtils.parseDate(begin, "yyyy-MM-dd");
				Date endTime = DateUtils.parseDate(end, "yyyy-MM-dd");
				
				if(beginTime.getTime() <= curr.getTime()
						&& endTime.getTime() >= curr.getTime()){
					System.out.println("yes");
				}
				
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 
		 
	    }  
	
}
