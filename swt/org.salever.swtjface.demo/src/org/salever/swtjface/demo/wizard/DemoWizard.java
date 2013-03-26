package org.salever.swtjface.demo.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DemoWizard extends Wizard {

	private DemoWizardPage1 page1;
	private DemoWizardPage2 page2;
	
	public DemoWizard() {
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
		page1 = new DemoWizardPage1();
		addPage(page1);
		
		page2 = new DemoWizardPage2();
		addPage(page2);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	public static void main(String[] args){
		Display d = new Display();
		Shell shell = new Shell(d);
		DemoWizard wizard = new DemoWizard();
		WizardDialog dlg = new WizardDialog(shell, wizard);
		dlg.open();
		
	}
	
	@Override
	public boolean canFinish() {
		
		return super.canFinish();
	}
}
