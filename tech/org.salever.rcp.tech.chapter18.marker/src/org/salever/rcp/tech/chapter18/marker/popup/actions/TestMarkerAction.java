package org.salever.rcp.tech.chapter18.marker.popup.actions;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class TestMarkerAction implements IObjectActionDelegate {

	private Shell shell;
	
	private IResource resource;
	
	/**
	 * Constructor for Action1.
	 */
	public TestMarkerAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		try {
			IMarker marker;
			marker = resource
					.createMarker("org.salever.rcp.tech.chapter18.testMarker");
			marker.setAttribute(IMarker.MESSAGE, "Test Marker");
			marker.setAttribute(IMarker.LOCATION, resource.getFullPath()
					.toString());
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;
		resource = (IResource) sselection.getFirstElement();
	}

}
