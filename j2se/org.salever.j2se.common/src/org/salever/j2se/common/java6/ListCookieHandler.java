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
 * Create on Mar 21, 2012 4:53:29 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author LiXP
 * 
 */
public class ListCookieHandler extends CookieHandler {

	private List<Cookie> cookieJar = new LinkedList<Cookie>();

	public void put(URI uri, Map<String, List<String>> responseHeaders)
			throws IOException {
		System.out.println("Cache: " + cookieJar);
		List<String> setCookieList = responseHeaders.get("Set-Cookie");
		if (setCookieList != null) {
			for (String item : setCookieList) {
				Cookie cookie = new Cookie(uri, item);
				// Remove cookie if it already exists
				// New one will replace
				for (Cookie existingCookie : cookieJar) {
					if ((cookie.getURI().equals(existingCookie.getURI()))
							&& (cookie.getName().equals(existingCookie
									.getName()))) {
						cookieJar.remove(existingCookie);
						break;
					}
				}
				System.out.println("Adding to cache: " + cookie);
				cookieJar.add(cookie);
			}
		}
	}

	public Map<String, List<String>> get(URI uri,
			Map<String, List<String>> requestHeaders) throws IOException {
		// Retrieve all the cookies for matching URI
		// Put in comma-separated list
		StringBuilder cookies = new StringBuilder();
		for (Cookie cookie : cookieJar) {
			// Remove cookies that have expired
			if (cookie.hasExpired()) {
				cookieJar.remove(cookie);
			} else if (cookie.matches(uri)) {
				if (cookies.length() > 0) {
					cookies.append(", ");
				}
				cookies.append(cookie.toString());
			}
		}

		// Map to return
		Map<String, List<String>> cookieMap = new HashMap<String, List<String>>(
				requestHeaders);
		// Convert StringBuilder to List, store in map
		if (cookies.length() > 0) {
			List<String> list = Collections.singletonList(cookies.toString());
			cookieMap.put("Cookie", list);
		}
		System.out.println("CookieMap: " + cookieMap);
		// Make read-only
		return Collections.unmodifiableMap(cookieMap);
	}
}
