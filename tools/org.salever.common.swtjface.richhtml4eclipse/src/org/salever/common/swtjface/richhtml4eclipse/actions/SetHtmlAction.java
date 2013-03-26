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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.salever.common.swtjface.richhtml4eclipse.dialogs.SetHtmlDialog;
import org.salever.common.swtjface.richhtml4eclipse.widgets.HtmlComposer;
import org.salever.common.swtjface.richhtml4eclipse.widgets.JavaScriptCommands;


/**
 * @author Tom Seidel <tom.seidel@spiritlink.de>
 * 
 */
public class SetHtmlAction extends Action {

	private HtmlComposer composer = null;

	public SetHtmlAction(HtmlComposer composer) {
		super("设置HTML", IAction.AS_PUSH_BUTTON); //$NON-NLS-1$
		setToolTipText("设置HTML"); //$NON-NLS-1$
		setImageDescriptor(ActionUtil.getImageDescriptor("images/newdocument.gif"));
		this.composer = composer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		SetHtmlDialog dialog = new SetHtmlDialog(this.composer.getShell());
		if (dialog.open() == IDialogConstants.OK_ID) {
			System.out.println(JavaScriptCommands.SET_HTML(dialog.getHtml()));
			this.composer
					.execute(JavaScriptCommands.SET_HTML(dialog.getHtml()));
		}
	}

}
