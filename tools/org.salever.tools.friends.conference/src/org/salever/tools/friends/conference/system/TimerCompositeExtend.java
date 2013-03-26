/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on May 31, 2012 3:01:16 PM
 *******************************************************************************/
package org.salever.tools.friends.conference.system;

import java.text.DecimalFormat;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * @author LiXP
 * 
 */
public class TimerCompositeExtend extends Composite {

	private int timerCount;

	private int curentTimeCount;

	private boolean running;

	private CLabel timerLabel;

	private static Font TIMER_FONT = SWTResourceManager.getFont(
			"Times New Roman", 25, SWT.BOLD);

	private static Color RED_COLOR = SWTResourceManager.getColor(SWT.COLOR_RED);

	private static Color YELLOW_COLOR = SWTResourceManager
			.getColor(SWT.COLOR_YELLOW);

	private static Color GREEN_COLOR = SWTResourceManager
			.getColor(SWT.COLOR_GREEN);

	public TimerCompositeExtend(Composite parent, int style) {
		super(parent, style);

		init();
	}

	protected void end() {
		running = false;
	}

	private void init() {

		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.marginHeight = 0;
		this.setLayout(gridLayout);

		timerLabel = new CLabel(this, SWT.CENTER);
		timerLabel.setForeground(GREEN_COLOR);
		timerLabel.setFont(TIMER_FONT);
		timerLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GridData gd_timerLabel = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_timerLabel.heightHint = 40;
		timerLabel.setLayoutData(gd_timerLabel);
		timerLabel.setText("00:00:00");

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(new GridLayout(4, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnSetTimer = new Button(composite_1, SWT.NONE);

		GridData gd_btnSetTimer = new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1);
		gd_btnSetTimer.widthHint = 65;
		btnSetTimer.setLayoutData(gd_btnSetTimer);
		btnSetTimer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValue();
			}
		});
		btnSetTimer.setText("Set");

		Button startBtn = new Button(composite_1, SWT.NONE);
		GridData gd_startBtn = new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1);
		gd_startBtn.widthHint = 65;
		startBtn.setLayoutData(gd_startBtn);
		startBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				start();
			}
		});
		startBtn.setText("Start");

		Button endBtn = new Button(composite_1, SWT.NONE);
		GridData gd_endBtn = new GridData(SWT.CENTER, SWT.CENTER, true, false,
				1, 1);
		gd_endBtn.widthHint = 65;
		endBtn.setLayoutData(gd_endBtn);
		endBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				end();
			}
		});
		endBtn.setText("Pause");

		Button resetBtn = new Button(composite_1, SWT.NONE);
		GridData gd_resetBtn = new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1);
		gd_resetBtn.widthHint = 65;
		resetBtn.setLayoutData(gd_resetBtn);
		resetBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reset();
			}
		});
		resetBtn.setText("Restart");

		btnSetTimer.setFont(SWTResourceManager.getFont("华文细黑", 11, SWT.NORMAL));
		startBtn.setFont(SWTResourceManager.getFont("华文细黑", 11, SWT.NORMAL));
		endBtn.setFont(SWTResourceManager.getFont("华文细黑", 11, SWT.NORMAL));
		resetBtn.setFont(SWTResourceManager.getFont("华文细黑", 11, SWT.NORMAL));
	}

	protected void refreshTimerLabel() {

		if (this.isDisposed()) {
			running = false;
			return;
		}

		if (timerCount < 10) {
			timerLabel.setForeground(RED_COLOR);
		} else if (timerCount < 20) {
			timerLabel.setForeground(YELLOW_COLOR);
		} else {
			timerLabel.setForeground(GREEN_COLOR);
		}

		showTimerLabel();

	}

	protected void reset() {
		timerCount = curentTimeCount;
		showTimerLabel();
		start();
	}

	protected void setValue() {
		InputDialog dialog = new InputDialog(timerLabel.getShell(), "New time",
				"Please input", "60", new IInputValidator() {

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
				setCurentTimeCount(timerCount);
				refreshTimerLabel();
			}
		}

	}

	private void showTimerLabel() {
		int hours = timerCount / 3600;
		int minutes = (timerCount - hours * 3600) / 60;
		int seconds = timerCount - hours * 3600 - minutes * 60;

		if (seconds < 0) {
			seconds = 0;
		}

		DecimalFormat format = new DecimalFormat("00");
		timerLabel.setText(format.format(hours) + ":" + format.format(minutes)
				+ ":" + format.format(seconds));
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
					timerCount--;
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							refreshTimerLabel();
						}
					});
				}

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}

				if (timerCount == 0) {
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							// refreshTimerLabel();
							MessageDialog.openInformation(getShell(), "Note",
									"TIME IS EXSAULTED!");
						}
					});

				}
			}
		}).start();
	}

	/**
	 * @param curentTimeCount
	 *            the curentTimeCount to set
	 */
	public void setCurentTimeCount(int curentTimeCount) {
		this.curentTimeCount = curentTimeCount;
	}

	/**
	 * @return the curentTimeCount
	 */
	public int getCurentTimeCount() {
		return curentTimeCount;
	}

}
