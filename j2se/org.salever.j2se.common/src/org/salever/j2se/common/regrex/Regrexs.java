/**
 * @created on 2010-12-28 上午11:05:02
 */
package org.salever.j2se.common.regrex;

/**
 * <li>(abc): \(\s?[^\)]+\)</li>
 * 
 * <li>-- abc --: --\s[^--]+--</li>
 * 
 * <li>匹配邮编 12345 or 12345-6789: \d{5}-\d{4}|\d{5}</li>
 * 
 * <li>ip 123.123.123.123: ((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)</li>
 * 
 * <li>匹配用尖括号括起来的以a开头的字符串 {@code <abc> : <a[^>]+>}</li>
 * @author
 */
public final class Regrexs {

	// (abc) \(\s?[^\)]+\)

	// -- abc -- --\s[^--]+--

	// 匹配邮编 12345 or 12345-6789 \d{5}-\d{4}|\d{5}
	
	/**
	 * 
	 */
	public static boolean match(String source, String regex){
		return source.matches(regex);
	}
	
	public static void main(String[] args){
		String source = "2.7.a";
		String regex = "2.7.\\d";
//		System.out.println(match(source, regex));
		
		String url = "http://localhost:80";
		regex = "\\s[^:]*://";
		System.out.println(url.contains("://"));
		
		
		
	}

}
