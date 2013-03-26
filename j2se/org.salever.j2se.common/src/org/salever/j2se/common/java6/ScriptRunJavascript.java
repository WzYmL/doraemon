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
 * Create on Apr 13, 2012 3:40:08 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author LiXP
 * 
 */
public class ScriptRunJavascript {

	/**
	 * @param args
	 * @throws ScriptException
	 */
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("javascript");

		Double d = (Double) engine
				.eval("var date = new Date(); date.getHours()");
		String message = "";
		if (d < 10) {
			message = "Good morning!";
		} else if (d < 16) {
			message = "Good afternoon!";
		} else if (d < 20) {
			message = "Good evening!";
		} else {
			message = "Good night";
		}

		System.out.printf("Hours %s: %s %n", d, message);
	}

}
