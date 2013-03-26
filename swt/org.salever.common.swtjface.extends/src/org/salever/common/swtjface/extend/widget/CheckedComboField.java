/**
 * @author LiXiaopeng
 * @created on 2010-11-24 下午09:11:46
 */
package org.salever.common.swtjface.extend.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */
public class CheckedComboField extends Composite {

	private Combo combo;
	private String description;
	private Button button;
	private String[] items;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedComboField(Composite parent, int style, String description,
			String[] items) {
		super(parent, style);
		this.description = description;
		this.items = items;
		init();
	}

	/**
	 * 初始化面板
	 */
	private void init() {

//		GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false,
//				1, 1);
//		gd.widthHint = 230;
//		setLayoutData(gd);
		
		GridLayout gl_composite = new GridLayout(2, false);
//		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		this.setLayout(gl_composite);

		button = new Button(this, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button.widthHint =  GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		button.setLayoutData(gd_button);
		button.setText(description);

		combo = new Combo(this, SWT.BORDER|SWT.READ_ONLY);
		GridData gd_text = new GridData(GridData.FILL_BOTH);
//		gd_text.widthHint = 120;
		combo.setLayoutData(gd_text);
		for (String item : items) {
			combo.add(item);
		}

	}

	
	
	/**
	 * @return the combo
	 */
	public Combo getCombo() {
		return combo;
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	public boolean isEnable(){
		return button.getSelection();
	}
	
	public String getValue(){
		return combo.getText();
	}
}
