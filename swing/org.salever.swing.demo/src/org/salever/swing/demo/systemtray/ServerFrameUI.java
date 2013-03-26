package org.salever.swing.demo.systemtray;

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
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ServerFrameUI extends JFrame {

	static Image image = Toolkit.getDefaultToolkit().getImage(
			"icons/server.jpg"); //$NON-NLS-1$

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

	private ProgressMonitor pbar;

	protected int counter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); //$NON-NLS-1$
					ServerFrameUI frame = new ServerFrameUI();
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
	public ServerFrameUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("XXXX服务器"); //$NON-NLS-1$

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

		startButton = new JButton("启动"); //$NON-NLS-1$
		contentPane.add(startButton, constraints);
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				startServer();
			}
		});

		stopButton = new JButton("停止"); //$NON-NLS-1$
		constraints.gridx = 1;
		contentPane.add(stopButton, constraints);
		stopButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});

		JButton exitButton = new JButton("退出"); //$NON-NLS-1$
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
			if (!serverRunning) {
				return;
			}
			// XXX 添加退出
			trayIcon.displayMessage("提示", "成功停止服务器", TrayIcon.MessageType.INFO); //$NON-NLS-1$ //$NON-NLS-1$
			serverRunning = false;
			refreshButtonState();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "停止服务器失败，详细信息：" + e, //$NON-NLS-1$
					"错误", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}

	}

	protected void startServer() {
		try {
			// XXX 添加启动

			pbar = new ProgressMonitor(null, "Monitoring Progress",
					"Initializing . . .", 0, 100);
			Timer timer = new Timer(500, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							if (pbar.isCanceled()) {
								pbar.close();
								System.exit(1);
							}
							pbar.setProgress(counter);

							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							pbar.setNote("Operation is " + counter
									+ "% complete");
							counter += 2;
						}
					});
				}
			});
			timer.start();

			trayIcon.displayMessage("提示", "成功启动服务器", TrayIcon.MessageType.INFO); //$NON-NLS-1$ //$NON-NLS-1$
			serverRunning = true;
			refreshButtonState();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "启动服务器失败，详细信息：" + e, //$NON-NLS-1$
					"错误", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
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

		startItem = new MenuItem("Start Server"); //$NON-NLS-1$
		startItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startServer();
			}
		});
		menu.add(startItem);

		stopItem = new MenuItem("Stop Server"); //$NON-NLS-1$
		stopItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});
		menu.add(stopItem);

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

	private void runInBackground() {
		setVisible(false);
		trayIcon.setToolTip("服务器转入后台运行..."); //$NON-NLS-1$
		trayIcon.displayMessage("提示", "服务器将转入后台运行...", //$NON-NLS-1$ //$NON-NLS-1$
				TrayIcon.MessageType.INFO);
	}

}
