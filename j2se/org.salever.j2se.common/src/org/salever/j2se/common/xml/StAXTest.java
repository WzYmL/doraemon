/**
 * 
 */
package org.salever.j2se.common.xml;

import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 * @author LiXP
 * 
 */
public class StAXTest {

	public static void main(String[] args) throws Exception {
		String url;
		if (args.length == 0) {
			url = "http://www.w3c.org";
			System.out.println("Using " + url);
		} else {
			url = args[0];
		}
		InputStream in = new URL(url).openStream();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory.createXMLStreamReader(in);
		while (parser.hasNext()) {
			int event = parser.next();

			if (event == XMLStreamConstants.START_ELEMENT) {
				if (parser.getLocalName().equals("a")) {
					String href = parser.getAttributeValue(null, "href");
					if (href != null) {
						System.out.println(href);
					}
				}
			}
		}

	}

}
