/**
 * 
 */
package org.salever.android.step.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * @author LiXiaopeng
 * 
 */
public class ProgressBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ly = new LinearLayout(this);
		LinearLayout.LayoutParams paramsMain = new LinearLayout.LayoutParams(
				200, 10);
		paramsMain.topMargin = 10;
		paramsMain.leftMargin = 10;
		ProgressBar pb = new ProgressBar(this, null,
				android.R.attr.progressBarStyleHorizontal);
		pb.setProgress(30);
		ly.addView(pb, paramsMain);
		setContentView(ly);
	}
}
