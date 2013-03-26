/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-30 下午02:26:02
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml.ex;

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
public class XmlExParser {

	/**
	 * Parse XML file content to XmlConfigEx object.
	 * 
	 * @param path
	 *            - source XML file path
	 * @return - XmlConfig object
	 * @throws XmlException
	 */
	public static XmlConfigEx parseFromXml(String path) throws XmlException {
		FileInputStream in;
		XmlConfigEx config = null;
		try {
			in = new FileInputStream(path);
			config = (XmlConfigEx) XmlUtil.fromXml(in, XmlConfigEx.class);
		} catch (FileNotFoundException e) {
			throw new XmlException(e);
		} catch (XmlException e) {
			throw new XmlException(e);
		}
		return config;
	}
}
