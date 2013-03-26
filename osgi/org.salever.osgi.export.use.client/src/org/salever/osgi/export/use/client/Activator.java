package org.salever.osgi.export.use.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.salever.osgi.export.use.test.TestUtil;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println(this + ": Hello World!!");

		TestUtil testUtil = new TestUtil();
		System.out.println(testUtil.getUtil().util());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println(this + ": Goodbye World!!");
	}

}
