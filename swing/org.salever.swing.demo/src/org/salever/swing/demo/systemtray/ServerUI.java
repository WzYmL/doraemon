package org.salever.swing.demo.systemtray;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Cursor;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ServerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Image image = Toolkit.getDefaultToolkit().getImage(
			"icons/server.jpg"); //$NON-NLS-1$
	protected static final int STATE_INIT = 0;
	protected static final int STATE_STARTING = 1;
	protected static final int STATE_RUNNING = 2;

	protected static final int STATE_STOPED = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); //$NON-NLS-1$
					ServerUI frame = new ServerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;

	private JTextArea outputText;

	private JButton optionButton;

	private JProgressBar progressBar;

	private SystemTray tray;

	// private MenuItem optionItem;

	private TrayIcon trayIcon;

	private int serverState = STATE_INIT;

	/**
	 * Create the frame.
	 */
	public ServerUI() {
		setAlwaysOnTop(true);
		setTitle("服务器");
		setResizable(false);
		setBounds(100, 100, 450, 300);

		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}

		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}

		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 80, 200 };
		gridBagLayout.rowHeights = new int[] { 40 };
		gridBagLayout.columnWeights = new double[] { 0.15, 0.6 };
		panel.setLayout(gridBagLayout);

		optionButton = new JButton("启动服务器");
		optionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerform();
			}
		});
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.fill = GridBagConstraints.BOTH;
		gbc_startButton.insets = new Insets(0, 0, 0, 5);
		gbc_startButton.gridx = 0;
		gbc_startButton.gridy = 0;
		panel.add(optionButton, gbc_startButton);

		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 0;
		panel.add(progressBar, gbc_progressBar);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(10, 0));

		outputText = new JTextArea();
		outputText.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(outputText);
		panel_1.add(scrollPane);

		JLabel lblNewLabel = new JLabel("启动信息：");
		panel_1.add(lblNewLabel, BorderLayout.NORTH);

		createSystemTray();
	}

	protected void actionPerform() {
		switch (serverState) {
		case STATE_INIT:
			startServer();
			break;
		case STATE_STARTING:

			break;
		case STATE_RUNNING:
			stopServer();
			break;
		case STATE_STOPED:
			startServer();
			break;

		default:
			break;
		}
	}

	private void createSystemTray() {

		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported"); //$NON-NLS-1$
			return;
		}
		tray = SystemTray.getSystemTray();

		PopupMenu menu = new PopupMenu();
		MenuItem messageItem = new MenuItem("Show Main Window"); //$NON-NLS-1$
		messageItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				trayIcon.setToolTip("服务器正在运行..."); //$NON-NLS-1$
			}
		});
		menu.add(messageItem);

		// optionItem = new MenuItem("Start Server"); //$NON-NLS-1$
		// optionItem.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// startServer();
		// }
		// });
		// menu.add(optionItem);

		//		stopItem = new MenuItem("Stop Server"); //$NON-NLS-1$
		// stopItem.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// stopServer();
		// }
		// });
		// menu.add(stopItem);

		MenuItem closeItem = new MenuItem("Exit"); //$NON-NLS-1$
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();

			}
		});
		menu.add(closeItem);

		trayIcon = new TrayIcon(image, "系统托盘", menu); //$NON-NLS-1$
		trayIcon.setImageAutoSize(true);
		trayIcon.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(true);
			}
		});

		trayIcon.setToolTip("服务器初始化..."); //$NON-NLS-1$

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

	private void refreshLabel() {
		switch (serverState) {
		case STATE_INIT:
			optionButton.setText("启动服务器");
			optionButton.setEnabled(true);
			// optionItem.setLabel("Start");
			// optionItem.setEnabled(true);
			break;
		case STATE_STARTING:
			optionButton.setText("启动服务器");
			optionButton.setEnabled(false);
			// optionItem.setLabel("Start");
			// optionItem.setEnabled(false);
			break;
		case STATE_RUNNING:
			optionButton.setText("停止服务器");
			optionButton.setEnabled(true);
			// optionItem.setLabel("Stop");
			// optionItem.setEnabled(true);
			break;
		case STATE_STOPED:
			optionButton.setText("启动服务器");
			optionButton.setEnabled(true);
			// optionItem.setLabel("Start");
			// optionItem.setEnabled(true);
			break;

		default:
			break;
		}

	}

	/**
	 * 刷新状态，并刷新按钮
	 * 
	 * @param state
	 */
	public void resetState(int state) {
		serverState = state;
		refreshLabel();
	}

	private void runInBackground() {
		setVisible(false);
		trayIcon.setToolTip("服务器转入后台运行..."); //$NON-NLS-1$
		trayIcon.displayMessage("提示", "服务器将转入后台运行...", //$NON-NLS-1$ //$NON-NLS-1$
				TrayIcon.MessageType.INFO);
	}

	private void startServer() {

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.setTitle("服务器正在启动...");
		trayIcon.displayMessage("提示", "正在启动服务器...", TrayIcon.MessageType.INFO); //$NON-NLS-1$ //$NON-NLS-1$
		trayIcon.setToolTip("服务器正在启动..."); //$NON-NLS-1$
		try {

			resetState(STATE_STARTING);
			outputText.append("服务器正在启动...\n");

			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					progressBar.setValue(30);
					outputText.append(String.format("已完成 %d%% 进度.\n",
							progressBar.getValue()));

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					progressBar.setValue(50);
					outputText.append(String.format("已完成 %d%% 进度.\n",
							progressBar.getValue()));

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					progressBar.setValue(100);
					outputText.append(String.format("已完成 %d%% 进度.\n",
							progressBar.getValue()));

					setCursor(null); // turn off the wait cursor
					outputText.append("服务器成功启动!\n");
					resetState(STATE_RUNNING);
					trayIcon.displayMessage(
							"提示", "成功启动服务器", TrayIcon.MessageType.INFO); //$NON-NLS-1$ //$NON-NLS-1$
					trayIcon.setToolTip("服务器正在运行...");
					ServerUI.this.setTitle("服务器正在运行...");

				}
			};
			new Thread(runnable).start();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "启动服务器失败，详细信息：" + e, //$NON-NLS-1$
					"错误", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}

	}

	private void stopServer() {
		try {
			if (serverState != STATE_RUNNING) {
				return;
			}
			// XXX 添加退出
			progressBar.setValue(0);
			this.setTitle("服务器已停止");
			trayIcon.displayMessage("提示", "成功停止服务器", TrayIcon.MessageType.INFO); //$NON-NLS-1$ //$NON-NLS-1$
			resetState(STATE_STOPED);
			outputText.append("服务器已停止.\n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "停止服务器失败，详细信息：" + e, //$NON-NLS-1$
					"错误", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}

	}
}
