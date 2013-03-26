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
 * Create on 2011-5-5 下午03:00:46
 *******************************************************************************/
package org.salever.j2se.common.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author
 */
public class WaitAndNotifyTest {

	DateFormat format = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");

	private String getTime() {
		return format.format(Calendar.getInstance().getTime());
	}

	private Object monitor = new Object();

	public synchronized void waitOnce(String thread, final long ms) {
		Thread waitThread = new Thread() {
			public void run() {
				synchronized (monitor) {
					try {
						System.out.println("Thread "
								+ Thread.currentThread().getName()
								+ " Wait at " + getTime());
						monitor.wait(ms);
						System.out.println("Thread "
								+ Thread.currentThread().getName()
								+ " Waked at " + getTime());
					} catch (InterruptedException e) {
					}
				}
			};
		};
		waitThread.setName(thread);
		waitThread.start();
	}

	public void awake(String thread) {
		Thread notifyThread = new Thread() {
			public void run() {
				synchronized (monitor) {
					monitor.notify();
					System.out.println("Thread "
							+ Thread.currentThread().getName() + " Notify at "
							+ getTime());
				}
			};
		};
		notifyThread.setName(thread);
		notifyThread.start();
	}

	public void awakeAndWait(String thread, final long ms) {
		Thread notifyThread = new Thread() {
			public void run() {
				synchronized (monitor) {
					monitor.notify();
					System.out.println("Thread "
							+ Thread.currentThread().getName() + " Notify at "
							+ getTime());
					// 保持了对象锁的等待
					try {
						Thread.sleep(ms);
					} catch (InterruptedException e) {
					}
				}
				// 释放了对象锁之后的等待
				try {
					Thread.sleep(ms);
				} catch (InterruptedException e) {
				}
			};
		};
		notifyThread.setName(thread);
		notifyThread.start();
	}

	public void awakeAll(String thread) {
		Thread notifyThread = new Thread() {
			public void run() {
				synchronized (monitor) {
					monitor.notifyAll();
					System.out.println("Thread "
							+ Thread.currentThread().getName()
							+ " Notify all at " + getTime());
				}
			};
		};
		notifyThread.setName(thread);
		notifyThread.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WaitAndNotifyTest test = new WaitAndNotifyTest();
		test.waitOnce("1", Long.MAX_VALUE);// 在对象上等待无限长
		test.waitOnce("2", Long.MAX_VALUE);// 在对象上等待无限长
		test.waitOnce("3", Long.MAX_VALUE);// 在对象上等待无限长
		try {// 延迟2s
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		test.awakeAll("4");
	}

}
