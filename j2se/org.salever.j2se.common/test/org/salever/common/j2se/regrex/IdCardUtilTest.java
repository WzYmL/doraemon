package org.salever.common.j2se.regrex;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.salever.j2se.common.regrex.IdCardUtil;

public class IdCardUtilTest {

	@Test
	public void testValidate() {
		String id = "429001198802126115";
		assertTrue(IdCardUtil.validate(id));
	}

	@Test
	public void testCheckBirthDate() {
		assertTrue(IdCardUtil.checkBirthDate("20000202"));
	}
}
