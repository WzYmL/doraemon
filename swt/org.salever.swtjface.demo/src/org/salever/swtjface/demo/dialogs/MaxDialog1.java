package org.salever.swtjface.demo.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.salever.swtjface.demo.SWTResourceManager;

public class MaxDialog1 extends TitleAreaDialog {

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public MaxDialog1(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.NO_TRIM);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(container, SWT.NONE);
		label.setText("New Label");

		Button btnMaximumOpen = new Button(container, SWT.NONE);
		btnMaximumOpen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openDialog();
			}
		});
		btnMaximumOpen.setText("Maximum Open");

		return area;
	}

	protected void openDialog() {
		MaxDialog2 dialog = new MaxDialog2(getShell());
		dialog.open();
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

	@Override
	protected void configureShell(Shell newShell) {
		Window.setDefaultImage(SWTResourceManager.getImage(MaxDialog1.class,
				"/org/salever/learning/swt/demo/tree/icons/add.gif"));
		super.configureShell(newShell);
		newShell.setSize(Display.getDefault().getBounds().width, Display.getDefault().getBounds().height);
	}

	public static void main(String[] args) {
		new MaxDialog1(null).open();
	}
}
