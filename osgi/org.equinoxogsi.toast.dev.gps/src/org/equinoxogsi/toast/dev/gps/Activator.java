//package org.equinoxogsi.toast.dev.gps;
//
//import org.equinoxogsi.toast.internal.dev.gps.FakeGps;
//import org.osgi.framework.BundleActivator;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceRegistration;
//
//public class Activator implements BundleActivator {
//
//	private ServiceRegistration registration;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
//	 * )
//	 */
//	public void start(BundleContext context) throws Exception {
//		IGps gps = new FakeGps();
//		registration = context.registerService(IGps.class.getName(), gps, null);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
//	 */
//	public void stop(BundleContext context) throws Exception {
//		registration.unregister();
//	}
//
// }
