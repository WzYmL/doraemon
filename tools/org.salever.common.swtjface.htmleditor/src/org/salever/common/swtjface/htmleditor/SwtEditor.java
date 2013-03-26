package org.salever.common.swtjface.htmleditor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.salever.common.swtjface.htmleditor.actions.jsCommands;
import org.salever.common.swtjface.htmleditor.popups.Hyperlink;
import org.salever.common.swtjface.htmleditor.popups.Table;

/**
 * @author Carlos M�ndez P�rez, Javier S�ez Gasc�n
 * 
 */
public class SwtEditor extends Composite {

	private Browser browser = null;
	private String html = null;
	private String textAreaValue = null;
	private ToolBar toolBar = null;
	boolean showHTML = false;

	public SwtEditor(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		createToolBar();
		createBrowser();
		setSize(new Point(672, 200));
		setLayout(new GridLayout());
	}

	/**
	 * This method initializes browser	
	 *
	 */
	private void createBrowser() {
		if (html==null)html = jsCommands.createFrame(null,textAreaValue);
		GridData gridData1 = new org.eclipse.swt.layout.GridData();
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalSpan = 1;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		
		browser = new Browser(this, SWT.SINGLE | SWT.BORDER);	
		browser.setText(html);		
		browser.setLayoutData(gridData1);
		browser
				.addProgressListener(new org.eclipse.swt.browser.ProgressAdapter() {
					public void completed(
							org.eclipse.swt.browser.ProgressEvent e) {
						jsCommands.editMode(browser);
					}
				});
	}
	
	private void loadFile() {
		FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
        fileDialog.setText("Open");
        //fileDialog.setFilterPath("C:/");
        String[] filterExt = { "*.html", "*.*" };
        fileDialog.setFilterExtensions(filterExt);
        String selected = fileDialog.open();
        if (selected == null)
          return;
        // code here to open the file and display
        FileReader file = null;
        try {
          file = new FileReader(selected);
        } catch (FileNotFoundException e) {
          MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR
              | SWT.OK);
          messageBox.setMessage("Could not open file.");
          messageBox.setText("Error");
          messageBox.open();
          return;
        }
        BufferedReader fileInput = new BufferedReader(file);
        String text = null;
        StringBuffer sb = new StringBuffer();
        try {
          do {
            if (text != null)
              sb.append(text);
          } while ((text = fileInput.readLine()) != null);
        } catch (IOException e1) {
          MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR
              | SWT.OK);
          messageBox.setMessage("Could not write to file.");
          messageBox.setText("Error");
          messageBox.open();
          return;
        }
        textAreaValue = sb.toString();
        html = jsCommands.createFrame(null,textAreaValue);
        browser.setText(html);
        MessageBox messageBox = 
        	  new MessageBox(getShell(), SWT.OK|SWT.ICON_INFORMATION);
        	messageBox.setMessage("File Loaded");
        	messageBox.setText("Load File");
        	if (messageBox.open() == SWT.OK)
        	{
        		jsCommands.editMode(browser);
        	}      
      }

	/**
	 * This method initializes toolBar	
	 *
	 */
	private void createToolBar() {
		toolBar = new ToolBar(this, SWT.BORDER);
		ToolItem toolItemLoad = new ToolItem(toolBar, SWT.PUSH);
		toolItemLoad.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p04.png")));
		toolItemLoad.setWidth(50);
		ToolItem toolItemBold = new ToolItem(toolBar, SWT.PUSH);
		toolItemBold.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p07.png")));
		toolItemBold.setWidth(32);
		ToolItem toolItemItalic = new ToolItem(toolBar, SWT.PUSH);
		toolItemItalic.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p10.png")));
		toolItemItalic.setWidth(32);
		ToolItem toolItemUnderline = new ToolItem(toolBar, SWT.PUSH);
		toolItemUnderline.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p24.png")));
		toolItemUnderline.setWidth(32);
		ToolItem toolItemStrikeThrough = new ToolItem(toolBar, SWT.PUSH);
		toolItemStrikeThrough.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p38.png")));
		toolItemStrikeThrough.setWidth(32);
		ToolItem toolItemSuperScript = new ToolItem(toolBar, SWT.PUSH);
		toolItemSuperScript.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p80.png")));
		toolItemSuperScript.setText("");
		toolItemSuperScript.setWidth(32);
		ToolItem toolItemSubScript = new ToolItem(toolBar, SWT.PUSH);
		toolItemSubScript.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p79.png")));
		toolItemSubScript.setText("");
		toolItemSubScript.setWidth(32);
		ToolItem toolItemColor = new ToolItem(toolBar, SWT.PUSH);
		toolItemColor.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p37.png")));
		toolItemColor.setText("");
		toolItemColor.setWidth(32);
		ToolItem toolItemCenter = new ToolItem(toolBar, SWT.PUSH);
		toolItemCenter.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p05.png")));
		toolItemCenter.setText("");
		toolItemCenter.setWidth(32);
		ToolItem toolItemLeft = new ToolItem(toolBar, SWT.PUSH);
		toolItemLeft.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p06.png")));
		toolItemLeft.setText("");
		toolItemLeft.setWidth(32);
		ToolItem toolItemRight = new ToolItem(toolBar, SWT.PUSH);
		toolItemRight.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p15.png")));
		toolItemRight.setWidth(48);
		ToolItem toolItemExpand = new ToolItem(toolBar, SWT.PUSH);
		toolItemExpand.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p13.png")));
		toolItemExpand.setWidth(32);
		ToolItem toolItemShowCode = new ToolItem(toolBar, SWT.PUSH);
		toolItemShowCode.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p90.png")));
		toolItemShowCode.setWidth(32);
		ToolItem toolItemNumberedList = new ToolItem(toolBar, SWT.PUSH);
		toolItemNumberedList.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p81.png")));
		toolItemNumberedList.setWidth(32);
		ToolItem toolItemList = new ToolItem(toolBar, SWT.PUSH);
		toolItemList.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p82.png")));
		toolItemList.setWidth(32);
		ToolItem toolItemJustifyLeft = new ToolItem(toolBar, SWT.PUSH);
		toolItemJustifyLeft.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p112.png")));
		toolItemJustifyLeft.setWidth(32);
		ToolItem toolItemJustifyLess = new ToolItem(toolBar, SWT.PUSH);
		toolItemJustifyLess.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p113.png")));
		toolItemJustifyLess.setWidth(32);
		ToolItem toolItemHiperLink = new ToolItem(toolBar, SWT.PUSH);
		toolItemHiperLink.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p45.png")));
		toolItemHiperLink.setWidth(32);
		ToolItem toolItemTable = new ToolItem(toolBar, SWT.PUSH);
		toolItemTable.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p14.png")));
		toolItemTable.setWidth(32);
		ToolItem toolItemImage = new ToolItem(toolBar, SWT.PUSH);
		toolItemImage.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p144.png")));
		toolItemImage.setWidth(32);
		ToolItem toolItemRule = new ToolItem(toolBar, SWT.PUSH);
		toolItemRule.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/22x22/p102.png")));
		toolItemRule.setWidth(32);
		toolItemRule
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser,
								"InsertHorizontalRule", "false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemImage
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						 org.salever.common.swtjface.htmleditor.popups.Image img = new org.salever.common.swtjface.htmleditor.popups.Image();
						 Shell sh = img.createSShell(getShell(),browser);
						 sh.addShellListener(new org.eclipse.swt.events.ShellListener(){
								public void shellActivated(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellClosed(ShellEvent e) {
									// TODO Auto-generated method stub
									getShell().setEnabled(true);
								}

								public void shellDeactivated(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellDeiconified(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellIconified(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}
							
						 });
						 sh.open();					
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemTable
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						 org.salever.common.swtjface.htmleditor.popups.Table hl = new Table();
						 Shell sh = hl.createSShell(getShell(),browser);
						 sh.addShellListener(new org.eclipse.swt.events.ShellListener(){
								public void shellActivated(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellClosed(ShellEvent e) {
									// TODO Auto-generated method stub
									getShell().setEnabled(true);
								}

								public void shellDeactivated(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellDeiconified(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellIconified(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}
							
						 });
						 sh.open();					
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemHiperLink
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						 Hyperlink hl = new Hyperlink();
						 Shell sh = hl.createSShell(getShell(),browser);
						 sh.addShellListener(new org.eclipse.swt.events.ShellListener(){
								public void shellActivated(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellClosed(ShellEvent e) {
									// TODO Auto-generated method stub
									getShell().setEnabled(true);
								}

								public void shellDeactivated(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellDeiconified(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}

								public void shellIconified(ShellEvent e) {
									// TODO Auto-generated method stub
									
								}
							
						 });						 
						 sh.open();
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemJustifyLess
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Outdent", "false",
						"0");					
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemJustifyLeft
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Indent", "false",
						"0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemList
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser,
								"InsertUnorderedList", "false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemNumberedList
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "InsertOrderedList",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemShowCode
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						if (showHTML )
						{
							jsCommands.copyTextArea2iFrame(browser);
							showHTML=false;
						}	
						else
						{
							jsCommands.copyiFrame2TextArea(browser);	
							showHTML=true;
						}
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemExpand
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyFull",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemRight
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyRight",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemLeft
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyLeft",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemCenter
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyCenter",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemColor
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						ColorDialog dlg = new ColorDialog(getShell());
						RGB color = new RGB(0, 0, 0);
						dlg.open();
						color = dlg.getRGB();
						jsCommands.executeCommand(browser, "forecolor",
								"false", jsCommands.color (color,"."));
						//jsCommands.Write (browser,jsCommands.color (color,"."));
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemSubScript
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "SubScript",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemSuperScript
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "SuperScript",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemStrikeThrough
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "StrikeThrough",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemUnderline
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Underline",
								"false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemItalic
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Italic", "false",
						"0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemBold
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						jsCommands
						.executeCommand(browser, "Bold", "false", "0");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolItemLoad
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						loadFile();
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
	}		

}  //  @jve:decl-index=0:visual-constraint="10,10"
