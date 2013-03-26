package org.salever.swtjface.demo.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TestPagination extends ApplicationWindow {

	private TableViewer viewer;

	public static final int ID = 0;
	public static final int NAME = 1;
	public static final int GENDER = 2;
	public static final int COLOR = 3;

	public static final String[] COLUMN_NAME = { "ID号", "姓名", "性别", "喜欢颜色" };

	private List<TableEditor> editors = new ArrayList<TableEditor>();

	public TestPagination() {
		super(null);
	}

	private List<PersonEO> getPage1() {
		List<PersonEO> persons = new ArrayList<PersonEO>();
		persons.add(new PersonEO(111, "张三", "男", "灰色"));
		persons.add(new PersonEO(112, "李四", "女", "白色"));
		return persons;
	}

	private List<PersonEO> getPage2() {
		List<PersonEO> persons = new ArrayList<PersonEO>();
		persons.add(new PersonEO(211, "小王", "男", "黑色"));
		persons.add(new PersonEO(212, "小慧", "女", "粉红色"));
		return persons;
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(300, 200);
		shell.setText("TableViewer分页模拟程序");
		shell.setMaximized(true);
	}

	@Override
	protected Control createContents(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		parent.setLayout(gridLayout);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI);
		for (int i = 0; i < COLUMN_NAME.length; i++) {
			new TableColumn(viewer.getTable(), SWT.LEFT)
					.setText(COLUMN_NAME[i]);
			viewer.getTable().getColumn(i).setWidth(100);
		}
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new MyContentProvider());
		viewer.setLabelProvider(new MyLabelProvider());
		viewer.setColumnProperties(COLUMN_NAME);

		Button first = new Button(parent, SWT.PUSH);
		first.setText("第一页");
		Button second = new Button(parent, SWT.PUSH);
		second.setText("第二页");

		first.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer.setInput(getPage1());
				populateTable();
			}
		});
		second.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer.setInput(getPage2());
				populateTable();
			}
		});
		return parent;
	}

	protected void populateTable() {
		Table table = viewer.getTable();
		TableItem[] items = table.getItems();

		for (int i = 0; i < items.length; i++) {
			// Reuse, not new!
			if (editors.size() > i) {
				TableItem tableItem = items[i];
				TableEditor tableEditor = editors.get(i);
				if (tableEditor != null) {
					Button button = (Button) tableEditor.getEditor();
					button.setText(tableItem.getText(0));
					tableItem.setText(0, "");
					continue;
				}
			}

			final TableEditor editor = new TableEditor(table);
			final Button button = new Button(table, SWT.RADIO);
			String str = items[i].getText(0);
			System.out.println("item[i].getText(0)----> " + str);
			button.setText(str);
			System.out.println("button text ----> " + str);
			editor.grabHorizontal = true;
			editor.setEditor(button, items[i], 0);
			items[i].setText(0, "");
			editors.add(editor);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (button.getSelection()) {
						System.out.println("selected button--> "
								+ button.getText());
					}
				}
			});
		}

	}

	public static void main(String[] args) {
		TestPagination test = new TestPagination();
		test.setBlockOnOpen(true);
		test.open();
		Display.getCurrent().dispose();

	}

	public class MyContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List)
				return ((List) inputElement).toArray();
			return null;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

	}

	public class MyLabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			PersonEO person = (PersonEO) element;
			if (columnIndex == ID)
				return person.getID() + "";
			else if (columnIndex == NAME)
				return person.getName() + "";
			else if (columnIndex == GENDER)
				return person.getGender();
			else if (columnIndex == COLOR)
				return person.getColor() + "";
			return "";
		}

		public void dispose() {
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {

		}

	}
}
