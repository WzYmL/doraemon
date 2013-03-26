/**
 * 
 */
package org.salever.android.step.activity;

import org.salever.android.step.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author LiXiaopeng
 * 
 */
public class OtherActivity extends Activity {

	public static final String KEY = "store";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_other_layout);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			Object data = bundle.get("activity");
			setTitle("Now this is other activity: " + data);
		}
		findViewById(R.id.OtherButton01).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString(KEY, "Data from other activity");
						Intent intent = new Intent();
						intent.putExtras(bundle);
						setResult(RESULT_OK, intent);
						finish();

					}

				});
	}
}
