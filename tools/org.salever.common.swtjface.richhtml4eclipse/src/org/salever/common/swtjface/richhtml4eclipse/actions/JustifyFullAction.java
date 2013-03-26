/*******************************************************************************
 * Copyright (c) 2006 Tom Seidel, Spirit Link GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package org.salever.common.swtjface.richhtml4eclipse.actions;

import java.util.Properties;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.salever.common.swtjface.richhtml4eclipse.widgets.AllActionConstants;
import org.salever.common.swtjface.richhtml4eclipse.widgets.ComposerStatus;
import org.salever.common.swtjface.richhtml4eclipse.widgets.EventConstants;
import org.salever.common.swtjface.richhtml4eclipse.widgets.HtmlComposer;
import org.salever.common.swtjface.richhtml4eclipse.widgets.JavaScriptCommands;
import org.salever.common.swtjface.richhtml4eclipse.widgets.PropertyConstants;


/**
 * 
 * @author Tom Seidel <tom.seidel@spiritlink.de>
 * 
 */
public class JustifyFullAction extends Action implements Listener {
	private HtmlComposer composer = null;

	public JustifyFullAction(HtmlComposer composer) {
		super("", IAction.AS_CHECK_BOX);
		setImageDescriptor(ActionUtil.getImageDescriptor("images/justifyfull.gif"));
		this.composer = composer;
		this.composer.addListener(EventConstants.JUSTIFYFULL, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		this.composer.execute(JavaScriptCommands.JUSTIFYFULL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.
	 * Event)
	 */
	public void handleEvent(Event event) {
		Properties props = (Properties) event.data;
		if (ComposerStatus.SELECTED.equals(props
				.getProperty(PropertyConstants.STATUS))) {
			// current selection/cursor is bold --> set the action checked
			setChecked(true);
		} else if (ComposerStatus.NORMAL.equals(props
				.getProperty(PropertyConstants.STATUS))) {
			setChecked(false);
		} else if (event.type == EventConstants.ALL
				&& AllActionConstants.RESET_ALL.equals(props
						.getProperty(PropertyConstants.COMMAND))) {
			// callback if the cursor changed, reset the state.
			setChecked(false);
		}
	}

}
