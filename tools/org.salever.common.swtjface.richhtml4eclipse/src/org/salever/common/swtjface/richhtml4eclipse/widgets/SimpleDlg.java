/**
 * 
 */
package org.salever.common.swtjface.richhtml4eclipse.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author LiXiaopeng
 * 
 */
public class SimpleDlg extends Dialog {

	public SimpleHtmlEditComposite c;
	TinyMCEPath tinyMCEPath = TinyMCEPath.getCurrentPath();

	protected SimpleDlg(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
		tinyMCEPath.setUrl("c:\\" + TinyMCEPath.BASE_PATH);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite com = (Composite) super.createDialogArea(parent);
		c = new SimpleHtmlEditComposite(com, SWT.NONE, "aaaaaaa",
				tinyMCEPath.getUrl());
		c.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button button = new Button(com, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				c.resetHtml("BBBBBB");
			}
		});
		button.setText("New Button");
		return com;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDlg dlg = new SimpleDlg(null);
		if (dlg.open() == Window.OK) {

		}

	}

	@Override
	protected Point getInitialSize() {
		// TODO Auto-generated method stub
		return new Point(1000, 800);
	}

}
