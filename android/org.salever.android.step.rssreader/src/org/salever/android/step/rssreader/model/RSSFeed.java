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
 * Create on 2011-2-18 ÏÂÎç01:58:33
 *******************************************************************************/
package org.salever.android.step.rssreader.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author LiXiaopeng
 *
 */
public class RSSFeed {

	public static final String CHANNEL = "channel";
	
	private String title;
	private String pubDate;
	private int itemCount;
	private List<RSSItem> items;
	
	/**
	 * 
	 */
	public RSSFeed() {
		items = new Vector<RSSItem>(0);
	}
	
	public int addItem(RSSItem item){
		this.items.add(item);
		this.itemCount ++;
		return this.itemCount;
	}
	
	public RSSItem getItem(int location){
		return this.items.get(location);
	}
		
	public List<Map<String, Object>> getAllItemForShow(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for(RSSItem item: items){
			map = new HashMap<String, Object>();
			map.put(RSSItem.TITLE, item.getTitle());
			map.put(RSSItem.PUBLISH_DATE, item.getPubDate());
			list.add(map);
		}
		return list;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * @param itemCount the itemCount to set
	 */
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	/**
	 * @return the items
	 */
	public List<RSSItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<RSSItem> items) {
		this.items = items;
	}
	
	
}
