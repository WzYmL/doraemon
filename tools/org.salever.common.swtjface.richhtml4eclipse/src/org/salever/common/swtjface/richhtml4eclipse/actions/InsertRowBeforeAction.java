/*******************************************************************************
 * Copyright (c) 2007 Tom Seidel, Spirit Link GmbH
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
import org.salever.common.swtjface.richhtml4eclipse.Messages;
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
public class InsertRowBeforeAction extends Action implements Listener {
	private HtmlComposer composer = null;

	public InsertRowBeforeAction(HtmlComposer composer) {
		super(Messages.InsertRowBeforeAction_0, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(ActionUtil.getImageDescriptor("images/table_insert_row_before.gif")); //$NON-NLS-1$ //$NON-NLS-2$
		this.composer = composer;
		this.composer.addListener(EventConstants.INSERT_ROW_BEFORE, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		this.composer.execute(JavaScriptCommands.INSERT_ROW_BEFORE);
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
		if (ComposerStatus.NORMAL.equals(props
				.getProperty(PropertyConstants.STATUS))) {
			setEnabled(true);
		} else if (ComposerStatus.DISABLED.equals(props
				.getProperty(PropertyConstants.STATUS))) {
			setEnabled(false);
		} else if (event.type == EventConstants.ALL
				&& AllActionConstants.RESET_ALL.equals(props
						.getProperty(PropertyConstants.COMMAND))) {
			setEnabled(false);
		}
	}

}
