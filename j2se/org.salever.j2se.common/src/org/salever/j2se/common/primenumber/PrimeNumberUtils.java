/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on 2011-3-7 下午03:28:18
 *******************************************************************************/
package org.salever.j2se.common.primenumber;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;

/**
 * @author
 */
public class PrimeNumberUtils {

	/**
	 * 构造一个任意的{@code 1<=a<number}
	 * 
	 * @param number
	 * @param random
	 * @return
	 */
	private static BigInteger createRandomInteger(BigInteger number,
			Random random) {

		BigInteger result = new BigInteger(number.bitLength(), random);
		if (result.compareTo(number) >= 0) {
			result = result.divide(BigInteger.valueOf(2));
		}

		if (result.compareTo(BigInteger.valueOf(0)) == 0) {
			result = BigInteger.valueOf(1);
		}
		return result;
	}

	/**
	 * 素数测试原理，如果p是一个素数，那么对于任意的{@code 1<=a
	 * <p},有a^p-1 mod p = 1
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isBigPrime(BigInteger number) {

		BigInteger random = createRandomInteger(number, new Random());

		return testPrime(number, random);
	}

	/**
	 * 素数测试原理，如果p是一个素数，那么对于任意的{@code 1<=a
	 * <p},有a^p-1 mod p = 1
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isBigPrime(BigInteger number, int agains) {

		BigInteger[] randoms = new BigInteger[agains];
		Random r = new Random();

		for (int index = 0; index < agains; index++) {
			randoms[index] = createRandomInteger(number, r);
		}

		for (BigInteger bi : randoms) {
			if (!testPrime(number, bi)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 素数测试原理，如果p是一个素数，那么对于任意的{@code 1<=a
	 * <p},有a^p-1 mod p = 1
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isPrime(long number) {

		long middle = (long) Math.sqrt(number);

		for (long index = 1; index < middle; index++) {

			BigInteger bi1 = BigInteger.valueOf(index);
			BigInteger bi2 = BigInteger.valueOf(number);

			BigInteger result = bi1
					.modPow(bi2.add(BigInteger.valueOf(-1)), bi2);

			if (result.intValue() != 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 素数测试原理，如果p是一个素数，那么对于任意的{@code 1<=a
	 * <p},有a^p-1 mod p = 1
	 * 
	 * @param number
	 * @return
	 */
	private static boolean testPrime(BigInteger number, BigInteger random) {

		System.out.println(random.intValue());

		BigInteger result = random.modPow(number.add(BigInteger.valueOf(-1)),
				number);

		System.out.println(result.intValue());

		return result.intValue() == 1;
	}

	public static int[] getAllPrimeNumberUnder(int a) {
		if (a <= 1) {
			return new int[0];
		}
		BitSet bit = new BitSet(a + 1);
		int count = a - 1;
		for (int i = 2; i <= a; i++) {
			if (bit.get(i)) {
				continue;
			}
			int j = i + i;
			while (j <= a) {
				if (!bit.get(j)) {
					bit.set(j, true);
					count--;
				}
				j += i;
			}
		}
		int[] result = new int[count];
		count = 0;
		for (int i = 2; i <= a; i++) {
			if (!bit.get(i)) {
				result[count++] = i;
			}
		}
		return result;
	}
}
