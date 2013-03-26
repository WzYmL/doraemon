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
 * Create on 2011-5-4 下午03:47:41
 *******************************************************************************/
package org.salever.j2se.common.thread;

/**
 * @author
 */
public class ThreadLocalTest {

	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {

		public Integer initialValue() {

			return 0;

		}

	};

	// ②获取下一个序列值

	public int getNextNum() {

		seqNum.set(seqNum.get() + 1);

		return seqNum.get();

		// return num++;

	}

	int num = 0;

	public static void main(String[] args)

	{

		ThreadLocalTest sn = new ThreadLocalTest();

		// ③ 3个线程共享sn，各自产生序列号

		TestClient t1 = new TestClient(sn);

		TestClient t2 = new TestClient(sn);

		TestClient t3 = new TestClient(sn);

		t1.start();

		t2.start();

		t3.start();

	}

}

class TestClient extends Thread

{

	private ThreadLocalTest sn;

	public TestClient(ThreadLocalTest sn) {

		this.sn = sn;

	}

	public void run()

	{

		for (int i = 0; i < 3; i++) {
			// ④每个线程打出3个序列值

			System.out.println("thread[" + Thread.currentThread().getName() +

			"] sn[" + sn.getNextNum() + "]");

		}

	}

}
