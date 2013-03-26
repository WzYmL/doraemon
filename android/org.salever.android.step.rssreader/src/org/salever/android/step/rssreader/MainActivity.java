package org.salever.android.step.rssreader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.salever.android.step.rssreader.model.RSSFeed;
import org.salever.android.step.rssreader.model.RSSItem;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private static final String RSS_URL = "http://news.qq.com/newssh/rss_newssh.xml";
	private RSSFeed feed;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		feed = getFeed(RSS_URL);
		showListView();
	}

	/**
	 * @param rssUrl
	 * @return
	 */
	private RSSFeed getFeed(String rssUrl) {

		try {
			URL url = new URL(rssUrl);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			RSSHandler handler = new RSSHandler();
			reader.setContentHandler(handler);

			InputSource source = new InputSource(url.openStream());
			reader.parse(source);
			return handler.getFeed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void showListView() {
		ListView itemList = (ListView) findViewById(R.id.ListView01);
		if (feed == null) {
			setTitle("访问的RSS无效");
			return;
		}
		SimpleAdapter adapter = new SimpleAdapter(this, feed
				.getAllItemForShow(), android.R.layout.simple_list_item_2,
				new String[] { RSSItem.TITLE, RSSItem.PUBLISH_DATE },
				new int[] { android.R.id.text1, android.R.id.text2 });
		itemList.setAdapter(adapter);
		itemList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				itemClick(arg2);
				
			}
		});
		itemList.setSelection(0);
	}

	/**
	 * @param arg2
	 */
	protected void itemClick(int position) {
		Intent intent = new Intent(this, ShowDescriptionActivity.class);
		Bundle bundle = new Bundle();
		RSSItem item = feed.getItem(position);
		bundle.putString(RSSItem.TITLE, item.getTitle());
		bundle.putString(RSSItem.DESCRIPTION, item.getDescription());
		bundle.putString(RSSItem.CATEGORY, item.getCategory());
		bundle.putString(RSSItem.LINK, item.getLink());
		bundle.putString(RSSItem.PUBLISH_DATE, item.getPubDate());
		intent.putExtra("org.salever.android.step.rssreader.model.RSSItem", bundle);
		
		startActivityForResult(intent, 0);
	}
}