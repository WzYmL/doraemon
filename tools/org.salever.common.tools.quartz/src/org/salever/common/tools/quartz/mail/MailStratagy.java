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
 * Create on 2011-4-18 下午02:45:52
 *******************************************************************************/
package org.salever.common.tools.quartz.mail;

import java.util.Date;

/**
 * @author 
 */
public class MailStratagy {

	/**
	 * Selection condition criterion.
	 */
	private Object criterion;
	
	private boolean enable;
	
	private Date createTime;
	
	private Date startTime;
	
	private int repeatInterval;
	
	private int repeatCount;
	
	private String mailTopic;
	
	private String mailContent;
	
	private String mailAttachment;
}
