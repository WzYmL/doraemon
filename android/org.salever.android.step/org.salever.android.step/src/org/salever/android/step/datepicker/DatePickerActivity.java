/**
 * 
 */
package org.salever.android.step.datepicker;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.DatePicker;

/**
 * @author LiXiaopeng
 *
 */
public class DatePickerActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.date_picker);
		setTitle("DatePickerActivity");
		
		final DatePicker dp = (DatePicker) findViewById(R.id.DatePicker01);
		dp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				setTitle("YYYY-MM-DD" + dp.getYear() + "-" + dp.getMonth() + "-" + dp.getDayOfMonth());		
			}
			
		});
		dp.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				setTitle("YYYY-MM-DD" + dp.getYear() + "-" + dp.getMonth() + "-" + dp.getDayOfMonth());		
				return true;
			}
		});
	}
}
