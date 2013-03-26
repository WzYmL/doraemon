/**
 * 
 */
package org.salever.j2se.tools.mysql.imports;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author LiXP
 * 
 */
public class ImportsTest {

	/**
	 * Test method for
	 * {@link org.salever.j2se.tools.mysql.exports.MySQLExportUtil#exportMySQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testExportMySQL() {
		try {
			MySQLImportUtil.importMySQL("localhost", "root", "root", "test1",
					"", "d:/sql.sql");
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

}
