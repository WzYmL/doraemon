/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.jobs.tests;

import static org.powermock.api.mockito.PowerMockito.spy;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.talend.jobs.Component;
import org.talend.jobs.Job;
import org.talend.jobs.JobsFactory;
import org.talend.jobs.JobsPackage;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Job</b></em>
 * '. <!-- end-user-doc -->
 * 
 * @generated
 */
public class JobTest extends TestCase {

	class JobAdapter implements Adapter {

		@Override
		public void notifyChanged(Notification notification) {
			System.out.println("Job adapter");
			System.out.println("Notification: " + notification);

			if (notification.getFeature() == JobsPackage.Literals.JOB__COMPONENTS) {
				if (notification.getEventType() == Notification.ADD) {
					Component comp = (Component) notification.getNewValue();
					System.out.println("new component is added: " + comp);
					assertEquals(comp.getName(), "My first comp");
				}
				if (notification.getEventType() == Notification.REMOVE) {
					Component comp = (Component) notification.getOldValue();
					System.out.println("component is deleted: " + comp);
					assertEquals(((Job) notification.getNotifier())
							.getComponents().isEmpty(), true);
				}
			}
			if (notification.getFeature() == JobsPackage.Literals.JOB__NAME) {
				System.out.println("Job name is changed to : "
						+ notification.getNewValue());
			}
		}

		@Override
		public Notifier getTarget() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTarget(Notifier newTarget) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isAdapterForType(Object type) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	/**
	 * The fixture for this Job test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected Job fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(JobTest.class);
	}

	/**
	 * Constructs a new Job test case with the given name. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public JobTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Job test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Job fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Job test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected Job getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(JobsFactory.eINSTANCE.createJob());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	@Test
	public void test() {
		Job job = JobsFactory.eINSTANCE.createJob();
		JobAdapter jobAdapter = new JobAdapter();
		jobAdapter = spy(jobAdapter);
		job.eAdapters().add(jobAdapter);

		job.setName("A Job");
		assertEquals(job.getName(), "A Job");

		assertEquals(job.getComponents().isEmpty(), true);

		Component component = JobsFactory.eINSTANCE.createComponent();
		component.setName("My first comp");
		job.getComponents().add(component);
		assertEquals(job.getComponents().size(), 1);

		job.getComponents().remove(component);
		assertEquals(job.getComponents().isEmpty(), true);

		job.setName("New Name");
		assertEquals(job.getName(), "New Name");

	}

	@Test
	public void testResourceSaveAndLoadNoConstainement() throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Create resource
		URI jobUri = URI.createFileURI("MyJob.xml");
		Resource jobResource = resourceSet.createResource(jobUri);

		URI compUri = URI.createFileURI("MyComponent.xml");
		Resource compResource = resourceSet.createResource(compUri);

		Job job = JobsFactory.eINSTANCE.createJob();
		job.setName("Job Number one");

		Component component = JobsFactory.eINSTANCE.createComponent();
		component.setName("comp1");
		job.getComponents().add(component);

		jobResource.getContents().add(job);
		compResource.getContents().add(component);

		// Save
		jobResource.save(null);
		jobResource.save(System.out, null);

		compResource.save(null);
		compResource.save(System.out, null);

		assertEquals(new File("MyJob.xml").exists(), true);
		assertEquals(new File("MyComponent.xml").exists(), true);

		// Load objects
		ResourceSet loadResourceSet = new ResourceSetImpl();
		loadResourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Create resource
		URI loadJobUri = URI.createFileURI("MyJob.xml");
		Resource loadJobResource = loadResourceSet
				.getResource(loadJobUri, true);
		//
		// URI loadCompUri = URI.createFileURI("MyComponent.xml");
		// Resource loadCompResource = loadResourceSet.getResource(loadCompUri,
		// true);
		//
		// Component firstComponent = (Component) EcoreUtil.getObjectByType(
		// loadCompResource.getContents(), JobsPackage.Literals.COMPONENT);

		Job firstJob = (Job) EcoreUtil.getObjectByType(
				loadJobResource.getContents(), JobsPackage.Literals.JOB);

		assertEquals(firstJob.getComponents().size(), 1);
		assertEquals(loadResourceSet.getResources().size(), 1);
		assertEquals(firstJob.getComponents().get(0).getName(), "comp1");
		assertEquals(loadResourceSet.getResources().size(), 2);
		// assertEquals(firstJob.getComponents().get(0), firstComponent);
		// assertEquals(firstComponent.getName(), "comp1");
	}

} // JobTest
