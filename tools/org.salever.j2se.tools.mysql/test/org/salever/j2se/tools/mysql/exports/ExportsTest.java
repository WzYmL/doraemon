/**
 * 
 */
package org.salever.j2se.tools.mysql.exports;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

/**
 * @author LiXP
 * 
 */
public class ExportsTest {

	/**
	 * Test method for
	 * {@link org.salever.j2se.tools.mysql.exports.MySQLExportUtil#exportMySQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testExportMySQL() {
		try {
			Assert.assertEquals(0, MySQLExportUtil.exportMySQL("localhost",
					"root", "root", "test", "account", "d:/sql.sql"));
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

}
