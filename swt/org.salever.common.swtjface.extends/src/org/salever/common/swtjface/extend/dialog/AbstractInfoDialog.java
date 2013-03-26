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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractInfoDialog extends TitleAreaDialog {

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public AbstractInfoDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			if (!validate()) {
				return;
			}
			saveFields();
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * 校验数据
	 * 
	 * @return
	 */
	protected abstract boolean validate();

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
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	/**
	 * 初始化组件，需要子类在调用
	 */
	protected abstract void initFields();

	/**
	 * 保存组件的值到对象中
	 */
	protected abstract void saveFields();

	/**
	 * 返回编辑的对象
	 * 
	 * @return
	 */
	public abstract Object getData();

}
