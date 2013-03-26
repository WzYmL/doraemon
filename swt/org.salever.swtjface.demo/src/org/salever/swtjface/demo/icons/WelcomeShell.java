package org.salever.swtjface.demo.icons;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.salever.swtjface.demo.SWTResourceManager;


public class WelcomeShell extends Shell {
	private static final int DEFAULE_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 300;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			WelcomeShell shell = new WelcomeShell(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Label finishLabel;
	private Label environmentLabel;
	private Label databaseLabel;

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public WelcomeShell(Display display) {
		super(display, SWT.NO_TRIM);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayout(gridLayout);

		CLabel label = new CLabel(this, SWT.NONE);
		GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1);
		gd_label.heightHint = 200;
		label.setBackground(SWTResourceManager.getImage(WelcomeWindow.class,
		"/org/salever/learning/swt/demo/icons/splash.gif"));
		label.setLayoutData(gd_label);
		label.setText("");

		Composite composite = new Composite(this, SWT.NONE);
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

		createContents();
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
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Welcome");
		setSize(DEFAULE_WIDTH, DEFAULT_HEIGHT);
		setImage(SWTResourceManager.getImage(MainWindow.class,
				"/org/salever/learning/swt/demo/tree/icons/add.gif"));
		setLocation(computerCenterLoacation());

	}

	private Point computerCenterLoacation() {
		Rectangle rec = Display.getDefault().getBounds();
		int centerX = rec.width / 2;
		int centerY = rec.height / 2;
		Point point = new Point(centerX - DEFAULE_WIDTH / 2, centerY
				- DEFAULT_HEIGHT / 2);
		return point;
	}

	@Override
	protected void checkSubclass() {
	}
}
