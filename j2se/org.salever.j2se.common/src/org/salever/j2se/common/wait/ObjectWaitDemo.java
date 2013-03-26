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
 * Create on  上午10:28:38 2011-9-27 ye2011
 *******************************************************************************/
package org.salever.j2se.common.wait;

/**
 * @author LiXP
 * 
 */
public class ObjectWaitDemo {

	private Object monitor = new Object();

	public void waitForSomeone() {

		new Thread() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				int index = 0;

				while (index < 10) {
					try {
						synchronized (monitor) {
							monitor.wait();
						}
					} catch (InterruptedException e) {
						System.out.println("Interrupt: " + e);
					}

					System.out.println(Thread.currentThread().getName() + " Wake up. " + index);

					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}
					index++;
				}
			}
		}.start();

	}

	public void awake() {
		new Thread() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				while (true) {
					synchronized (monitor) {
						monitor.notifyAll();
						System.out.println("notifyAll.");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}

			}
		}.start();
	}

	public static void main(String[] args) throws InterruptedException {
		ObjectWaitDemo demo = new ObjectWaitDemo();
		
		demo.waitForSomeone();

		demo.waitForSomeone();

		demo.waitForSomeone();
		
		demo.awake();

	}
}
