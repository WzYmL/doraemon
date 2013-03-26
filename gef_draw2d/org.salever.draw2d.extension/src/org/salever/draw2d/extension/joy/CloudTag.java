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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author LiXP
 * 
 */
public class CloudTag extends Figure {

	private List<String> clouds;

	private Rectangle rec;

	public CloudTag() {
		clouds = new ArrayList<String>();
	}

	@Override
	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		this.rec = rect;
	}

	@Override
	protected void paintClientArea(Graphics graphics) {
		graphics.drawLine(rec.x, rec.y + 0, rec.x + 100, rec.y + 100);
	}
}
