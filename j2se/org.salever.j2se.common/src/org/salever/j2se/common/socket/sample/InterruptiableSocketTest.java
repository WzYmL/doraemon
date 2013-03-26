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
 * Create on  上午11:48:07 2011-9-13 ye2011
 *******************************************************************************/
package org.salever.j2se.common.socket.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author LiXP
 * 
 */
public class InterruptiableSocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new InterruptibleSocketFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}

/**
 * @author LiXP
 * 
 */
class InterruptibleSocketFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7738857858442690266L;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 600;
	private JTextArea messages;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelgButton;
	private Runnable server;
	private Scanner in;
	private Thread connectThead;

	/**
	 * 
	 */
	public InterruptibleSocketFrame() {
		setSize(WIDTH, HEIGHT);
		setTitle("InterruptibleSocketTest");

		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);

		messages = new JTextArea();
		add(new JScrollPane(messages));

		interruptibleButton = new JButton("Interrupte");
		blockingButton = new JButton("Blocking");
		cancelgButton = new JButton("Canel");
		cancelgButton.setEnabled(false);
		northPanel.add(interruptibleButton);
		northPanel.add(blockingButton);
		northPanel.add(cancelgButton);

		interruptibleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelgButton.setEnabled(true);

				connectThead = new Thread(new Runnable() {
					public void run() {
						try {
							connectInterruptibly();
						} catch (IOException e) {
							messages.append("\nInterruptibleSocketFrame.connectInterruptibly: "
									+ e);
						}
					}
				});
				connectThead.start();
			}
		});
		blockingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelgButton.setEnabled(true);

				connectThead = new Thread(new Runnable() {
					public void run() {
						try {
							connectBlocking();
						} catch (IOException e) {
							messages.append("\nInterruptibleSocketFrame.connectBlocking: "
									+ e);
						}
					}
				});
				connectThead.start();
			}
		});
		cancelgButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				connectThead.interrupt();
				cancelgButton.setEnabled(false);
			}
		});

		server = new TestServer();
		new Thread(server).start();
	}

	public void connectInterruptibly() throws IOException {
		messages.append("Interruptible:\n");
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(
				"localhost", 8189));
		try {
			in = new Scanner(socketChannel);
			while (!Thread.currentThread().isInterrupted()) {
				
				System.out.println(Thread.currentThread());
				
				messages.append("Reading: ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			System.out.println("Interrupte");
			socketChannel.close();
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					messages.append("Chanel closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
			});
		}
	}

	private void connectBlocking() throws IOException {

		messages.append("Blocking:\n");
		Socket socket = new Socket("localhost", 8189);
		try {
			in = new Scanner(socket.getInputStream());
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println(Thread.currentThread());
				messages.append("Reading: ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			System.out.println("Interrupte");
			socket.close();
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					messages.append("Chanel closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
			});
		}

	}

	class TestServer implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(8189);

				while (true) {
					Socket incoming = serverSocket.accept();
					Runnable runnable = new TestServerhandler(incoming);
					Thread thread = new Thread(runnable);
					thread.start();
				}
			} catch (IOException e) {
				messages.append("\nTestServer.run: " + e);
			}
		}
	}

	class TestServerhandler implements Runnable {

		private Socket incoming;
		private int counter;

		/**
		 * @param incoming
		 */
		public TestServerhandler(Socket incoming) {
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
				OutputStream outputStream = incoming.getOutputStream();
				PrintWriter out = new PrintWriter(outputStream, true);
				while (counter < 100) {
					counter++;
					if (counter <= 10) {
						out.println(counter);
					}
					Thread.sleep(1000);
				}
				incoming.close();
				messages.append("Closing server\n");

			} catch (Exception e) {
				messages.append("\nTestServerHandler.run: " + e);
			}
		}
	}
}
