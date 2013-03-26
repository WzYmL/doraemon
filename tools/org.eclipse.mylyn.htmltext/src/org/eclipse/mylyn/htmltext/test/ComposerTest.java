package org.eclipse.mylyn.htmltext.test;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.mylyn.htmltext.HtmlComposer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ComposerTest extends TitleAreaDialog {

	private String url;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ComposerTest(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		url = "file://E:/workspace/common_ws/org.eclipse.mylyn.htmltext/eclipsebridge/base.html";
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		
		
		final HtmlComposer composer = new HtmlComposer(container, SWT.NONE, url);
		
		composer.getBrowser().addKeyListener(new KeyListener() {
			
			public void keyReleased(KeyEvent e) {
				
				
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.stateMask == SWT.CTRL && e.keyCode == 'c'){
					System.out.println("CTRL + C");
				}
				System.out.println("CTRL + X");
			}
		});
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(getShell(), "HTML", composer.getHtml());
			}
		});
		button.setText("New Button");
		composer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composer.setHtml("<p>\n"
				+ "	This is some <strong>sample text</strong>. You are using <a href=\"http://ckeditor.com/\">CKEditor</a>.</p>");
		

	    getShell().getDisplay().addFilter(SWT.KeyDown, new Listener()
	    {
	     public void handleEvent(Event e)
	     {
	    	 if(e.character == 3){
	    		 System.out.println("CTRL + C");
	    		 e.doit = false;
	    		 return;
	    	 }
	      if ((e.stateMask == SWT.CTRL) && (e.keyCode == 'c'))
	      {
	    	  System.out.println("CTRL + C");
	    	  e.doit = false;
	    	  return;
	    	  
	    
	      }
	      int i = e.character;
	      System.out.println(e.toString() + i);
	     }
	    });;
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
		return new Point(740, 668);
	}

	public static void main(String[] args){
		new ComposerTest(null).open();
	}
}
