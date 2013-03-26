/**
 * 
 */
package org.salever.j2se.common.bczm;

/**
 * @author LiXiaopeng
 * 
 */
public class CountOneInNumber {

	public static long calc(long number) {

		int factor = 1;

		long lowerNum;

		long higherNum;

		int currentNum;

		long count = 0;

		while (number / factor != 0) {
			lowerNum = number - (number / factor) * factor;
			currentNum = (int) ((number / factor) % 10);
			higherNum = number / (factor * 10);

			switch (currentNum) {
			case 0:
				count += higherNum * factor;
				break;
			case 1:
				count += higherNum * factor + lowerNum + 1;
				break;
			default:
				count += (higherNum + 1) * factor;
				break;
			}
			
			factor *= 10;
		}
		return count;
	}

}
