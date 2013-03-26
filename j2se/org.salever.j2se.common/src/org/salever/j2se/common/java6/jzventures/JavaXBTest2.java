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
package org.salever.j2se.common.java6.jzventures;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
		JAXBContext jc = JAXBContext.newInstance(Schedule.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Schedule schedule = (org.salever.j2se.common.java6.jzventures.Schedule) unmarshaller
				.unmarshal(new File("scheduler.xml"));
		System.out.println(schedule.getLocation().toString());

	}

}
