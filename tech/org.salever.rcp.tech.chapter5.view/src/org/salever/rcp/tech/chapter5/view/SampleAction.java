package org.salever.rcp.tech.chapter5.view;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;


public class SampleAction implements IViewActionDelegate {
	SampleViewPart myView;

	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.myView = (SampleViewPart) view;

	}

	public void run(IAction action) {
		// TODO Auto-generated method stub
		MessageDialog.openInformation(myView.getViewSite().getShell(),
				"Information",
				"Very well, you did it, you did add an action to this view. You are my hero!");
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
