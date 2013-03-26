package org.salever.draw2d.extension.fake;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.SimpleRaisedBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class ButtonFigure extends Label {

	public ButtonFigure() {
		this("");
	}

	public ButtonFigure(String text) {
		super(text);
		// LineBorder lineBorder = new LineBorder();
		// lineBorder.setColor(ResourceConstants.BORDER_COLOR);
		// Choose which style is better, flat or 3D
		SimpleRaisedBorder border = new SimpleRaisedBorder();
		setBorder(border);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Label#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected void paintFigure(Graphics graphics) {
		Dimension size = FigureUtilities.getTextExtents(getText(),
				graphics.getFont());
		Rectangle bounds = getBounds();
		graphics.drawText(getText(), bounds.x + 3, bounds.y
				+ (bounds.height - size.height) / 2);
	}
}