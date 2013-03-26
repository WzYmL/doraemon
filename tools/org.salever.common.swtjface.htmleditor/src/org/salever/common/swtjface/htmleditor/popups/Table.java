package org.salever.common.swtjface.htmleditor.popups;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CLabel;
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
public class Table {

	private Shell sShell = null;
	private CLabel cLabel = null;
	private Text txtfilas = null;
	private CLabel Columnas = null;
	private Text txtColumnas = null;
	private Label label = null;
	private Text txtWidth = null;
	private CLabel Border = null;
	private Text txtBorder = null;
	private CLabel cLabel1 = null;
	private Button button = null;
	private Button Cancel = null;
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
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = true;
		GridData gridData2 = new GridData();
		gridData2.grabExcessHorizontalSpace = true;
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		gridData.grabExcessHorizontalSpace = true;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		sShell = new Shell();
		sShell.setText("Insert Table");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(300, 200));
		cLabel1 = new CLabel(sShell, SWT.NONE);
		cLabel1.setText("Insert Table");
		cLabel1.setLayoutData(gridData);
		Label filler6 = new Label(sShell, SWT.NONE);
		cLabel = new CLabel(sShell, SWT.NONE);
		cLabel.setText("Rows");
		txtfilas = new Text(sShell, SWT.BORDER);
		txtfilas.setLayoutData(gridData1);
		Label filler10 = new Label(sShell, SWT.NONE);
		Label filler12 = new Label(sShell, SWT.NONE);
		Label filler5 = new Label(sShell, SWT.NONE);
		Columnas = new CLabel(sShell, SWT.NONE);
		Columnas.setText("Columns");
		txtColumnas = new Text(sShell, SWT.BORDER);
		Label filler9 = new Label(sShell, SWT.NONE);
		Label filler11 = new Label(sShell, SWT.NONE);
		Label filler4 = new Label(sShell, SWT.NONE);
		label = new Label(sShell, SWT.NONE);
		label.setText("Width");
		txtWidth = new Text(sShell, SWT.BORDER);
		txtWidth.setLayoutData(gridData2);
		Label filler8 = new Label(createSShell(), SWT.NONE);
		Label filler1 = new Label(sShell, SWT.NONE);
		Label filler3 = new Label(sShell, SWT.NONE);
		Border = new CLabel(sShell, SWT.NONE);
		Border.setText("Border");
		Border.setLayoutData(gridData3);
		txtBorder = new Text(sShell, SWT.BORDER);
		Label filler7 = new Label(sShell, SWT.NONE);
		Label filler = new Label(sShell, SWT.NONE);
		Label filler2 = new Label(sShell, SWT.NONE);
		Label filler13 = new Label(sShell, SWT.NONE);
		button = new Button(sShell, SWT.NONE);
		button.setText("Accept");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				Integer rows= null;
				Integer columns = null;
				String width = null,border=null;
				rows = (Integer) new Integer((String)txtfilas.getText());
				columns = (Integer) new Integer((String)txtColumnas.getText());
				width = txtWidth.getText();
				border = txtBorder.getText();
				jsCommands.Write(parentBrowser,org.salever.common.swtjface.htmleditor.actions.jsCommands.table(rows,columns, width, border));
				System.out.println(org.salever.common.swtjface.htmleditor.actions.jsCommands.table(rows,columns, width, border));
				parent.setEnabled(true);
				sShell.close();
			}
		});
		Cancel = new Button(sShell, SWT.NONE);
		Cancel.setText("Cancel");
		Cancel.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				parent.setEnabled(true);
				sShell.close();
			}
		});
		return sShell;
	}

}
