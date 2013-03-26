package org.salever.rcp.tools.wordcounts.dialog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.TableViewerColumnSorter;
import org.salever.rcp.tools.wordcounts.models.CountModel;
import org.salever.rcp.tools.wordcounts.models.ListContentProvider;
import org.salever.rcp.tools.wordcounts.models.ModelTableLabelProvider;

public class WordCountDailgo extends TitleAreaDialog {

	private Table table;
	private Text text;

	private List<CountModel> models;
	private TableViewer tableViewer;

	private String content;

	private String title;
	private int total;

	private Map<String, Integer> caches;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @param title
	 */
	public WordCountDailgo(Shell parentShell, String content, String title) {
		super(parentShell);
		this.content = content;
		this.title = title;
		initModels();
	}

	public String format(double rate) {
		DecimalFormat df5 = new DecimalFormat("0.000"); //$NON-NLS-1$
		String result = df5.format(rate * 100);
		return result + "%"; //$NON-NLS-1$
	}

	private void initModels() {
		models = new ArrayList<CountModel>();
		caches = new HashMap<String, Integer>();
		total = 0;

		String results = content.replace("\r\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
		String[] strings = results.split(" "); //$NON-NLS-1$
		for (String string : strings) {
			string = string.toLowerCase();
			Integer integer = caches.get(string);
			if (integer == null) {
				caches.put(string, 1);
			} else {
				caches.put(string, integer + 1);
			}
			total++;
		}

	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.WordCountDailgo_9);
		setMessage(Messages.WordCountDailgo_10);
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Group group = new Group(container, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		group.setText(Messages.WordCountDailgo_11);
		group.setLayout(new GridLayout(3, false));

		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText(Messages.WordCountDailgo_12);

		text = new Text(group, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button countButton = new Button(group, SWT.NONE);
		countButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				countWord();
			}
		});
		GridData gd_countButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_countButton.widthHint = 80;
		countButton.setLayoutData(gd_countButton);
		countButton.setText(Messages.WordCountDailgo_13);

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		tableViewer = new TableViewer(composite, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText(Messages.WordCountDailgo_14);

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setAlignment(SWT.CENTER);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText(Messages.WordCountDailgo_15);

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setAlignment(SWT.CENTER);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText(Messages.WordCountDailgo_16);

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		new TableViewerColumnSorter(tableViewerColumn_3) {
			@Override
			protected int doCompare(Viewer viewer, Object e1, Object e2) {
				CountModel model1 = (CountModel) e1;
				CountModel model2 = (CountModel) e2;
				return Double.compare(model1.getRate(), model2.getRate());
			}

			@Override
			protected Object getValue(Object o) {
				return super.getValue(o);
			}
		};
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_3.setAlignment(SWT.CENTER);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText(Messages.WordCountDailgo_17);
		tableViewer.setLabelProvider(new ModelTableLabelProvider());
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setInput(models);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));

		Button deleteButton = new Button(composite_1, SWT.NONE);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteModel();
			}
		});
		GridData gd_deleteButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_deleteButton.widthHint = 80;
		deleteButton.setLayoutData(gd_deleteButton);
		deleteButton.setText(Messages.WordCountDailgo_18);

		Button saveButton = new Button(composite_1, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveToLocal();
			}
		});
		GridData gd_saveButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_saveButton.widthHint = 80;
		saveButton.setLayoutData(gd_saveButton);
		saveButton.setText(Messages.WordCountDailgo_19);

		return area;
	}

	protected void saveToLocal() {

		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
		String path = dlg.open();
		if (path == null) {
			return;
		}
		try {
			OutputStream out = new FileOutputStream(path);
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8"); //$NON-NLS-1$
			writer.write(formatOutput());
			writer.close();
			out.close();
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}

	}

	private String formatOutput() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(Messages.WordCountDailgo_21,
				Messages.WordCountDailgo_22)
				+ String.format(Messages.WordCountDailgo_23,
						Messages.WordCountDailgo_24)
				+ String.format(Messages.WordCountDailgo_25,
						Messages.WordCountDailgo_26)
				+ String.format(Messages.WordCountDailgo_27,
						Messages.WordCountDailgo_28));
		sb.append("\r\n"); //$NON-NLS-1$
		for (CountModel model : models) {
			sb.append(String.format("%15s", model.getWord()) + "  " //$NON-NLS-1$ //$NON-NLS-2$
					+ String.format("%10d", model.getCounts()) + "  " //$NON-NLS-1$ //$NON-NLS-2$
					+ String.format("%10d", model.getTotalCounts()) + "  " //$NON-NLS-1$ //$NON-NLS-2$
					+ String.format("%10s", format(model.getRate()))); //$NON-NLS-1$
			sb.append("\r\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	private String fillString(String word, int i) {
		StringBuffer sb = new StringBuffer();
		sb.append(word);
		int index = i;
		while (index <= i) {
			sb.append(" "); //$NON-NLS-1$
			index++;
		}
		return sb.toString();
	}

	protected void deleteModel() {
		List<CountModel> results = getSelectedModels();
		if (!results.isEmpty()) {
			this.models.removeAll(results);
			tableViewer.refresh();
		}
	}

	private List<CountModel> getSelectedModels() {
		List<CountModel> models = new ArrayList<CountModel>();
		IStructuredSelection selection = (IStructuredSelection) tableViewer
				.getSelection();
		for (Object o : selection.toArray()) {
			models.add((CountModel) o);
		}
		return models;
	}

	protected void countWord() {
		String word = text.getText();
		if (word.isEmpty()) {
			return;
		}
		word = word.toLowerCase();
		Integer counts = caches.get(word);
		if (counts == null) {
			counts = 0;
		}

		CountModel model = new CountModel();
		model.setWord(word);
		model.setCounts(counts);
		model.setTotalCounts(total);
		model.setRate(counts * 1.0 / total);

		models.add(model);
		tableViewer.refresh();
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				false);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(576, 512);
	}
}
