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
 * Create on Apr 11, 2012 4:28:03 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author LiXP
 * 
 */
public class FirstCompile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int result = compiler.run(null, null, null, "Hello.java");
		System.out.println("Result: " + (result == 0));
	}

}
