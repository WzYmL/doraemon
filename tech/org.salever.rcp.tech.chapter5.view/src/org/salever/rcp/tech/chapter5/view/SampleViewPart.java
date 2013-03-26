/**
 * 
 */
package org.salever.rcp.tech.chapter5.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class SampleViewPart extends ViewPart {

	public SampleViewPart() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Text text = new Text(parent, SWT.BORDER);
		text.setText("Imagine a fantastic user interface here");

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
