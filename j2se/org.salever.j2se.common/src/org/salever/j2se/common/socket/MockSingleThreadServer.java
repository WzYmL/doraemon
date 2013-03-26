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
 * Create on  下午03:10:22 2011-9-14 ye2011
 *******************************************************************************/
package org.salever.j2se.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LiXP
 *
 */
public class MockSingleThreadServer {

	private static final int PORT = 8899;
	private ServerSocket serverSocket;
	private Socket incoming;

	/**
	 * 
	 */
	public MockSingleThreadServer() {
	}

	/**
	 * 
	 */
	public void start() {
		try {
			try{
				serverSocket = new ServerSocket(PORT);
				incoming = serverSocket.accept();
				
				InputStream inputStream = incoming.getInputStream();
				OutputStream outputStream = incoming.getOutputStream();
				
				PrintWriter writer = new PrintWriter(outputStream, true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf8"));
				
				String packet = "Welcome";
				sendPacket(writer, packet);
				
				String line = reader.readLine();
				while(line != null && !line.trim().equals("BYE")){
					dealPacket(writer, line);
					line = reader.readLine();
				}
			}finally{
				incoming.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param writer
	 * @param line
	 */
	private void dealPacket(PrintWriter writer, String line) {
		System.out.println(line);
		sendPacket(writer, "You input: " + line);
	}

	/**
	 * @param writer
	 * @param packet
	 */
	private void sendPacket(PrintWriter writer, String packet) {
//		writer.print(packet + "\r\n");
		writer.println(packet);
	}
	
	public static void main(String[] args) {
		new MockSingleThreadServer().start();
	}
}
