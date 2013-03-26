/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.menu;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;
import org.salever.rcp.tools.topology_viewer.actions.OpenConfigureAction;


/**
 * @author salever@126.com
 * 
 */
public class LabelContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry actionResgistry;

	public LabelContextMenuProvider(EditPartViewer viewer,
			ActionRegistry registry) {
		super(viewer);
		this.actionResgistry = registry;
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		GEFActionConstants.addStandardActionGroups(menu);

		menu.appendToGroup(GEFActionConstants.GROUP_UNDO,
				getAction(OpenConfigureAction.ID));
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, new Separator());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO,
				getAction(ActionFactory.UNDO.getId()));
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO,
				getAction(ActionFactory.REDO.getId()));
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, new Separator());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO,
				getAction(GEFActionConstants.DIRECT_EDIT));
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT,
				getAction(ActionFactory.DELETE.getId()));

	}

	private IAction getAction(String id) {
		return actionResgistry.getAction(id);
	}

}
