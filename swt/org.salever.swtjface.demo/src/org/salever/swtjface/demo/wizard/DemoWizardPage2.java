package org.salever.swtjface.demo.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class DemoWizardPage2 extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public DemoWizardPage2() {
		super("wizardPage");
		setTitle("Wizard Page title 2");
		setDescription("Wizard Page description");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
	}
	
	@Override
	public boolean isPageComplete() {
		return isCurrentPage();
	}

}
