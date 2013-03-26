package org.salever.gef.examples.layersupport.shapes;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction newAction, saveAction, exitAction, openPerspectiveAction,aboutAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		newAction = ActionFactory.NEW.create(window);
		register(newAction);
		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		openPerspectiveAction = ActionFactory.OPEN_PERSPECTIVE_DIALOG.create(window);
		register(openPerspectiveAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File", ".file");
		fileMenu.add(newAction);
		fileMenu.add(saveAction);
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);

		MenuManager windowMenu = new MenuManager("&Window", ".window");
		windowMenu.add(openPerspectiveAction);
		menuBar.add(windowMenu);
		
		MenuManager helpMenu = new MenuManager("&Help", ".help");
		helpMenu.add(aboutAction);
		menuBar.add(helpMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.application.ActionBarAdvisor#fillCoolBar(org.eclipse.jface
	 * .action.ICoolBarManager)
	 */
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		
		ToolBarManager toolbar = new ToolBarManager();
		toolbar.add(newAction);
		toolbar.add(saveAction);
		coolBar.add(toolbar);
		
	}
}
