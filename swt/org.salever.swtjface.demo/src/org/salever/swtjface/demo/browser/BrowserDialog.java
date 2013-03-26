package org.salever.swtjface.demo.browser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class BrowserDialog extends TitleAreaDialog {

	private Browser browser;
	private String html;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public BrowserDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		Composite area = new Composite(container, SWT.NONE);

		area.setLayout(new GridLayout(2, false));
		area.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite browserComposite = new Composite(area, SWT.NONE);
		browserComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		browserComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		browser = new Browser(browserComposite, SWT.NONE);
		browser.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				MessageDialog.openInformation(getShell(), "keyReleased", e.toString());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				MessageDialog.openInformation(getShell(), "keyPressed", e.toString());
			}
		});
		
		Composite buttonComposite = new Composite(area, SWT.NONE);
		buttonComposite.setLayout(new GridLayout(1, false));
		buttonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1));

		Button saveHtmlButton = new Button(buttonComposite, SWT.NONE);
		saveHtmlButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAsHtml();
			}
		});
		GridData gd_saveHtmlButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_saveHtmlButton.widthHint = 100;
		saveHtmlButton.setLayoutData(gd_saveHtmlButton);
		saveHtmlButton.setText("保存为HTML");

		Button saveWordButton = new Button(buttonComposite, SWT.NONE);
		saveWordButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAsWord();
			}
		});
		GridData gd_saveWordButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_saveWordButton.widthHint = 100;
		saveWordButton.setLayoutData(gd_saveWordButton);
		saveWordButton.setText("保存为Word");

		Button savePdfButton = new Button(buttonComposite, SWT.NONE);
		savePdfButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAsPdf();
			}
		});
		GridData gd_savePdfButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_savePdfButton.widthHint = 100;
		savePdfButton.setLayoutData(gd_savePdfButton);
		savePdfButton.setText("保存为PDF");

		initBrowser();

		return area;
	}

	private void initBrowser() {
		// browser.setText(html);
		browser.setUrl("www.baidu.com");
	}

	protected void saveAsPdf() {
		FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
		dialog.setFilterExtensions(new String[] { "*.pdf", "*.*" });
		String path = dialog.open();
		if (path != null) {
			savePDF(path, browser.getText());
		}

	}

	private void savePDF(String path, String text) {
		String tempHtml = "www.baidu.com";
		saveHtml(tempHtml, text);
		
	}

	protected void saveAsWord() {

	}

	protected void saveAsHtml() {
		FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
		dialog.setFilterExtensions(new String[] { "*.html", "*.*" });
		String path = dialog.open();
		if (path != null) {
			saveHtml(path, browser.getText());
		}

	}

	private void saveHtml(String path, String text) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream out = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
			writer.write(browser.getText());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create contents of the button bar.
	 * 
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
	// @Override
	// protected Point getInitialSize() {
	// return new Point(450, 300);
	// }

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setMaximized(true);
		newShell.setLocation(Display.getCurrent().getClientArea().width / 2
	            - newShell.getShell().getSize().x / 2, Display.getCurrent()
	            .getClientArea().height / 2 - newShell.getSize().y / 2); 

	}

	public static void main(String[] args) {
		new BrowserDialog(null).open();
	}
}
