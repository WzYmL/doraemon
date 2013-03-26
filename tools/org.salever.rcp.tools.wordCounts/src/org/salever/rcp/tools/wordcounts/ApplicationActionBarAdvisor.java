package org.salever.rcp.tools.wordcounts;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.salever.rcp.tools.wordcounts.actions.CountAction;
import org.salever.rcp.tools.wordcounts.actions.OpenAction;
import org.salever.rcp.tools.wordcounts.actions.OpenAndCountAction;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction iExitAction;
	private IWorkbenchAction iAboutAction;
	private IWorkbenchAction iSaveAsAction;
	private IWorkbenchAction iSaveAction;

	private Action openAction;
	private Action countAction;
	private Action openAndCountAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		iExitAction = ActionFactory.QUIT.create(window);
		register(iExitAction);
		iSaveAction = ActionFactory.SAVE.create(window);
		register(iSaveAction);
		iAboutAction = ActionFactory.ABOUT.create(window);
		register(iAboutAction);
		iSaveAsAction = ActionFactory.SAVE_AS.create(window);
		register(iSaveAsAction);

		openAction = new OpenAction(window);
		register(openAction);

		countAction = new CountAction(window);
		register(countAction);

		openAndCountAction = new OpenAndCountAction(window);
		register(openAndCountAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File", "_file__");
		MenuManager helpMenu = new MenuManager("&Help", "_help_");
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		// File Menu
		fileMenu.add(openAction);
		fileMenu.add(openAndCountAction);
		fileMenu.add(new Separator());
		fileMenu.add(countAction);
		fileMenu.add(iSaveAsAction);
		fileMenu.add(new Separator());
		fileMenu.add(iExitAction);

		// Help Menu
		helpMenu.add(iAboutAction);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// This will add a new toolbar to the application
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));
		// Add the entry to the toolbar
		toolbar.add(openAction);
		// toolbar.add(iSaveAction);
		toolbar.add(iExitAction);
	}
}
