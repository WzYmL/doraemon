/**
 * 
 */
package org.salever.common.swtjface.extend.widget.util;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

/**
 * @author oracle
 * 
 */
public final class ProcessDialogUtil {

	private ProcessDialogUtil() {
	}

	public static void runProcessDialog(Shell shell, boolean fork,
			boolean cancelable, IRunnableWithProgress runnable) {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
		try {
			dialog.run(fork, cancelable, runnable);
		} catch (InvocationTargetException e) {
			// ignore
		} catch (InterruptedException e) {
			// cancel
		}

	}
}
