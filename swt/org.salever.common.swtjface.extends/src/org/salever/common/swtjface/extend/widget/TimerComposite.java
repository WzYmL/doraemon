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
package org.salever.common.swtjface.extend.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Spinner;

/**
 * @author LiXP
 * 
 */
public class TimerComposite extends Composite {

	private Spinner timerSpinner;

	private int timerCount;

	private boolean running;

	public TimerComposite(Composite parent, int style) {
		super(parent, style);

		init();
	}

	private void init() {

		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.marginHeight = 0;
		this.setLayout(gridLayout);

		timerSpinner = new Spinner(this, SWT.BORDER);
		timerSpinner.setMaximum(Integer.MAX_VALUE);
		timerSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
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

}
