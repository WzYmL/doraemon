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
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class CheckedTextField extends Composite {

	private Text text;
	private String description;
	private Button button;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedTextField(Composite parent, int style, String description) {
		super(parent, style);
		this.description = description;
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
//		gl_composite.marginHeight = 0;
		this.setLayout(gl_composite);
		
		button = new Button(this, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		button.setLayoutData(gd_button);
		button.setText(description);
		
		text = new Text(this, SWT.BORDER);
		GridData gd_text = new GridData(GridData.FILL_BOTH);
		gd_text.widthHint = 120;
		text.setLayoutData(gd_text);
		
	}

	/**
	 * @return the text
	 */
	public Text getText() {
		return text;
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
		return text.getText();
	}

}
