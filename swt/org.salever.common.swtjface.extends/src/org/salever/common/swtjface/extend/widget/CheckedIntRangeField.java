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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

/**
 * 
 */
public class CheckedIntRangeField extends Composite {

	private String description;
	private Button button;
	private Spinner startSpinner;
	private Spinner stopSpinner;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedIntRangeField(Composite parent, int style, String description) {
		super(parent, style);
		this.description = description;
		init();
	}

	/**
	 * 初始化面板
	 */
	private void init() {

//		GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd.widthHint = 230;
//		setLayoutData(gd);

		GridLayout layout = new GridLayout(4, false);
//		layout.marginWidth = 0;
//		layout.marginHeight = 0;
		setLayout(layout);

		button = new Button(this, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		button.setLayoutData(gd_button);
		button.setText(description);

		startSpinner = new Spinner(this, SWT.BORDER);

		Label label = new Label(this, SWT.NONE);
		label.setText("至");

		stopSpinner = new Spinner(this, SWT.BORDER);

		startSpinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int start = startSpinner.getSelection();
//				int stop = stopSpinner.getSelection();
				stopSpinner.setMinimum(start);
//				startSpinner.setMaximum(stop);

			}
		});

		stopSpinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				int start = startSpinner.getSelection();
				int stop = stopSpinner.getSelection();
//				stopSpinner.setMinimum(start);
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

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	public boolean isEnable(){
		return button.getSelection();
	}
	
	public int getStartValue(){
		return startSpinner.getSelection();
	}
	
	public int getEndValue(){
		return stopSpinner.getSelection();
	}
}
