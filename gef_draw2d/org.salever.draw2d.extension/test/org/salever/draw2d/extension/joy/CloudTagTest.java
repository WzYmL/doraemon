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
 * Create on Mar 5, 2012 5:27:44 PM
 *******************************************************************************/
package org.salever.draw2d.extension.joy;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author LiXP
 * 
 */
public class CloudTagTest {

	private Shell shell;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		CloudTagTest cloudTag = new CloudTagTest(shell);

		cloudTag.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	public CloudTagTest(Shell newShell) {
		this.shell = newShell;
		shell.setText("Cloud Tag");

		initShell();

	}

	private void initShell() {
		shell.setBounds(0, 0, 1000, 800);
		shell.setLayout(new FillLayout(SWT.VERTICAL));

		FigureCanvas canvas = new FigureCanvas(shell);
		Figure root = new Figure();
		root.setLayoutManager(new XYLayout());
		canvas.setContents(root);

		CloudTag cloudTag = new CloudTag();
		cloudTag.setBounds(new Rectangle(0, 0, 800, 800));

		cloudTag.setBorder(new LineBorder());

		root.add(cloudTag);

	}

	public Shell getShell() {
		return shell;
	}

	public void open() {
		this.shell.open();
	}

}
