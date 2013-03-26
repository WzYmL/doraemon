/**
 * 
 */
package org.salever.android.step.spinner;

import java.util.ArrayList;
import java.util.List;

import org.salever.android.step.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * @author LiXiaopeng
 * 
 */
public class SpinnerActivity extends Activity {

	public static final String[] mCountries = { "China", "USA", "UK",
			"Germany", "France", "Italy" };

	public SpinnerActivity() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		setTitle("Spinner Test");

		Spinner spinner = (Spinner) findViewById(R.id.Spinner01);
		List<String> countries = new ArrayList<String>();
		for (String string : mCountries) {
			countries.add(string);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, countries);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		Spinner spinner2 = (Spinner) findViewById(R.id.Spinner02);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
				.createFromResource(this, R.array.SpinnerData,
						android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
	}

}
