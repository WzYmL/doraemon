/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.system;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author salever@126.com
 * 
 */
public class Messages {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle("org.salever.rcp.tools.topology_viewer.system.Message");

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}
}
