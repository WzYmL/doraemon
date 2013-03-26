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
 * Create on 2011-2-18 ÏÂÎç02:33:42
 *******************************************************************************/
package org.salever.android.step.rssreader;

import org.salever.android.step.rssreader.model.RSSFeed;
import org.salever.android.step.rssreader.model.RSSItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author LiXiaopeng
 * 
 */
public class RSSHandler extends DefaultHandler {

	private static final int RSS_TITLE = 1;
	private static final int RSS_LINK = 2;
	private static final int RSS_DESCRIPTION = 3;
	private static final int RSS_CATEGORY = 4;
	private static final int RSS_PUBDATE = 5;

	private RSSFeed feed;
	private RSSItem item;

	private String lastElementName;
	private int currentState;

	/**
	 * @return the feed
	 */
	public RSSFeed getFeed() {
		return feed;
	}

	/**
	 * @param feed the feed to set
	 */
	public void setFeed(RSSFeed feed) {
		this.feed = feed;
	}

	/**
	 * 
	 */
	public RSSHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		feed = new RSSFeed();
		item = new RSSItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equals(RSSFeed.CHANNEL)) {
			currentState = 0;
			return;
		}

		if (localName.equals(RSSItem.ITEM)) {
			item = new RSSItem();
			return;
		}

		if (localName.equals(RSSItem.TITLE)) {
			currentState = RSS_TITLE;
			return;
		}

		if (localName.equals(RSSItem.DESCRIPTION)) {
			currentState = RSS_DESCRIPTION;
			return;
		}

		if (localName.equals(RSSItem.LINK)) {
			currentState = RSS_LINK;
			return;
		}

		if (localName.equals(RSSItem.CATEGORY)) {
			currentState = RSS_CATEGORY;
			return;
		}

		if (localName.equals(RSSItem.PUBLISH_DATE)) {
			currentState = RSS_PUBDATE;
			return;
		}

		currentState = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals(RSSItem.ITEM)) {
			feed.addItem(item);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String string = new String(ch, start, length);
		switch (currentState) {
		case RSS_TITLE:
			item.setTitle(string);
			currentState = 0;
			break;
		case RSS_CATEGORY:
			item.setCategory(string);
			currentState = 0;
			break;
		case RSS_DESCRIPTION:
			item.setDescription(string);
			currentState = 0;
			break;
		case RSS_LINK:
			item.setLink(string);
			currentState = 0;
			break;
		case RSS_PUBDATE:
			item.setPubDate(string);
			currentState = 0;
			break;
		default:
			return;
		}
	}
}
