package org.salever.tools.friends.conference.dialogs;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.salever.tools.friends.conference.models.Country;
import org.salever.tools.friends.conference.system.AppConstants;
import org.salever.tools.friends.conference.system.FileUtil;
import org.salever.tools.friends.conference.system.ListContentProvider;
import org.salever.tools.friends.conference.system.ViewerLabelProvider;

public class SelectCountryDialog extends TitleAreaDialog {
	private static final Font DEFAULT_FONT = SWTResourceManager.getFont("华文细黑",
			10, SWT.NORMAL);

	private static class Sorter extends ViewerSorter {
		public int compare(Viewer viewer, Object e1, Object e2) {
			Country item1 = (Country) e1;
			Country item2 = (Country) e2;
			if (item1.getName() == null || item2.getName() == null) {
				return 0;
			}
			return item1.getName().compareTo(item2.getName());
		}
	}

	private ListViewer allLV;

	private ListViewer selectedLV;

	private java.util.List<Country> allCounties;

	private java.util.List<Country> selectedCounties;

	private java.util.List<Country> selectedResults;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectCountryDialog(Shell parentShell) {
		super(parentShell);

		initModels();
	}

	protected void addAll() {
		java.util.List<Country> selected = getSelectedModels(allLV);
		for (Country country : selected) {
			if (!selectedCounties.contains(country)) {
				selectedCounties.add(country);
				allCounties.remove(country);
			}
		}
		selectedLV.refresh();
		allLV.refresh();
	}

	protected void cancel() {
		if (MessageDialog.openConfirm(getShell(), "Confirm",
				"Back without Saving the Changes?")) {
			this.cancelPressed();
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(AppConstants.APP_TITLE);
		newShell.setImage(WelcomeDialog.LOGO_IMAGE);
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// createButton(parent, IDialogConstants.OK_ID, "关闭", true);
		// createButton(parent, IDialogConstants.CANCEL_ID,
		// IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(WelcomeDialog.TITLE_IMAGE);
		setMessage("Welcome to Country Assignment");
		setTitle("Country Assignment");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		container
				.setBackgroundImage(SWTResourceManager
						.getImage(SelectCountryDialog.class,
								"/org/salever/tools/friends/conference/dialogs/icons/bg_country_.jpg"));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new GridLayout(3, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		gd_composite.heightHint = 400;
		gd_composite.widthHint = 1;
		composite.setLayoutData(gd_composite);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(DEFAULT_FONT);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setText("All countries:");
		new Label(composite, SWT.NONE);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(DEFAULT_FONT);
		lblNewLabel_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setText("Selected countries:");

		allLV = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		allLV.setSorter(new Sorter());
		List allList = allLV.getList();
		allList.setFont(DEFAULT_FONT);
		GridData gd_allList = new GridData(SWT.FILL, SWT.FILL, false, true, 1,
				1);
		gd_allList.widthHint = 300;
		allList.setLayoutData(gd_allList);
		allLV.setLabelProvider(new ViewerLabelProvider());
		allLV.setContentProvider(new ListContentProvider());
		allLV.setSorter(new Sorter());
		allLV.setInput(allCounties);

		Composite buttonComp = new Composite(composite, SWT.NONE);
		buttonComp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_buttonComp = new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1);
		gd_buttonComp.widthHint = 120;
		buttonComp.setLayoutData(gd_buttonComp);
		buttonComp.setLayout(new GridLayout(1, false));

		Button addButton = new Button(buttonComp, SWT.NONE);
		addButton.setFont(DEFAULT_FONT);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAll();
			}
		});
		GridData gd_addButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_addButton.heightHint = 35;
		gd_addButton.widthHint = 100;
		addButton.setLayoutData(gd_addButton);
		addButton.setText("Add -->");

		Button removeButton = new Button(buttonComp, SWT.NONE);
		removeButton.setFont(DEFAULT_FONT);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeAll();
			}
		});
		GridData gd_removeButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_removeButton.heightHint = 35;
		gd_removeButton.widthHint = 100;
		removeButton.setLayoutData(gd_removeButton);
		removeButton.setText("<-- Remove ");

		Button newButton = new Button(buttonComp, SWT.NONE);
		newButton.setFont(DEFAULT_FONT);
		newButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newCountry();
			}
		});
		GridData gd_newButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_newButton.heightHint = 35;
		gd_newButton.widthHint = 100;
		newButton.setLayoutData(gd_newButton);
		newButton.setText("New Country");

		Button saveLocalButton = new Button(buttonComp, SWT.NONE);
		saveLocalButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveToLocal();
			}
		});
		GridData gd_saveLocalButton = new GridData(SWT.LEFT, SWT.BOTTOM, false,
				true, 1, 1);
		gd_saveLocalButton.heightHint = 35;
		gd_saveLocalButton.widthHint = 100;
		saveLocalButton.setLayoutData(gd_saveLocalButton);
		saveLocalButton.setFont(DEFAULT_FONT);
		saveLocalButton.setText("Save As");

		Button openButton = new Button(buttonComp, SWT.NONE);
		openButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openFromLocal();
			}
		});
		GridData gd_openButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_openButton.heightHint = 35;
		gd_openButton.widthHint = 100;
		openButton.setLayoutData(gd_openButton);
		openButton.setFont(DEFAULT_FONT);
		openButton.setText("Open");

		Button saveButton = new Button(buttonComp, SWT.NONE);
		saveButton.setFont(DEFAULT_FONT);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveCountry();
			}
		});
		GridData gd_saveButton = new GridData(SWT.LEFT, SWT.BOTTOM, false,
				false, 1, 1);
		gd_saveButton.heightHint = 35;
		gd_saveButton.widthHint = 100;
		saveButton.setLayoutData(gd_saveButton);
		saveButton.setText("OK");

		Button cancelButton = new Button(buttonComp, SWT.NONE);
		cancelButton.setFont(DEFAULT_FONT);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancel();
			}
		});
		GridData gd_cancelButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_cancelButton.heightHint = 35;
		gd_cancelButton.widthHint = 100;
		cancelButton.setLayoutData(gd_cancelButton);
		cancelButton.setText("Back");

		selectedLV = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL
				| SWT.MULTI);
		List selectedList = selectedLV.getList();
		selectedList.setFont(DEFAULT_FONT);
		GridData gd_selectedList = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_selectedList.widthHint = 300;
		selectedList.setLayoutData(gd_selectedList);
		selectedLV.setLabelProvider(new ViewerLabelProvider());
		selectedLV.setContentProvider(new ListContentProvider());
		selectedLV.setSorter(new Sorter());
		selectedLV.setInput(selectedCounties);
		return area;
	}

	protected void openFromLocal() {
		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
		dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });
		String path = dialog.open();
		if (path != null) {
			String contents = FileUtil.readFromLocal(new File(path));
			if (contents != null) {
				// contents = contents.replace("\n", "$");
				String[] strings = contents.split("\n");
				selectedCounties.clear();
				for (String countryStr : strings) {
					selectedCounties.add(new Country(countryStr));
				}
				selectedLV.refresh();
			}
		}

	}

	protected void saveToLocal() {
		FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
		dialog.setFileName("conturies");
		dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });
		String path = dialog.open();
		if (path != null) {
			FileUtil.wrireToLocal(path, buildContent());
		}
	}

	/**
	 * 
	 * @return
	 */
	private String buildContent() {
		StringBuffer sb = new StringBuffer();
		for (Country country : selectedCounties) {
			sb.append(country.getName()).append(
					System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(800, 650);
	}

	private java.util.List<Country> getSelectedModels(ListViewer lv) {
		ISelection selection = lv.getSelection();
		IStructuredSelection sselection = (IStructuredSelection) selection;
		java.util.List<Country> selected = new ArrayList<Country>();
		for (Object obj : sselection.toArray()) {
			selected.add((Country) obj);
		}
		return selected;
	}

	/**
	 * 返回选择
	 * 
	 * @return
	 */
	public java.util.List<Country> getSelectedResults() {
		return selectedResults;
	}

	private void initModels() {
		allCounties = new ArrayList<Country>();
		selectedCounties = new ArrayList<Country>();

		for (String countryStr : AppConstants.COUNTIRES) {
			allCounties.add(new Country(countryStr));
		}
	}

	protected void newCountry() {
		InputDialog inputDialog = new InputDialog(getShell(), "New Country",
				"Please input name:", "newCountry", new IInputValidator() {

					@Override
					public String isValid(String newText) {
						if (newText == null || newText.isEmpty()) {
							return "Please input name for new country!";
						}
						return null;
					}
				});

		if (inputDialog.open() == Window.OK) {
			String value = inputDialog.getValue();
			Country country = new Country(value);
			if (selectedCounties.contains(country)) {
				MessageDialog.openError(getShell(), "Note", "The country ("
						+ value + ") already exists, please try another one!");
				return;
			}
			selectedCounties.add(country);
			selectedLV.refresh();
			selectedLV.setSelection(new StructuredSelection(country));
		}

	}

	protected void removeAll() {
		java.util.List<Country> selected = getSelectedModels(selectedLV);

		allCounties.addAll(selected);
		allLV.refresh();

		selectedCounties.removeAll(selected);
		selectedLV.refresh();

	}

	protected void saveCountry() {
		if (MessageDialog.openConfirm(getShell(), "Confirm",
				"Save to the Conference?")) {
			selectedResults = new ArrayList<Country>(selectedCounties);
			this.okPressed();
		}

	}
}
