package com.talend.router.ftp.runner.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.talend.router.ftp.runner.FtpServerRunner;

public class ServerFrame extends JFrame {

	static Image image = Toolkit.getDefaultToolkit().getImage(
			"images/sample.gif");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private SystemTray tray;

	private TrayIcon trayIcon;

	private boolean serverRunning = false;

	private JButton startButton;

	private JButton stopButton;

	private MenuItem startItem;

	private MenuItem stopItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					ServerFrame frame = new ServerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("FTP Server Tool");

		createMainComp();

		createSystemTray();
		refreshButtonState();

	}

	private void createMainComp() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(300, 200);
		Dimension frameSize = getSize();

		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}

		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}

		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 100, 100 };
		gridBagLayout.rowHeights = new int[] { 32, 32, 32 };
		gridBagLayout.columnWeights = new double[] { 0.3, 0.3, 0.3 };
		gridBagLayout.rowWeights = new double[] { 0.3, 0.3, 0.3 };
		contentPane.setLayout(gridBagLayout);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(0, 0, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 1;

		startButton = new JButton("Start");                                                                                                        
		contentPane.add(startButton, constraints);
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				startServer();
			}
		});

		stopButton = new JButton("Stop");
		constraints.gridx = 1;
		contentPane.add(stopButton, constraints);
		stopButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});

		JButton exitButton = new JButton("Exit");
		constraints.gridx = 2;
		contentPane.add(exitButton, constraints);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}

	protected void stopServer() {
		try {
			if(!serverRunning){
				return;
			}
			FtpServerRunner.shutdownServer();
			trayIcon.displayMessage("Info", "Stop FTP Server succeed",
					TrayIcon.MessageType.INFO);
			serverRunning = false;
			refreshButtonState();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,
					"Stop FTP Server fails, details:" + e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	protected void startServer() {
		try {
			FtpServerRunner.startFtpServer();
			trayIcon.displayMessage("info", "Start FTP Server succeed",
					TrayIcon.MessageType.INFO);
			serverRunning = true;
			refreshButtonState();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,
					"Start FTP Server fails, details:" + e, "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void refreshButtonState() {
		startButton.setEnabled(!serverRunning);
		stopButton.setEnabled(serverRunning);
		startItem.setEnabled(!serverRunning);
		stopItem.setEnabled(serverRunning);
	}

	private void createSystemTray() {

		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		tray = SystemTray.getSystemTray();

		PopupMenu menu = new PopupMenu();
		MenuItem messageItem = new MenuItem("Show Main Window");
		messageItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				trayIcon.setToolTip("FTP Server Tool is running.");
			}
		});
		menu.add(messageItem);

		startItem = new MenuItem("Start Server");
		startItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startServer();
			}
		});
		menu.add(startItem);

		stopItem = new MenuItem("Stop Server");
		stopItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});
		menu.add(stopItem);

		MenuItem closeItem = new MenuItem("Exit");
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
				
			}
		});
		menu.add(closeItem);

		trayIcon = new TrayIcon(image, "SystemTray", menu);
		trayIcon.setImageAutoSize(true);
		trayIcon.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(true);
			}
		});

		trayIcon.setToolTip("FTP Server Tool is running.");

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
		}

	}

	protected void exit() {
		stopServer();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
		}
		System.exit(0);
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {

		int id = e.getID();
		switch (id) {
		case WindowEvent.WINDOW_CLOSING:
			runInBackground();
			return;

		}
		super.processWindowEvent(e);
	}

	private void runInBackground() {
		setVisible(false);
		trayIcon.setToolTip("FTP Server Tool is running in backgroud.");
		trayIcon.displayMessage("Info",
				"FTP Server Tool is running in backgroud.",
				TrayIcon.MessageType.INFO);
	}

}
