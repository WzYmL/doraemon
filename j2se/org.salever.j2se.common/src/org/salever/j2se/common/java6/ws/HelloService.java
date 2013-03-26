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
 * Create on Apr 10, 2012 3:10:09 PM
 *******************************************************************************/
package org.salever.j2se.common.java6.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author LiXP
 * 
 */
@WebService
public class HelloService {

	@WebMethod
	public String helloWorld() {
		return "Hello, World";
	}
}
