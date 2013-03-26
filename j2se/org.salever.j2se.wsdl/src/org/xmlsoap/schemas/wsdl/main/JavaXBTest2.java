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
 * Create on Mar 27, 2012 2:48:13 PM
 *******************************************************************************/
package org.xmlsoap.schemas.wsdl.main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xmlsoap.schemas.wsdl.TDefinitions;

/**
 * @author LiXP
 * 
 */
public class JavaXBTest2 {

	/**
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(TDefinitions.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		JAXBElement schedule = (JAXBElement) unmarshaller.unmarshal(new File(
				"wsdl/DemoService.wsdl"));
		TDefinitions value = (TDefinitions) schedule.getValue();
		System.out.println(value.toString());

	}

}
