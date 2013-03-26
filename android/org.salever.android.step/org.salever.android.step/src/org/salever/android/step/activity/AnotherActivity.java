/**
 * 
 */
package org.salever.android.step.activity;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author LiXiaopeng
 *
 */
public class AnotherActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_another_layout);
		Button button01 = (Button) findViewById(R.id.AnotherButton01);
		button01.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		setTitle("Now this is acticity 02");
	}
}
