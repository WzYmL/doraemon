/**
 * @created on 2011-1-12 上午11:26:52
 */
package org.salever.common.j2se.format;

import static org.junit.Assert.*;

import org.junit.Test;
import org.salever.j2se.common.format.NumberFormatUtil;


/**
 * @author 
 */
public class NumberFormatUtilTest {

	@Test
	public void testFormatDoublePercent(){
		double d = 1;
		assertEquals("100.00%", NumberFormatUtil.formatDoublePercent(d));
	}
}
