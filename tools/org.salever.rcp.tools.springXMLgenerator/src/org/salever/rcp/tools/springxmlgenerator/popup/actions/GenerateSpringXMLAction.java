package org.salever.rcp.tools.springxmlgenerator.popup.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.salever.rcp.tools.springxmlgenerator.xml.SpringXMLWriter;

public class GenerateSpringXMLAction implements IObjectActionDelegate {

	private Shell shell;

	private IStructuredSelection selection;

	private List<ICompilationUnit> selectedClazzes;

	private IPath xmlProjectRelativePath;

	/**
	 * Constructor for Action1.
	 */
	public GenerateSpringXMLAction() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	private List<ICompilationUnit> buildSelectedUnits() {
		Set<ICompilationUnit> units = new HashSet<ICompilationUnit>();
		try {
			Object[] selectedElement = selection.toArray();
			for (Object obj : selectedElement) {
				if (obj instanceof IPackageFragment) {
					IPackageFragment pkg = (IPackageFragment) obj;
					units.addAll(Arrays.asList(pkg.getCompilationUnits()));
				} else if (obj instanceof ICompilationUnit) {
					units.add((ICompilationUnit) obj);
				} else {
					// Not supported now
				}
			}

		} catch (JavaModelException e) {
			//
		}

		List<ICompilationUnit> sortedUnits = new ArrayList<ICompilationUnit>();
		sortedUnits.addAll(units);
		Collections.sort(sortedUnits, new Comparator<ICompilationUnit>() {
			public int compare(ICompilationUnit o1, ICompilationUnit o2) {
				String name1 = o1.getElementName();
				String name2 = o2.getElementName();
				return name1.compareTo(name2);
			}
		});
		return sortedUnits;
	}

	/**
	 * 
	 * @return
	 */
	private IPath computeXMLOutputDirectory() {

		IJavaElement element = (IJavaElement) selection.getFirstElement();
		switch (element.getElementType()) {
		case IJavaElement.PACKAGE_FRAGMENT:
			return element.getResource().getLocation();
		case IJavaElement.COMPILATION_UNIT:
			return element.getParent().getResource().getLocation();
		default:
			return element.getJavaProject().getResource().getLocation();
		}
	}

	private IProject getProject() {
		IJavaElement element = (IJavaElement) selection.getFirstElement();
		return element.getJavaProject().getProject();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		generateXML();
		
	}

	/**
	 * 
	 */
	private void generateXML() {
		selectedClazzes = buildSelectedUnits();
		xmlProjectRelativePath = computeXMLOutputDirectory();
		IPath resultPath = null;
		try {
			resultPath = SpringXMLWriter.writeSpringXML(selectedClazzes,
					xmlProjectRelativePath);
			IProject project = getProject();
			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (IOException e) {
			MessageDialog.openError(
					shell,
					"Failed",
					"Failed to generate SpringContext XML, details: "
							+ e.getMessage());
			return;
		} catch (CoreException e) {
			MessageDialog.openError(
					shell,
					"Failed",
					"Failed to refresh SpringContext XML, details: "
							+ e.getMessage());
			return;
		}

		MessageDialog.openInformation(shell, "Successful",
				"Generate SpringContext XML at: " + resultPath.toOSString());
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = (IStructuredSelection) selection;
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

}
