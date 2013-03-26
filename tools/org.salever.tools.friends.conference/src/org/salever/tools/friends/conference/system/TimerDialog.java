package org.salever.tools.friends.conference.system;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

public class TimerDialog extends TitleAreaDialog {

	private int timerCount;

	private boolean running;

	private Spinner timerSpinner;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public TimerDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite timerComposite = new Composite(container, SWT.NONE);
		timerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		timerComposite.setLayout(new GridLayout(1, false));

		timerSpinner = new Spinner(timerComposite, SWT.BORDER);
		timerSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Composite composite_1 = new Composite(timerComposite, SWT.NONE);
		composite_1.setLayout(new GridLayout(3, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button startBtn = new Button(composite_1, SWT.NONE);
		startBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				start();
			}
		});
		startBtn.setText("开始");

		Button endBtn = new Button(composite_1, SWT.NONE);
		endBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				end();
			}
		});
		endBtn.setText("暂停");

		Button resetBtn = new Button(composite_1, SWT.NONE);
		resetBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reset();
			}
		});
		resetBtn.setText("归零");

		return area;
	}

	protected void reset() {
		timerCount = 0;
		timerSpinner.setSelection(timerCount);
	}

	protected void end() {
		running = false;
	}

	protected void start() {
		timerCount = timerSpinner.getSelection();
		running = true;
		new Thread(new Runnable() {

			public void run() {
				while (timerCount > 0 && running) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							timerSpinner.setSelection(timerCount--);
						}
					});
				}
			}
		}).start();
	}

	protected void verifyInput(VerifyEvent e) {
		if (!Character.isDigit(e.character)) {
			e.doit = false;
		} else {
			e.doit = true;
		}
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(522, 399);
	}

	public static void main(String[] args) {
		new TimerDialog(null).open();
	}
}
