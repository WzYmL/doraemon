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
 * Create on 2011-1-28 ÏÂÎç01:32:47
 *******************************************************************************/
package org.salever.android.step.intent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author LiXiaopeng
 *
 */
public class Utils {

	public static void openInfoDialog(Context context, String title, String message) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(message)
				.setNegativeButton("È·¶¨", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				}).create().show();
	}
}
