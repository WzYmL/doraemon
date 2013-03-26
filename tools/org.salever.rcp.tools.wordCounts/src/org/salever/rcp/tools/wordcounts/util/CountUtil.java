package org.salever.rcp.tools.wordcounts.util;

public class CountUtil {
	// 测试代码
	public static void main(String[] args) {
		String s = "java I Love  JAVA,我是迪锐2010年的学员！It's so difficult to study java";
		count(s);// 调用方法
		System.out.println("======================");
		find(s, "我");// 调用方法
	}

	// 字符统计代码
	public static void count(String s) {
		int count1 = 0;// 统计小写字母的个数
		int count2 = 0;// 统计大写字母的个数
		int count3 = 0;// 统计非字母的个数
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			if (a > 'a' && a < 'z') {
				count1++;
			}
			if (a > 'A' && a < 'Z') {
				count2++;
			} else {
				count3++;
			}
		}
		System.out.println("小写字母数为： " + count1);
		System.out.println("大写字母数为： " + count2);
		System.out.println("非字母数为： " + count3);
	}

	// 测试字符串中某字串出现的个数
	public static int find(String s, String subs) {
		int count = 0;

		while (s != null && s.length() >= subs.length()) {

			int n = s.indexOf(subs);// 找到第一个字串出现的位置
			if (n >= 0) {// 如果有相应字串，则执行一下代码
				count++;
				s = s.substring(n + 1);
			} else {// 如果找不到，就退出
				break;
			}
		}
		return count;
	}
}