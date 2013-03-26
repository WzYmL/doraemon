package org.salever.common.swtjface.htmleditor.popups;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.salever.common.swtjface.htmleditor.actions.jsCommands;

/**
 * @author Carlos M�ndez P�rez, Javier S�ez Gasc�n
 * 
 */
public class Image {

	private Shell sShell = null;
	private Label Title = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Text txtTitle = null;
	private Label label = null;
	private Text txtURL = null;
	private Text txtWidth = null;
	private Text txtHeight = null;
	private Text txtBorder = null;
	private Button button = null;
	private Shell parent = null;
	private Browser parentBrowser = null;
	/**
	 * This method initializes sShell
	 */
	public Shell createSShell(Shell sh,Browser br)
	{
		parent = sh;
		parent.setEnabled(false);
		parentBrowser = br;
		return createSShell();
	}
	public Shell createSShell() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(300, 200));
		Title = new Label(sShell, SWT.NONE);
		Title.setText("Title");
		txtTitle = new Text(sShell, SWT.BORDER);
		label1 = new Label(sShell, SWT.NONE);
		label1.setText("URL");
		txtURL = new Text(sShell, SWT.BORDER);
		label2 = new Label(sShell, SWT.NONE);
		label2.setText("Widht");
		txtWidth = new Text(sShell, SWT.BORDER);
		label3 = new Label(sShell, SWT.NONE);
		label3.setText("Height");
		txtHeight = new Text(sShell, SWT.BORDER);
		label = new Label(sShell, SWT.NONE);
		label.setText("Border");
		txtBorder = new Text(sShell, SWT.BORDER);
		Label filler1 = new Label(sShell, SWT.NONE);
		Label filler = new Label(sShell, SWT.NONE);
		Label filler2 = new Label(sShell, SWT.NONE);
		button = new Button(sShell, SWT.NONE);
		button.setText("Accept");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				parent.setEnabled(true);
				jsCommands.Write(parentBrowser, org.salever.common.swtjface.htmleditor.actions.jsCommands.image(txtBorder.getText(), txtURL.getText(), txtWidth.getText(), txtHeight.getText(),txtTitle.getText()));
				//jsCommands.executeCommand(parentBrowser, "inserthtml", "false",
				//	org.tencompetence.tencc.rte.actions.jsCommands.image(txtBorder.getText(), txtURL.getText(), txtWidth.getText(), txtHeight.getText(),txtTitle.getText())
				//	);					
				
				sShell.close();
			}
		});
		return sShell;
	}

}
