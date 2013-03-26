/**
 * @created on 2011-1-12 上午11:25:42
 */
package org.salever.j2se.common.format;

import java.text.NumberFormat;

/**
 * @author 
 */
public class NumberFormatUtil {

	/**
	 * 格式化为百分数，保留后两位数字
	 * @param rate
	 * @return
	 */
	public static String formatDoublePercent(double rate){
		NumberFormat format = NumberFormat.getPercentInstance();
		format.setMinimumFractionDigits(2);    
		String result = format.format(rate);
		return result;
	}
}
