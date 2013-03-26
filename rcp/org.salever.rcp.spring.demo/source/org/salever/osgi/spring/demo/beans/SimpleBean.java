/** Copyright (c) 2010 salever@126.com. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     salever@126.com - initial API and implementation
 *
 * Create on  上午09:51:57 2011-10-9 ye2011
 *******************************************************************************/
package org.salever.osgi.spring.demo.beans;

import org.springframework.stereotype.Service;

/**
 * @author LiXP
 *
 */
@Service
public class SimpleBean {

	public String getMessage(){
		return "Hello world";
	}
	
}
