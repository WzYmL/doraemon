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
package org.salever.android.step.notification;

import org.salever.android.step.MainActivity;
import org.salever.android.step.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
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
public class NotificationActivity extends Activity {

	private static final int NOTIFICATION_ID = R.layout.notification_layout;
	
	private NotificationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(NOTIFICATION_ID);

		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Button button;

		button = (Button) findViewById(R.id.sun_1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setWeather("晴空万里", "天气预报", "晴空万里", R.drawable.icon);
			}
		});

		button = (Button) findViewById(R.id.coludy_1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setWeather("阴云密布", "天气预报", "阴云密布", R.drawable.icon);
			}
		});

		button = (Button) findViewById(R.id.rain_1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setWeather("大雨连绵", "天气预报", "大雨连绵", R.drawable.icon);
			}
		});

		button = (Button) findViewById(R.id.defaultSound);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDefault(Notification.DEFAULT_SOUND);
			}
		});

		button = (Button) findViewById(R.id.defaultVibrate);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDefault(Notification.DEFAULT_VIBRATE);
			}
		});
		button = (Button) findViewById(R.id.defaultAll);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDefault(Notification.DEFAULT_ALL);
			}
		});

		button = (Button) findViewById(R.id.clear);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				manager.cancel(NOTIFICATION_ID);
			}
		});
	}

	protected void setDefault(int defaultSound) {
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		String title = "晴空万里";
		String content = "天气预报";
		Notification notification = new Notification(R.drawable.icon, content,
				System.currentTimeMillis());
		notification.setLatestEventInfo(this, title, content, contentIntent);
		notification.defaults = defaultSound;
		manager.notify(NOTIFICATION_ID, notification);
		
	}

	protected void setWeather(String ticketText, String title, String content,
			int drawable) {
		Notification notification = new Notification(drawable, ticketText,
				System.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		notification.setLatestEventInfo(this, title, content, contentIntent);
		manager.notify(NOTIFICATION_ID, notification);
	}
}
