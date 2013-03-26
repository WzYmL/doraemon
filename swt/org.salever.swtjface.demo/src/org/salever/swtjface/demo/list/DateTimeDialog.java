package org.salever.swtjface.demo.list;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;

public class DateTimeDialog extends Dialog {

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DateTimeDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("New Button");
		
		DateTime dateTime_1 = new DateTime(composite, SWT.BORDER | SWT.TIME);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setText("New Button");
		
		DateTime dateTime_2 = new DateTime(composite, SWT.BORDER | SWT.CALENDAR);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setText("New Button");

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
		return new Point(450, 300);
	}
}
