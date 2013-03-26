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
 * Create on Mar 14, 2012 2:07:52 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import javax.activation.CommandInfo;
import javax.activation.CommandMap;

/**
 * @author LiXP
 * 
 */
public class CommandTypes {

	public static void main(String[] args) {
		CommandMap commandMap = CommandMap.getDefaultCommandMap();
		String[] mimeTypes = commandMap.getMimeTypes();

		for (String type : mimeTypes) {
			System.out.println(type);
			CommandInfo[] commandInfos = commandMap.getAllCommands(type);
			for (CommandInfo info : commandInfos) {
				System.out.println("\t" + info.getCommandName());
			}
		}
	}
}
