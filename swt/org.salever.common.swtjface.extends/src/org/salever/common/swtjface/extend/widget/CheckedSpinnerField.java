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
import org.eclipse.swt.widgets.Spinner;

/**
 * 
 */
public class CheckedSpinnerField extends Composite {

	private Spinner spinner;
	private String description;
	private Button button;
	private int max;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedSpinnerField(Composite parent, int style, String description,
			int max) {
		super(parent, style);
		this.description = description;
		this.max = max;
		init();
	}

	/**
	 * 初始化面板
	 */
	private void init() {

//		GridData dg = new GridData(SWT.LEFT, SWT.CENTER, false, false,
//				1, 1);
//		dg.widthHint = 225;
//		setLayoutData(dg);
		
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

		spinner = new Spinner(this, SWT.BORDER);
		GridData gd_text = new GridData(GridData.FILL_BOTH);
		gd_text.widthHint = 120;
		spinner.setLayoutData(gd_text);
		spinner.setMaximum(max);
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
	public Spinner getSpinner() {
		return spinner;
	}

	public boolean isEnable(){
		return button.getSelection();
	}
	
	public int getValue(){
		return spinner.getSelection();
	}
}
