/**
 * @file CustomCellEditLocator.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����07:07:13
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;
import org.salever.rcp.tools.topology_viewer.figures.TopoFigure;


/**
 * @author salever@126.com
 * 
 * @name CustomCellEditLocator
 * 
 */
public class CustomCellEditorLocator implements CellEditorLocator {

	private IFigure figure;

	public CustomCellEditorLocator(IFigure figure) {
		super();
		this.figure = figure;
	}

	@Override
	public void relocate(CellEditor celleditor) {
		// TODO Auto-generated method stub
		Text text = (Text) celleditor.getControl();
		Rectangle rect = figure.getBounds().getCopy();
		figure.translateToAbsolute(rect);
		/* text.setBounds(rect.x, rect.y, rect.width, rect.height); */
		text.setBounds(rect.x, rect.y
				+ ((TopoFigure) figure).getImage().getBounds().height,
				rect.width, 15);

	}

}
