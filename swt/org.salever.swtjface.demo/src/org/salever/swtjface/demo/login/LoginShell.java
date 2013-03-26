package org.salever.swtjface.demo.login;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.salever.swtjface.demo.SWTResourceManager;


public class LoginShell extends Shell {
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LoginShell shell = new LoginShell(display);
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

	/**
	 * Create the shell.
	 * @param display
	 */
	public LoginShell(Display display) {
		super(display, SWT.NO_TRIM);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(LoginShell.class, "/org/salever/learning/swt/demo/login/login.png"));
		composite.setLayout(null);
		
		Composite composite2 = new Composite(composite, SWT.NONE);
		composite2.setBounds(125, 112, 262, 39);
		composite2.setLayout(new GridLayout(1, false));
		text = new Text(composite2, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	
		Composite composite3 = new Composite(composite, SWT.NONE);
		composite3.setBounds(125, 173, 262, 39);
		composite3.setLayout(new GridLayout(1, false));
		text_1 = new Text(composite3, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Button button = new Button(composite, SWT.CHECK);
		button.setBounds(126, 230, 13, 16);
		
		Button btnLogin = new Button(composite, SWT.NONE);
		btnLogin.setBounds(125, 294, 108, 39);
		btnLogin.setText("Login");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(250, 294, 98, 39);
		btnCancel.setText("Cancel");

		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(447, 356);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
