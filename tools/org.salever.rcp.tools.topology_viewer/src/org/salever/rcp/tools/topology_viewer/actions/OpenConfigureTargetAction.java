/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.actions;

import org.eclipse.ui.actions.LabelRetargetAction;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 */
public class OpenConfigureTargetAction extends LabelRetargetAction {


	public OpenConfigureTargetAction() {
		super(null, null);
		setId(OpenConfigureAction.ID);
		setText(Messages.getString("Action.Open_Config"));
	}

	public OpenConfigureTargetAction(String actionID, String text) {
		super(actionID, text);
	}

	public OpenConfigureTargetAction(String actionID, String text, int style) {
		super(actionID, text, style);
	}

}
