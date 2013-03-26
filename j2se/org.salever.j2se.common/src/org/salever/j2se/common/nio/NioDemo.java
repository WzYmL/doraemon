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
 * Create on 2011-2-22 下午01:36:36
 *******************************************************************************/
package org.salever.j2se.common.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import org.salever.j2se.common.util.LogUtil;

/**
 * @author 
 */
public class NioDemo {

	/**
	 * Copy file using nio.
	 * @param inFile
	 * @param outFile
	 */
	public static void copyFile(String inFile, String outFile) {
		try {
			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);
			FileChannel cIn = in.getChannel();
			FileChannel cOut = out.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int length = 0;
			while(true){
				buffer.clear();
				length = cIn.read(buffer);
				if(length == -1){
					break;
				}
				buffer.flip();
				cOut.write(buffer);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param hostname
	 * @param port
	 */
	public static void readHtmlContent(String hostname, int port){
		InetSocketAddress socketAddress = new InetSocketAddress(hostname, port);	
		SocketChannel channel = null;
		
		try {
			channel = SocketChannel.open(socketAddress);
			Charset charset = Charset.forName("GBK");
			channel.write(charset.encode("GET " + "/ HTTP/1.1" + "\r\n\r\n"));
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(channel.read(buffer) != -1){
				buffer.flip();
				System.out.println(charset.decode(buffer));
				buffer.clear();
			}
		} catch (IOException e) {
			LogUtil.error(e.getLocalizedMessage());
		}finally{
			if(channel != null){
				try {
					channel.close();
				} catch (IOException e) {
				}
			}
		}
		
	}
	
	public static void main(String[] args){
//		String in = "D:\\software\\Basic\\Dreamweaver_11_LS3.exe";
//		String out = "c:/test.txt";
//		LogUtil.info("Begin to copy " + in + " to " + out);
//		copyFile(in, out);
//		LogUtil.info("Finish to copy " + in + " to " + out);
		
		readHtmlContent("www.baidu.com", 80);
	}
}
