package org.salever.draw2d.extension.fake;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

public class TextAreaFigure extends Label {

	private static final Image UP = createImage("icons/textarea_1.gif");

	private static final Image DOWN = createImage("icons/textarea_2.gif");

	private static Image createImage(String name) {
		InputStream stream = TextAreaFigure.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	/**
	 * 
	 */
	public TextAreaFigure() {
		LineBorder lineBorder = new LineBorder();
		lineBorder.setColor(ResourceConstants.BORDER_COLOR);
		setBorder(lineBorder);
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
		graphics.fillRectangle(FigureUtils.adjustForLineBorder(bound));
		graphics.setBackgroundColor(ColorConstants.button);
		graphics.fillRectangle(bound.x - UP.getBounds().width + bound.width,
				bound.y + 2, UP.getBounds().height, bound.height - 2);
		graphics.drawImage(UP,
				bound.x - UP.getBounds().width - 2 + bound.width, bound.y + 2);
		graphics.drawImage(DOWN, bound.x - UP.getBounds().width - 2
				+ bound.width, bound.y + bound.height - UP.getBounds().height);
	}
}
