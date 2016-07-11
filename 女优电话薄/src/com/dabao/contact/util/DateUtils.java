package com.dabao.contact.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	/**
	 * 解析时间 
	 * @param time  时间戳
	 * @return
	 * 当天：  HH:mm
	 * 昨天：  昨天
	 * 以前：  星期几
	 */
	public static String parse(long time) {
		//描述传来的参数描述时间
		Calendar other = Calendar.getInstance();
		other.setTimeInMillis(time);
		//描述当前系统时间
		Calendar now = Calendar.getInstance();
		//判断是否是同一天
		if(now.get(Calendar.YEAR) == other.get(Calendar.YEAR) 
				&&now.get(Calendar.DAY_OF_YEAR) == 
				other.get(Calendar.DAY_OF_YEAR)){
			return sdf.format(other.getTime());
		}
		//判断是否是昨天
		now.add(Calendar.DAY_OF_YEAR, -1);
		if(now.get(Calendar.YEAR) == other.get(Calendar.YEAR) 
				&&now.get(Calendar.DAY_OF_YEAR) == 
				other.get(Calendar.DAY_OF_YEAR)){
			return "昨天";
		}
		//判断星期几
		int day = other.get(Calendar.DAY_OF_WEEK);
		String dayString = "";
		switch (day) {
		case Calendar.MONDAY:
			dayString = "星期一";
			break;
		case Calendar.TUESDAY:
			dayString = "星期二";
			break;
		case Calendar.WEDNESDAY:
			dayString = "星期三";
			break;
		case Calendar.THURSDAY:
			dayString = "星期四";
			break;
		case Calendar.FRIDAY:
			dayString = "星期五";
			break;
		case Calendar.SATURDAY:
			dayString = "星期六";
			break;
		case Calendar.SUNDAY:
			dayString = "星期日";
			break;
		}
		return dayString;
	}

}
