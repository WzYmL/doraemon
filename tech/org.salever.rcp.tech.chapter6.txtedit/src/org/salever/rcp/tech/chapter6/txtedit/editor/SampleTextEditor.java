/**
 * 
 */
package org.salever.rcp.tech.chapter6.txtedit.editor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * @author salever@126.com
 * 
 */
public class SampleTextEditor extends EditorPart implements ISaveablePart2 {

	public static final String ID = "org.salever.rcp.tech.chapter6.txtedit.texteditor";

	private Text text;

	private boolean dirty;

	private FileInput input;

	/**
	 * 
	 */
	public SampleTextEditor() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {

		text = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL
				| SWT.CANCEL | SWT.MULTI);
		loadText();

		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				dirty = true;
				firePropertyChange(ISaveablePart2.PROP_DIRTY);
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {

		saveToLocal(input.getName());
		dirty = false;
		firePropertyChange(ISaveablePart2.PROP_DIRTY);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		FileDialog dialog = new FileDialog(getEditorSite().getShell(), SWT.SAVE);
		String path = dialog.open();
		if (path != null) {
			saveToLocal(path);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite,
	 * org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(input.getName());
		this.input = (FileInput) input;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * �����ļ�����
	 */
	private void loadText() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(input.getName()), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = reader.readLine();
			}
			reader.close();
			text.setText(sb.toString());
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
	}

	@Override
	public int promptToSaveOnClose() {
		if (dirty) {
			if (MessageDialog.openConfirm(getEditorSite().getShell(), "��ʾ",
					"����δ���棬ȷ�ϼ���رղ�����")) {
				return ISaveablePart2.NO;
			} else {
				return ISaveablePart2.CANCEL;
			}
		}
		return YES;
	}

	/**
	 * �����ļ����ݵ�����
	 * 
	 * @param path
	 */
	private void saveToLocal(String path) {
		try {
			OutputStream out = new FileOutputStream(path);
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
			writer.write(text.getText());
			writer.close();
			out.close();
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		text.setFocus();
	}

}
