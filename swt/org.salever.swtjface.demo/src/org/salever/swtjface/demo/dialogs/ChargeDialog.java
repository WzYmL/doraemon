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
 * Create on  下午03:35:25 2011-11-1 ye2011
 *******************************************************************************/
package org.salever.swtjface.demo.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author LiXP
 * 
 */
public class ChargeDialog extends TitleAreaDialog {

	private static final Font CHARGE_FONT = new Font(null, "Times New Roman", 20,
			SWT.BOLD);

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ChargeDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
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
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		composite.setLayout(new GridLayout(1, false));

		CLabel hoursLabel = new CLabel(composite, SWT.NONE);
		hoursLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		hoursLabel.setText("New Label");

		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		composite_1.setLayout(new GridLayout(1, false));

		CLabel priceLabel = new CLabel(composite_1, SWT.NONE);
		priceLabel.setFont(CHARGE_FONT);
		GridData gd_priceLabel = new GridData(SWT.CENTER, SWT.CENTER, true,
				true, 1, 1);
		gd_priceLabel.widthHint = 200;
		gd_priceLabel.heightHint = 80;
		priceLabel.setLayoutData(gd_priceLabel);
		priceLabel.setText("New Label");

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
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

	public static void main(String[] args) {
		new ChargeDialog(null).open();
	}
}
