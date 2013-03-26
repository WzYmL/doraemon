package org.salever.rcp.tech.chapter17;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {

	public static final String ID = Messages.View_0;
	
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		top.setLayout(layout);
		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		banner.setLayout(layout);
		
		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
		
		Label l = new Label(banner, SWT.WRAP);
		l.setText(Messages.View_1);
		l.setFont(boldFont);
		l = new Label(banner, SWT.WRAP);
		l.setText(Messages.View_2);
		
		l = new Label(banner, SWT.WRAP);
		l.setText(Messages.View_3);
		l.setFont(boldFont);
    
		final Link link = new Link(banner, SWT.NONE);
		link.setText(Messages.View_4);
		link.addSelectionListener(new SelectionAdapter() {    
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(getSite().getShell(), Messages.View_5, Messages.View_6);
			}    
		});
    
		l = new Label(banner, SWT.WRAP);
		l.setText(Messages.View_7);
		l.setFont(boldFont);
		l = new Label(banner, SWT.WRAP);
		l.setText(Messages.View_8);
		// message contents
		Text text = new Text(top, SWT.MULTI | SWT.WRAP);
		text.setText(Messages.View_9+
						Messages.View_10+
						Messages.View_11 +
						Messages.View_12+
						Messages.View_13+
						Messages.View_14+
						Messages.View_15+
						Messages.View_16);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	public void setFocus() {
	}
}
