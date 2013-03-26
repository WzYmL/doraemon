package org.salever.j2se.common.java6;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloSplash {
	public static void main(String args[]) {
		Runnable runner = new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				JFrame frame = new JFrame("Java 6 Revealed");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JLabel label = new JLabel(" Java 6 Revealed", JLabel.CENTER);
				frame.add(label, BorderLayout.CENTER);
				frame.setSize(300, 95);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
}