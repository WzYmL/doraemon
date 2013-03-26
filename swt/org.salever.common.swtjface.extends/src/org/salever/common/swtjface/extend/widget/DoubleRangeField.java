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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.salever.common.swtjface.extend.widget.util.NumberUtil;

/**
 * 
 */
public class DoubleRangeField extends Composite {

	private String description;
	private Text startText;
	private Text stopText;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public DoubleRangeField(Composite parent, int style, String description) {
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

		Label desLabel = new Label(this, SWT.NONE);
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button_1.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		desLabel.setLayoutData(gd_button_1);
		desLabel.setText(description);

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

		if (arg0.character == SWT.BS) {
			arg0.doit = true;
			return;
		}

		String text = arg0.text;
		if (NumberUtil.isDouble(text)) {
			arg0.doit = true;
		} else {
			arg0.doit = false;
		}
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
		return NumberUtil.stringToDouble(startText.getText());
	}

	public double getEndDouble() {
		return NumberUtil.stringToDouble(stopText.getText());
	}

}
