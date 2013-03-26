/**
 * 
 */
package org.salever.j2se.common.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author 
 * 
 */
public class PropertiesUtil {

	/**
	 * Set the property.
	 * 
	 * @param path
	 *            - properties file path
	 * @param key
	 *            - property key
	 * @param value
	 *            - property value
	 */
	public static void setProperty(String path, String key, String value) {
		Properties p = new PreformattedProperties();
		try {
			InputStream in = new FileInputStream(path);
			try {
				p.load(in);
			} finally {
				in.close();
			}
			if (value == null) {
				p.remove(key);
			} else {
				p.setProperty(key, value);
			}

			OutputStream out = new FileOutputStream(path); // 加载配置文件
			try {
				p.store(out, null);
				out.flush();
			} finally {
				out.close();
			}

		} catch (Exception e) {
		}
	}

	/**
	 * Get property value.
	 * 
	 * @param path
	 *            - properties file path
	 * @param key
	 *            - property key
	 * @return property value if exists, otherwise {@code null}
	 */
	public static String getProperty(String path, String key) {
		Properties p = new Properties();
		try {
			InputStream in = new FileInputStream(path);
			try {
				p.load(in);
			} finally {
				in.close();
			}
			return p.getProperty(key);

		} catch (Exception e) {
			return null;
		}
	}
}
