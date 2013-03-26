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
 * Create on 2011-1-25 ����02:42:51
 *******************************************************************************/
package org.salever.android.step.store.sqlite;

import org.salever.android.step.store.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author LiXiaopeng
 * 
 */
public class SQLiteActivity extends Activity {

	public static final String DATABASE_NAME = "STEP_DATABASE";
	public static final int DATABASE_VERSION = 1;

	private SQLiteOpenHelper sqLiteOpenHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_activity);
		sqLiteOpenHelper = new DatabaseHelper(this);

		Button button = (Button) findViewById(R.id.SQLiteButtonInsertRecord);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				insertRecord();
			}
		});

		button = (Button) findViewById(R.id.SQLiteButtonSearchRecord);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				searchRecord();
			}
		});

		button = (Button) findViewById(R.id.SQLiteButtonDeleteRecord);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				deleteRecord();
			}
		});

		button = (Button) findViewById(R.id.SQLiteButtonDeleteTable);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				deleteTable();
			}
		});
		
		button = (Button) findViewById(R.id.SQLiteButtonCreateTable);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				createTable();
			}
		});
	}

	/**
	 * 
	 */
	protected void createTable() {
		SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
		String sql = "CREATE TABLE STEP_STORE_TABLE (TITLE text not null, BODY text not null);";
		Log.i("Create SQL: ", sql);
		try {
			db.execSQL(sql);
			openInfoDialog("�����ɹ�", "������ STEP_STORE_TABLE");
		} catch (SQLException e) {
			setTitle("����ʧ��");
			openInfoDialog("����ʧ��", "����ʧ�� " + e);
		}
		
	}

	/**
	 * 
	 */
	protected void deleteTable() {
		SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
		String sql = "DROP TABLE STEP_STORE_TABLE";
		try {
			Log.i("Drop SQL: ", sql);
			db.execSQL(sql);
			setTitle("ɾ���ɹ�");
			openInfoDialog("ɾ���ɹ�", "ɾ���� STEP_STORE_TABLE");
		} catch (SQLException e) {
			setTitle("ɾ��ʧ��");
			openInfoDialog("ɾ��ʧ��", "ɾ��ʧ�� " + e);
		}

	}

	/**
	 * 
	 */
	protected void deleteRecord() {
		SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

		try {
			int rows = db.delete("STEP_STORE_TABLE", "TITLE = ? ",
					new String[] { "Hello" });
			setTitle("ɾ���ɹ�");
			openInfoDialog("ɾ���ɹ�", "ɾ��  " + rows + " ��");
		} catch (SQLException e) {
			setTitle("ɾ��ʧ��");
			openInfoDialog("ɾ��ʧ��", "ɾ��ʧ�� " + e.getLocalizedMessage());
		}
	}

	/**
	 * 
	 */
	protected void searchRecord() {
		SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
		try {
			Cursor cursor = db.query("STEP_STORE_TABLE", null, null, null,
					null, null, null);
			Integer count = cursor.getCount();
			setTitle("���ҵ�  " + count + " ����¼");
			openInfoDialog("����", "���ҵ�  " + count + " ����¼");
		} catch (SQLException e) {
			Log.e("Search Failed", e.getLocalizedMessage());
			setTitle("����ʧ��");
			openInfoDialog("����ʧ��", e.getLocalizedMessage());
		}
	}

	protected void openInfoDialog(String title, String message) {
		new AlertDialog.Builder(this).setTitle(title).setMessage(message)
				.setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				}).create().show();
	}

	/**
	 * 
	 */
	protected void insertRecord() {
		SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
		String sql = "INSERT INTO STEP_STORE_TABLE VALUES('Hello', 'hello world')";
		try {
			Log.i("Insert SQL: ", sql);
			db.execSQL(sql);
			setTitle("����ɹ�");
			openInfoDialog("����ɹ�", "����ɹ�");
		} catch (SQLException e) {
			setTitle("����ʧ��");
			openInfoDialog("����ʧ��", "����ʧ��" + e.getLocalizedMessage());
		}

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
			String sql = "CREATE TABLE STEP_STORE_TABLE (TITLE text not null, BODY text not null);";
			Log.i("Create SQL: ", sql);
			try {
				db.execSQL(sql);
			} catch (SQLException e) {
				setTitle("����ʧ��");
			}
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

		}

	}
}
