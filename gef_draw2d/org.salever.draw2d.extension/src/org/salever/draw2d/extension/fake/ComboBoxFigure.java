package org.salever.draw2d.extension.fake;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

public class ComboBoxFigure extends Label {

	private static final Image IMAGE = createImage("icons/combobox.gif");

	private static Image createImage(String name) {
		InputStream stream = ComboBoxFigure.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	public ComboBoxFigure() {
		this("");
	}

	public ComboBoxFigure(String text) {
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
		graphics.drawImage(IMAGE, bound.x + bound.width
				- IMAGE.getBounds().width, bound.y + 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Label#setText(java.lang.String)
	 */
	public void setText(String s) {
		super.setText(s);
		repaint();
	}
}
