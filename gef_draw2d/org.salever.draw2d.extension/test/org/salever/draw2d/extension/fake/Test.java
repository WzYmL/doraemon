/*******************************************************************************
 * Copyright (c) 2005-2011, Chinese Eclipse Community(CEC) All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 *  http://www.ceclipse.org
 *
 * Contributors:
 *   salever@126.com - initial API and implementation 
 *******************************************************************************/
package org.salever.draw2d.extension.fake;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.salever.draw2d.extension.fake.TabFolderFigure;
import org.salever.draw2d.extension.fake.TabItemFigure;

/**
 * TODO 此处填写 class 信息
 * 
 * @author
 * @date 2011-4-19
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		Test app = new Test(shell);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public Test(Shell shell) {
		shell.setText("Tab folder demo");
		shell.setBounds(100, 100, 500, 500);
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		FigureCanvas canvas = new FigureCanvas(shell);

		Figure root = new Figure();
		GridLayout layout = new GridLayout();
		root.setLayoutManager(layout);

		TabFolderFigure tabFolder = new TabFolderFigure();
		TabItemFigure item1 = new TabItemFigure(tabFolder, "item1");

		Figure ic1 = new Panel();
		ic1.setBackgroundColor(ColorConstants.lightBlue);
		ic1.setLayoutManager(new XYLayout());
		Label label = new Label("AAAAAAAAAAA");
		label.setBounds(new Rectangle(10, 10, 100, 50));
		label.setBorder(new LineBorder());
		ic1.add(label);
		label.setBackgroundColor(ColorConstants.red);
		item1.setContent(ic1);
		tabFolder.addItem(item1);

		item1 = new TabItemFigure(tabFolder, "item2222222221");
		ic1 = new Panel();
		ic1.setBackgroundColor(ColorConstants.lightGreen);
		Button button = new Button("Button");
		button.setBounds(new Rectangle(10, 10, 100, 20));
		ic1.add(button);
		label.setBackgroundColor(ColorConstants.red);
		item1.setContent(ic1);
		tabFolder.addItem(item1);

		item1 = new TabItemFigure(tabFolder, "item23331");
		ic1 = new Panel();
		Ellipse shape = new Ellipse();
		shape.setBounds(new Rectangle(10, 10, 300, 300));
		ic1.add(shape);
		ic1.setBackgroundColor(ColorConstants.white);
		item1.setContent(ic1);
		tabFolder.addItem(item1);

		root.add(tabFolder);

		GridData gd = new GridData(GridData.FILL_BOTH);
		layout.setConstraint(tabFolder, gd);

		canvas.setContents(root);
	}

}
