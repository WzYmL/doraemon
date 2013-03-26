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
 * Create on Apr 13, 2012 3:22:19 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.util.List;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 * @author LiXP
 * 
 */
public class ListScriptEngines {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		List<ScriptEngineFactory> engineFactories = scriptEngineManager
				.getEngineFactories();

		for (ScriptEngineFactory factory : engineFactories) {
			System.out.printf("Name: %s%n " + "Version %s%n "
					+ "Language Name: %s%n " + "Language Version: %s%n "
					+ "Extension: %s%n " + "Mime Types: %s%n " + "Names: %s%n",
					factory.getEngineName(), factory.getEngineVersion(),
					factory.getLanguageName(), factory.getLanguageVersion(),
					factory.getExtensions(), factory.getMimeTypes(),
					factory.getNames());
			factory.getScriptEngine();
		}

	}

}
