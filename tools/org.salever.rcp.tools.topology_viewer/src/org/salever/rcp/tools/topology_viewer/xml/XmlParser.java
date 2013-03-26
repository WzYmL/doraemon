/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-30 下午02:26:02
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.salever.rcp.tools.topology_viewer.xml.util.XmlException;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlUtil;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class XmlParser {

	/**
	 * Parse XML file content to XmlConfig object.
	 * 
	 * @param path
	 *            - source XML file path
	 * @return - XmlConfig object
	 * @throws XmlException
	 */
	public static XmlConfig parseFromXml(String path) throws XmlException {
		FileInputStream in;
		XmlConfig config = null;
		try {
			in = new FileInputStream(path);
			config = (XmlConfig) XmlUtil.fromXml(in, XmlConfig.class);
		} catch (FileNotFoundException e) {
			throw new XmlException(e);
		} catch (XmlException e) {
			throw new XmlException(e);
		}
		return config;
	}
}
