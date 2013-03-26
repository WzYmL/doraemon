/*******************************************************************************
 * Copyright (c) 2009 Tom Seidel, Remus Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.htmltext.example.databinding;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.mylyn.htmltext.HtmlComposer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class HtmlComposerObservable extends AbstractObservableValue {

	private final HtmlComposer htmlComposer;

	protected String newValue;

	protected Object oldValue;

	private final ModifyListener listener = new ModifyListener() {

		public void modifyText(ModifyEvent e) {
			newValue = htmlComposer.getHtml();
			fireValueChange(new ValueDiff() {

				@Override
				public Object getNewValue() {
					return newValue;
				}

				@Override
				public Object getOldValue() {
					return oldValue;
				}

			});
			oldValue = newValue;

		}
	};

	public static final int EVENT_ID_MODIFY = 62;

	public HtmlComposerObservable(final HtmlComposer composer) {
		htmlComposer = composer;
		htmlComposer.addModifyListener(listener);

	}

	@Override
	protected Object doGetValue() {
		return htmlComposer.getHtml();
	}

	@Override
	protected void doSetValue(final Object value) {
		htmlComposer.setHtml(String.valueOf(value));
	}

	public Object getValueType() {
		return String.class;
	}

	@Override
	public synchronized void dispose() {
		htmlComposer.removeModifyListener(listener);
		super.dispose();
	}

}
