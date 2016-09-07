package com.lzx.demo.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarDemo {
	public static void main(String[] args) throws Exception {

		// Calendar cal = Calendar.getInstance();
		//
		// cal.setTime(new Date());
		//
		// System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
		
		Calendar calendar = Calendar.getInstance();
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
	}

	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}
	  // 获得当前周- 周一的日期
    private static  String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }
    // 获得当前周- 周日  的日期
    private static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;

    }
}
