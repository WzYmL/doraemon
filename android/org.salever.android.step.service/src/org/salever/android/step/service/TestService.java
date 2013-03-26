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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author LiXiaopeng
 * 
 */
public class TestService extends Service {

	private static final String TAG = "TestService";

	private NotificationManager manager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, "============> TestService.onBind");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onUnbind(android.content.Intent)
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		Log.e(TAG, "============> TestService.onUnbind");
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onRebind(android.content.Intent)
	 */
	@Override
	public void onRebind(Intent intent) {
		Log.e(TAG, "============> TestService.onRebind");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		Log.e(TAG, "============> TestService.onCreate");
		manager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		showNotification();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		Log.e(TAG, "============> TestService.onStart");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		Log.e(TAG, "============> TestService.onDestroy");
	}

	/**
	 * 
	 */
	private void showNotification() {
		Notification notification = new Notification(
				android.R.drawable.bottom_bar, "Service Strat", System
						.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		notification.setLatestEventInfo(this, "Test Service", "Start Service",
				contentIntent);
		manager.notify(R.string.start_service, notification);
	}

	class LocalBinder extends Binder {
		TestService getService() {
			return TestService.this;
		}
	}

}
