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
 * Create on 2011-2-11 下午01:52:07
 *******************************************************************************/
package org.salever.android.step.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	private static final int MENU_1 = Menu.FIRST;
	private static final int MENU_2 = Menu.FIRST + 1;
	private static final String ACTION_1 = "org.salever.android.step.intent.action.broadcast1";
	private static final String ACTION_2 = "org.salever.android.step.intent.action.broadcast2";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0, MENU_1, 0, "显示Notification");
    	menu.add(0, MENU_2, 0, "取消Notification");
    	menu.findItem(MENU_1);
    	return true;
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	int index = item.getItemId();
    	switch(index){
    	case MENU_1:
    		menuItem1Clicked();
    		break;
    	case MENU_2:
    		menuItem2Clicked();
    		break;
    	}
    	return true;
    }

	/**
	 * 
	 */
	private void menuItem1Clicked() {
		Intent intent = new Intent(ACTION_1);
		sendBroadcast(intent);
	}

	/**
	 * 
	 */
	private void menuItem2Clicked() {
		Intent intent = new Intent(ACTION_2);
		sendBroadcast(intent);
	}
}