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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.salever.common.swtjface.richhtml4eclipse.Messages;
import org.salever.common.swtjface.richhtml4eclipse.widgets.HtmlComposer;
import org.salever.common.swtjface.richhtml4eclipse.widgets.JavaScriptCommands;


/**
 * @author Tom Seidel <tom.seidel@spiritlink.de>
 * 
 */
public class InsertLayerAction extends Action {

	private HtmlComposer composer = null;

	public InsertLayerAction(HtmlComposer composer) {
		super(Messages.InsertLayerAction_0, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(ActionUtil.getImageDescriptor("images/insert_layer.gif")); //$NON-NLS-1$
		this.composer = composer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		this.composer.execute(JavaScriptCommands.INSERT_LAYER);

	}

}
