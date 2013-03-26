package org.salever.android.step.textview;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewActivity extends Activity{

	public TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textview_layout);
		setTitle("This is a TextView hello world ------ 0_0!");
		text = (TextView) findViewById(R.id.TextView01);
		text.setText("This is a TextView hello world ------ 0_0!");
	}
}
