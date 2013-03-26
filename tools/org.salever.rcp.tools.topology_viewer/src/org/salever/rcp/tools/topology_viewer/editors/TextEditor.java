package org.salever.rcp.tools.topology_viewer.editors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
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

public class TextEditor extends EditorPart {

	public static final String ID = "org.salever.rcp.tools.topology_viewer.editors.TextEditor"; //$NON-NLS-1$
	private boolean dirty = false;
	private String path;
	private StyledText styledText;

	public TextEditor() {
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
		path = getEditorInput().getName();
		setPartName(CustomFactory.getNameFromPath(path));
		{
			styledText = new StyledText(container, SWT.BORDER | SWT.WRAP
					| SWT.H_SCROLL | SWT.V_SCROLL);
			styledText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					dirty = true;
					firePropertyChange(IEditorPart.PROP_DIRTY);
				}

			});
			styledText.setText(readFromLocalFile(path));
			dirty = false;
		}

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
		if (dirty) {
			if (path == "") {
				doSaveAs();
			} else {
				writeToLocalFile(path);
			}
			dirty = false;
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
		saveToLocalFile();
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
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

	/**
	 * 
	 */
	public void refreshText() {
		styledText.setText(readFromLocalFile(path));
		dirty = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	/**
	 * 
	 */
	private void saveToLocalFile() {

		FileDialog dlg = new FileDialog(Display.getCurrent().getActiveShell(),
				SWT.SAVE);
		dlg.setFileName(path);
		String fileName = dlg.open();
		if (fileName != null) {
			writeToLocalFile(fileName);
		}
	}

	@Override
	public void setFocus() {
		styledText.setFocus();
	}

	/**
	 * 
	 * @param path
	 * @param contet
	 * @throws UnsupportedEncodingException
	 */
	private void writeToLocalFile(String path) {
		try {
			PrintWriter writer = new PrintWriter(path, "utf-8");
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
