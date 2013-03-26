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
 * Create on 2011-1-25 œ¬ŒÁ02:32:30
 *******************************************************************************/
package org.salever.android.step.menu;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * @author LiXiaopeng
 * 
 */
public class MenuActivity extends Activity {

	private static final int ITEM1 = 0;
	private static final int ITEM2 = 1;
	private Button button01;
	private Button button02;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);

		button01 = (Button) findViewById(R.id.MenuButton01);
		button02 = (Button) findViewById(R.id.MenuButton02);
		button01.setVisibility(View.INVISIBLE);
		button02.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM1, 0, "œ‘ æButton01");
		menu.add(0, ITEM2, 1, "œ‘ æButton02");
		menu.findItem(ITEM1);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case ITEM1:
			button01.setVisibility(View.VISIBLE);
			button02.setVisibility(View.INVISIBLE);
			break;
		case ITEM2:
			button01.setVisibility(View.INVISIBLE);
			button02.setVisibility(View.VISIBLE);
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
