package org.salever.swtjface.demo.stacklayout;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StackLayoutDlg extends Dialog {

	private StackLayout layout;
	private Composite composite_2;
	private Composite composite_3;
	private Composite composite_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public StackLayoutDlg(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));
		composite.setLayout(new GridLayout(1, false));

		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = composite_2;
				composite_1.layout();
			}
		});
		button.setText("New Button");

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = composite_3;
				composite_1.layout();
			}
		});
		button_1.setText("New Button");
		layout = new StackLayout();
		 composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayout(layout);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		composite_2 = new Composite(composite_1, SWT.NONE);

		Combo combo = new Combo(composite_2, SWT.NONE);
		combo.setBounds(0, 0, 90, 24);

		composite_3 = new Composite(composite_1, SWT.NONE);

		Button button_2 = new Button(composite_3, SWT.NONE);
		button_2.setBounds(0, 0, 77, 26);
		button_2.setText("New Button");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
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
		return new Point(550, 414);
	}

	public static void main(String[] args) {
		new StackLayoutDlg(null).open();
	}
}
