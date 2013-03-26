package org.salever.draw2d.extension.fake;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class TextFieldFigure extends Label {

	public TextFieldFigure() {
		this("");
	}

	public TextFieldFigure(String text) {
		super(text);
		setMinimumSize(new Dimension(FigureConstants.COMBOBOX_DEFAULT_WIDTH,
				FigureConstants.COMBOBOX_FIXED_HEIGHT));
		LineBorder lineBorder = new LineBorder();
		lineBorder.setColor(ResourceConstants.BORDER_COLOR);
		setBorder(lineBorder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.draw2d.Figure#setBounds(org.eclipse.draw2d.geometry.Rectangle
	 * )
	 */
	@Override
	public void setBounds(Rectangle rect) {
		rect.height = FigureConstants.COMBOBOX_FIXED_HEIGHT;
		super.setBounds(rect);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Label#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		Rectangle bound = getBounds();
		graphics.setBackgroundColor(ColorConstants.white);
		graphics.fillRectangle(bound.x, bound.y, bound.width, bound.height);
		graphics.drawText(getText(), bound.x + 2, bound.y + 4);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Label#setText(java.lang.String)
	 */
	@Override
	public void setText(String s) {
		super.setText(s);
		repaint();
	}
}