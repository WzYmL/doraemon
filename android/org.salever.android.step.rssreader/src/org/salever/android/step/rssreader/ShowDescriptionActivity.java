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
 * Create on 2011-2-18 下午03:18:35
 *******************************************************************************/
package org.salever.android.step.rssreader;

import org.salever.android.step.rssreader.model.RSSItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author LiXiaopeng
 * 
 */
public class ShowDescriptionActivity extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_description_activity);

		Intent intent = getIntent();
		String content = "";
		if (intent == null) {
			Utils.openInfoDialog(this, "提示", "不好意思 出错啦");
		} else {
			Bundle bundle = intent.getBundleExtra("org.salever.android.step.rssreader.model.RSSItem");
			if (bundle == null) {
				Utils.openInfoDialog(this, "提示", "不好意思 出错啦");
				return;
			}
			content = bundle.getString(RSSItem.TITLE) + "\n\n"
					+ bundle.getString(RSSItem.PUBLISH_DATE) + "\n\n"
					+ bundle.getString(RSSItem.DESCRIPTION)
					+ "\n\n详细请访问以下地址：\n" + bundle.getString(RSSItem.LINK);
		}

		TextView text = (TextView) findViewById(R.id.TextViewDesc);
		text.setText(content);

		Button button = (Button) findViewById(R.id.ButtonBack);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
