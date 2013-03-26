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
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author LiXP
 * 
 */
public class SingleColorCycleTest {

	private Shell shell;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		SingleColorCycleTest cloudTag = new SingleColorCycleTest(shell);

		cloudTag.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	public SingleColorCycleTest(Shell newShell) {
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

		// ClolorfulCycleFigure cloudTag = new ClolorfulCycleFigure();
		// cloudTag.setBounds(new Rectangle(0, 0, 300, 300));
		// root.add(cloudTag);

		SingleClolorCacheFigure cloudTag1 = new SingleClolorCacheFigure();
		cloudTag1.setBounds(new Rectangle(300, 300, 300, 300));
		root.add(cloudTag1);

		cloudTag1.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent me) {
				Point location = me.getLocation();
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClicked(MouseEvent me) {
				// TODO Auto-generated method stub

			}

		});

	}

	public Shell getShell() {
		return shell;
	}

	public void open() {
		this.shell.open();
	}

}
