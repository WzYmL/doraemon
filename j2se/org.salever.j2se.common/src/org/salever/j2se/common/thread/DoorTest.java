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
 * Create on 2011-5-5 下午02:06:04
 *******************************************************************************/
package org.salever.j2se.common.thread;

/**
 * @author
 */
public class DoorTest {

	ILogger log = SimpleLogger.getLogger();

	private boolean doorClosed = true;

	private Object doorMonitor = new Object();

	private void internalOpenTheDoor() {
		if (doorClosed) {
			synchronized (doorMonitor) {
				try {
					doorMonitor.wait(10000);
					doorClosed = false;
					log.notice("Open successed.");
				} catch (InterruptedException e) {
					log.error("Open operation interrupted.", e);
				}
			}
		} else {
			log.error("Door is open, could not open again.");
		}
	}

	private void internalCloseTheDoor() {
		if(!doorClosed){
			synchronized (doorMonitor) {
				try {
					doorMonitor.wait(10000);
					doorClosed = true;
					log.notice("Close successed.");
				} catch (InterruptedException e) {
					log.error("Close operation interrupted.", e);
				}
			}
		}else {
			log.error("Door is closed, could not close again.");
		}
	}
	
	private Thread openThread = new Thread(){
		public void run() {
			while(true){
				internalOpenTheDoor();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
			}
			
		}
	};
	
	
	
	private Thread closeThread = new Thread(){
		public void run() {
			while(true){
				internalCloseTheDoor();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
			}
			
		}
	};
	
	private Thread bugThread = new Thread(){
		public void run() {
			synchronized (doorMonitor) {
				doorMonitor.notify();
			}
			
		};
	};
	
	public void testOpen(){
		openThread.start();
	}
	
	public void testClose(){
		closeThread.start();
	}
	
	public void bug(){
		bugThread.start();
	}
	
	public static void main(String[] args) {
		DoorTest test = new DoorTest();
		test.testOpen();
		test.bug();
//		test.testClose();
	}
}
