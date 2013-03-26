/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.salever.swtjface.demo.processbar;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ProgressBars {

	/**
	 * Open a progress monitor dialog and switch the blocking.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		final Display display = new Display();
		Shell shell = new Shell(display);

		final ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);

		try {
			dialog.run(true, false, new IRunnableWithProgress() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.eclipse.jface.operation.IRunnableWithProgress#run(org
				 * .eclipse.core.runtime.IProgressMonitor)
				 */
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					IProgressMonitorWithBlocking blocking = (IProgressMonitorWithBlocking) monitor;

					blocking.beginTask("Job......", IProgressMonitor.UNKNOWN);

					work();

					blocking.done();
					display.syncExec(new Runnable() {
						@Override
						public void run() {
							MessageDialog.openInformation(display
									.getActiveShell(), "OK", "Job 	OK.");

						}

					});

				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			MessageDialog.openError(null, "Error", "Job failed.");
			e.printStackTrace();
		}

		if (dialog.open() == Window.OK) {
			System.out.println("OK");
		} else {
			System.out.println("Cancel");
		}

		display.dispose();
	}

	protected static void work() {
		int count = 0;
		while (count < 3) {
			count++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
