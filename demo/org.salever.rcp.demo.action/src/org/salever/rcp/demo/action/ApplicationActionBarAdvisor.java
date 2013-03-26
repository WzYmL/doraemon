package org.salever.rcp.demo.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.salever.rcp.demo.action.actions.OpenAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private Action openAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		openAction = new OpenAction();
		register(openAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager manager = new MenuManager("Open", "_open_");
		manager.add(openAction);

		menuBar.add(manager);
	}

}
