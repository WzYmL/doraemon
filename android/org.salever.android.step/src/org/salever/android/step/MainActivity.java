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
 * Create on 2011-1-25 ÏÂÎç02:32:30
 *******************************************************************************/

package org.salever.android.step;

import org.salever.android.step.activity.ActivityActivity;
import org.salever.android.step.aotucompletetextview.AutoCompleteTextViewActivity;
import org.salever.android.step.datepicker.DatePickerActivity;
import org.salever.android.step.dialog.DialogActivity;
import org.salever.android.step.edittext.EditTextActivity;
import org.salever.android.step.gallery.GalleryActivity;
import org.salever.android.step.listview.ListViewActivity;
import org.salever.android.step.menu.MenuActivity;
import org.salever.android.step.notification.NotificationActivity;
import org.salever.android.step.progressbar.ProgressBarActivity;
import org.salever.android.step.ratingbar.RatingBarActivity;
import org.salever.android.step.scrollview.ScrollViewActivity;
import org.salever.android.step.spinner.SpinnerActivity;
import org.salever.android.step.textview.TextViewActivity;
import org.salever.android.step.toast.ToastActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button01, button02, button03, button04, button05, button06,
			button07, button08, button09, button10, button11, button12;
	private OnClickListener listener01, listener02, listener03, listener04,
			listener05, listener06, listener07, listener08, listener09,
			listener12, listener10, listener11;

	public MainActivity() {
		listener01 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				setTitle("Button test ------ hello world! 0_0");
			}
		};

		listener02 = new MainOnClickListener(MainActivity.this,
				TextViewActivity.class);

		listener03 = new MainOnClickListener(MainActivity.this,
				EditTextActivity.class);

		listener04 = new MainOnClickListener(MainActivity.this,
				SpinnerActivity.class);

		listener05 = new MainOnClickListener(MainActivity.this,
				AutoCompleteTextViewActivity.class);

		listener06 = new MainOnClickListener(MainActivity.this,
				DatePickerActivity.class);

		listener07 = new MainOnClickListener(MainActivity.this,
				ScrollViewActivity.class);

		listener08 = new MainOnClickListener(MainActivity.this,
				ProgressBarActivity.class);

		listener09 = new MainOnClickListener(MainActivity.this,
				RatingBarActivity.class);

		listener10 = new MainOnClickListener(MainActivity.this,
				GalleryActivity.class);

		listener11 = new MainOnClickListener(MainActivity.this,
				MenuActivity.class);

		listener12 = new MainOnClickListener(MainActivity.this,
				ActivityActivity.class);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button01 = (Button) findViewById(R.id.Button01);
		button01.setOnClickListener(listener01);

		button02 = (Button) findViewById(R.id.Button02);
		button02.setOnClickListener(listener02);

		button03 = (Button) findViewById(R.id.Button03);
		button03.setOnClickListener(listener03);

		button04 = (Button) findViewById(R.id.Button04);
		button04.setOnClickListener(listener04);

		button05 = (Button) findViewById(R.id.Button05);
		button05.setOnClickListener(listener05);

		button06 = (Button) findViewById(R.id.Button06);
		button06.setOnClickListener(listener06);

		button07 = (Button) findViewById(R.id.Button07);
		button07.setOnClickListener(listener07);

		button08 = (Button) findViewById(R.id.Button08);
		button08.setOnClickListener(listener08);

		button09 = (Button) findViewById(R.id.Button09);
		button09.setOnClickListener(listener09);

		button10 = (Button) findViewById(R.id.Button10);
		button10.setOnClickListener(listener10);

		button11 = (Button) findViewById(R.id.Button11);
		button11.setOnClickListener(listener11);

		button12 = (Button) findViewById(R.id.Button12);
		button12.setOnClickListener(listener12);

		findViewById(R.id.Button13).setOnClickListener(
				new MainOnClickListener(this, DialogActivity.class));
		
		findViewById(R.id.Button14).setOnClickListener(
				new MainOnClickListener(this, ToastActivity.class));
		
		findViewById(R.id.Button15).setOnClickListener(
				new MainOnClickListener(this, NotificationActivity.class));
		
		findViewById(R.id.Button16).setOnClickListener(
				new MainOnClickListener(this, ListViewActivity.class));
	}

	private static class MainOnClickListener implements OnClickListener {
		Activity packageContext;
		Class<?> cls;

		public MainOnClickListener(Activity packageContext, Class<?> cls) {
			this.packageContext = packageContext;
			this.cls = cls;
		}

		@Override
		public void onClick(View v) {
			packageContext.setTitle("Hello world ------" + cls.getName()
					+ " Test demo!");
			Intent intent = new Intent(packageContext, cls);
			packageContext.startActivity(intent);
		}

	}
}