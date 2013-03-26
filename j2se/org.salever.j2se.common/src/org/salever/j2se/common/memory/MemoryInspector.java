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
 * Create on 2011-2-21 上午10:43:14
 *******************************************************************************/
package org.salever.j2se.common.memory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author
 */
public class MemoryInspector {

	private static final DateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static void info(String message) {
		System.out.println(format.format(Calendar.getInstance().getTime())
				+ ": " + message);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Runtime rt = Runtime.getRuntime();
	
		info("Max   memory: " + rt.maxMemory());
		long fisrt = rt.freeMemory();
		info("Total memory: " + rt.totalMemory());
		info("Free memory: " + fisrt);
	
		int size = 10000;
		
		Object[] b = new Object[size];
		long bL = rt.freeMemory();
		info("Free memory: " + bL);
	    info("Object allocate Cost memory: " + (fisrt - bL) + ", Array size :" + size);
		
		long[] l2048 = new long[size];
		long lL = rt.freeMemory();
		info("Free memory: " + lL);
		info("long allocate Cost memory: " + (bL - lL) + ", Array size :" + size);
		
	    
	    int[] i2048 = new int[size];
		long iL = rt.freeMemory();
		info("Free memory: " + iL);
		info("int allocate Cost memory: " + (lL - iL) + ", Array size :" + size);
		
	
		double[] d2048 = new double[size];
		long dL = rt.freeMemory();
		info("Free memory: " + dL);
		info("double allocate Cost memory: " + (iL - dL) + ", Array size :" + size);
		String[] s2048 = new String[2048];
		
		
		info("Max   memory: " + rt.maxMemory());
		info("Total memory: " + rt.totalMemory());
		
	}

}
