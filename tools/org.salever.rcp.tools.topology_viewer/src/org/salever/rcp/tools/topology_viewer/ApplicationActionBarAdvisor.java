package org.salever.rcp.tools.topology_viewer;

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
import org.salever.rcp.tools.topology_viewer.actions.GenerateXMLAction;
import org.salever.rcp.tools.topology_viewer.actions.OpenAction;
import org.salever.rcp.tools.topology_viewer.actions.ReloadAction;
import org.salever.rcp.tools.topology_viewer.system.Messages;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction aboutAction;
	private IWorkbenchAction exitAction;
	private IWorkbenchAction saveAction;
	private IWorkbenchAction saveAsAction;
	private IWorkbenchAction copyAction;
	private IWorkbenchAction cutAction;
	private IWorkbenchAction pasteAction;

	//private NewAction newAction;
	private OpenAction openAction;
	private ReloadAction reloadAction;
	private GenerateXMLAction xmlAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolBar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		coolBar.add(new ToolBarContributionItem(toolBar, "main"));
		toolBar.add(openAction);
		toolBar.add(xmlAction);
		toolBar.add(reloadAction);
		toolBar.add(saveAction);
		toolBar.add(saveAsAction);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager(Messages
				.getString("Menu.File_Menu"), "File");
		fileMenu.add(openAction);
		fileMenu.add(new Separator());
		fileMenu.add(xmlAction);
		fileMenu.add(new Separator());
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);

		MenuManager editMenu = new MenuManager(Messages
				.getString("Menu.Edit_Menu"), "Edit");
		editMenu.add(copyAction);
		editMenu.add(cutAction);
		editMenu.add(pasteAction);
		/* menuBar.add(editMenu); */

		MenuManager helpMenu = new MenuManager(Messages
				.getString("Menu.Help_Menu"), "Help");
		helpMenu.add(aboutAction);
		menuBar.add(helpMenu);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {

		aboutAction = ActionFactory.ABOUT.create(window);
		aboutAction.setText(Messages.getString("Action.About"));
		exitAction = ActionFactory.QUIT.create(window);
		exitAction.setText(Messages.getString("Action.Exit"));
		saveAction = ActionFactory.SAVE.create(window);
		saveAction.setText(Messages.getString("Action.Save"));
		saveAsAction = ActionFactory.SAVE_AS.create(window);
		saveAsAction.setText(Messages.getString("Action.SaveAs"));
		saveAsAction.setToolTipText("Save mapping file");
		copyAction = ActionFactory.COPY.create(window);
		copyAction.setText(Messages.getString("Action.Copy"));
		cutAction = ActionFactory.CUT.create(window);
		cutAction.setText(Messages.getString("Action.Cut"));
		pasteAction = ActionFactory.PASTE.create(window);
		pasteAction.setText(Messages.getString("Action.Paste"));

		//newAction = new NewAction(window);
		openAction = new OpenAction(window);
		reloadAction = new ReloadAction(window);
		xmlAction = new GenerateXMLAction(window);

		register(aboutAction);
		register(exitAction);
		register(saveAction);
		register(saveAsAction);
		register(copyAction);
		register(cutAction);
		register(pasteAction);

		//register(newAction);
		register(openAction);
		register(reloadAction);
		register(xmlAction);
	}
}
