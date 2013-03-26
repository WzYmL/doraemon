/**
 * @file DiagramActionbarContributor.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  下午04:15:24
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.actions;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;
import org.salever.rcp.tools.topology_viewer.system.CustomConstants;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 * @name DiagramActionbarContributor
 * 
 */
public class DiagramActionbarContributor extends ActionBarContributor {

	private OpenMappingFileAction openMapAction;

	@SuppressWarnings("unused")
	private SwitchIconTypeAction switchAction;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
	 */
	@Override
	protected void buildActions() {

		this.addRetargetAction(new UndoRetargetAction());
		this.addRetargetAction(new RedoRetargetAction());
		this.addRetargetAction(new DeleteRetargetAction());
		this.addRetargetAction(new ZoomInRetargetAction());
		this.addRetargetAction(new ZoomOutRetargetAction());

		// 更多功能工具栏
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));

		this.addRetargetAction(new OpenConfigureTargetAction());

	}

	@Override
	public void contributeToMenu(IMenuManager menuManager) {

		openMapAction = new OpenMappingFileAction(getPage()
				.getWorkbenchWindow());

		switchAction = new SwitchIconTypeAction(getPage().getWorkbenchWindow());

		IMenuManager editMenu = new MenuManager(Messages
				.getString("Menu.Edit_Menu"), "Edit");
		menuManager.insertAfter("File", editMenu);
		editMenu.add(openMapAction);
		editMenu.add(getActionRegistry().getAction(
				CustomConstants.ACTION_OPEN_CONFIG_TARGET));
		//editMenu.add(switchAction);
		editMenu.add(new Separator());

		editMenu.add(getAction(ActionFactory.UNDO.getId()));
		editMenu.add(getAction(ActionFactory.REDO.getId()));
		editMenu.add(getAction(ActionFactory.DELETE.getId()));
		editMenu.add(new Separator());
		editMenu.add(new Separator());
		editMenu.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_LEFT));
		editMenu.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_CENTER));
		editMenu.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_RIGHT));
		editMenu.add(getActionRegistry()
				.getAction(GEFActionConstants.ALIGN_TOP));
		editMenu.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_MIDDLE));
		editMenu.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_BOTTOM));
		editMenu.add(new Separator());

		editMenu.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		editMenu
				.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		/*
		 * IMenuManager fileMenu = (IMenuManager)menuManager.find("file");
		 * fileMenu.insertAfter(OpenAction.ID,openMapAction);
		 */
		/*
		 * IMenuManager runMenu = (IMenuManager)menuManager.find("help");
		 * runMenu
		 * .add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		 */
	}

	@Override
	public void contributeToToolBar(IToolBarManager toolBar) {

		toolBar.add(getAction(ActionFactory.UNDO.getId()));
		toolBar.add(getAction(ActionFactory.REDO.getId()));
		toolBar.add(getAction(ActionFactory.DELETE.getId()));
		toolBar.add(new Separator());

		// 更多工具栏
		toolBar.add(getActionRegistry()
				.getAction(GEFActionConstants.ALIGN_LEFT));
		toolBar.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_CENTER));
		toolBar.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_RIGHT));
		toolBar
				.add(getActionRegistry()
						.getAction(GEFActionConstants.ALIGN_TOP));
		toolBar.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_MIDDLE));
		toolBar.add(getActionRegistry().getAction(
				GEFActionConstants.ALIGN_BOTTOM));
		toolBar.add(new Separator());

		toolBar.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		toolBar.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		toolBar.add(new Separator());

		//toolBar.add(switchAction);
		toolBar.add(openMapAction);
		/*
		 * toolBar.add(getActionRegistry().getAction(
		 * CustomConstants.ACTION_OPEN_CONFIG_TARGET));
		 */

	}

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub

	}
}
