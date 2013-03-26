/**
 * @author LiXiaopeng
 * @created on 2010-11-24 下午09:11:46
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
public class CheckedCheckboxField extends Composite {

	private Button checkbox;
	private String description;
	private Button button;
	private String checkboxInfo;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedCheckboxField(Composite parent, int style, String description, String checkboxInfo) {
		super(parent, style);
		this.description = description;
		this.checkboxInfo = checkboxInfo;
		init();
	}

	/**
	 * 初始化面板
	 */
	private void init() {

		// GridData dg = new GridData(SWT.LEFT, SWT.CENTER, false, false,
		// 1, 1);
		// dg.widthHint = 225;
		// setLayoutData(dg);

		GridLayout gl_composite = new GridLayout(2, false);
//		gl_composite.marginWidth = 0;
//		gl_composite.marginHeight = 0;
		this.setLayout(gl_composite);

		button = new Button(this, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		button.setLayoutData(gd_button);
		button.setText(description);

		checkbox = new Button(this, SWT.CHECK);
		GridData gd_text = new GridData(GridData.FILL_BOTH);
//		gd_text.widthHint = 120;
		checkbox.setText(checkboxInfo);
		checkbox.setLayoutData(gd_text);
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @return the spinner
	 */
	public Button getCheckbox() {
		return checkbox;
	}

	public boolean isEnable(){
		return button.getSelection();
	}
	
	public boolean getValue(){
		return checkbox.getSelection();
	}
}
