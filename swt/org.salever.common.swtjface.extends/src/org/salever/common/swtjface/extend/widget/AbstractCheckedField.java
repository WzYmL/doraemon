/**
 * @author LiXiaopeng
 * @created on 2010-11-27 下午06:26:47
 */
package org.salever.common.swtjface.extend.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */
public abstract class AbstractCheckedField extends Composite {

	private String description;
	private Button button;

	public AbstractCheckedField(Composite parent, int style, String description) {
		super(parent, style);
		this.description = description;

		init();
	}

	private void init() {

		GridData gd_nameText = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_nameText.widthHint = 225;
		setLayoutData(gd_nameText);

		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		this.setLayout(gl_composite);

		button = new Button(this, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button.widthHint = 100;
		button.setLayoutData(gd_button);
		button.setText(description);

	}

}
