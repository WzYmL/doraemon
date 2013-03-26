/**
 * @author LiXiaopeng
 * @created on 2010-11-24 下午09:11:46
 */
package org.salever.common.swtjface.extend.widget;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

/**
 * 
 */
public class CheckedDateRangeField extends Composite {

	private String description;
	private Button button;
	private DateTime startDate;
	private DateTime endDate;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedDateRangeField(Composite parent, int style, String description) {
		super(parent, style);
		this.description = description;
		init();
	}

	/**
	 * 初始化面板
	 */
	private void init() {
		// GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false,
		// false, 2, 1);
		// gd_composite_1.widthHint = 460;
		// setLayoutData(gd_composite_1);
		GridLayout gridLayout = new GridLayout(4, false);
//		gridLayout.marginHeight = 0;
//		gridLayout.marginWidth = 0;
		setLayout(gridLayout);

		button = new Button(this, SWT.CHECK);
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button_1.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		button.setLayoutData(gd_button_1);
		button.setText(description);

		startDate = new DateTime(this, SWT.BORDER);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("至");

		endDate = new DateTime(this, SWT.BORDER);

	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @return the startDate
	 */
	public DateTime getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public DateTime getEndDate() {
		return endDate;
	}

	/**
	 * 获得起始时间
	 * @return
	 */
	public Date getStartDateTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(startDate.getYear(), startDate.getMonth(),
				startDate.getDay(), 0, 0, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获得终止时间
	 * @return
	 */
	public Date getEndDateTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(endDate.getYear(), endDate.getMonth(),
				endDate.getDay(), 23, 59, 59);
		return calendar.getTime();
	}

	public boolean isEnable(){
		return button.getSelection();
	}
}
