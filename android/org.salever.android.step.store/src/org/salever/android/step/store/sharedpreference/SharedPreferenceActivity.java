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
 * Create on 2011-1-25 下午02:32:30
 *******************************************************************************/

package org.salever.android.step.store.sharedpreference;

import org.salever.android.step.store.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * SharedPreference 示例
 * 
 * @author 
 * 
 */
public class SharedPreferenceActivity extends Activity {

	private static final String SETTING_INFOS = "setting_info";
	private static final String NAME = "Name";
	private static final String PASSWORD = "Password";
	private EditText cName, cPassword;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_preference_activity);

		cName = (EditText) findViewById(R.id.SPNameEditText);
		cPassword = (EditText) findViewById(R.id.SPPasswordEditText);

		SharedPreferences sharedPreferences = getSharedPreferences(
				SETTING_INFOS, 0);
		String name = sharedPreferences.getString(NAME, "");
		String password = sharedPreferences.getString(PASSWORD, "");

		cName.setText(name);
		cPassword.setText(password);

		Button button = (Button) findViewById(R.id.SPButton01);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});
	}

	protected void save() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				SETTING_INFOS, 0);
		// 记得对自己commit
		sharedPreferences.edit().putString(NAME, cName.getText().toString())
				.commit();
		sharedPreferences.edit().putString(PASSWORD,
				cPassword.getText().toString()).commit();

	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
