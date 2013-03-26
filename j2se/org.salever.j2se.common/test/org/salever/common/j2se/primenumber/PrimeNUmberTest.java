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
 * Create on 2011-3-7 下午03:32:47
 *******************************************************************************/
package org.salever.common.j2se.primenumber;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import org.salever.j2se.common.primenumber.PrimeNumberUtils;

/**
 * @author 
 */
public class PrimeNUmberTest {

	/**
	 * Test method for {@link org.salever.j2se.common.primenumber.PrimeNumberUtils#isPrime(long)}.
	 */
	@Test
	public void testIsPrime() {
//		assertTrue(PrimeNumberUtils.isPrime(561));
	}

	@Test
	public void testIsBigPrime() {
		
		int end = 100000;
		int count = 0;
		long last = 0;
		
		long l1 = System.currentTimeMillis();
		
		for(int index = 2; index < end; index ++){
			if(PrimeNumberUtils.isPrime(index)){
				count ++;
				last = index;
			}
		}
		
		System.out.println(count);
		System.out.println(last);
		System.out.println("It takes " + (System.currentTimeMillis() - l1) + " ms to count.");
	}

}
