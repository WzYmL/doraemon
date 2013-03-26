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
 * Create on 2011-2-11 ÏÂÎç02:23:17
 *******************************************************************************/
package org.salever.android.step.intent;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author LiXiaopeng
 * 
 */
public class CustomBroadcastReceiver2 extends BroadcastReceiver {

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
		deleteNotification();
	}

	/**
	 * 
	 */
	private void deleteNotification() {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		manager.cancel(CustomBroadcastReceiver1.NOTIFICATION_ID);

	}

}
