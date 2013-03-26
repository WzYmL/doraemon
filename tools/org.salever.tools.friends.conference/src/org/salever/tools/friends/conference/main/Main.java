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
 * Create on May 31, 2012 10:18:13 AM
 *******************************************************************************/
package org.salever.tools.friends.conference.main;

import org.eclipse.swt.widgets.Shell;
import org.salever.tools.friends.conference.dialogs.WelcomeDialog;

/**
 * @author LiXP
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.setImage(WelcomeDialog.LOGO_IMAGE);
		WelcomeDialog welcomeDialog = new WelcomeDialog(null);
		welcomeDialog.open();

	}

}
