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
 * Create on  上午10:56:40 2011-9-13 ye2011
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
public class SocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8189);
//			socket.connect(new InetSocketAddress("localhost", 8189), 1000);
			try{
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				Scanner scanner = new Scanner(inputStream);
				String line = scanner.nextLine();
				System.out.println(line);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter out = new PrintWriter(outputStream, true);
				
				String inLine = in.readLine();
				while(inLine != null){
					out.println(inLine);
					inLine = in.readLine();
				}
				
				while(scanner.hasNextLine()){
					line = scanner.nextLine();
					System.out.println(line);
				}
			} finally {
				socket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
