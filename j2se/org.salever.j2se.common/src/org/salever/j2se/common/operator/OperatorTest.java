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
 * Create on 2011-2-25 上午11:17:05
 *******************************************************************************/
package org.salever.j2se.common.operator;

/**
 * @author
 */
public class OperatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean b1 = false;
		boolean b2 = true;
		boolean b3 = b1 | b2;
		System.out.println(b3);

		int i1 = 3;
		int i2 = -1;
		int i3 = i1 << 2;
		binaryPrint(i1);
		binaryPrint(i3);
		binaryPrint(i2>>>1);
	}

	public static void binaryPrint(int integer) {
		System.out.print(integer + ", Binary: ");
		for (int i = 31; i >= 0; i--) {
			if (((integer >> i) & 1) != 0) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
		}
		System.out.println("");
	}

}
