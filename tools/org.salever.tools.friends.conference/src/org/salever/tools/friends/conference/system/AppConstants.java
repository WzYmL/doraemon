/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution"," and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on May 31"," 2012 10:31:49 AM
 *******************************************************************************/
package org.salever.tools.friends.conference.system;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * @author LiXP
 * 
 */
public interface AppConstants {

	String APP_TITLE = "WEMUN Conference Organizer";

	String[] COUNTIRES = new String[] { "Afghanistan", "Albania", "Algeria",
			"Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia",
			"Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
			"Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
			"Bhutan", "Bolivia (Plurinational State of)",
			"Bosnia and Herzegovina", "Botswana", "Brazil",
			"Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi",
			"Cambodia", "Cameroon", "Canada", "Cape Verde",
			"Central African Republic", "Chad", "Chile", "China", "Colombia",
			"Comoros", "Congo", "Costa Rica", "Cote D'Ivoire", "Croatia",
			"Cuba", "Cyprus", "Czech Republic",
			"Democratic People's Republic of Korea",
			"Democratic Republic of the Congo", "Denmark", "Djibouti",
			"Dominica", "Dominican Republic", "Ecuador", "Egypt",
			"El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
			"Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia",
			"Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala",
			"Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras",
			"Hungary", "Iceland", "India", "Indonesia",
			"Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy",
			"Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati",
			"Kuwait", "Kyrgyzstan", "Lao People’s Democratic Republic",
			"Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
			"Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi",
			"Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
			"Mauritania", "Mauritius", "Mexico",
			"Micronesia (Federated States of)", "Monaco", "Mongolia",
			"Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia",
			"Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua",
			"Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau",
			"Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
			"Poland", "Portugal", "Qatar", "Republic of Korea",
			"Republic of Moldova", "Romania", "Russian Federation", "Rwanda",
			"Saint Kitts and Nevis", "Saint Lucia",
			"Saint Vincent and the Grenadines", "Samoa", "San Marino",
			"Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia",
			"Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia",
			"Solomon Islands", "Somalia", "South Africa", "South ?Sudan",
			"Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden",
			"Switzerland", "Syrian Arab Republic", "Tajikistan", "Thailand",
			"The former Yugoslav Republic of Macedonia", "Timor-Leste", "Togo",
			"Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
			"Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
			"United Arab Emirates",
			"United Kingdom of Great Britain and Northern Ireland",
			"United Republic of Tanzania", "United States of America",
			"Uruguay", "Uzbekistan", "Vanuatu",
			"Venezuela (Bolivarian Republic of)", "Viet Nam", "Yemen",
			"Zambia", "Zimbabwe" };

	public static Font DEFAULT_FONT = SWTResourceManager.getFont("华文细黑", 15,
			SWT.BOLD);
}
