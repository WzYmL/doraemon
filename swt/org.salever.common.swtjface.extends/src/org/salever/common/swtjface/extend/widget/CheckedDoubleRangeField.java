/**
 * @author LiXiaopeng
 * @created on 2010-11-24 下午09:11:46
 */
package org.salever.common.swtjface.extend.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class CheckedDoubleRangeField extends Composite {

	private String description;
	private Button button;
	private Text startText;
	private Text stopText;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedDoubleRangeField(Composite parent, int style,
			String description) {
		super(parent, style);
		this.description = description;
		init();
	}

	/**
	 * 初始化面板
	 */
	private void init() {

		// GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		// gd.widthHint = 230;
		// setLayoutData(gd);

		GridLayout layout = new GridLayout(4, false);
		// layout.marginWidth = 0;
		// layout.marginHeight = 0;
		setLayout(layout);

		button = new Button(this, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		button.setLayoutData(gd_button);
		button.setText(description);

		startText = new Text(this, SWT.BORDER);

		Label label = new Label(this, SWT.NONE);
		label.setText("至");

		stopText = new Text(this, SWT.BORDER);

		startText.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				validate(arg0);
			}
		});

		stopText.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				validate(arg0);
			}
		});
	}

	protected void validate(VerifyEvent arg0) {
		String text = arg0.text;
		if (isDouble(text)) {
			arg0.doit = true;
		} else {
			arg0.doit = false;
		}
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @return the startText
	 */
	public Text getStartText() {
		return startText;
	}

	/**
	 * @return the stopText
	 */
	public Text getEndText() {
		return stopText;
	}

	public double getStartDouble() {
		return stingToDouble(startText.getText());
	}

	public double getEndDouble() {
		return stingToDouble(stopText.getText());
	}

	public boolean isEnable() {
		return button.getSelection();
	}

	public static Double stingToDouble(String text) {
		try {
			return Double.parseDouble(text);
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
