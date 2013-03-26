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
 * Create on 2011-1-30 下午02:00:27
 *******************************************************************************/
package org.salever.android.step.store.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.salever.android.step.store.R;
import org.salever.android.step.store.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author LiXiaopeng
 * 
 */
public class WebServiceActivity extends Activity {

	private static final String URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName";

	private static final String CITY_PARAM = "theCityName";

	private EditText cCity;
//	private EditText cWeather;
	private WebView cwWeather;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_service_activity);

		cCity = (EditText) findViewById(R.id.EditTextCity);
//		cWeather = (EditText) findViewById(R.id.EditTextWeather);
		cwWeather = (WebView) findViewById(R.id.WebView01);
		
		Button button = (Button) findViewById(R.id.WeatherButton01);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					cwWeather.loadUrl(URL + "?" + CITY_PARAM + "=" + "北京");
//					showWeather();
				} catch (Exception e) {
					Utils.openInfoDialog(WebServiceActivity.this, "Error",
							"查询天气失败  " + e);
				}
			}
		});
	}

	/**
	 * @throws IOException
	 * @throws ClientProtocolException
	 * 
	 */
	protected void showWeather() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String city = cCity.getText().toString();
		params.add(new BasicNameValuePair(CITY_PARAM, city));
		httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse reponse = new DefaultHttpClient().execute(httpPost);
		if (reponse.getStatusLine().getStatusCode() != 404) {
			String result = EntityUtils.toString(reponse.getEntity());
			Utils.openInfoDialog(WebServiceActivity.this, "Info",
					result);
			cwWeather.loadData(result, "text/xml", HTTP.UTF_8);
//			cwWeather.loadUrl(URL + "?" + CITY_PARAM + "=" + city);
		}
	}
}
