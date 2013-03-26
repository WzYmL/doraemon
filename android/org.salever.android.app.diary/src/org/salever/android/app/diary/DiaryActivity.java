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
 * Create on 2011-1-25 下午04:05:59
 *******************************************************************************/
package org.salever.android.app.diary;

import org.salever.android.app.diary.db.DiaryDbAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DiaryActivity extends ListActivity {

	private static final int ITEM_INSERT = 1;
	private static final int ITEM_DELETE = 2;
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;

	private static DiaryDbAdapter diaryDbAdapter;

	// private ListView diaryList;

	private Cursor diaryCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		diaryDbAdapter = new DiaryDbAdapter(this);

		try {
			diaryDbAdapter.open();
		} catch (SQLException e) {
			Utils.openInfoDialog(this, "Error", "数据库创建出错, "
					+ e.getLocalizedMessage());
		}

		// diaryList = (ListView) findViewById(android.R.id.list);
		renderListView();
	}

	/**
	 * @return the diaryDbAdapter
	 */
	public static DiaryDbAdapter getDiaryDbAdapter() {
		return diaryDbAdapter;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM_INSERT, 0, "新建一篇新日记");
		menu.add(0, ITEM_DELETE, 1, "删除一篇日记");
		menu.findItem(ITEM_INSERT);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case ITEM_INSERT:
			createDiray();
			return true;
		case ITEM_DELETE:
			diaryDbAdapter.deleteDiary(getSelectedItemId());
			renderListView();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * 需要对position和id进行一个很好的区分 position指的是点击的这个ViewItem在当前ListView中的位置
	 * 每一个和ViewItem绑定的数据，肯定都有一个id，通过这个id可以找到那条数据。
	 * 
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView,
	 *      android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Cursor currentCursor = diaryCursor;
		currentCursor.moveToPosition(position);
		Intent i = new Intent(this, DiaryEditActivity.class);
		i.putExtra(DiaryDbAdapter.KEY_ROW_ID, id);
		i.putExtra(DiaryDbAdapter.KEY_TITLE, currentCursor.getString(currentCursor
				.getColumnIndexOrThrow(DiaryDbAdapter.KEY_TITLE)));
		i.putExtra(DiaryDbAdapter.KEY_BODY,  currentCursor.getString(currentCursor
				.getColumnIndexOrThrow(DiaryDbAdapter.KEY_BODY)));
		startActivityForResult(i, ACTIVITY_EDIT);
	}

	/**
	 * 
	 */
	private void renderListView() {

		try {
			diaryCursor = diaryDbAdapter.getAllNotes();
		} catch (SQLException e) {
			Utils.openInfoDialog(this, "Error", "数据库查找出错, "
					+ e.getLocalizedMessage());
			return;
		}

		startManagingCursor(diaryCursor);
		String[] from = new String[] { DiaryDbAdapter.KEY_TITLE,
				DiaryDbAdapter.KEY_CREATED };
		int[] to = new int[] { R.id.TextViewTitle, R.id.TextViewCreated };
		try {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.list_item, diaryCursor, from, to);
			setListAdapter(adapter);
		} catch (Exception e) {
			Utils.openInfoDialog(this, "Error", "初始化场景出错, "
					+ e.getLocalizedMessage());
			return;
		}

	}

	/**
	 * 
	 */
	private void createDiray() {
		Intent i = new Intent(this, DiaryEditActivity.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		renderListView();
	}

}