package org.salever.osgi.depends.clientbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.salever.osgi.depends.hostbundle.HostService;

public class ClientActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		ClientActivator.context = bundleContext;

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					HostService.service();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					System.out.println(this.toString() + ": Sleep 5000ms");
				}

			}
		}).start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		ClientActivator.context = null;
	}

}
