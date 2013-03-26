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
 * Create on Mar 14, 2012 2:13:40 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.io.File;

import javax.activation.FileTypeMap;

/**
 * @author LiXP
 * 
 */
public class FileTypes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileTypeMap fileTypeMap = FileTypeMap.getDefaultFileTypeMap();

		String path = ".";
		File dir = new File(path);
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			System.out.println(file.getName() + ":"
					+ fileTypeMap.getContentType(file));
		}
	}

}
