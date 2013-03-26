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
 * Create on 2011-1-30 ÏÂÎç02:00:27
 *******************************************************************************/
package org.salever.android.step.store.contentprovider;

import android.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Contacts.Phones;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * @author LiXiaopeng
 * 
 */
public class ContentProviderActivity extends ListActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_list_item);
		Cursor cursor = getContentResolver().query(
				Phones.CONTENT_URI, null, null, null, null);
		startManagingCursor(cursor);

		ListAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor, new String[]{Phones.NAME, Phones.NUMBER},
				new int[] {android.R.id.text1, android.R.id.text2});
		setListAdapter(adapter);
	}
}
