package org.salever.swtjface.demo.login;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.salever.swtjface.demo.SWTResourceManager;


public class LoginDialog extends TitleAreaDialog {

	private Text usernameText;
	private Text passwordText;

	private Image image = SWTResourceManager.getImage(LoginDialog.class,
			"/org/salever/learning/swt/demo/tree/icons/add.gif");

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public LoginDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("欢迎登录系统！");
		setMessage(format(Calendar.getInstance().getTime()));
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.marginWidth = 10;
		gl_container.marginTop = 10;
		gl_container.marginLeft = 10;
		gl_container.marginHeight = 10;
		gl_container.horizontalSpacing = 10;
		gl_container.marginRight = 10;
		gl_container.marginBottom = 10;
		container.setLayout(gl_container);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.CENTER, SWT.FILL, true, true,
				1, 1);
		gd_composite.widthHint = 300;
		composite.setLayoutData(gd_composite);

		Label infoLabell = new Label(composite, SWT.WRAP);
		infoLabell.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));
		infoLabell.setText("请输入用户名和密码登录系统。若忘记用户名或密码，请与系统管理员联系。");

		Composite composite_1 = new Composite(container, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.CENTER, SWT.FILL, true,
				true, 1, 1);
		gd_composite_1.widthHint = 300;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setLayout(new GridLayout(2, false));

		Label userLabel = new Label(composite_1, SWT.NONE);
		userLabel.setText("用户名：");

		usernameText = new Text(composite_1, SWT.BORDER);
		usernameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label passwordLabel = new Label(composite_1, SWT.NONE);
		passwordLabel.setText("密码：");

		passwordText = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		return area;
	}

	private String format(Date time) {
		Format format = new SimpleDateFormat("yyyy年MM月dd号 HH时mm分ss秒");
		if (time != null) {
			return format.format(time);
		}
		return "";
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setImage(image);
		newShell.setText("欢迎登录");
		super.configureShell(newShell);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(400, 300);
	}

	public static void main(String[] args) {
		new LoginDialog(null).open();
	}
}
