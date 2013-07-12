package org.salever.rcp.dbsystem.client.ui.intro;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.salever.rcp.dbSystem.client.db.model.Admin;
import org.salever.rcp.dbsystem.client.ui.dialogs.LoginDialog;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(700, 550));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(false);
		configurer.setTitle("RCP Product");
	}

	@Override
	public void postWindowCreate() {
		doLogin();
		super.postWindowCreate();
		getWindowConfigurer().getWindow().getShell().setMaximized(true);
	}

	private void doLogin() {
		LoginDialog dlg = new LoginDialog(getWindowConfigurer().getWindow()
				.getShell());
		if (dlg.open() != Window.OK) {
			System.exit(0);
		}
		Admin admin = dlg.getModel();

		ApplicationActionBarAdvisor.getStatusLine().setMessage(
				"欢迎管理员：" + admin.getUsername());
	}
}
