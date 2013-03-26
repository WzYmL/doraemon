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
package org.salever.android.step.aotucompletetextview;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * @author LiXiaopeng
 * 
 */
public class AutoCompleteTextViewActivity extends Activity {

	public static final String[] mCountries = { "China", "USA", "UK",
			"Germany", "France", "Italy" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auto_complete_textview);
		setTitle("AutoCompleteTextViewActivity");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_dropdown_item_1line, mCountries);
		AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView01);
		actv.setAdapter(adapter);
	}
}
