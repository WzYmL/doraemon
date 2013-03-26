/**
 * @author LiXiaopeng
 * @created on 2010-11-24 下午09:11:46
 */
package org.salever.common.swtjface.extend.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

/**
 * 
 */
public class IntRangeField extends Composite {

	private String description;
	private Spinner startSpinner;
	private Spinner stopSpinner;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public IntRangeField(Composite parent, int style, String description) {
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
//		layout.marginWidth = 0;
//		layout.marginHeight = 0;
		setLayout(layout);
		Label desLabel = new Label(this, SWT.NONE);
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button_1.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		desLabel.setLayoutData(gd_button_1);
		desLabel.setText(description);
		startSpinner = new Spinner(this, SWT.BORDER);

		Label label = new Label(this, SWT.NONE);
		label.setText("至");

		stopSpinner = new Spinner(this, SWT.BORDER);

		startSpinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int start = startSpinner.getSelection();
				// int stop = stopSpinner.getSelection();
				stopSpinner.setMinimum(start);
				// startSpinner.setMaximum(stop);

			}
		});

		stopSpinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// int start = startSpinner.getSelection();
				int stop = stopSpinner.getSelection();
				// stopSpinner.setMinimum(start);
				startSpinner.setMaximum(stop);

			}
		});
	}

	/**
	 * @return the startSpinner
	 */
	public Spinner getStartSpinner() {
		return startSpinner;
	}

	/**
	 * @return the stopSpinner
	 */
	public Spinner getStopSpinner() {
		return stopSpinner;
	}

	public int getStartValue() {
		return startSpinner.getSelection();
	}

	public int getEndValue() {
		return stopSpinner.getSelection();
	}
}
