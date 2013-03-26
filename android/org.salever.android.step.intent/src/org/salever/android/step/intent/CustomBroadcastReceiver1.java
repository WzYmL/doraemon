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
 * Create on 2011-2-11 ÏÂÎç01:56:16
 *******************************************************************************/
package org.salever.android.step.intent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author LiXiaopeng
 * 
 */
public class CustomBroadcastReceiver1 extends BroadcastReceiver {

	public static final int NOTIFICATION_ID = 20110211;

	private Context context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		showNotification();
	}

	/**
	 * 
	 */
	private void showNotification() {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(
				android.R.drawable.btn_default, "in CustomBroadcastReceiver1",
				System.currentTimeMillis());

		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, MainActivity.class), 0);
		notification.setLatestEventInfo(context, "in CustomBroadcastReceiver1",
				null, contentIntent);
		manager.notify(NOTIFICATION_ID, notification);
	}

}
