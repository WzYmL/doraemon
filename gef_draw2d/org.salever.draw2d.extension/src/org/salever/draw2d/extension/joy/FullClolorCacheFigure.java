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
 * Create on Mar 5, 2012 5:56:00 PM
 *******************************************************************************/
package org.salever.draw2d.extension.joy;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

/**
 * @author LiXP
 * 
 */
public class FullClolorCacheFigure extends Figure {

	private Image image;

	@Override
	protected void paintClientArea(Graphics graphics) {
		float brightness = 1.0f;
		drawCyclePane(graphics, brightness);
	}

	private void drawCyclePane(Graphics graphics, float brightness) {

		Rectangle rec = getBounds();
		if (image == null) {
			image = new Image(null, rec.width, rec.height);
			GC gc = new GC(image);
			gc.setAntialias(SWT.ON);
			int standardRadius = rec.width / 2;
			for (int index = standardRadius; index > 10; index = index - 1) {
				drawCycle(gc, index, brightness);
			}
			ImageData imageData = image.getImageData();
			imageData.transparentPixel = imageData.getPixel(0, 0);
			image.dispose();
			image = new Image(null, imageData);
		}
		graphics.drawImage(image, rec.getLocation());
	}

	private void drawCycle(GC graphics, int radius, float brightness) {

		Rectangle rec = getBounds();
		int standardRadius = rec.width / 2;
		int delta = standardRadius - radius;
		float saturation = 1.0f - delta * 1.0f / standardRadius;
		Color color = null;
		for (int index = 0; index < 360; index++) {
			color = new Color(null, new RGB(index, saturation, brightness));
			graphics.setBackground(color);
			graphics.fillArc(delta, delta, radius * 2, radius * 2, index, 1);
			color.dispose();
		}
	}

	public Image getImage() {
		return image;
	}
}
