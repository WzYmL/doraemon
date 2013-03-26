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
 * Create on 2011-2-11 ÏÂÎç02:53:43
 *******************************************************************************/
package org.salever.android.step.service;

import org.salever.android.step.service.alarm.AlarmActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TestService bindService;

	private ServiceConnection connection;

	private boolean isBinded;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTitle("Test Service");

		connection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				bindService = null;
				Toast.makeText(MainActivity.this, "Service disconnected",
						Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				bindService = ((TestService.LocalBinder) service).getService();
				Toast.makeText(MainActivity.this, "Service connected",
						Toast.LENGTH_SHORT).show();
			}
		};
		initButtons();
	}

	/**
	 * 
	 */
	private void initButtons() {
		Button button;
		button = (Button) findViewById(R.id.StartButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startService();
			}
		});

		button = (Button) findViewById(R.id.StopButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopService();
			}
		});

		button = (Button) findViewById(R.id.BindButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				bindService();
			}
		});

		button = (Button) findViewById(R.id.UnbindButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				unbindService();
			}
		});
		
		button = (Button) findViewById(R.id.ButtonStartAlarmActivity);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 */
	protected void unbindService() {
		if (isBinded) {
			unbindService(connection);
			isBinded = false;
		}

	}

	/**
	 * 
	 */
	protected void bindService() {
		Intent intent = new Intent(this, TestService.class);
		bindService(intent, connection, BIND_AUTO_CREATE);
		isBinded = true;
	}

	/**
	 * 
	 */
	protected void stopService() {
		Intent intent = new Intent(this, TestService.class);
		stopService(intent);

	}

	/**
	 * 
	 */
	protected void startService() {
		Intent intent = new Intent(this, TestService.class);
		startService(intent);

	}
}