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

package org.salever.android.step.store;

import org.salever.android.step.store.contentprovider.ContentProviderActivity;
import org.salever.android.step.store.sharedpreference.SharedPreferenceActivity;
import org.salever.android.step.store.sqlite.SQLiteActivity;
import org.salever.android.step.store.web.WebServiceActivity;

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
public class MainActivity extends Activity {

	private static class MainOnClickListener implements OnClickListener {
		Activity packageContext;
		Class<?> cls;

		public MainOnClickListener(Activity packageContext, Class<?> cls) {
			this.packageContext = packageContext;
			this.cls = cls;
		}

		@Override
		public void onClick(View v) {
			packageContext.setTitle("Hello world ------" + cls.getName()
					+ " Test demo!");
			Intent intent = new Intent(packageContext, cls);
			packageContext.startActivity(intent);
		}

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button;

		button = (Button) findViewById(R.id.Button01);
		button.setOnClickListener(new MainOnClickListener(this,
				SharedPreferenceActivity.class));

		button = (Button) findViewById(R.id.Button02);
		button.setOnClickListener(new MainOnClickListener(this,
				SQLiteActivity.class));

		button = (Button) findViewById(R.id.Button03);
		button.setOnClickListener(new MainOnClickListener(this,
				ContentProviderActivity.class));

		button = (Button) findViewById(R.id.Button04);
		button.setOnClickListener(new MainOnClickListener(this,
				WebServiceActivity.class));
	}
}