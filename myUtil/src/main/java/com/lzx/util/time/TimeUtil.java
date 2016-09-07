package com.lzx.util.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获得当前日期
	 * 格式（yyyy-MM-dd）
	 * @return
	 */
	public static String getNowDate(){
		return dateFormat.format(getDate());
	}
	
	/**
	 * 获得当前日期
	 * 格式（yyyy-MM-dd HH:mm:ss）
	 * @param string 
	 * @return
	 */
	public static String getNowTime(){
		return timeFormat.format(getDate());
	}
	/**
	 * 获得当前时间Date对象
	 * @return
	 */
	private static Date getDate(){
		return new Date();
	}
	/**
	 * 字符串格式化为时间
	 * 格式（yyyy-MM-dd）
	 * @param date
	 * @return
	 */
	public static Date dateParse(String date){
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 字符串格式化为时间
	 * 格式（yyyy-MM-dd HH:mm:ss）
	 * @param date
	 * @return
	 */
	public static Date timeParse(String date){
		try {
			return timeFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String dateFormat(Date date){
		return dateFormat.format(date);
	}
	public static String timeFormat(Date date){
		return timeFormat.format(date);
	}
	public static void main(String[] args) {
	}
	
}
