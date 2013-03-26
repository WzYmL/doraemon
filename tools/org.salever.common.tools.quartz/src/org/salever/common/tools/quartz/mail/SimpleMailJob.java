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
 * Create on 2011-4-15 上午11:05:26
 *******************************************************************************/
package org.salever.common.tools.quartz.mail;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 
 */
public class SimpleMailJob implements Job{
	
	Logger log = LoggerFactory.getLogger(SimpleMailJob.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobDetail detail = arg0.getJobDetail();
		JobDataMap map = detail.getJobDataMap();
		
		MessageData data = (MessageData) map.get(MessageData.MESSAGE_DATA_KEY);
		
		log.error(".........Send mail............");
		log.info("User name: " + data.getUsername());
		log.info("User mail address:" + data.getMail());
		log.info("Mail content:" + data.getData());
		log.error(".........Finish sending mail..........");
		log.error("");
	}

}
