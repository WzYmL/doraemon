//package org.equinoxogsi.toast.dev.airbag;
//
//import org.equinoxogsi.toast.internal.dev.airbag.FakeAirbag;
//import org.osgi.framework.BundleActivator;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceRegistration;
//
//public class Activator implements BundleActivator {
//
//	private IAirbag airbag;
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
//		airbag = new FakeAirbag();
//		registration = context.registerService(IAirbag.class.getName(), airbag,
//				null);
//		airbag.startup();
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
//		airbag.shutdown();
//	}
//
// }
