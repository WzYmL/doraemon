//package org.equinoxogsi.toast.client.emergency;
//
//import org.equinoxogsi.toast.dev.airbag.IAirbag;
//import org.equinoxogsi.toast.dev.gps.IGps;
//import org.osgi.framework.BundleActivator;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceReference;
//import org.osgi.util.tracker.ServiceTracker;
//import org.osgi.util.tracker.ServiceTrackerCustomizer;
//
//public class Activator implements BundleActivator {
//
//	private IAirbag airbag;
//
//	private IGps gps;
//
//	// private ServiceReference airbagRef;
//
//	// private ServiceReference gpsRef;
//
//	private ServiceTracker airbagTracker;
//
//	private ServiceTracker gpsTracker;
//
//	private EmergencyMonitor monitor;
//
//	private BundleContext context;
//
//	private void bind() {
//		if (gps == null) {
//			gps = (IGps) gpsTracker.getService();
//		}
//		if (gps == null) {
//			return;
//		}
//
//		if (airbag == null) {
//			airbag = (IAirbag) airbagTracker.getService();
//		}
//		if (airbag == null) {
//			return;
//		}
//
//		monitor.setAirbag(airbag);
//		monitor.setGps(gps);
//		monitor.startup();
//	}
//
//	private ServiceTrackerCustomizer createAirebagCustomizer() {
//		return new ServiceTrackerCustomizer() {
//
//			@Override
//			public Object addingService(ServiceReference reference) {
//				Object object = context.getService(reference);
//				synchronized (Activator.this) {
//					if (Activator.this.airbag == null) {
//						Activator.this.airbag = (IAirbag) object;
//						Activator.this.bind();
//					}
//				}
//				return object;
//			}
//
//			@Override
//			public void modifiedService(ServiceReference reference,
//					Object service) {
//
//			}
//
//			@Override
//			public void removedService(ServiceReference reference,
//					Object service) {
//				synchronized (Activator.this) {
//					if (service != Activator.this.airbag) {
//						return;
//					}
//					Activator.this.unbind();
//					Activator.this.bind();
//				}
//			}
//
//		};
//
//	}
//
//	private ServiceTrackerCustomizer createGpsCustomizer() {
//		return new ServiceTrackerCustomizer() {
//
//			@Override
//			public Object addingService(ServiceReference reference) {
//				Object object = context.getService(reference);
//				synchronized (Activator.this) {
//					if (Activator.this.gps == null) {
//						Activator.this.gps = (IGps) object;
//						Activator.this.bind();
//					}
//				}
//				return object;
//			}
//
//			@Override
//			public void modifiedService(ServiceReference reference,
//					Object service) {
//
//			}
//
//			@Override
//			public void removedService(ServiceReference reference,
//					Object service) {
//				synchronized (Activator.this) {
//					if (service != Activator.this.gps) {
//						return;
//					}
//					Activator.this.unbind();
//					Activator.this.bind();
//				}
//			}
//
//		};
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
//	 * )
//	 */
//	public void start(BundleContext context) throws Exception {
//
//		this.context = context;
//		monitor = new EmergencyMonitor();
//
//		ServiceTrackerCustomizer airebagCustomizer = createAirebagCustomizer();
//
//		airbagTracker = new ServiceTracker(context, IAirbag.class.getName(),
//				airebagCustomizer);
//
//		ServiceTrackerCustomizer gpsCustomizer = createGpsCustomizer();
//		gpsTracker = new ServiceTracker(context, IGps.class.getName(),
//				gpsCustomizer);
//
//		airbagTracker.open();
//		gpsTracker.open();
//
//		System.out.println("Launching....");
//
//		// airbagRef = context.getServiceReference(IAirbag.class.getName());
//		// gpsRef = context.getServiceReference(IGps.class.getName());
//
//		// if (airbagRef == null || gpsRef == null) {
//		// System.err.println("Unable to acquire GPS or Airbag");
//		// return;
//		// }
//		//
//		// airbag = (IAirbag) context.getService(airbagRef);
//		// gps = (IGps) context.getService(gpsRef);
//
//		if (airbag == null || gps == null) {
//			System.err.println("Unable to acquire GPS or Airbag");
//			return;
//		}
//
//		monitor = new EmergencyMonitor();
//		monitor.setAirbag(airbag);
//		monitor.setGps(gps);
//		monitor.startup();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
//	 */
//	public void stop(BundleContext context) throws Exception {
//
//		// monitor.shutdown();
//
//		// if (airbagRef != null) {
//		// context.ungetService(airbagRef);
//		// }
//		// if (gpsRef != null) {
//		// context.ungetService(gpsRef);
//		// }
//
//		airbagTracker.close();
//		gpsTracker.close();
//
//		System.out.println("Terminating....");
//
//	}
//
//	protected void unbind() {
//		if (gps == null || airbag == null) {
//			return;
//		}
//		monitor.shutdown();
//
//		gps = null;
//		airbag = null;
//
//	}
//
// }
