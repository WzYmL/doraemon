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
package org.salever.android.step.activity;

import org.salever.android.step.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author LiXiaopeng
 * 
 */
public class ActivityActivity extends Activity {

	private Button button01, button02;
	private OnClickListener listener01, listener02;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listener01 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ActivityActivity.this,
						OtherActivity.class);
				intent.putExtra("activity", "Data from activity activity");
				startActivityForResult(intent, 0);
			}

		};

		listener02 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTitle("This is activity 01");
				Intent intent = new Intent(ActivityActivity.this,
						AnotherActivity.class);
				startActivity(intent);

			}

		};

		setContentView(R.layout.activity_layout);
		button01 = (Button) findViewById(R.id.ActivityButton01);
		button02 = (Button) findViewById(R.id.ActivityButton02);

		button01.setOnClickListener(listener01);
		button02.setOnClickListener(listener02);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == RESULT_CANCELED) {
				setTitle("Cancle");
			} else if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				if (bundle != null) {
					String content;
					content = bundle.getString(OtherActivity.KEY);
					setTitle(content);
				}
			}
		}
	}
}
