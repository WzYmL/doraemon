/**
 * 
 */
package org.salever.common.swtjface.extend.widget.util;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author
 * 
 */
public class DialogUtil {

	/**
	 * 打开文件名修改对话框
	 * 
	 * @param shell
	 * @param title
	 * @param message
	 * @param initValue
	 * @return
	 */
	public static InputDialog openFileNameInputDialog(Shell shell,
			String title, String message, String initValue) {
		InputDialog dialog = new InputDialog(shell, title, message, initValue,
				new IInputValidator() {

					public String isValid(String newText) {
						if (!FileUtil.isValidFileName(newText)) {
							return "模板名非法。";
						}

						return null;
					}
				});
		return dialog;
	}
}
