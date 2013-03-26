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
 * Create on 2011-1-25 ÏÂÎç02:36:07
 *******************************************************************************/
package org.salever.android.step.store.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

/**
 * @author LiXiaopeng
 *
 */
public abstract class BsicIO extends Activity{

	/**
	 * 
	 */
	public BsicIO() {
		String fieName = "xxx.tmp";
		try {
			FileOutputStream out = openFileOutput(fieName, Context.MODE_APPEND);
			FileInputStream in = openFileInput(fieName);
			
			//...
			
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
