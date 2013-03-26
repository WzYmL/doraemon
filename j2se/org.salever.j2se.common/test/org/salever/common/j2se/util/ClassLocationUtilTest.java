package org.salever.common.j2se.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.salever.j2se.common.util.ClassLocationUtil;

public class ClassLocationUtilTest {

	@Test
	public void testGetAppPath() {
		assertEquals("/E:/eclipse_ws/common_ws", ClassLocationUtil.getParentPath(ClassLocationUtilTest.class));
	}

}
