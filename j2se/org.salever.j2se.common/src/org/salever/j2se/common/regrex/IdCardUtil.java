/**
 * 
 */
package org.salever.j2se.common.regrex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiXP
 * 
 */
public class IdCardUtil {

	/**
	 * ======================================================================
	 * 地区编码
	 */
	private static Map<String, String> areaCodes;

	static {
		areaCodes = new HashMap<String, String>();
		areaCodes.put("11", "北京");
		areaCodes.put("12", "天津");
		areaCodes.put("13", "河北");
		areaCodes.put("14", "山西");
		areaCodes.put("15", "内蒙古");
		areaCodes.put("21", "辽宁");
		areaCodes.put("22", "吉林");
		areaCodes.put("23", "黑龙江");
		areaCodes.put("31", "上海");
		areaCodes.put("32", "江苏");
		areaCodes.put("33", "浙江");
		areaCodes.put("34", "安徽");
		areaCodes.put("35", "福建");
		areaCodes.put("36", "江西");
		areaCodes.put("37", "山东");
		areaCodes.put("41", "河南");
		areaCodes.put("42", "湖北");
		areaCodes.put("43", "湖南");
		areaCodes.put("44", "广东");
		areaCodes.put("45", "广西");
		areaCodes.put("46", "海南");
		areaCodes.put("50", "重庆");
		areaCodes.put("51", "四川");
		areaCodes.put("52", "贵州");
		areaCodes.put("53", "云南");
		areaCodes.put("54", "西藏");
		areaCodes.put("61", "陕西");
		areaCodes.put("62", "甘肃");
		areaCodes.put("63", "青海");
		areaCodes.put("64", "宁夏");
		areaCodes.put("65", "新疆");
		areaCodes.put("71", "台湾");
		areaCodes.put("81", "香港");
		areaCodes.put("82", "澳门");
		areaCodes.put("91", "国外");
	}

	/**
	 * 校验码
	 */
	private static final String[] VAL_CODEARR = { "1", "0", "x", "9", "8", "7",
			"6", "5", "4", "3", "2" };

	/**
	 * 乘积因子
	 */
	private static final String[] WI = { "7", "9", "10", "5", "8", "4", "2",
			"1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };

	/**
	 * Check Address code, according to the GB/T2260
	 * 
	 * @param code
	 * @return
	 */
	public static boolean checkAddressCode(String code) {
		if (code == null || code.length() != 6) {
			return false;
		}

		String areaCodes = code.substring(0, 2);
		if (!IdCardUtil.areaCodes.containsKey(areaCodes)) {
			return false;
		}
		return true;
	}

	public static boolean checkBirthDate(String code) {
		if (code == null || code.length() != 8) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))"
						+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?"
						+ "((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])"
						+ "|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))"
						+ "|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}"
						+ "(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?"
						+ "((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|"
						+ "([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
						+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])"
						+ "|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:"
						+ "([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(code);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the first 17 bits are all digits.
	 * 
	 * @param code
	 * @return
	 */
	public static boolean checkDigits(String code) {

		String digits = code.substring(0, 18);
		for (char c : digits.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkLastBit(String code) {

		int sum = 0;

		for (int index = 0; index < code.length() - 1; index++) {
			char c = code.charAt(index);
			String actorStr = WI[index];

			int codeInt = sting2Int(String.valueOf(c));
			int actor = sting2Int(actorStr);

			sum += (codeInt * actor);

		}

		int mode = sum % 11;
		String acceptedBit = VAL_CODEARR[mode];

		return acceptedBit.charAt(0) == (code.charAt(17));
	}

	/**
	 * Check the length, don't support 15 length old ID card. Only 18 is
	 * accepted.
	 * 
	 * @return
	 */
	protected static boolean checkLength(String code) {
		if (code != null && code.length() == 18) {
			return true;
		}
		return false;
	}

	protected static int sting2Int(String areaCodes2) {
		try {
			return Integer.parseInt(areaCodes2);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，
	 * 三位数字顺序码和一位数字校验码。
	 * 
	 * 2、地址码(前六位数） 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
	 * 
	 * 3、出生日期码（第七位至十四位） 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
	 * 
	 * 4、顺序码（第十五位至十七位）
	 * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。
	 * 
	 * 5、校验码（第十八位数） （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16
	 * ，先对前17位数字的权求和 Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6
	 * 3 7 9 10 5 8 4 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7
	 * 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
	 * 
	 * 
	 * */
	public static boolean validate(String code) {

		if (!checkLength(code)) {
			return false;
		}

		if (!checkDigits(code)) {
			return false;
		}

		if (!checkAddressCode(code.substring(0, 6))) {
			return false;
		}

		if (!checkBirthDate(code.substring(6, 14))) {
			return false;
		}

		if (!checkLastBit(code)) {
			return false;
		}

		return true;
	}
}
