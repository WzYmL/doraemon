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
 * Create on 2011-1-25 ÏÂÎç02:32:30
 *******************************************************************************/
package org.salever.android.step.scrollview;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author LiXiaopeng
 * 
 */
public class ScrollViewActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_view_layout);
		setTitle("ScrollViewActivity");
	}
}
