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
package org.salever.android.app.diary;

import org.salever.android.app.diary.db.DiaryDbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class DiaryEditActivity extends Activity {

	private static final int ITEM1 = 1;
	private static final int ITEM2 = 2;

	private EditText cTitle, cBody;

	private Long rowId;

	private DiaryDbAdapter diaryDbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_edit_activity);

		diaryDbAdapter = new DiaryDbAdapter(this);
		diaryDbAdapter.open();

		cTitle = (EditText) findViewById(R.id.EditTextTitle);
		cBody = (EditText) findViewById(R.id.EditTextBody);

		// 每一个intent都会带一个Bundle型的extras数据。
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String title = extras.getString(DiaryDbAdapter.KEY_TITLE);
			String body = extras.getString(DiaryDbAdapter.KEY_BODY);
			rowId = extras.getLong(DiaryDbAdapter.KEY_ROW_ID);

			if (title != null) {
				cTitle.setText(title);
			}
			if (body != null) {
				cBody.setText(body);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM1, 0, "保存");
		menu.add(0, ITEM2, 1, "取消");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {
		case ITEM1:
			saveDiary();
			break;
		case ITEM2:
			Intent intent = new Intent();
			setResult(RESULT_CANCELED, intent);
			finish();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * 
	 */
	private void saveDiary() {
		String title = cTitle.getText().toString();
		String body = cBody.getText().toString();
		if (rowId != null) {
			diaryDbAdapter.updateDiary(rowId, title, body);
		} else {
			diaryDbAdapter.createDiary(title, body);
		}
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();

	}
}
