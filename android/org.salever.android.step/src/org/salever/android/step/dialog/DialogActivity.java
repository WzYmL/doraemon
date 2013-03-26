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
package org.salever.android.step.dialog;

import java.util.ArrayList;
import java.util.List;

import org.salever.android.step.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author LiXiaopeng
 * 
 */
public class DialogActivity extends Activity {

	private List<Button> buttons = new ArrayList<Button>();
	private List<OnClickListener> listeners = new ArrayList<OnClickListener>();
	private int mProgress;
	protected ProgressDialog mProgressDialog;
	protected int MAX_PROGRESS = 100;

	private Handler mProgressHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (mProgress >= MAX_PROGRESS) {
				mProgressDialog.dismiss();
			} else {
				mProgress++;
				mProgressDialog.incrementProgressBy(1);
				mProgressHandler.sendEmptyMessageDelayed(0, 100);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_layout);
		setTitle("Dialog Activity");
		buttons.add((Button) findViewById(R.id.DialogButton01));
		buttons.add((Button) findViewById(R.id.DialogButton02));
		buttons.add((Button) findViewById(R.id.DialogButton03));
		buttons.add((Button) findViewById(R.id.DialogButton04));

		findViewById(R.id.DialogButton01).setOnClickListener(
				new DialogObLickListener(R.id.DialogButton01, this));

		findViewById(R.id.DialogButton02).setOnClickListener(
				new DialogObLickListener(R.id.DialogButton02, this));

		findViewById(R.id.DialogButton03).setOnClickListener(
				new DialogObLickListener(R.id.DialogButton03, this));

		findViewById(R.id.DialogButton04).setOnClickListener(
				new DialogObLickListener(R.id.DialogButton04, this));
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case R.id.DialogButton01:
			return buildDialog1(this);
		case R.id.DialogButton02:
			return buildDialog2(this);
		case R.id.DialogButton03:
			return buildDialog3(this);
		case R.id.DialogButton04:
			return buildDialog4(this);
		}

		return super.onCreateDialog(id);
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		if (id == R.id.DialogButton01) {
			setTitle("测试dialog1");
		}
		super.onPrepareDialog(id, dialog);
	}

	private Dialog buildDialog4(DialogActivity dialogActivity) {
		mProgressDialog = new ProgressDialog(dialogActivity);
		mProgressDialog.setTitle("进度条");
		mProgressDialog.setMessage("请稍后");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgress(0);
		mProgress = 0;
		mProgressDialog.setProgress(0);
		mProgressHandler.sendEmptyMessage(0);
		return mProgressDialog;
	}

	private Dialog buildDialog3(DialogActivity dialogActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	private Dialog buildDialog2(DialogActivity dialogActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	private Dialog buildDialog1(DialogActivity dialogActivity) {

		AlertDialog.Builder builder = new AlertDialog.Builder(dialogActivity);
		builder.setIcon(R.drawable.icon);
		builder.setTitle("Alert dialog 1");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("点击确定按钮");

			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				setTitle("点击取消按钮");

			}
		});
		return builder.create();
	}

	static class DialogObLickListener implements OnClickListener {

		private int dialogId;
		private Activity activity;

		/**
		 * @param dialogId
		 * @param activity
		 */
		public DialogObLickListener(int dialogId, Activity activity) {
			this.dialogId = dialogId;
			this.activity = activity;
		}

		@Override
		public void onClick(View v) {
			activity.showDialog(dialogId);
		}
	}
}
