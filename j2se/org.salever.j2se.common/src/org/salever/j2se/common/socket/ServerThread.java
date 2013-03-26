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
 * Create on  上午11:13:31 2011-9-15 ye2011
 *******************************************************************************/
package org.salever.j2se.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author LiXP
 * 
 */
public class ServerThread {

	private Thread workThread;
	
	private Thread socketThread;

	private int state;

	public Socket socket;

	public void start() {
		workThread = new WorkThread();
		workThread.start();
		
		socketThread = new SocketThread();
		socketThread.start();
	}

	public void doThing(int state) {
		this.state = state;
		workThread.interrupt();
		sendMessage(state + "");
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ServerThread serverThread = new ServerThread();
		serverThread.start();

		Thread.sleep(2000);
		serverThread.sendMessage("Send message");
	}

	class WorkThread extends Thread{
		
		/**
		 * 
		 */
		public WorkThread() {
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("I am awaked by somebody: " + state);
					switch (state) {
					case 0:
						System.out.println("I am do thing 0.");
						break;
					case 1:
						System.out.println("I am do thing 1.");
						break;
					case 2:
						System.out.println("I am do thing 2.");
						break;

					default:
						System.out.println("I am do thing " + state);
						break;
					}
					continue;
				}
				System.out.println("I am awaked naturelly");
				doThing(1);
			}
		}
	}
	
	class SocketThread extends Thread{
		 /**
		 * 
		 */
		public SocketThread() {
			// TODO Auto-generated constructor stub
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			try {
				 socket = new Socket("localhost", 8189);
				try{
					InputStream inputStream = socket.getInputStream();
					Scanner scanner = new Scanner(inputStream);
					while(scanner.hasNextLine()){
						int line = scanner.nextInt();
						System.out.println(line);
						doThing(line);
					}
					
				} finally {
					socket.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	public void sendMessage(String message){
		try {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			writer.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
