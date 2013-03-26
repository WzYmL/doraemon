/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-29 下午04:24:51
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class FigureTest {

	/**
	 * @param args
	 */

	public static void main(String args[]) {
		Display d = new Display();
		final Shell shell = new Shell(d);
		shell.setSize(1000, 600);
		LightweightSystem lws = new LightweightSystem(shell);
		Figure contents = new Figure();
		XYLayout contentsLayout = new XYLayout();
		contents.setLayoutManager(contentsLayout);

		Label l = new Label("I am a bubble message!");
		l.setLocation(new Point(400, 400));
		contents.add(l, new Rectangle(400, 300, 200, 12));

		
		lws.setContents(contents);
		shell.open();
		while (!shell.isDisposed())
			while (!d.readAndDispatch())
				d.sleep();
	}

}
