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
 * Create on May 17, 2012 4:40:53 PM
 *******************************************************************************/
package org.salever.osgi.export.use.test;

import org.salever.osgi.export.use.util.IUtil;

/**
 * @author LiXP
 * 
 */
public class TestUtil {

	public IUtil getUtil() {
		return new IUtil() {
			@Override
			public String util() {
				return TestUtil.this.toString() + " util implementaion";
			}
		};
	}
}
