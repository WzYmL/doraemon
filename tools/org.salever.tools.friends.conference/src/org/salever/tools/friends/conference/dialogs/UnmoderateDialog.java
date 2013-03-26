package org.salever.tools.friends.conference.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.salever.tools.friends.conference.system.TimerCompositeExtend;

public class UnmoderateDialog extends TitleAreaDialog {

	private TimerCompositeExtend speakTimeComp;
	private int curentTimeCount;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public UnmoderateDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(WelcomeDialog.TITLE_IMAGE);
		setMessage("Welcome to Un-moderated Caucus");
		setTitle("Un-moderated Caucus");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("华文细黑", 11, SWT.NORMAL));
		lblNewLabel.setText("Anun-moderated caucus for:");

		speakTimeComp = new TimerCompositeExtend(container, SWT.None);
		speakTimeComp.setEnabled(true);
		speakTimeComp.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		speakTimeComp.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true,
				false, 1, 1));

		return area;
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
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Un-moderated Caucus");
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			curentTimeCount = speakTimeComp.getCurentTimeCount();
		}
		super.buttonPressed(buttonId);
	}

	public int getCurentTimeCount() {
		return curentTimeCount;
	}
}
