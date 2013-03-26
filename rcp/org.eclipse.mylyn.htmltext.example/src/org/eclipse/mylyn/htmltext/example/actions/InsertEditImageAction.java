/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.htmltext.example.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.mylyn.htmltext.HtmlComposer;
import org.eclipse.mylyn.htmltext.commands.Command;
import org.eclipse.mylyn.htmltext.commands.dialog.InsertEditImageCommand;
import org.eclipse.mylyn.htmltext.example.Activator;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class InsertEditImageAction extends AbstractCommandWrapper {

	public InsertEditImageAction(HtmlComposer composer) {
		super("Insert/Edit Image", IAction.AS_PUSH_BUTTON, composer); //$NON-NLS-1$
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, "icons/image.gif")); //$NON-NLS-1$

	}

	@Override
	protected Command getWrappedCommand() {
		return new InsertEditImageCommand();
	}

}
