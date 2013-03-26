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
package org.salever.android.app.diary.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author LiXiaopeng
 * 
 */
public class DiaryDbAdapter {

	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy年MM月dd日HH时mm分ss秒");

	public static final String DATABASE_NAME = "Diary";
	private static final int DATABASE_VERSION = 1;
	public static final String KEY_TITLE = "title";
	public static final String KEY_BODY = "body";
	public static final String KEY_CREATED = "created";
	public static final String TABLE_NAME = "DIARY_TABLE";

	public static final String KEY_ROW_ID = "_id";

	private SQLiteOpenHelper helper;

	private SQLiteDatabase database;

	private Context context;

	/**
	 * 
	 */
	public DiaryDbAdapter(Context context) {
		this.context = context;

	}

	public void open() {
		helper = new DatabaseHelper(context);
		database = helper.getWritableDatabase();
	}

	/**
	 * @param database
	 * 
	 */
	private void createTable(SQLiteDatabase database) {
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ROW_ID
				+ " integer primary key autoincrement, " + KEY_TITLE + " text not null, "
				+ KEY_BODY + " text not null, " + KEY_CREATED + " text);";
		Log.i("Create SQL: ", sql);
		database.execSQL(sql);

	}

	public void close() {
		helper.close();
	}

	/**
	 * 创建日记
	 * 
	 * @param title
	 * @param body
	 */
	public long createDiary(String title, String body) {
		ContentValues initValues = new ContentValues();
		initValues.put(KEY_TITLE, title);
		initValues.put(KEY_BODY, body);
		Calendar calendar = Calendar.getInstance();
		String date = format.format(calendar.getTime());
		initValues.put(KEY_CREATED, date);
		return database.insert(TABLE_NAME, null, initValues);
	}

	public boolean deleteDiary(long id) {
		return database.delete(TABLE_NAME, KEY_ROW_ID + "=" + id, null) > 0;
	}

	public Cursor getAllNotes() throws SQLException {
		return database.query(TABLE_NAME, null, null, null, null, null, null);
	}

	public Cursor getDiary(long id) throws SQLException {
		Cursor cursor = database.query(TABLE_NAME, null, KEY_ROW_ID + "=" + id,
				null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	public boolean updateDiary(long rowId, String title, String body) {
		ContentValues initValues = new ContentValues();
		initValues.put(KEY_TITLE, title);
		initValues.put(KEY_BODY, body);
		Calendar calendar = Calendar.getInstance();
		String date = format.format(calendar.getTime());
		initValues.put(KEY_CREATED, date);
		return database.update(TABLE_NAME, initValues, KEY_ROW_ID + " ="
				+ rowId, null) > 0;
	}

	private class DatabaseHelper extends SQLiteOpenHelper {

		/**
		 * @param context
		 * @param name
		 * @param factory
		 * @param version
		 */
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database
		 * .sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			createTable(db);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database
		 * .sqlite.SQLiteDatabase, int, int)
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}

	}
}
