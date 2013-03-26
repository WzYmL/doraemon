package org.salever.tools.friends.conference.dialogs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;
import org.salever.tools.friends.conference.models.Country;
import org.salever.tools.friends.conference.system.AppConstants;
import org.salever.tools.friends.conference.system.FileUtil;
import org.salever.tools.friends.conference.system.ListContentProvider;
import org.salever.tools.friends.conference.system.TableLabelProvider;
import org.salever.tools.friends.conference.system.TimerCompositeExtend;

public class ManageConferenceDialog extends TitleAreaDialog {

	private static final Font DEFAULT_LABEL_FONT = SWTResourceManager.getFont(
			"华文细黑", 11, SWT.NONE);

	private java.util.List<Country> selectedResults;

	private TableViewer selecteTV;

	private StatisticsModel statisticsModel;

	private Label statisticsLabel;

	private Text outputText;

	private Composite labelComp;

	private int selectIndex;

	private Button timeButton;

	private static SimpleDateFormat fullTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	private int unmoderateTime;

	private Label presentLabel;

	private Label simlpeLabel;

	private Label absLabel;

	private Label comLabel;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ManageConferenceDialog(Shell parentShell,
			java.util.List<Country> selectedResults) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.RESIZE);
		this.selectedResults = selectedResults;
		statisticsModel = new StatisticsModel();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(WelcomeDialog.TITLE_IMAGE);
		parent.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.BOLD));
		setTitle("Conference Management");
		setMessage("Welcome to Conference Management");
		Composite area = (Composite) super.createDialogArea(parent);
		final Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		// container
		// .setBackgroundImage(SWTResourceManager
		// .getImage(SelectCountryDialog.class,
		// "/org/salever/tools/friends/conference/dialogs/icons/bg_conference_.jpg"));

		// container.addPaintListener(new PaintListener() {
		// Image image = SWTResourceManager
		// .getImage(SelectCountryDialog.class,
		// "/org/salever/tools/friends/conference/dialogs/icons/bg_conference_.jpg");
		//
		// @Override
		// public void paintControl(PaintEvent e) {
		// Point size = container.getSize();
		// Point p = container.getLocation();
		// e.gc.drawImage(image, 0, 0, 800, 490, p.x, p.y, size.x, size.y);
		// }
		// });

		Composite composite = new Composite(container, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new GridLayout(2, false));

		selecteTV = new TableViewer(composite, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		Table list = selecteTV.getTable();
		list.setFont(SWTResourceManager.getFont("华文细黑", 10, SWT.NORMAL));
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Widget item = e.item;
				TableItem ti = (TableItem) item;
				selectIndex = selecteTV.getTable().getSelectionIndex();
				refreshFontBold();

				Font initialFont = ti.getFont();
				FontData[] fontData = initialFont.getFontData();
				fontData[0].setStyle(SWT.BOLD);
				ti.setFont(new Font(null, fontData[0]));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				doubleClick();
			}
		});
		GridData gd_list = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 3);
		gd_list.widthHint = 200;
		list.setLayoutData(gd_list);
		selecteTV.setLabelProvider(new TableLabelProvider());
		selecteTV.setContentProvider(new ListContentProvider());
		selecteTV.setInput(selectedResults);

		outputText = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		outputText.setFont(SWTResourceManager.getFont("华文细黑", 12, SWT.NORMAL));
		outputText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_3.setLayout(new GridLayout(2, false));
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, false,
				false, 1, 1);
		gd_composite_3.horizontalIndent = 10;
		composite_3.setLayoutData(gd_composite_3);

		timeButton = new Button(composite_3, SWT.CHECK);
		timeButton.setSelection(true);

		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("华文细黑", 11, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setText("Add timestamp to record");

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_composite_2.heightHint = 160;
		composite_2.setLayoutData(gd_composite_2);

		Composite composite_1 = new Composite(composite_2, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.TOP, true, false,
				1, 1);
		gd_composite_1.heightHint = 149;
		composite_1.setLayoutData(gd_composite_1);

		Button moderateButton = new Button(composite_1, SWT.NONE);
		moderateButton.setLayoutData(new RowData(150, 40));
		moderateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moderate();
			}
		});
		moderateButton.setText("Moderated Caucus");

		Button unmoderatedButton = new Button(composite_1, SWT.NONE);
		unmoderatedButton.setLayoutData(new RowData(150, 40));
		unmoderatedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unmoderate();
			}
		});
		unmoderatedButton.setText("Un-moderated Caucus");

		Button wpButton = new Button(composite_1, SWT.NONE);
		wpButton.setLayoutData(new RowData(150, 40));
		wpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				wp();
			}
		});
		wpButton.setText("WP");

		Button drButton = new Button(composite_1, SWT.NONE);
		drButton.setLayoutData(new RowData(150, 40));
		drButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dr();
			}
		});
		drButton.setText("DR");

		Button admendmentButton = new Button(composite_1, SWT.NONE);
		admendmentButton.setLayoutData(new RowData(150, 40));
		admendmentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				admendent();
			}
		});
		admendmentButton.setText("Amendment");

		moderateButton.setFont(SWTResourceManager.getFont("华文细黑", 10,
				SWT.NORMAL));
		unmoderatedButton.setFont(SWTResourceManager.getFont("华文细黑", 10,
				SWT.NORMAL));
		wpButton.setFont(SWTResourceManager.getFont("华文细黑", 10, SWT.NORMAL));
		drButton.setFont(SWTResourceManager.getFont("华文细黑", 10, SWT.NORMAL));
		admendmentButton.setFont(SWTResourceManager.getFont("华文细黑", 10,
				SWT.NORMAL));

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setLayoutData(new RowData(150, 40));
		btnNewButton
				.setFont(SWTResourceManager.getFont("华文细黑", 10, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeFont();
			}
		});
		btnNewButton.setText("Change Font");

		Composite timerComps = new Composite(composite_2, SWT.NONE);
		timerComps.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		timerComps.setLayout(new GridLayout(1, false));
		GridData gd_timerComps = new GridData(SWT.LEFT, SWT.TOP, false, false,
				1, 1);
		gd_timerComps.widthHint = 300;
		timerComps.setLayoutData(gd_timerComps);
		TimerCompositeExtend timerComposite = new TimerCompositeExtend(
				timerComps, SWT.None);
		timerComposite.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		timerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		TimerCompositeExtend timerComposite2 = new TimerCompositeExtend(
				timerComps, SWT.None);
		timerComposite2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		timerComposite2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		Button callButton = new Button(composite, SWT.NONE);
		callButton.setFont(SWTResourceManager.getFont("华文细黑", 15, SWT.NORMAL));
		callButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callCountry();
			}
		});
		GridData gd_callButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_callButton.heightHint = 40;
		gd_callButton.widthHint = 200;
		callButton.setLayoutData(gd_callButton);
		callButton.setText("Role Call");

		labelComp = new Composite(composite, SWT.BORDER);
		labelComp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labelComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));
		labelComp.setLayout(new GridLayout(8, false));

		statisticsLabel = new Label(labelComp, SWT.NONE);
		statisticsLabel.setFont(DEFAULT_LABEL_FONT);
		statisticsLabel.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));

		presentLabel = new Label(labelComp, SWT.NONE);
		presentLabel.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_GREEN));
		presentLabel
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		presentLabel.setFont(DEFAULT_LABEL_FONT);
		presentLabel.setText("0");

		Label lblNewLabel_2 = new Label(labelComp, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setFont(DEFAULT_LABEL_FONT);
		lblNewLabel_2.setText(" Simple Majority: ");

		simlpeLabel = new Label(labelComp, SWT.NONE);
		simlpeLabel.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_GREEN));
		simlpeLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		simlpeLabel.setFont(DEFAULT_LABEL_FONT);
		simlpeLabel.setText("0");

		Label lblNewLabel_4 = new Label(labelComp, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setFont(DEFAULT_LABEL_FONT);
		lblNewLabel_4.setText(" Absolute Majority: ");

		absLabel = new Label(labelComp, SWT.NONE);
		absLabel.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_GREEN));
		absLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		absLabel.setFont(DEFAULT_LABEL_FONT);
		absLabel.setText("0");

		Label lblNewLabel_6 = new Label(labelComp, SWT.NONE);
		lblNewLabel_6.setFont(DEFAULT_LABEL_FONT);
		lblNewLabel_6.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setText(", 20% of the Committee:");

		comLabel = new Label(labelComp, SWT.NONE);
		comLabel.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_DARK_GREEN));
		comLabel.setFont(DEFAULT_LABEL_FONT);
		comLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		comLabel.setText("0");
		refreshStatistics();
		return area;
	}

	protected void doubleClick() {
		Country country = getSelectedModels(selecteTV);
		if (country != null) {
			boolean addTime = timeButton.getSelection();
			if (addTime) {
				String date = fullTimeFormat.format(Calendar.getInstance()
						.getTime());
				outputText.append(date + ": ");
			}
			outputText.append(country.getName());
			outputText.append("\r\n");
		}

	}

	protected void changeFont() {
		FontDialog dialog = new FontDialog(getShell(), SWT.None);
		FontData fontData = dialog.open();
		if (fontData != null) {
			Font font = new Font(null, fontData);
			outputText.setFont(font);
		}

	}

	protected void moderate() {

		Country country = getSelectedModels(selecteTV);
		if (country == null) {
			return;
		}

		ModeratedCaucusRecordDialog dialog = new ModeratedCaucusRecordDialog(
				getShell());
		if (dialog.open() == IDialogConstants.OK_ID) {
			String topic = dialog.getTopic();
			int totalTime = dialog.getTotalTime();
			int speakTime = dialog.getSpeakTime();

			boolean addTime = timeButton.getSelection();
			if (addTime) {
				String date = fullTimeFormat.format(Calendar.getInstance()
						.getTime());
				outputText.append(date + ": ");
			}
			outputText.append("" + country.getName()
					+ " motions for a moderated caucus on the topic of\" "
					+ topic + " \", the total time is " + totalTime / 60
					+ " minutes, each delegate has " + speakTime
					+ " seconds to speak.\r\n");

		}

	}

	protected void refreshFontBold() {
		for (TableItem ti : selecteTV.getTable().getItems()) {
			Font initialFont = ti.getFont();
			FontData[] fontData = initialFont.getFontData();
			fontData[0].setStyle(SWT.NONE);
			ti.setFont(new Font(null, fontData[0]));
		}

		if (selectIndex > -1
				&& selectIndex < selecteTV.getTable().getItemCount()) {
			TableItem ti = selecteTV.getTable().getItem(selectIndex);
			Font initialFont = ti.getFont();
			FontData[] fontData = initialFont.getFontData();
			fontData[0].setStyle(SWT.BOLD);
			ti.setFont(new Font(null, fontData[0]));
		}

	}

	protected void admendent() {
		Country country = getSelectedModels(selecteTV);
		if (country != null) {
			boolean addTime = timeButton.getSelection();
			if (addTime) {
				String date = fullTimeFormat.format(Calendar.getInstance()
						.getTime());
				outputText.append(date + ": ");
			}
			outputText.append(country.getName()
					+ " motions to introduce the amendment.");
			outputText.append("\r\n");
		}

	}

	protected void dr() {
		Country country = getSelectedModels(selecteTV);
		if (country != null) {
			boolean addTime = timeButton.getSelection();
			if (addTime) {
				String date = fullTimeFormat.format(Calendar.getInstance()
						.getTime());
				outputText.append(date + ": ");
			}
			outputText.append(country.getName()
					+ " motions to introduce the draft resolution.");
			outputText.append("\r\n");
		}

	}

	protected void wp() {
		Country country = getSelectedModels(selecteTV);
		if (country != null) {
			boolean addTime = timeButton.getSelection();
			if (addTime) {
				String date = fullTimeFormat.format(Calendar.getInstance()
						.getTime());
				outputText.append(date + ": ");
			}
			outputText.append(country.getName()
					+ " motions to introduce the working paper.");
			outputText.append("\r\n");
		}

	}

	protected void unmoderate() {
		Country country = getSelectedModels(selecteTV);
		if (country != null) {

			UnmoderateDialog dialog = new UnmoderateDialog(getShell());
			if (dialog.open() == IDialogConstants.OK_ID) {
				unmoderateTime = dialog.getCurentTimeCount();
				boolean addTime = timeButton.getSelection();
				if (addTime) {
					String date = fullTimeFormat.format(Calendar.getInstance()
							.getTime());
					outputText.append(date + ": ");
				}
				outputText.append(country.getName()
						+ " motions for anun-moderated caucus for "
						+ unmoderateTime + " seconds");
				outputText.append("\r\n");
			}

		}

	}

	protected void callCountry() {
		Country country = getSelectedModels(selecteTV);
		if (country != null) {

			if (country.getCallCount() == 1) {
				MessageDialog.openConfirm(getShell(), "Note", "This country("
						+ country.getName() + ") has been called. ");
				return;
			}

			country.setCallCount(country.getCallCount() + 1);
			selecteTV.refresh();
			statisticsModel.attendance = statisticsModel.attendance + 1;
			recomputeStatistics(statisticsModel);
			refreshStatistics();
		}

	}

	private void refreshStatistics() {
		statisticsLabel.setText("Present List: ");

		presentLabel.setText("" + statisticsModel.attendance);
		simlpeLabel.setText("" + statisticsModel.simlpeMax);
		absLabel.setText("" + statisticsModel.absMax);
		comLabel.setText("" + statisticsModel.fileNum);

		labelComp.layout();
	}

	private void recomputeStatistics(StatisticsModel model) {

		model.simlpeMax = (int) (Math.floor(model.attendance / 2.0) + 1);
		model.absMax = computeAbsMax(model.attendance);
		model.fileNum = (int) (model.attendance / 5.0 + 1);

	}

	private int computeAbsMax(int attendance) {
		double tmp = attendance * 2 / 3;
		double floor = Math.floor(tmp);
		if (Double.compare(tmp, floor) == 0) {
			return (int) tmp;
		}
		return (int) (floor + 1);
	}

	private Country getSelectedModels(TableViewer lv) {
		ISelection selection = lv.getSelection();
		IStructuredSelection sselection = (IStructuredSelection) selection;
		return (Country) sselection.getFirstElement();
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Save Log", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "Cancel", false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(882, 685);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(AppConstants.APP_TITLE);
		newShell.setImage(WelcomeDialog.LOGO_IMAGE);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			saveAsLocal();
			return;
		}
		resetCountries();
		super.buttonPressed(buttonId);
	}

	private void saveAsLocal() {
		FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
		String path = dialog.open();
		if (path != null) {
			FileUtil.wrireToLocal(path, outputText.getText());
		}
	}

	private void resetCountries() {
		if (selectedResults == null) {
			return;
		}
		for (Country country : selectedResults) {
			country.setCallCount(0);
		}

	}

	/**
	 * 统计信息
	 * 
	 * @author LiXP
	 * 
	 */
	protected static class StatisticsModel {
		protected int attendance;
		protected int simlpeMax;
		protected int absMax;
		protected int fileNum;

	}
}
