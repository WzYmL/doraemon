package org.salever.tools.friends.conference.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.salever.tools.friends.conference.system.TimerCompositeExtend;

public class ModeratedCaucusRecordDialog extends TitleAreaDialog {

	private static final Font DEFAULT_FONT = SWTResourceManager.getFont("华文细黑",
			11, SWT.NORMAL);

	private String topic;

	private int totalTime;

	private int speakTime;

	private Text topicText;
	private TimerCompositeExtend totalTimeComp;
	private TimerCompositeExtend speakTimeComp;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ModeratedCaucusRecordDialog(Shell parentShell) {
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
		setMessage("Moderated Caucus Record");
		setTitle("Moderated Caucus");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Group grpTopic = new Group(container, SWT.NONE);
		grpTopic.setFont(DEFAULT_FONT);
		GridData gd_grpTopic = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		gd_grpTopic.heightHint = 150;
		grpTopic.setLayoutData(gd_grpTopic);
		grpTopic.setText("Input Topic");
		grpTopic.setLayout(new FillLayout(SWT.HORIZONTAL));

		topicText = new Text(grpTopic, SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.CANCEL | SWT.MULTI);
		topicText.setFont(DEFAULT_FONT);

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1,
				1));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(DEFAULT_FONT);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("The Total Time:");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(DEFAULT_FONT);
		lblNewLabel_1.setText("Individual Speaking Time:");

		totalTimeComp = new TimerCompositeExtend(composite, SWT.None);
		totalTimeComp.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		totalTimeComp.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true,
				false, 1, 1));

		speakTimeComp = new TimerCompositeExtend(composite, SWT.None);
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
		return new Point(600, 450);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Moderated Caucus");
		newShell.setImage(WelcomeDialog.LOGO_IMAGE);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			topic = topicText.getText();
			totalTime = totalTimeComp.getCurentTimeCount();
			speakTime = speakTimeComp.getCurentTimeCount();
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @return the totalTime
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * @return the speakTime
	 */
	public int getSpeakTime() {
		return speakTime;
	}

}
