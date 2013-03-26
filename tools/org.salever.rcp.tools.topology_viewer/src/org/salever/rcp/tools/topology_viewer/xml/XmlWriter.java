/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-1 上午11:19:48
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.salever.rcp.tools.topology_viewer.xml.util.XmlException;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlUtil;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class XmlWriter {

	/**
	 * Writer object to XML file.
	 * 
	 * @param object
	 *            - the <Class>XmlConfig</Class> object
	 * @param path
	 *            - new XML file path
	 * @throws XmlException
	 *             - FileNotFoundException or other exception
	 */
	public static void writeToXml(Object object, String path)
			throws XmlException {
		try {
			FileOutputStream out = new FileOutputStream(path);
			XmlUtil.toXml(object, out);
			out.close();
		} catch (FileNotFoundException e) {
			throw new XmlException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
