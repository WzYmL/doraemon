package org.salever.tools.friends.conference.dialogs;

import java.util.List;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.salever.tools.friends.conference.models.Country;
import org.salever.tools.friends.conference.system.AppConstants;

public class WelcomeDialog extends TitleAreaDialog {

	public static final Image TITLE_IMAGE = SWTResourceManager.getImage(
			WelcomeDialog.class,
			"/org/salever/tools/friends/conference/dialogs/icons/title_bg.jpg");

	public static final Image LOGO_IMAGE = SWTResourceManager.getImage(
			WelcomeDialog.class,
			"/org/salever/tools/friends/conference/dialogs/icons/LOGO.png");

	private List<Country> results;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public WelcomeDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.MIN);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(WelcomeDialog.TITLE_IMAGE);
		setMessage("Welcome to " + AppConstants.APP_TITLE);
		setTitle("Welcome");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Canvas canvas = new Canvas(container, SWT.NONE);
		canvas.setLayout(null);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		canvas.setBackgroundImage(SWTResourceManager
				.getImage(WelcomeDialog.class,
						"/org/salever/tools/friends/conference/dialogs/icons/bg_new.jpg"));

		Button selectCountryButton = new Button(canvas, SWT.NONE);
		selectCountryButton.setFont(SWTResourceManager.getFont("华文细黑", 11,
				SWT.NORMAL));
		selectCountryButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectCountry();
			}
		});
		selectCountryButton.setBounds(298, 425, 150, 50);
		selectCountryButton.setText("Country Assignment");

		Button conferenceButton = new Button(canvas, SWT.NONE);
		conferenceButton.setFont(SWTResourceManager.getFont("华文细黑", 11,
				SWT.NORMAL));
		conferenceButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				manageConference();
			}
		});
		conferenceButton.setBounds(458, 425, 150, 50);
		conferenceButton.setText("Conference Start");

		Button exitButton = new Button(canvas, SWT.NONE);
		exitButton.setFont(SWTResourceManager.getFont("华文细黑", 11, SWT.NORMAL));
		exitButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exit();
			}
		});
		exitButton.setBounds(618, 425, 150, 50);
		exitButton.setText("Exit");

		return area;
	}

	protected void manageConference() {
		ManageConferenceDialog dialog = new ManageConferenceDialog(getShell(),
				results);
		dialog.open();
	}

	protected void selectCountry() {
		SelectCountryDialog selectCountryDialog = new SelectCountryDialog(
				getShell());
		if (selectCountryDialog.open() == Window.OK) {
			results = selectCountryDialog.getSelectedResults();
		}
	}

	protected void exit() {
		this.okPressed();
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(821, 650);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(AppConstants.APP_TITLE);
		newShell.setImage(LOGO_IMAGE);
	}
}
