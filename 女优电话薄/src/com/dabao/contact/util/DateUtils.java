package com.dabao.contact.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	/**
	 * ����ʱ�� 
	 * @param time  ʱ���
	 * @return
	 * ���죺  HH:mm
	 * ���죺  ����
	 * ��ǰ��  ���ڼ�
	 */
	public static String parse(long time) {
		//���������Ĳ�������ʱ��
		Calendar other = Calendar.getInstance();
		other.setTimeInMillis(time);
		//������ǰϵͳʱ��
		Calendar now = Calendar.getInstance();
		//�ж��Ƿ���ͬһ��
		if(now.get(Calendar.YEAR) == other.get(Calendar.YEAR) 
				&&now.get(Calendar.DAY_OF_YEAR) == 
				other.get(Calendar.DAY_OF_YEAR)){
			return sdf.format(other.getTime());
		}
		//�ж��Ƿ�������
		now.add(Calendar.DAY_OF_YEAR, -1);
		if(now.get(Calendar.YEAR) == other.get(Calendar.YEAR) 
				&&now.get(Calendar.DAY_OF_YEAR) == 
				other.get(Calendar.DAY_OF_YEAR)){
			return "����";
		}
		//�ж����ڼ�
		int day = other.get(Calendar.DAY_OF_WEEK);
		String dayString = "";
		switch (day) {
		case Calendar.MONDAY:
			dayString = "����һ";
			break;
		case Calendar.TUESDAY:
			dayString = "���ڶ�";
			break;
		case Calendar.WEDNESDAY:
			dayString = "������";
			break;
		case Calendar.THURSDAY:
			dayString = "������";
			break;
		case Calendar.FRIDAY:
			dayString = "������";
			break;
		case Calendar.SATURDAY:
			dayString = "������";
			break;
		case Calendar.SUNDAY:
			dayString = "������";
			break;
		}
		return dayString;
	}

}
