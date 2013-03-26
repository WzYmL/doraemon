package org.salever.rcp.demo.preference;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IAction preferenceAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		preferenceAction = ActionFactory.PREFERENCES.create(window);
		register(preferenceAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {

		IMenuManager window = new MenuManager("Windows", "window");
		menuBar.add(window);

		window.add(preferenceAction);
	}

}
