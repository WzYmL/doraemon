/**
 * 
 */
package org.salever.common.swtjface.extend.widget.util;

/**
 * @author developer
 * 
 */
public final class NumberUtil {

	public static long stingToLong(String string) {
		try {
			return Long.parseLong(string);
		} catch (Exception e) {
			return 0L;
		}
	}

	public static boolean isLong(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Double stringToDouble(String text) {
		try {
			return Double.parseDouble(text);
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
