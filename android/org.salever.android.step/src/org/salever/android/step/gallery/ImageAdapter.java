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
 * Create on 2011-1-25 下午02:32:30
 *******************************************************************************/
package org.salever.android.step.gallery;

import android.R;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * @author LiXiaopeng
 * 
 */
public class ImageAdapter extends BaseAdapter {

	private Activity acticity;
	 public static Integer[] mImageIds = { 
			 //定义整型数组 即图片源  
			 R.drawable.alert_dark_frame,  
			 R.drawable.alert_light_frame,  
			 R.drawable.arrow_down_float,  
			R.drawable.bottom_bar,  
			 R.drawable.btn_dialog,  
			R.drawable.btn_radio,  
			 R.drawable.dialog_frame  
			 };  
	public ImageAdapter(Activity acticity) {
		super();
		this.acticity = acticity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mImageIds.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view = new ImageView(acticity);
		view.setImageResource(mImageIds[position]);
		view.setAdjustViewBounds(true);
		view.setLayoutParams(new Gallery.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		view.setBackgroundResource(R.drawable.picture_frame);
		return view;
	}

}
