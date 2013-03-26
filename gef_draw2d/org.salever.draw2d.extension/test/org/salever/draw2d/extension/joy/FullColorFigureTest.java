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
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author LiXP
 * 
 */
public class FullColorFigureTest {

	private Shell shell;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		FullColorFigureTest cloudTag = new FullColorFigureTest(shell);

		cloudTag.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	public FullColorFigureTest(Shell newShell) {
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

		final FullClolorCacheFigure cloudTag = new FullClolorCacheFigure();
		cloudTag.setBounds(new Rectangle(0, 0, 300, 300));

		cloudTag.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseHover(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent me) {
				Image image = cloudTag.getImage();
				if (image != null) {
					ImageData imageData = image.getImageData();
					int pixel = imageData.getPixel(me.x, me.y);
					int r = pixel & 0xff;
					int g = (pixel >> 8) & 0xff;
					int b = (pixel >> 16) & 0xff;

					System.out.println("R:" + r + " G:" + g + " B:" + b);
				}
			}

		});

		root.add(cloudTag);

	}

	public Shell getShell() {
		return shell;
	}

	public void open() {
		this.shell.open();
	}

}
