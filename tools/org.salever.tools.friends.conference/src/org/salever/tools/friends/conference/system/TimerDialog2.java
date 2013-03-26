package org.salever.tools.friends.conference.system;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class TimerDialog2 extends TitleAreaDialog {

	private int timerCount;

	private boolean running;

	private CLabel timerLabel;

	private static Font TIMER_FONT = SWTResourceManager.getFont(
			"Times New Roman", 18, SWT.NORMAL);

	private static Color RED_COLOR = SWTResourceManager.getColor(SWT.COLOR_RED);

	private static Color YELLOW_COLOR = SWTResourceManager
			.getColor(SWT.COLOR_YELLOW);

	private static Color GREEN_COLOR = SWTResourceManager
			.getColor(SWT.COLOR_GREEN);

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public TimerDialog2(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.RESIZE | SWT.TITLE);
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

		timerLabel = new CLabel(timerComposite, SWT.NONE);
		timerLabel.setForeground(GREEN_COLOR);
		timerLabel.setFont(TIMER_FONT);
		timerLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GridData gd_timerLabel = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_timerLabel.heightHint = 40;
		timerLabel.setLayoutData(gd_timerLabel);
		timerLabel.setText("100");

		Composite composite_1 = new Composite(timerComposite, SWT.NONE);
		composite_1.setLayout(new GridLayout(4, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnSetTimer = new Button(composite_1, SWT.NONE);
		GridData gd_btnSetTimer = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnSetTimer.widthHint = 40;
		btnSetTimer.setLayoutData(gd_btnSetTimer);
		btnSetTimer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValue();
			}
		});
		btnSetTimer.setText("Set");

		Button startBtn = new Button(composite_1, SWT.NONE);
		GridData gd_startBtn = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_startBtn.widthHint = 40;
		startBtn.setLayoutData(gd_startBtn);
		startBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				start();
			}
		});
		startBtn.setText("Start");

		Button endBtn = new Button(composite_1, SWT.NONE);
		GridData gd_endBtn = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_endBtn.widthHint = 40;
		endBtn.setLayoutData(gd_endBtn);
		endBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				end();
			}
		});
		endBtn.setText("Pause");

		Button resetBtn = new Button(composite_1, SWT.NONE);
		GridData gd_resetBtn = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_resetBtn.widthHint = 40;
		resetBtn.setLayoutData(gd_resetBtn);
		resetBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reset();
			}
		});
		resetBtn.setText("Clear");

		TimerCompositeExtend timerCompositeExtend = new TimerCompositeExtend(
				container, SWT.None);
		timerCompositeExtend.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1));
		return area;
	}

	protected void setValue() {
		InputDialog dialog = new InputDialog(timerLabel.getShell(), "New time",
				"Please input", "100", new IInputValidator() {

					@Override
					public String isValid(String newText) {

						try {
							int i = Integer.parseInt(newText);
							if (i < 1) {
								return "Please input a positive Number";
							}
						} catch (Exception e) {
							return "Please input a Number";
						}

						return null;
					}
				});
		if (dialog.open() == Window.OK) {
			String value = dialog.getValue();
			if (value != null) {
				timerCount = Integer.parseInt(value);
				refreshTimerLabel();
			}
		}

	}

	protected void reset() {
		timerCount = 0;
		timerLabel.setText(timerCount + "");
	}

	protected void end() {
		running = false;
	}

	protected void start() {
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
							refreshTimerLabel();
						}
					});
				}

				if (timerCount == 0) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							MessageDialog.openInformation(getShell(), "Note",
									"TIME IS EXSAULTED!");
						}
					});

				}
			}
		}).start();
	}

	protected void refreshTimerLabel() {
		if (timerCount < 10) {
			timerLabel.setForeground(RED_COLOR);
			timerLabel.setText(timerCount + "");
			timerCount--;
		} else if (timerCount < 20) {
			timerLabel.setForeground(YELLOW_COLOR);
			timerLabel.setText(timerCount + "");
			timerCount--;
		} else {
			timerLabel.setForeground(GREEN_COLOR);
			timerLabel.setText(timerCount + "");
			timerCount--;
		}

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
		new TimerDialog2(null).open();
	}
}
