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
 * Create on 2011-2-22 下午02:18:16
 *******************************************************************************/
package org.salever.j2se.common.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

import org.salever.j2se.common.util.LogUtil;

/**
 * @author
 */
public class ServerDemo {

	public static void main(String[] args) throws Exception {
		boolean readAllready = true;
		Charset charset = Charset.forName("utf-8");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		ServerSocketChannel ssocketChannel = ServerSocketChannel.open();
		ssocketChannel.socket().bind(new InetSocketAddress(6018));
		LogUtil.info("启动了一个ServerSocketChannel");
		ssocketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		ssocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		LogUtil.info("等待客户端连接......");

		String content = "";
		
		while (selector.select() > 0) {
			Set<SelectionKey> set = selector.selectedKeys();
			for (SelectionKey key : set) {
				SocketChannel channel;
				if(key.isAcceptable()){
					channel = ssocketChannel.accept();
					LogUtil.error("有新的客户端连接：" + channel);
					LogUtil.error("地址是: " + channel.socket());
					channel.configureBlocking(false);
					channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
				}
				
				if(key.isReadable()){
					readAllready = true;
					LogUtil.error("有新的读取");
					channel = (SocketChannel) key.channel();
					channel.read(buffer);
					buffer.flip();
					content = charset.decode(buffer).toString() + "\r\n";
					LogUtil.info("Read from clent <<<<<<<<<<< " + content);
//					channel.register(selector, SelectionKey.OP_WRITE);
					buffer.clear();
				}
				
				if(key.isWritable()){
					if(readAllready){
//						LogUtil.error("有新的写入");
						channel = (SocketChannel) key.channel();
						buffer.put(("Write into client >>>>>>>>> " + content).getBytes());
						buffer.flip();
//						LogUtil.info(charset.decode(buffer).toString());
						channel.write(buffer);
						buffer.clear();
//						channel.register(selector, SelectionKey.OP_READ);
						Thread.sleep(1000);
						readAllready = false;
					}

				}
			}
			set.clear();
//			LogUtil.info("完成一次IO操作");
		}
		LogUtil.info("服务器推出");

	}

}
