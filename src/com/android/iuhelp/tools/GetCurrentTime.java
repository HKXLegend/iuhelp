package com.android.iuhelp.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetCurrentTime {
	/**
	 * @return time ��ȡ��ǰʱ��
	 */
	public static String getTime() {
		String time = "";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
		time = format.format(date);
		return time;
	}
}
