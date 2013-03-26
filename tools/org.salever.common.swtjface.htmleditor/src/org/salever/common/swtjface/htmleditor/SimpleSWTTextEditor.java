package org.salever.common.swtjface.htmleditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.salever.common.swtjface.htmleditor.actions.jsCommands;
import org.salever.common.swtjface.htmleditor.popups.Hyperlink;
import org.salever.common.swtjface.htmleditor.popups.Table;

public class SimpleSWTTextEditor {

	public org.eclipse.swt.widgets.Shell sShell = null; // @jve:decl-index=0:visual-constraint="-14,14"

	private boolean hasChanged = false;

	private boolean isClosing = false;

	private static final String title = "Simple Text Editor"; // @jve:decl-index=0:

	private static final String NEW_LINE = System.getProperty("line.separator");

	private Browser browser = null;

	private ToolBar toolBarOthers = null;

	private Text textArea = null;

	private String html; // @jve:decl-index=0:

	private Button button, button2 = null;

	boolean showHTML = false;

	private String textAreaValue;

	/**
	 * This method initializes MaintoolBar
	 * 
	 */
	private void createMaintoolBar() {

	}

	/**
	 * This method initializes browser
	 * 
	 */
	private void createBrowser() {
		// =
		// "<html><title></title><body topmargin=0 leftmargin=0><iframe height=\"100%\" width=\"100%\" name=\"frmEditor\" id=\"frmEditor\" src=\"about:blank\"></iframe></body></html>";

		if (html == null)
			html = jsCommands.createFrame(null, null);
		GridData gridData1 = new org.eclipse.swt.layout.GridData();
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalSpan = 1;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		// TabFolder tabFolder = new TabFolder(sShell, SWT.NONE);
		// tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
		// 1, 1));
		//
		// TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		// tabItem.setText("New Item");
		// Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		// tabItem.setControl(composite_1);
		//
		// TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		// tabItem_1.setText("New Item");

		browser = new Browser(sShell, SWT.SINGLE | SWT.BORDER);
		browser.setText(html);
		browser.setLayoutData(gridData1);
		browser.addProgressListener(new org.eclipse.swt.browser.ProgressAdapter() {
			public void completed(org.eclipse.swt.browser.ProgressEvent e) {
				System.out.println("Complete:" + textAreaValue);
				jsCommands.editMode(browser);
			}
		});
	}

	/**
	 * This method initializes toolBarOthers
	 * 
	 */
	private void createToolBarOthers() {
		toolBarOthers = new ToolBar(sShell, SWT.NONE);
		ToolItem toolBarExit = new ToolItem(toolBarOthers, SWT.PUSH);
		ToolItem btnSave = new ToolItem(toolBarOthers, SWT.PUSH);
		btnSave.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p17.png")));
		ToolItem btlLoad = new ToolItem(toolBarOthers, SWT.PUSH);
		btlLoad.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p23.png")));
		ToolItem separator1 = new ToolItem(toolBarOthers, SWT.SEPARATOR);
		ToolItem toolBarBold = new ToolItem(toolBarOthers, SWT.PUSH);
		ToolItem toolBarItalic = new ToolItem(toolBarOthers, SWT.PUSH);
		ToolItem toolBarUnderline = new ToolItem(toolBarOthers, SWT.PUSH);
		ToolItem toolBarStrikeThrough = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarStrikeThrough.setImage(new Image(Display.getCurrent(),
				getClass().getResourceAsStream("icons/22x22/p38.png")));
		ToolItem toolBarSuperIndice = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarSuperIndice.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p80.png")));
		ToolItem toolBarSubIndice = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarSubIndice.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p79.png")));
		ToolItem btnFont = new ToolItem(toolBarOthers, SWT.PUSH);
		btnFont.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p111.png")));
		ToolItem bntColor = new ToolItem(toolBarOthers, SWT.PUSH);
		bntColor.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p116.png")));
		ToolItem separator2 = new ToolItem(toolBarOthers, SWT.SEPARATOR);
		ToolItem toolBarCenter = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarCenter.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p05.png")));
		toolBarCenter
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyCenter",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolBarLeft = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarLeft.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p06.png")));
		toolBarLeft
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyLeft",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolBarRight = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarRight.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p15.png")));
		toolBarRight
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyRight",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolBarFull = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarFull.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p13.png")));
		ToolItem btnShowHTML = new ToolItem(toolBarOthers, SWT.PUSH);
		btnShowHTML.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p90.png")));
		btnShowHTML
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						if (showHTML) {
							jsCommands.copyTextArea2iFrame(browser);
							showHTML = false;
						} else {
							jsCommands.copyiFrame2TextArea(browser);
							showHTML = true;
						}
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarFull
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "JustifyFull",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarBold.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p07.png")));
		toolBarBold.setWidth(48);
		toolBarBold.setText("");
		toolBarBold
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands
								.executeCommand(browser, "Bold", "false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarItalic.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p10.png")));
		toolBarItalic.setWidth(48);
		toolBarItalic
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Italic", "false",
								"0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarUnderline.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p24.png")));
		toolBarUnderline.setWidth(48);
		ToolItem separator3 = new ToolItem(toolBarOthers, SWT.SEPARATOR);
		ToolItem toolBarNumberedList = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarNumberedList.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p81.png")));
		ToolItem toolBarList = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarList.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p82.png")));
		ToolItem toolBarIndent = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarIndent.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p112.png")));
		ToolItem toolBarOutdent = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarOutdent.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p113.png")));
		ToolItem separator4 = new ToolItem(toolBarOthers, SWT.SEPARATOR);
		toolBarUnderline
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Underline",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarSuperIndice
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "SuperScript",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarSubIndice
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "SubScript",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarStrikeThrough
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "StrikeThrough",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolBarHiperLink = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarHiperLink.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p26.png")));
		btnFont.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				FontDialog font = new FontDialog(sShell);
				font.open();
			}

			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		bntColor.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				ColorDialog dlg = new ColorDialog(sShell);
				RGB color = new RGB(0, 0, 0);
				dlg.open();
				color = dlg.getRGB();
				jsCommands.Write(browser, jsCommands.color(color, "."));
			}

			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		btlLoad.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				loadFile();
			}

			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		btnSave.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				saveFile();
			}

			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		toolBarExit.setText("");
		toolBarExit.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p62.png")));
		toolBarExit.setWidth(48);
		ToolItem toolBarTable = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarTable.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p68.png")));
		ToolItem btnImage = new ToolItem(toolBarOthers, SWT.PUSH);
		btnImage.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("icons/22x22/p144.png")));
		btnImage.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				org.salever.common.swtjface.htmleditor.popups.Image img = new org.salever.common.swtjface.htmleditor.popups.Image();
				Shell sh = img.createSShell(sShell, browser);
				sh.open();
			}

			public void widgetDefaultSelected(
					org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		toolBarTable
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						org.salever.common.swtjface.htmleditor.popups.Table hl = new Table();
						Shell sh = hl.createSShell(sShell, browser);
						sh.open();
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarHiperLink
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						Hyperlink hl = new Hyperlink();
						Shell sh = hl.createSShell(sShell, browser);
						sh.open();
						// sShell.setEnabled(false);

						// System.out.println(sh.getData("lblTest"));
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarExit
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						doExit();
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarNumberedList
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "InsertOrderedList",
								"false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarList
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser,
								"InsertUnorderedList", "false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarIndent
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Indent", "false",
								"0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolBarHorizontalRule = new ToolItem(toolBarOthers, SWT.PUSH);
		toolBarHorizontalRule.setImage(new Image(Display.getCurrent(),
				getClass().getResourceAsStream("icons/22x22/p102.png")));
		toolBarHorizontalRule.setWidth(48);
		toolBarOutdent
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser, "Outdent", "false",
								"0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		toolBarHorizontalRule
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent e) {
						jsCommands.executeCommand(browser,
								"InsertHorizontalRule", "false", "0");
					}

					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
	}

	public static void main(String[] args) {
		/*
		 * Before this is run, be sure to set up the following in the launch
		 * configuration (Arguments->VM Arguments) for the correct SWT library
		 * path. The following is a windows example: -Djava.library.path=
		 * "installation_directory\plugins\org.eclipse.swt.win32_3.0.0\os\win32\x86"
		 */
		org.eclipse.swt.widgets.Display display = org.eclipse.swt.widgets.Display
				.getDefault();
		SimpleSWTTextEditor thisClass = new SimpleSWTTextEditor();
		thisClass.createSShell();
		thisClass.sShell.open();

		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new org.eclipse.swt.widgets.Shell();
		org.eclipse.swt.layout.GridLayout gridLayout2 = new GridLayout();
		sShell.setText("Rich Text Editor");
		createToolBarOthers();
		sShell.setLayout(gridLayout2);
		textArea = new Text(sShell, SWT.MULTI | SWT.WRAP);
		textArea.setEnabled(true);
		textArea.setEditable(true);
		textArea.setVisible(true);
		createBrowser();
		gridLayout2.numColumns = 1;
		gridLayout2.makeColumnsEqualWidth = true;
		sShell.setSize(new Point(846, 444));
		sShell.addShellListener(new org.eclipse.swt.events.ShellAdapter() {
			public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
				browser.setFocus();
			}

			public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
				if (!isClosing) {
					e.doit = doExit();
				}
			}
		});
	}

	private void loadFile() {
		FileDialog fileDialog = new FileDialog(sShell, SWT.OPEN);
		fileDialog.setText("Open");
		// fileDialog.setFilterPath("C:/");
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
			MessageBox messageBox = new MessageBox(sShell, SWT.ICON_ERROR
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
			MessageBox messageBox = new MessageBox(sShell, SWT.ICON_ERROR
					| SWT.OK);
			messageBox.setMessage("Could not write to file.");
			messageBox.setText("Error");
			messageBox.open();
			return;
		}
		textAreaValue = sb.toString();
		html = jsCommands.createFrame(null, textAreaValue);
		browser.setText(html);
		MessageBox messageBox = new MessageBox(sShell, SWT.OK
				| SWT.ICON_INFORMATION);
		messageBox.setMessage("File Loaded");
		messageBox.setText("Load File");
		if (messageBox.open() == SWT.OK) {
			jsCommands.editMode(browser);
		}
	}

	private void saveFile() {
		
		String text = browser.getText();
		System.out.println(text);
		
		FileDialog dialog = new FileDialog(sShell, SWT.SAVE);
		String result = dialog.open();
		if (result != null) {
			File f = new File(result);
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				text = browser.getText();
				System.out.println(text);
				
				// String text = "@todo";
				bw.write(text);
				bw.close();
				sShell.setText(title);
				hasChanged = false;
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private boolean doExit() {
		if (hasChanged) {
			MessageBox mb = new MessageBox(sShell, SWT.ICON_QUESTION | SWT.YES
					| SWT.NO | SWT.CANCEL);
			mb.setText("Save Changes?");
			mb.setMessage("File has been changed. Save before exit?");
			int state = mb.open();
			if (state == SWT.YES) {
				saveFile();
			} else if (state == SWT.CANCEL) {
				return false;
			}
		}
		isClosing = true;
		sShell.close();
		sShell.dispose();
		return true;
	}
}
