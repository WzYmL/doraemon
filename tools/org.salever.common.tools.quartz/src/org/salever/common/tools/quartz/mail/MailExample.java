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
 * Create on 2011-4-15 上午11:06:28
 *******************************************************************************/
package org.salever.common.tools.quartz.mail;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author
 */
public class MailExample {

	Logger log = LoggerFactory.getLogger(MailExample.class);

	public void run() {

		log.info("Initial scheduler...");

		try {
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			log.info("Initialation completed.");

			JobDetail job1 = new JobDetail("job1", "group1",
					SimpleMailJob.class);

			// Initial message data to for mail.
			JobDataMap dataMap = job1.getJobDataMap();
			MessageData data = new MessageData();
			loadData(data);
			dataMap.put(MessageData.MESSAGE_DATA_KEY, data);

			// Get a "nice round" time a few seconds in the future....
			long ts = TriggerUtils.getNextGivenSecondDate(null, 10).getTime();

			// Initial trigger.
			SimpleTrigger trigger1 = new SimpleTrigger("trigger1", "group1",
					"job1", "group1", new Date(ts), null, 4, 3000);

			// Schedule the job to run
			Date scheduleTime1 = scheduler.scheduleJob(job1, trigger1);
			log.info(job1.getFullName() + " will run at: " + scheduleTime1
					+ " and repeat: " + trigger1.getRepeatCount()
					+ " times, every " + trigger1.getRepeatInterval() / 1000
					+ " seconds");

			scheduler.start();

			try {
				// wait five minutes to show jobs
				Thread.sleep(60L * 1000L);
				// executing...
			} catch (Exception e) {
			}

			log.info("------- Shutting Down ---------------------");

			scheduler.shutdown(true);

			log.info("------- Shutdown Complete -----------------");

			SchedulerMetaData metaData = scheduler.getMetaData();
			log.info("Executed " + metaData.getNumberOfJobsExecuted()
					+ " jobs.");

		} catch (SchedulerException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Load message data.
	 * @param data
	 */
	private void loadData(MessageData data) {
		data.setData("Hello");
		data.setMail("prettybai@yahoo.cn");
		data.setUsername("User");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MailExample().run();
	}

}
