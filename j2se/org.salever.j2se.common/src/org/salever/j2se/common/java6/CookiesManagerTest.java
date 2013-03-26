package org.salever.j2se.common.java6;

import java.io.PrintStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class CookiesManagerTest {
	public static void main(String args[]) throws Exception {
		PrintStream console = System.out;

		String urlString = "";

		if (args.length != 0) {
			urlString = args[0];
		} else {
			urlString = "http://java.sun.com";
		}
		CookieManager manager = new CookieManager();
		CookieHandler.setDefault(manager);
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		Object obj = connection.getContent();
		url = new URL(urlString);
		connection = url.openConnection();
		obj = connection.getContent();
		CookieStore cookieJar = manager.getCookieStore();
		List<HttpCookie> cookies = cookieJar.getCookies();
		for (HttpCookie cookie : cookies) {
			console.printf("Cookie: %s%n", cookie);
		}
	}
}
