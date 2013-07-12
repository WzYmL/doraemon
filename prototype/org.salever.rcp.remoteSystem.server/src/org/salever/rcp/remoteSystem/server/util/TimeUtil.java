/**
 * 
 */
package org.salever.rcp.remoteSystem.server.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dev
 * 
 */
public class TimeUtil {

	/**
	 * 比较时间部分，仅仅比较时间不分，忽略日期部分，
	 * 
	 * @param time1
	 * @param time2
	 * @return 如果time1早于time2，则返回true，则返回false
	 */
	public static boolean before(Date time1, Date time2) {

		if (time1 == null || time2 == null) {
			return false;
		}

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(time1);
		calendar1.set(Calendar.YEAR, 1900);
		calendar1.set(Calendar.MONTH, 0);
		calendar1.set(Calendar.DAY_OF_MONTH, 1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(time2);
		calendar2.set(Calendar.YEAR, 1900);
		calendar2.set(Calendar.MONTH, 0);
		calendar2.set(Calendar.DAY_OF_MONTH, 1);

		return calendar1.compareTo(calendar2) <= 0;
	}

	/**
	 * 格式化日期为2000-1-1 12:30:30样式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		Format format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); //$NON-NLS-1$
		if (date == null) {
			return ""; //$NON-NLS-1$
		}
		return format.format(date);
	}

	/**
	 * 格式化日期为2000-1-1 12:30:30样式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToDay(Date date) {
		Format format = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
		if (date == null) {
			return ""; //$NON-NLS-1$
		}
		return format.format(date);
	}

	/**
	 * 格式化日期为2000-1样式的字符串 %Y-%m
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToMonth(Date date) {
		Format format = new SimpleDateFormat("yyyy-M"); //$NON-NLS-1$
		if (date == null) {
			return ""; //$NON-NLS-1$
		}
		return format.format(date);
	}
}
