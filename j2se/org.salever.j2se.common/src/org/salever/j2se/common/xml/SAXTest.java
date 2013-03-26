/**
 * 
 */
package org.salever.j2se.common.xml;

/**
 * @author LiXP
 *
 */
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This program demonstrates how to use a SAX parser. The program prints all
 * hyperlinks links of an XHTML web page. Usage: java SAXTest url
 */
public class SAXTest {
	public static void main(String[] args) throws Exception {
		String url;
		if (args.length == 0) {
			url = "http://www.w3c.org";
			System.out.println("Using " + url);
		} else {
			url = args[0];
		}

		DefaultHandler handler = new DefaultHandler() {
			public void startElement(String namespaceURI, String lname,
					String qname, Attributes attrs) {
				if (lname.equalsIgnoreCase("a") && attrs != null) {
					for (int i = 0; i < attrs.getLength(); i++) {
						String aname = attrs.getLocalName(i);
						if (aname.equalsIgnoreCase("href"))
							System.out.println(attrs.getValue(i));
					}
				}
			}
		};

		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		SAXParser saxParser = factory.newSAXParser();
		InputStream in = new URL(url).openStream();
		saxParser.parse(in, handler);
	}
}
