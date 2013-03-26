/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation 
 *     Email:salever@126.com
 *
 * Create on 2011-10-4 上午10:20:42
 *******************************************************************************/
package org.salever.common.swtjface.extend.dialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * 管理数据信息对话框的基类
 */
public abstract class AbstractManageDialog extends TitleAreaDialog implements
		PropertyChangeListener {

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	private static final String PROP_DIRTY = "dirty_prop";

	/**
	 * 内容是否需要保存
	 */
	private boolean dirty;

	private static final int APPLY_ID = -1;

	private boolean applySuccessful;
	/**
	 * 进度条对话框
	 */
	private ProgressMonitorDialog dialog;

	private Button applyButton;

	// private Button okButton;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public AbstractManageDialog(Shell parentShell) {
		super(parentShell);
		dirty = false;
		propertyChangeSupport.addPropertyChangeListener(this);
	}

	/**
	 * 提示数据到数据库
	 * 
	 */
	protected abstract void apply() throws RuntimeException;

	@Override
	protected void buttonPressed(int buttonId) {

		if (buttonId == IDialogConstants.OK_ID) {
			if (dirty) {
				processApply();
				// if (dialog.open() == Window.OK) {
				if (!applySuccessful) {
					return;
					// }
				}
			}

		} else if (buttonId == APPLY_ID) {
			processApply();
			return;
		}
		super.buttonPressed(buttonId);
	}

	@Override
	public final boolean close() {
		propertyChangeSupport.removePropertyChangeListener(this);
		return super.close();
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
		applyButton = createButton(parent, APPLY_ID, "应用", false);

		// okButton.setEnabled(false);
		applyButton.setEnabled(false);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}

	/**
	 * 初始化模型。一般从数据库中获取。需要自行调用。
	 */
	protected abstract void initModel();

	/**
	 * 提交数据，显示进度对话框，
	 */
	protected void processApply() {

		setDirty(false);

		dialog = new ProgressMonitorDialog(getShell());

		try {
			dialog.run(true, false, new IRunnableWithProgress() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.eclipse.jface.operation.IRunnableWithProgress#run(org
				 * .eclipse.core.runtime.IProgressMonitor)
				 */
				public void run(IProgressMonitor monitor) {
					IProgressMonitorWithBlocking blocking = (IProgressMonitorWithBlocking) monitor;

					blocking.beginTask("提交数据至数据库，请稍候...",
							IProgressMonitor.UNKNOWN);
					applySuccessful = true;
					apply();

					blocking.done();

				}
			});
		} catch (Exception e) {
			Throwable cause = e.getCause();
			if (cause != null) {
				MessageDialog.openError(getShell(), "提交失败",
						"数据提交失败，请检查数据是否有效。详细信息：" + cause.getMessage());
			} else {
				MessageDialog.openError(getShell(), "提交失败",
						"数据提交失败，请检查数据是否有效。详细信息未知。");
			}
			applySuccessful = false;
			setDirty(true);
			return;
		}

		MessageDialog.openInformation(getShell(), "提交成功", "数据已成功提交至数据库。");

	}

	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (prop.equals(PROP_DIRTY)) {
			Object obj = evt.getNewValue();
			final boolean b = (Boolean) obj;
			getShell().getDisplay().syncExec(new Runnable() {

				public void run() {
					applyButton.setEnabled(b);
				}

			});

		}
	}

	/**
	 * 设置内容是否需要保存，true则需要保存，同时激活按钮。
	 * 
	 * @param dirty
	 *            the dirty to set
	 */
	public final void setDirty(boolean dirty) {
		firePropertyChange(PROP_DIRTY, this.dirty, this.dirty = dirty);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(Display.getCurrent().getBounds().width, Display
				.getCurrent().getBounds().height);
	}
}
