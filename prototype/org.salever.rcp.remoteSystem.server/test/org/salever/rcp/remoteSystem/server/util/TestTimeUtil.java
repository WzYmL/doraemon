/**
 * 
 */
package org.salever.rcp.remoteSystem.server.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.salever.rcp.remoteSystem.server.util.TimeUtil;

/**
 * @author dev
 * 
 */
public class TestTimeUtil {

	/**
	 * Test method for
	 * {@link org.salever.rcp.remoteSystem.server.util.TimeUtil#before(java.util.Date, java.util.Date)}
	 * .
	 */
	@Test
	public void testCompareTo() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(2021, 0, 1, 12, 1, 0);
		Date time1 = calendar.getTime();

		calendar.set(2011, 0, 1, 12, 1, 0);
		Date time2 = calendar.getTime();

		Assert.assertTrue(TimeUtil.before(time1, time2));
	}
}
