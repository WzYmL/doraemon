package org.salever.rcp.tech.chapter8.wizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard {

	private MyPageOne one;
	private MyPageTwo two;

	public MyWizard() {
		super();
		setNeedsProgressMonitor(true);

	}

	public void addPages() {
		one = new MyPageOne();
		two = new MyPageTwo();
		addPage(one);
		addPage(two);

	}

	public boolean performFinish() {
		MessageDialog.openInformation(getShell(), "信息",
				"向导页1内填写了" + one.getText1() + "\n向导页2内填写了" + two.getText1());
		return true;
	}

}
