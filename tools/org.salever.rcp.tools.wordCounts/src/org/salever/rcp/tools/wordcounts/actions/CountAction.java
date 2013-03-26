/**
 * 
 */
package org.salever.rcp.tools.wordcounts.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.salever.rcp.tools.wordcounts.Activator;
import org.salever.rcp.tools.wordcounts.dialog.WordCountDailgo;
import org.salever.rcp.tools.wordcounts.editors.FileInput;
import org.salever.rcp.tools.wordcounts.editors.SampleTextEditor;

/**
 * @author
 * 
 */
public class CountAction extends Action {

	private static final String ID = "org.salever.rcp.tech.chapter6.txtedit.action.CountAction"; //$NON-NLS-1$

	private IWorkbenchWindow window;

	public CountAction(IWorkbenchWindow window) {
		this.window = window;
		setText(Messages.CountAction_1);
		setId(ID);
		setImageDescriptor(Activator.getImageDescriptor("icons/open.gif")); //$NON-NLS-1$
	}

	@Override
	public void run() {

		IWorkbenchPage page = window.getActivePage();
		IEditorPart activeEditor = page.getActiveEditor();
		if (activeEditor instanceof SampleTextEditor) {
			SampleTextEditor editor = (SampleTextEditor) activeEditor;
			IEditorInput editorInput = editor.getEditorInput();
			FileInput input = (FileInput) editorInput;
			WordCountDailgo dialog = new WordCountDailgo(window.getShell(),
					editor.getText().getText(), input.getName());
			dialog.open();
		}

	}
}
