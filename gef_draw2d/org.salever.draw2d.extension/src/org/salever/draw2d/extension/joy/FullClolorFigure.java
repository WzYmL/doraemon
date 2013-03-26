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
import org.eclipse.swt.graphics.RGB;

/**
 * @author LiXP
 * 
 */
public class FullClolorFigure extends Figure {

	@Override
	protected void paintClientArea(Graphics graphics) {
		float brightness = 1.0f;
		graphics.setAntialias(SWT.ON);
		drawCyclePane(graphics, brightness);
	}

	private void drawCyclePane(Graphics graphics, float brightness) {

		Rectangle rec = getBounds();
		int standardRadius = rec.width / 2;

		for (int index = standardRadius; index > 10; index = index - 1) {
			drawCycle(graphics, index, brightness);
		}
	}

	private void drawCycle(Graphics graphics, int radius, float brightness) {

		Rectangle rec = getBounds();
		int standardRadius = rec.width / 2;
		int delta = standardRadius - radius;
		float saturation = 1.0f - delta * 1.0f / standardRadius;
		Color color = null;
		for (int index = 0; index < 360; index++) {
			color = new Color(null, new RGB(index, saturation, brightness));
			graphics.setBackgroundColor(color);
			graphics.fillArc(rec.x + delta, rec.y + delta, radius * 2,
					radius * 2, index, 1);
			color.dispose();
		}
	}
}
