package org.salever.rcp.remotesystem.client.ui.dialogs;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.salever.rcp.remoteSystem.client.net.core.NetCoreActivator;
import org.salever.rcp.remoteSystem.client.net.core.preferences.PreferenceConstants;
import org.salever.rcp.remoteSystem.server.model.Admin;
import org.salever.rcp.remoteSystem.server.service.LoginService;

/**
 * 需要添加修饰图片，或者公司logo效果，UI 暂定
 */
public class LoginDialog extends TitleAreaDialog {

	private Admin model;

	private Text username;
	private Text password;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public LoginDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);

	}

	@Override
	protected void buttonPressed(int buttonId) {

		if (buttonId == IDialogConstants.OK_ID) {

			if (username.getText() == null
					|| username.getText().trim().equals("")) { //$NON-NLS-1$
				MessageDialog.openInformation(getShell(), "提示", //$NON-NLS-1$
						"请输入用户名。"); //$NON-NLS-1$
				username.setFocus();
				return;
			}

			if (password.getText() == null
					|| password.getText().trim().equals("")) { //$NON-NLS-1$
				MessageDialog.openInformation(getShell(), "提示", //$NON-NLS-1$
						"请输入密码。"); //$NON-NLS-1$
				password.setFocus();
				return;
			}

			try {
				IPreferenceStore store = NetCoreActivator.getDefault()
						.getPreferenceStore();
				store.setDefault(PreferenceConstants.P_USERNAME,
						username.getText());
				store.setDefault(PreferenceConstants.P_PASSWORD,
						password.getText());
				NetCoreActivator activator = NetCoreActivator.getDefault();
				activator.initializeApplicationContext();
				LoginService service = activator.getBean(LoginService.class);
				model = service.login();
			} catch (Exception e) {
				MessageDialog.openError(getShell(), "登录失败", //$NON-NLS-1$
						"登录系统失败，请检查用户名和密码。" + e); //$NON-NLS-1$
				username.selectAll();
				password.setText(""); //$NON-NLS-1$
				username.setFocus();
				return;
			}

			if (model == null) {
				MessageDialog.openError(getShell(), "登录失败", //$NON-NLS-1$
						"登录系统失败，请检查用户名和密码。"); //$NON-NLS-1$
				username.selectAll();
				password.setText(""); //$NON-NLS-1$
				username.setFocus();
				return;
			} else {

			}

		}

		super.buttonPressed(buttonId);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("欢迎登录 "); //$NON-NLS-1$
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		Point location = new Point(Display.getCurrent().getClientArea().width
				/ 2 - getInitialSize().x / 2, Display.getCurrent()
				.getClientArea().height / 2 - getInitialSize().y / 2);
		return location;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {

		Button button = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
		button.setSelection(true);
		button.setText("登录"); //$NON-NLS-1$

		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消"); //$NON-NLS-1$

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("欢迎登录系统！"); //$NON-NLS-1$
		setMessage(format(Calendar.getInstance().getTime()));
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.marginWidth = 10;
		gl_container.marginLeft = 10;
		gl_container.marginHeight = 10;
		gl_container.horizontalSpacing = 10;
		gl_container.marginRight = 10;
		container.setLayout(gl_container);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite composite_1 = new Composite(container, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.CENTER, SWT.FILL, true,
				true, 1, 1);
		gd_composite_1.widthHint = 350;
		composite_1.setLayoutData(gd_composite_1);
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.marginTop = 15;
		gl_composite_1.verticalSpacing = 10;
		composite_1.setLayout(gl_composite_1);

		Label userLabel = new Label(composite_1, SWT.NONE);
		userLabel.setText("用户名："); //$NON-NLS-1$

		username = new Text(composite_1, SWT.BORDER);
		username.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label passwordLabel = new Label(composite_1, SWT.NONE);
		passwordLabel.setText("密码："); //$NON-NLS-1$

		password = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(composite_1, SWT.NONE);

		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1,
				1));
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginHeight = 0;
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		composite.setLayout(gl_composite);

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				configDataServer();
			}
		});
		btnNewButton_1.setText("数据中心配置"); //$NON-NLS-1$
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("（需要重新启动）"); //$NON-NLS-1$

		return area;
	}

	protected void configDataServer() {
		PreferencesUtil
				.createPreferenceDialogOn(
						getShell(),
						"com.readidtech.common.client.net.core.preferences.ServerPreferencePage", //$NON-NLS-1$
						new String[] { "com.readidtech.common.client.net.core.preferences.ServerPreferencePage" }, //$NON-NLS-1$
						null).open();

	}

	private String format(Date time) {
		Format format = new SimpleDateFormat("yyyy年MM月dd号 HH时mm分ss秒"); //$NON-NLS-1$
		if (time != null) {
			return format.format(time);
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(400, 292);
	}

	public Admin getModel() {
		return model;
	}
}
