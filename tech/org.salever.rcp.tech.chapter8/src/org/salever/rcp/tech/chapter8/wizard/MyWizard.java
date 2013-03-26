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
		MessageDialog.openInformation(getShell(), "��Ϣ",
				"��ҳ1����д��" + one.getText1() + "\n��ҳ2����д��" + two.getText1());
		return true;
	}

}
