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
 * Create on  上午11:15:24 2011-9-13 ye2011
 *******************************************************************************/
package org.salever.j2se.common.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author LiXP
 * 
 */
public class EchoServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket serverSockert = new ServerSocket(8189);

			while (true) {
				Socket incoming = serverSockert.accept();
				Runnable runnable = new TreadedEchoHandler(incoming);

				Thread thread = new Thread(runnable);
				thread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

class TreadedEchoHandler implements Runnable {

	private Socket incoming;

	/**
	 * @param incoming
	 */
	public TreadedEchoHandler(Socket incoming) {
		this.incoming = incoming;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {

			try {
				InputStream inputStream = incoming.getInputStream();
				OutputStream outputStream = incoming.getOutputStream();

				Scanner scanner = new Scanner(inputStream);
				PrintWriter printWriter = new PrintWriter(outputStream, true);
				printWriter.println("0");
				boolean done = false;
				while (!done) {
					int line = scanner.nextInt();
					System.out.println(line);
					printWriter.println(line);
					if (line == -1) {
						done = true;
					}
				}
			} finally {
				incoming.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
