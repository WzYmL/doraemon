package org.salever.j2se.common.java6;

import java.net.CookieHandler;
import java.net.URL;
import java.net.URLConnection;

public class CookiesHandlerTest {
	public static void main(String args[]) throws Exception {

		String urlString = "";

		if (args.length != 0) {
			urlString = args[0];
		} else {
			urlString = "http://java.sun.com";
		}

		CookieHandler.setDefault(new ListCookieHandler());
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		Object obj = connection.getContent();
		System.out.println(obj);
		url = new URL(urlString);
		connection = url.openConnection();
		obj = connection.getContent();
		System.out.println(obj);
	}
}