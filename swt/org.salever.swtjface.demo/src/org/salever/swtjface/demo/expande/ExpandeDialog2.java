package org.salever.swtjface.demo.expande;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ExpandeDialog2 extends TitleAreaDialog {
	private Table table;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ExpandeDialog2(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		final Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		final Group group = new Group(container, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		GridData gd_group = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_group.heightHint = 150;
		group.setLayoutData(gd_group);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("New Label");
		
		final Button exapndeButton = new Button(group, SWT.NONE);
		GridData gd_exapndeButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_exapndeButton.widthHint = 100;
		exapndeButton.setLayoutData(gd_exapndeButton);
		exapndeButton.addSelectionListener(new SelectionAdapter() {
			boolean expande = true;
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(expande){
					expande = false;
					exapndeButton.setText("展开");
					GridData gd_group = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
					gd_group.heightHint = 50;
					group.setLayoutData(gd_group);
					container.layout();
				}else{
					expande = true;
					exapndeButton.setText("收起");
					GridData gd_group = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
					gd_group.heightHint = 150;
					group.setLayoutData(gd_group);
					container.layout();
				}
				
			}
		});
		exapndeButton.setText("收起");
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("New Label");
		new Label(group, SWT.NONE);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("New Label");
		
		Button btnOk = new Button(group, SWT.NONE);
		btnOk.setText("ok");
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(559, 523);
	}

	public static void main(String[] args){
		new ExpandeDialog2(null).open();
	}
}
