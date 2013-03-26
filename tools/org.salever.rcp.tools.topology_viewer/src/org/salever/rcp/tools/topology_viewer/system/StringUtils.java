/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-29 下午04:42:53
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.system;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class StringUtils {

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if (string == null || string.equals("")) {
			return false;
		}
		return true;
	}
}
