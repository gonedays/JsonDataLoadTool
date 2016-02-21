package cn.com.nisong.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zuoqi lu
 * @version 1.0.0
 */
public class DateUtil {
	public DateUtil() {
		//do nothing
	}

	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString) {
		return parseDate(dateString, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseDateTime(String dateString) {
		return parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @param dateString
	 * @param parsePartten
	 * @return
	 */
	public static Date parseDate(String dateString, String parsePartten) {
		Date ld_Value;
		SimpleDateFormat lsdf_Format = new SimpleDateFormat(parsePartten);

		if (dateString == null)
			return null;

		try {
			lsdf_Format.setLenient(false); //to be precise
			ld_Value = lsdf_Format.parse(dateString);
		} catch (ParseException e) {
			//logger.log(Log4j.LEVEL_ERROR, "DateUtil.parseDate() failed." + e);
			return null;
		}

		return ld_Value;
	}
	public static String formatDate(Date date){
		return format(date, "yyyy-MM-dd");
	}
	public static String formatDateTime(Date date){
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 日期格式化
	 * 
	 * @param date 日期对象
	 * @param pattern 日期格式,如：yyyy-MM-dd HH:mm:ss
	 * @return 返回一个String类型的日期。
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat lsdf_Format;
		String ls_Formatted;

		if (pattern == null) {
			System.err.println("DateUtil.format(): pattern is null");
			return null;
		}
		if (pattern == null) {
			System.err.println("DateUtil.format(): date is null");
			return null;
		}

		try {
			lsdf_Format = new SimpleDateFormat(pattern);
			ls_Formatted = lsdf_Format.format(date);
		} catch (Exception e) {
			System.err.println("DateUtil.formatDate(): " + e.getMessage());
			lsdf_Format = new SimpleDateFormat("yyyy-MM-dd");
			ls_Formatted = lsdf_Format.format(date);
		}

		return ls_Formatted;
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Number
	 * @return 返回相差指定年数的日期对象
	 */
	public static Date relativeYear(Date ad_Date, int ai_Number) {
		return relativeDate(ad_Date, Calendar.YEAR, ai_Number);
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Number
	 * @return 返回相差指定月份数的日期对象
	 */
	public static Date relativeMonth(Date ad_Date, int ai_Number) {
		return relativeDate(ad_Date, Calendar.MONTH, ai_Number);
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Number
	 * @return 返回相差指定天数的日期对象
	 */
	public static Date relativeDay(Date ad_Date, int ai_Number) {
		return relativeDate(ad_Date, Calendar.DATE, ai_Number);
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Type
	 * @param ai_Number
	 * @return 返回处理过的日期
	 */
	public static Date relativeDate(Date ad_Date, int ai_Type, int ai_Number) {
		if (ad_Date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ad_Date);
		calendar.add(ai_Type, ai_Number);

		return calendar.getTime();
	}

	/**
	 * 当前日期
	 * 
	 * @return String
	 */
	public static String currentDate() {
		return formatDate(Calendar.getInstance().getTime());
	}
	/**
	 * 当前日期
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return parseDate(currentDate());
	}
	/**
	 * 当前的日期和时间
	 * @return Date
	 */
	public static Date getCurrentDateTime(){
	    return Calendar.getInstance().getTime();
	}
	
	/**
	 * 当前日期的前一天
	 * 
	 * @return Date
	 */
	public static Date getYesterday() {
		return relativeDay(getCurrentDate(),-1);
	}
	
	/**
	 * 当前日期的前一天
	 * 
	 * @return String
	 */
	public static String yesterday() {
		return formatDate(getYesterday());
	}

	/**
	 * 当前时间
	 * 
	 * @return String
	 */
	public static String currentTime() {
		return format(Calendar.getInstance().getTime(), "HH:mm:ss");
	}

	/**
	 * 当前日期时间
	 * 
	 * @return String
	 */
	public static String currentDateTime() {
		return formatDateTime(Calendar.getInstance().getTime());
	}
	public static int currentDay(){
	    return getCurrent(Calendar.DAY_OF_MONTH);
	}
	public static int currentMonth(){
	    return getCurrent(Calendar.MONTH)+1;
	}
	public static int currentYear(){
	    return getCurrent(Calendar.YEAR);
	}
	private static int getCurrent(int type){
	    return Calendar.getInstance().get(type);
	}
	/**
	 * 当前秒
	 * 
	 * @return long
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static Date getDate(long muiliseconds) {
		Date date = Calendar.getInstance().getTime();
		date.setTime(muiliseconds);
		return date;
	}
	/**
	 * 把java.util.Date转化成java.sql.Timestamp
	 * @author Lu Zuoqi
	 * @since  2015年5月4日 下午4:42:23
	 * @param utilDate
	 * @return
	 */
	public static java.sql.Timestamp parseToSqlDateTime(java.util.Date utilDate){
				 
		if(utilDate==null)
			return null;
		
		//java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		 
		//java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		//java.sql.Time sTime=new java.sql.Time(utilDate.getTime());
		java.sql.Timestamp stp=new java.sql.Timestamp(utilDate.getTime());		
		return stp;
	}
	
	/**
	 * 把java.util.Date转化成java.sql.Date
	 * @author Lu Zuoqi
	 * @since  2015年5月4日 下午4:42:47
	 * @param utilDate
	 * @return
	 */
	public static java.sql.Date parseToSqlDate(java.util.Date utilDate){
		 
		if(utilDate==null)
			return null;
		 
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		  
		return sqlDate;
	}
	
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static java.sql.Date parseToSqlDate(String dateString) {
		java.sql.Date sqlDate=parseToSqlDate( parseDate(dateString, "yyyy-MM-dd"));
		return sqlDate;
	}

	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static java.sql.Timestamp parseToSqlDateTime(String dateString) {
		return parseToSqlDateTime(parseDate(dateString, "yyyy-MM-dd HH:mm:ss"));
	}
	
}
