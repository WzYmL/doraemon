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
 * Create on Mar 21, 2012 4:55:43 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author LiXP
 * 
 */
public class Cookie {

	String name;
	String value;
	URI uri;
	String domain;
	Date expires;
	String path;

	private static DateFormat expiresFormat1 = new SimpleDateFormat(
			"E, dd MMM yyyy k:m:s 'GMT'", Locale.US);
	private static DateFormat expiresFormat2 = new SimpleDateFormat(
			"E, dd-MMM-yyyy k:m:s 'GMT'", Locale.US);

	public Cookie(URI uri, String header) {
		String attributes[] = header.split(";");
		String nameValue = attributes[0].trim();
		this.uri = uri;
		this.name = nameValue.substring(0, nameValue.indexOf('='));
		this.value = nameValue.substring(nameValue.indexOf('=') + 1);
		this.path = "/";
		this.domain = uri.getHost();
		for (int i = 1; i < attributes.length; i++) {
			nameValue = attributes[i].trim();
			int equals = nameValue.indexOf('=');
			if (equals == -1) {
				continue;
			}
			String name = nameValue.substring(0, equals);
			String value = nameValue.substring(equals + 1);
			if (name.equalsIgnoreCase("domain")) {
				String uriDomain = uri.getHost();
				if (uriDomain.equals(value)) {
					this.domain = value;
				} else {
					if (!value.startsWith(".")) {
						value = "." + value;
					}
					uriDomain = uriDomain.substring(uriDomain.indexOf('.'));
					if (!uriDomain.equals(value)) {
						throw new IllegalArgumentException(
								"Trying to set foreign cookie");
					}
					this.domain = value;
				}
			} else if (name.equalsIgnoreCase("path")) {
				this.path = value;
			} else if (name.equalsIgnoreCase("expires")) {
				try {
					this.expires = expiresFormat1.parse(value);
				} catch (ParseException e) {
					try {
						this.expires = expiresFormat2.parse(value);
					} catch (ParseException e2) {
						throw new IllegalArgumentException(
								"Bad date format in header: " + value);
					}
				}
			}
		}
	}

	public boolean hasExpired() {
		if (expires == null) {
			return false;
		}
		Date now = new Date();
		return now.after(expires);
	}

	public String getName() {
		return name;
	}

	public URI getURI() {
		return uri;
	}

	public boolean matches(URI uri) {
		if (hasExpired()) {
			return false;
		}
		String path = uri.getPath();
		if (path == null) {
			path = "/";
		}
		return path.startsWith(this.path);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(name);
		result.append("=");
		result.append(value);
		return result.toString();
	}
}
