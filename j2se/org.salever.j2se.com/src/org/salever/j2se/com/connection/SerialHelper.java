/**
 * 
 */
package org.salever.j2se.com.connection;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.salever.j2se.com.Messages;
import org.salever.j2se.com.model.SerialConnectionException;


/**
 * @author salever
 * 
 */
public class SerialHelper {

	/**
	 * 打开串口
	 * 
	 * @param shell
	 * @return
	 */
	public static BusinessSerialConnection openSerialConnection(Shell shell,
			final boolean forceRestart) {
		boolean isRunning = SerialConnectionManager.chechConnectionRunning();
		if (isRunning && forceRestart) {
			if (!MessageDialog.openQuestion(shell, Messages.SerialHelper_notify, Messages.SerialHelper_comReconnect)) {
				return SerialConnectionManager.getConnection();
			}
		}

		if (isRunning && !forceRestart) {
			MessageDialog.openInformation(shell, Messages.SerialHelper_notify, Messages.SerialHelper_comConnected);
			return SerialConnectionManager.getConnection();
		}

		try {
			new ProgressMonitorDialog(shell).run(false, false,
					new IRunnableWithProgress() {

						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException,
								InterruptedException {
							try {
								SerialConnectionManager.connectSerialCom();
							} catch (SerialConnectionException e) {
								throw new InvocationTargetException(e);
							}
						}
					});
		} catch (Exception e) {
			MessageDialog.openError(shell, Messages.SerialHelper_notify,
					Messages.SerialHelper_comOpenFail
							+ (e.getCause() != null ? e.getCause().getMessage()
									: e.getMessage()));
			return SerialConnectionManager.getConnection();

		}
		MessageDialog.openInformation(shell, Messages.SerialHelper_notify, Messages.SerialHelper_comOpenOK);
		return SerialConnectionManager.getConnection();
	}
}
