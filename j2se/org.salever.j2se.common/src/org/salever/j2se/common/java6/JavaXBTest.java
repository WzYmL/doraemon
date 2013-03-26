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
package org.salever.j2se.common.java6;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.salever.j2se.common.java6.jzventures.Course;
import org.salever.j2se.common.java6.jzventures.Location;
import org.salever.j2se.common.java6.jzventures.Schedule;

/**
 * @author LiXP
 * 
 */
public class JavaXBTest {

	/**
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException {
		JAXBContext newInstance = JAXBContext.newInstance(Schedule.class);
		Marshaller marshaller = newInstance.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		Schedule schedule = new Schedule();
		schedule.setLocation(Location.EAST);
		schedule.getCourse().add(new Course());
		marshaller.marshal(schedule, System.out);

	}

	@XmlRootElement
	static class Point {
		private int x;

		private int y;

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}

		/**
		 * @param x
		 * @param y
		 */
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point() {
			// TODO Auto-generated constructor stub
		}
	}
}
