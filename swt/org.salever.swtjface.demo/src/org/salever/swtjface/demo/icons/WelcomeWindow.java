package org.salever.swtjface.demo.icons;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.salever.swtjface.demo.SWTResourceManager;

public class WelcomeWindow extends ApplicationWindow {

	private static final int DEFAULE_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 300;
	private Label environmentLabel;
	private Label databaseLabel;
	private Label finishLabel;

	/**
	 * Create the application window.
	 */
	public WelcomeWindow() {
		super(null);
		setShellStyle(SWT.NO_TRIM);
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {

		Composite container = new Composite(parent, SWT.NONE);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.horizontalSpacing = 0;
		gl_container.verticalSpacing = 0;
		gl_container.marginWidth = 0;
		gl_container.marginHeight = 0;
		container.setLayout(gl_container);

		CLabel label = new CLabel(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getImage(WelcomeWindow.class,
				"/org/salever/learning/swt/demo/icons/splash.gif"));
		GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1);
		gd_label.heightHint = 200;
		label.setLayoutData(gd_label);
		label.setText("");

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		gd_composite.heightHint = 80;
		composite.setLayoutData(gd_composite);

		Label systemLabel = new Label(composite, SWT.NONE);
		systemLabel.setText("系统正在启动中......");

		environmentLabel = new Label(composite, SWT.NONE);

		databaseLabel = new Label(composite, SWT.NONE);

		finishLabel = new Label(composite, SWT.NONE);
		return container;
	}

	public void finishEnv() {
		environmentLabel.setText("系统环境初始化......");
	}

	public void finishDatabse() {
		databaseLabel.setText("数据库初始化......");
	}

	public void finish() {
		finishLabel.setText("初始化完毕，正在进入系统......");
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * 
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			WelcomeWindow window = new WelcomeWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Application start...");
		newShell.setImage(SWTResourceManager.getImage(MainWindow.class,
				"/org/salever/learning/swt/demo/tree/icons/add.gif"));
		newShell.setLocation(computerCenterLoacation());
	}

	private Point computerCenterLoacation() {
		Rectangle rec = Display.getDefault().getBounds();
		int centerX = rec.width / 2;
		int centerY = rec.height / 2;
		Point point = new Point(centerX - DEFAULE_WIDTH / 2, centerY
				- DEFAULT_HEIGHT / 2);
		return point;
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(DEFAULE_WIDTH, DEFAULT_HEIGHT);
	}

}
