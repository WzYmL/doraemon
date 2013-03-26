package org.salever.rcp.tools.wordcounts.editors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.TableViewerColumnSorter;
import org.salever.rcp.tools.wordcounts.models.CountModel;
import org.salever.rcp.tools.wordcounts.models.ListContentProvider;
import org.salever.rcp.tools.wordcounts.models.ModelTableLabelProvider;
import org.salever.rcp.tools.wordcounts.util.ExcelExecutor;

public class WordCountEditor extends EditorPart {

	public static final String ID = "org.salever.rcp.tools.wordcounts.editors.WordCountEditor"; //$NON-NLS-1$
	private Table table;
	private TableViewer tableViewer;
	private List<CountModel> models;
	private Map<String, Integer> caches;
	private FileInput input;

	public WordCountEditor() {
	}

	private void initModels() {

		models.clear();
		caches.clear();
		String results = loadText();
		int total = 0;
		String[] strings = results.split(" "); //$NON-NLS-1$
		for (String string : strings) {
			string = string.toLowerCase().trim();
			if (string.isEmpty()) {
				continue;
			}
			Integer integer = caches.get(string);
			if (integer == null) {
				caches.put(string, 1);
			} else {
				caches.put(string, integer + 1);
			}
			total++;
		}
		int count = 0;

		for (Entry<String, Integer> entry : caches.entrySet()) {
			count = entry.getValue() == null ? 0 : entry.getValue();
			CountModel model = new CountModel();
			model.setWord(entry.getKey());
			model.setCounts(count);
			model.setTotalCounts(total);
			model.setRate(count * 1.0 / total);
			models.add(model);

		}

	}

	private String loadText() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(input.getName()), "utf-8")); //$NON-NLS-1$
			StringBuffer sb = new StringBuffer();
			String line = reader.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(" "); //$NON-NLS-1$
				line = reader.readLine();
			}
			reader.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		tableViewer = new TableViewer(container, SWT.BORDER
				| SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		createTV(tableViewer);

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));
		composite.setLayout(new GridLayout(1, false));

		Button refreshButton = new Button(composite, SWT.NONE);
		refreshButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		GridData gd_refreshButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_refreshButton.widthHint = 100;
		refreshButton.setLayoutData(gd_refreshButton);
		refreshButton.setText(Messages.WordCountEditor_0);

		Button deleteButton = new Button(composite, SWT.NONE);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteModel();
			}
		});
		GridData gd_deleteButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_deleteButton.widthHint = 100;
		deleteButton.setLayoutData(gd_deleteButton);
		deleteButton.setText(Messages.WordCountEditor_1);

		Button saveTxtButton = new Button(composite, SWT.NONE);
		saveTxtButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveToLocalTxt();
			}
		});
		GridData gd_saveTxtButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_saveTxtButton.widthHint = 100;
		saveTxtButton.setLayoutData(gd_saveTxtButton);
		saveTxtButton.setText(Messages.WordCountEditor_2);

		Button saveExcelButton = new Button(composite, SWT.NONE);
		saveExcelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveToLocalExcel();
			}
		});
		GridData gd_saveExcelButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_saveExcelButton.widthHint = 100;
		saveExcelButton.setLayoutData(gd_saveExcelButton);
		saveExcelButton.setText(Messages.WordCountEditor_3);

	}

	protected void saveToLocalExcel() {

		sortModels();
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.xls", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
		String path = dlg.open();
		if (path == null) {
			return;
		}

		try {
			ExcelExecutor.writeModels(sortModels(), path);
		} catch (Exception e) {
			MessageDialog.openError(getSite().getShell(), Messages.WordCountEditor_6, Messages.WordCountEditor_7 + e);
		}
	}

	private List<CountModel> sortModels() {
		List<CountModel> tmp = new ArrayList<CountModel>();
		tmp.addAll(models);
		Collections.sort(tmp, new Comparator<CountModel>() {
			@Override
			public int compare(CountModel o1, CountModel o2) {
				return o2.getCounts() - o1.getCounts();
			}
		});
		return tmp;
	}

	private String formatOutput() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%30s", Messages.WordCountEditor_9) + String.format("%10s", Messages.WordCountEditor_11) //$NON-NLS-1$ //$NON-NLS-3$
				+ String.format("%8s", Messages.WordCountEditor_13) + String.format("%8s", Messages.WordCountEditor_15)); //$NON-NLS-1$ //$NON-NLS-3$
		sb.append("\r\n"); //$NON-NLS-1$
		List<CountModel> list = sortModels();
		for (CountModel model : list) {
			sb.append(String.format("%30s", model.getWord()) + "  " //$NON-NLS-1$ //$NON-NLS-2$
					+ String.format("%10d", model.getCounts()) + "  " //$NON-NLS-1$ //$NON-NLS-2$
					+ String.format("%10d", model.getTotalCounts()) + "  " //$NON-NLS-1$ //$NON-NLS-2$
					+ String.format("%10s", format(model.getRate()))); //$NON-NLS-1$
			sb.append("\r\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	public String format(double rate) {
		DecimalFormat df5 = new DecimalFormat("0.000"); //$NON-NLS-1$
		String result = df5.format(rate * 100);
		return result + "%"; //$NON-NLS-1$
	}

	protected void saveToLocalTxt() {
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.txt", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
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

	protected void refresh() {
		initModels();
		tableViewer.refresh();
	}

	private void createTV(TableViewer tableViewer2) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(200);
		tblclmnNewColumn.setText(Messages.WordCountEditor_19);

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setAlignment(SWT.CENTER);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText(Messages.WordCountEditor_20);

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setAlignment(SWT.CENTER);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText(Messages.WordCountEditor_21);

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
		tblclmnNewColumn_3.setText(Messages.WordCountEditor_22);

		tableViewer.setLabelProvider(new ModelTableLabelProvider());
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setInput(models);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(input.getName());
		this.input = (FileInput) input;
		models = new ArrayList<CountModel>();
		caches = new HashMap<String, Integer>();
		initModels();
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
