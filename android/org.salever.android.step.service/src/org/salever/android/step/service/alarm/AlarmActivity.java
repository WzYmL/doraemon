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
 * Create on 2011-2-12 ÉÏÎç10:34:59
 *******************************************************************************/
package org.salever.android.step.service.alarm;

import java.util.Calendar;

import org.salever.android.step.service.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author LiXiaopeng
 * 
 */
public class AlarmActivity extends Activity {

	private static AlarmActivity instance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.alarm_activity);

		Button button = (Button) findViewById(R.id.ButtonStartAlarm);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startAlarmService();
			}
		});

		button = (Button) findViewById(R.id.ButtonStopAlarm);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopAlarmService();
			}
		});
	}

	public static AlarmActivity getInstance() {
		return instance;
	}

	/**
	 * 
	 */
	protected void stopAlarmService() {
		Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
		PendingIntent contentIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 5);

		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		manager.cancel(contentIntent);
		finish();

	}

	/**
	 * 
	 */
	protected void startAlarmService() {
		setTitle("Wait Alarm.....Alarm in 5 sencond");
		Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
		PendingIntent contentIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, 5);

		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
				contentIntent);

	}

}
