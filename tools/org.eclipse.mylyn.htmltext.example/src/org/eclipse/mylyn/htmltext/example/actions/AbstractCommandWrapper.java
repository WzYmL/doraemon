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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.action.Action;
import org.eclipse.mylyn.htmltext.HtmlComposer;
import org.eclipse.mylyn.htmltext.commands.Command;
import org.eclipse.mylyn.htmltext.model.TriState;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public abstract class AbstractCommandWrapper extends Action implements
		PropertyChangeListener {

	protected final Command wrappedCommand;
	protected final HtmlComposer composer;

	public AbstractCommandWrapper(String text, int style, HtmlComposer composer) {
		super(text, style);
		this.composer = composer;
		wrappedCommand = getWrappedCommand();
		assert (wrappedCommand != null);
		wrappedCommand.setComposer(composer);
		wrappedCommand.addPropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("state".equals(evt.getPropertyName())) {
			switch ((TriState) evt.getNewValue()) {
			case OFF:
				setChecked(false);
				setEnabled(true);
				break;
			case ON:
				setChecked(true);
				setEnabled(true);
				break;
			case DISABLED:
				setEnabled(false);
				setChecked(false);
			default:
				break;
			}
		}

	}

	@Override
	public void run() {
		wrappedCommand.execute();
	}

	protected abstract Command getWrappedCommand();

}
