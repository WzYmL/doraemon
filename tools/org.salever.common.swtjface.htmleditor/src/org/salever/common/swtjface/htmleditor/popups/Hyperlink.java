package org.salever.common.swtjface.htmleditor.popups;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
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
public class Hyperlink {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Label Title = null;
	private Text lblTitle = null;
	private Label url = null;
	private Text lblUrl = null;
	private Label target = null;
	private Text lblTarget = null;
	private Button accept = null;
	private Button cancel = null;
	private Label label = null;
	private Browser browser = null;
	private Label label1 = null;
	private Label text = null;
	private Text lblText = null;
	private Shell parent = null;  //  @jve:decl-index=0:
	private Browser parentBrowser = null;
	/**
	 * This method initializes sShell
	 * @return 
	 */
	public Shell createSShell(Shell sh,Browser br)
	{
		parent = sh;
		parent.setEnabled(false);
		parentBrowser = br;
		return createSShell();
	}
	
	public Shell createSShell() {
		GridData gridData21 = new GridData();
		gridData21.horizontalSpan = 2;
		gridData21.widthHint = 250;
		gridData21.grabExcessHorizontalSpace = true;
		GridData gridData11 = new GridData();
		gridData11.horizontalSpan = 3;
		GridData gridData3 = new GridData();
		gridData3.horizontalSpan = 3;
		GridData gridData2 = new GridData();
		gridData2.horizontalSpan = 2;
		gridData2.widthHint = 250;
		gridData2.grabExcessHorizontalSpace = false;
		GridData gridData1 = new GridData();
		gridData1.widthHint = 250;
		gridData1.horizontalSpan = 2;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.heightHint = -1;
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.widthHint = 250;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		sShell = new Shell();
		sShell.setText("Insert Hiperlink");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(416, 297));
		label = new Label(sShell, SWT.NONE);
		label.setText("Insert Link");
		label.setLayoutData(gridData3);
		text = new Label(sShell, SWT.NONE);
		text.setText("Text");
		lblText = new Text(sShell, SWT.BORDER);
		lblText.setText("TEST");
		lblText.setLayoutData(gridData21);
		Title = new Label(sShell, SWT.NONE);
		Title.setText("Title");
		lblTitle = new Text(sShell, SWT.BORDER);
		lblTitle.setLayoutData(gridData);
		url = new Label(sShell, SWT.NONE);
		url.setText("URL");
		lblUrl = new Text(sShell, SWT.BORDER);
		lblUrl.setLayoutData(gridData2);
		target = new Label(sShell, SWT.NONE);
		target.setText("Target");
		lblTarget = new Text(sShell, SWT.BORDER);
		lblTarget.setLayoutData(gridData1);
		Label filler4 = new Label(sShell, SWT.NONE);
		accept = new Button(sShell, SWT.NONE);
		accept.setText("Accept");
		accept.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				if (lblUrl.getText() != "") {
				browser.setUrl(lblUrl.getText());
				System.out.println(org.salever.common.swtjface.htmleditor.actions.jsCommands.hiperLink(lblText.getText(), lblTitle.getText(), lblUrl.getText(), lblTarget.getText()));
				parent.setEnabled(true);
				jsCommands.Write(parentBrowser, org.salever.common.swtjface.htmleditor.actions.jsCommands.hiperLink(lblText.getText(), lblTitle.getText(), lblUrl.getText(), lblTarget.getText()));
				sShell.close();
				}
			}
		});
		cancel = new Button(sShell, SWT.NONE);
		cancel.setText("Cancel");
		label1 = new Label(sShell, SWT.NONE);
		label1.setText("Preview");
		label1.setLayoutData(gridData11);
		createBrowser();
		cancel.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				parent.setEnabled(true);
				sShell.close();
			}
		});
		return sShell;
	}

	/**
	 * This method initializes browser	
	 *
	 */
	private void createBrowser() {
		GridData gridData4 = new GridData();
		gridData4.horizontalSpan = 3;
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.grabExcessVerticalSpace = true;
		gridData4.heightHint = 200;
		gridData4.widthHint = 400;
		browser = new Browser(sShell, SWT.NONE);
		browser.setLayoutData(gridData4);
	}

}
