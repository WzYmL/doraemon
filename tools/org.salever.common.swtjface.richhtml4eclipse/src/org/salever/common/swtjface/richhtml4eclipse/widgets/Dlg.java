/**
 * 
 */
package org.salever.common.swtjface.richhtml4eclipse.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.salever.common.swtjface.richhtml4eclipse.widgets.HtmlEditComposite;

/**
 * @author LiXiaopeng
 * 
 */
public class Dlg extends Dialog {

	TinyMCEPath tinyMCEPath = TinyMCEPath.getCurrentPath();

	protected Dlg(Shell parentShell) {
		super(parentShell);
		tinyMCEPath.setUrl("c:\\" + TinyMCEPath.BASE_PATH);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite com = (Composite) super.createDialogArea(parent);
		HtmlEditComposite c = new HtmlEditComposite(com, SWT.NONE,
				tinyMCEPath.getUrl());
		c.setLayoutData(new GridData(GridData.FILL_BOTH));
		return com;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Dlg(null).open();

	}

	@Override
	protected Point getInitialSize() {
		// TODO Auto-generated method stub
		return new Point(1000, 800);
	}

}
