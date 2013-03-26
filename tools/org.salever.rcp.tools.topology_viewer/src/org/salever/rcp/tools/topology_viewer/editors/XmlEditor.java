package org.salever.rcp.tools.topology_viewer.editors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;

public class XmlEditor extends EditorPart {

	public static final String ID = "org.salever.rcp.tools.topology_viewer.editors.XmlEditor"; //$NON-NLS-1$

	private String path;

	private StyledText styledText;

	private boolean dirty;

	public XmlEditor() {
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		styledText = new StyledText(container, SWT.BORDER | SWT.WRAP);
		styledText.setText(readFromLocalFile(path));
		setHightlight();
		styledText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dirty = true;
				firePropertyChange(IEditorPart.PROP_DIRTY);
			}
		});
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		dirty = false;
		writeTextToLocalFile(path);
	}

	@Override
	public void doSaveAs() {
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.OPEN);
		dlg.setFilterExtensions(new String[] { "*.xml" });
		String filePath = dlg.open();
		if (filePath != null) {
			writeTextToLocalFile(filePath);
		}
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
		this.path = ((XmlEditorInput) input).getPath().toString();
		this.setPartName(path);
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	public String readFromLocalFile(String path) {
		String content;
		String line;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "utf-8"));
			content = "";
			while ((line = reader.readLine()) != null) {
				content += (line + "\r\n");
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return content;

	}

	@Override
	public void setFocus() {
		styledText.setFocus();
	}

	private void setHightlight() {
		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				ArrayList<StyleRange> rangeList = CustomFactory
						.getXMLHightlightRange(styledText.getText());

				for (int index = 0; index < rangeList.size(); ++index) {
					StyleRange range = rangeList.get(index);
					styledText.setStyleRange(range);
				}
			}
		});
	}

	private void writeTextToLocalFile(String currentFilePath) {
		try {
			PrintWriter writer = new PrintWriter(currentFilePath, "utf-8");
			writer.write(styledText.getText());
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
