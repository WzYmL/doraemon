/**
 * 
 */
package org.salever.j2se.common.format;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author LiXP
 * 
 */
public class LocaleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale local = Locale.CHINA;

		NumberFormat format = NumberFormat.getCurrencyInstance(local);

		double d = 123454678.909;

		System.out.println(format.format(d));

	}

}
