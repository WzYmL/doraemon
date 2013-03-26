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
public class BulletListAction extends Action implements Listener {
	private HtmlComposer composer = null;

	public BulletListAction(HtmlComposer composer) {
		super(Messages.BulletListAction_0, IAction.AS_CHECK_BOX);
		setImageDescriptor(ActionUtil.getImageDescriptor("images/bullist.gif")); //$NON-NLS-1$ //$NON-NLS-2$
		this.composer = composer;
		this.composer.addListener(EventConstants.BULLIST, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		this.composer.execute(JavaScriptCommands.BULLIST);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.
	 * Event)
	 */
	public void handleEvent(Event event) {
		Properties evtProps = (Properties) event.data;
		if (event.type != EventConstants.ALL) {
			setChecked(ComposerStatus.SELECTED.equals(evtProps
					.getProperty(PropertyConstants.STATUS)));
		} else if (evtProps.getProperty(PropertyConstants.COMMAND).equals(
				AllActionConstants.RESET_ALL)) {
			setChecked(false);
		}
	}

}
