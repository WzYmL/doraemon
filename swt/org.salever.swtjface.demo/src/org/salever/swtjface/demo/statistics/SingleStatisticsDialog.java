package org.salever.swtjface.demo.statistics;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.salever.swtjface.demo.utils.GUICompositeUtil;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class SingleStatisticsDialog extends TitleAreaDialog {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			return element.toString();
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private Table table;
	private TableViewer resultTV;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SingleStatisticsDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Group filterGroup = new Group(container, SWT.NONE);
		filterGroup.setText("统计筛选信息");
		filterGroup.setLayout(new GridLayout(2, false));
		filterGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Composite filterComposite = new Composite(filterGroup, SWT.NONE);
		filterComposite.setLayout(new GridLayout(2, false));
		filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		String[] items = { "" };
		Combo categoryCombo = GUICompositeUtil.createComboComposite(
				filterComposite, "统计项类别：", items);

		Combo optionCombo = GUICompositeUtil.createComboComposite(
				filterComposite, "统计项", items);

		Composite filterButtonComposite = new Composite(filterGroup, SWT.NONE);
		filterButtonComposite.setLayout(new GridLayout(1, false));
		filterButtonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL,
				false, true, 1, 1));

		Button statisticButton = new Button(filterButtonComposite, SWT.NONE);
		statisticButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				statistics();
			}
		});
		GridData gd_statisticButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_statisticButton.widthHint = 100;
		statisticButton.setLayoutData(gd_statisticButton);
		statisticButton.setBounds(0, 0, 68, 23);
		statisticButton.setText("开始统计");

		Composite resultComposite = new Composite(container, SWT.NONE);
		resultComposite.setLayout(new GridLayout(2, false));
		resultComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		Composite resultContentComposite = new Composite(resultComposite,
				SWT.NONE);
		resultContentComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		resultContentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1));
		createResultContentComposite(resultContentComposite);

		Composite resultButtonComposite = new Composite(resultComposite,
				SWT.NONE);
		resultButtonComposite.setLayout(new GridLayout(1, false));
		resultButtonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL,
				false, true, 1, 1));
		createResultButtonComposite(resultButtonComposite);

		return area;
	}

	/**
	 * 创建结果按钮组件
	 * @param composite
	 */
	private void createResultButtonComposite(Composite composite) {
		Button excelButton = new Button(composite, SWT.NONE);
		excelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAsExcel();
			}
		});
		GridData gd_excelButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_excelButton.widthHint = 100;
		excelButton.setLayoutData(gd_excelButton);
		excelButton.setText("保存为Excel");

		Button imageButton = new Button(composite, SWT.NONE);
		imageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveAsImage();
			}
		});
		GridData gd_imageButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_imageButton.widthHint = 100;
		imageButton.setLayoutData(gd_imageButton);
		imageButton.setText("保存为图片");

		Button printButton = new Button(composite, SWT.NONE);
		printButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				print();
			}
		});
		GridData gd_printButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_printButton.widthHint = 100;
		printButton.setLayoutData(gd_printButton);
		printButton.setText("打印");

	}

	/**
	 * 创建结果显示组件
	 * @param composite
	 */
	private void createResultContentComposite(Composite composite) {
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);

		TabItem tebleTabItem = new TabItem(tabFolder, SWT.NONE);
		tebleTabItem.setText("表格显示");

		Composite tableItemComposite = new Composite(tabFolder, SWT.NONE);
		tebleTabItem.setControl(tableItemComposite);
		tableItemComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		resultTV = new TableViewer(tableItemComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = resultTV.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

	
		GUICompositeUtil.createTVColumn(resultTV, "序号", 80);
		GUICompositeUtil.createTVColumn(resultTV, "统计项", 200);
		GUICompositeUtil.createTVColumn(resultTV, "人数", 150);
		GUICompositeUtil.createTVColumn(resultTV, "比例", 150);
		resultTV.setLabelProvider(new TableLabelProvider());
		resultTV.setContentProvider(new ContentProvider());
		
		TabItem barTabItem = new TabItem(tabFolder, SWT.NONE);
		barTabItem.setText("柱形图显示");

		Composite barItemComposite = new Composite(tabFolder, SWT.NONE);
		barTabItem.setControl(barItemComposite);
		barItemComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		CLabel barCharLabel = new CLabel(barItemComposite, SWT.NONE);
		barCharLabel.setText("");

		TabItem cakeTabItem = new TabItem(tabFolder, SWT.NONE);
		cakeTabItem.setText("饼状图显示");

		Composite cakeItemComposite = new Composite(tabFolder, SWT.NONE);
		cakeTabItem.setControl(cakeItemComposite);
		cakeItemComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		CLabel cakeCharLabel = new CLabel(cakeItemComposite, SWT.NONE);
		cakeCharLabel.setText("");

	}

	protected void saveAsImage() {
		// TODO Auto-generated method stub

	}

	protected void print() {
		// TODO Auto-generated method stub

	}

	protected void saveAsExcel() {
		// TODO Auto-generated method stub

	}

	protected void statistics() {
		// TODO Auto-generated method stub

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
		return new Point(669, 529);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		// newShell.setSize(Display.getCurrent().getBounds().width, Display
		// .getCurrent().getBounds().height);
	}
}
