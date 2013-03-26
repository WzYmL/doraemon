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
package org.salever.android.step.edittext;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author LiXiaopeng
 *
 */
public class EditTextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittext_layout);
		
		final EditText text = (EditText) findViewById(R.id.EditText01);
		
		Button button = (Button) findViewById(R.id.Button07);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				setTitle("Input: " + text.getText());
				
			}
			
		});
	}
}
