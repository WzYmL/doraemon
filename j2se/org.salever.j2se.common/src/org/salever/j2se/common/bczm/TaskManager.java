/**
 * 
 */
package org.salever.j2se.common.bczm;

/**
 * @author LiXiaopeng
 * 
 */
public class TaskManager {

	public static void halfBusyLine() throws InterruptedException {
		int count = 1;
		while (true) {	
			for (int i = 0; i < 200000000; i++);				
			Thread.sleep(10);
		
		}
	}
}
