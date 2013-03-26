/**
 * 
 */
package org.salever.android.step.scrollview;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author LiXiaopeng
 * 
 */
public class ScrollViewActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_view_layout);
		setTitle("ScrollViewActivity");
	}
}
