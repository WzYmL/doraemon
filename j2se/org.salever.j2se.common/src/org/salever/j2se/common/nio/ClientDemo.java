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
 * Create on 2011-2-22 下午02:19:48
 *******************************************************************************/
package org.salever.j2se.common.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

import org.salever.j2se.common.util.LogUtil;

/**
 * @author
 */
public class ClientDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SocketChannel channel = SocketChannel.open();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Selector selector = Selector.open();
		buffer.put("Client>>>>>>>>>>> ".getBytes());
		buffer.flip();

		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		channel.connect(new InetSocketAddress("192.168.3.14", 6018));
		while (!channel.finishConnect())
			;
		channel.write(buffer);
		LogUtil.info("连接成功，发送buffer");
		Thread.sleep(1000);
		while (selector.select() > 0) {
		
			Set<SelectionKey> set = selector.selectedKeys();
			for (SelectionKey key : set) {
				int ops = key.readyOps();
				
				if((ops & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT){
					channel.write(buffer);
					LogUtil.error("连接成功");
				}
				
				if((ops & SelectionKey.OP_READ) == SelectionKey.OP_READ){
					LogUtil.error(" 收到东西");
					channel.read(buffer);
					buffer.flip();
					LogUtil.info("收到的是:"
							+ new String(buffer.array(), 0, buffer.limit()));
					channel.write(buffer);
				}
				
				if((ops & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE){
					LogUtil.error("发送东西");
					channel.write(buffer);
					LogUtil.info("发送东西：" + new String(buffer.array(), 0, buffer.limit()));
				}
			}
			selector.selectedKeys().clear();
			Thread.sleep(2000);
		}
	}
}
