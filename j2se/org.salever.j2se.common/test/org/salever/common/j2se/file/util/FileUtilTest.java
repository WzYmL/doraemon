/**
 * @created on 2011-1-12 上午10:30:53
 */
package org.salever.common.j2se.file.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.salever.j2se.common.file.utl.FileUtil;

/**
 * @author 
 */
public class FileUtilTest {

	/**
	 * Test method for {@link org.salever.j2se.common.file.utl.FileUtil#wrireToLocal(java.lang.String, java.lang.String)}.
	 */
//	@Test
	public void testWrireToLocal() {
		String content = "AAAAAAA<html>你好</html>";
		String file = "out/utf.htm";
		FileUtil.wrireToLocal(file, content);
	}
	
	@Test 
	public void testIsValidFileName(){
		assertTrue(FileUtil.isValidFileName("1()1"));
	}

}
