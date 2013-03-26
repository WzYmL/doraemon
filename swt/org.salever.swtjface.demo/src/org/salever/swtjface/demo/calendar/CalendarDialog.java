/** Copyright (c) 2010 salever@126.com. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     salever@126.com - initial API and implementation
 *
 * Create on  下午01:51:37 2011-10-12 ye2011
 *******************************************************************************/
package org.salever.swtjface.demo.calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;

/**
 * @author LiXP
 *
 */
public class CalendarDialog extends Dialog {

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public CalendarDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("New Label");
		
		DateTime dateTime = new DateTime(container, SWT.BORDER);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		
		DateTime dateTime_1 = new DateTime(container, SWT.BORDER | SWT.TIME);
		
		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setText("New Label");
		
		DateTime dateTime_2 = new DateTime(container, SWT.BORDER | SWT.CALENDAR | SWT.LONG);
		
		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setText("New Label");
		
		DateTime dateTime_3 = new DateTime(container, SWT.BORDER | SWT.SHORT);
		
		Label lblNewLabel_4 = new Label(container, SWT.NONE);
		lblNewLabel_4.setText("New Label");
		
		DateTime dateTime_4 = new DateTime(container, SWT.BORDER | SWT.LONG);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(652, 579);
	}

}
