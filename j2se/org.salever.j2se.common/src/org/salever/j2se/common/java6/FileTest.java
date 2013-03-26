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
 * Create on Mar 23, 2012 3:25:40 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author LiXP
 * 
 */
public class FileTest {

	/**
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		File file = new File("The End");

		URL url = file.toURL();
		URL uri = file.toURI().toURL();

		System.out.println("Bad URL :" + url);
		System.out.println("Good URL:" + uri);

	}

}
