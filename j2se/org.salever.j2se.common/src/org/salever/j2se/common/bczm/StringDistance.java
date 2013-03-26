/**
 * 
 */
package org.salever.j2se.common.bczm;

/**
 * @author LiXiaopeng
 * 
 */
public class StringDistance {

	public static int calculateStringDistance(String s1, int begin1, int end1,
			String s2, int begin2, int end2) {

		if (begin1 >= end1) {
			if (begin2 >= end2) {
				return 0;
			} else {
				return end2 - begin2;
			}
		}

		if (begin2 >= end2) {
			if (begin1 >= end1) {
				return 0;
			} else {
				return end1 - begin1;
			}
		}

		if (s1.charAt(begin1) == s2.charAt(begin2)) {
			return calculateStringDistance(s1, begin1 + 1, end1, s2,
					begin2 + 1, end2);
		} else {
			int t1 = calculateStringDistance(s1, begin1 + 1, end1, s2,
					begin2 + 2, end2);
			int t2 = calculateStringDistance(s1, begin1 + 2, end1, s2,
					begin2 + 1, end2);
			int t3 = calculateStringDistance(s1, begin1 + 2, end1, s2,
					begin2 + 2, end2);
			return Math.min(t1, Math.min(t2, t3)) + 1;
		}
	}
}
