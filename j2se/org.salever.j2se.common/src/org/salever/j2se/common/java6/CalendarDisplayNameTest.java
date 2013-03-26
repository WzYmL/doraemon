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
 * Create on Mar 15, 2012 2:45:32 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

/**
 * @author LiXP
 * 
 */
public class CalendarDisplayNameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Locale locale = Locale.getDefault();
		Map<String, Integer> displayNames = calendar.getDisplayNames(
				Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.FRANCE);

		System.out.printf("%s%n", displayNames.toString());

		String today = calendar.getDisplayName(Calendar.DAY_OF_WEEK,
				Calendar.LONG, locale);
		System.out.printf("%s%n", today);

		Locale jpLocale = new Locale("ja", "JP", "JP");
		Calendar jpCalendar = Calendar.getInstance(jpLocale);

		Map<String, Integer> jpNames = jpCalendar.getDisplayNames(Calendar.ERA,
				Calendar.LONG, jpLocale);

		System.out.printf("%s%n", jpNames.toString());
		System.out.println("The current calendar class is: "
				+ jpCalendar.getClass());

	}

}
