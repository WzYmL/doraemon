/**
 * @author LiXiaopeng
 * @created on 2010-11-9 下午08:05:29
 */
package org.salever.common.swtjface.extend.widget.util;

import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.salever.common.swtjface.extend.widget.GUIConstants;

/**
 * 创建各种SWT组件的工具类
 */
public class GUICompositeUtil {

	/**
	 * 多行文本输入框的指定高度120px，以800px高为最低标准屏幕高度。
	 */
	public static final int MUTIL_TEXT_COMPOSITE_HEIGHT = 120;

	private GUICompositeUtil() {
	}

	/**
	 * 创建Spinner组件
	 * 
	 * @param container
	 * @param description
	 * @param max
	 * @return
	 */
	public static Spinner createSpinnerComposite(Composite container,
			String description, int max) {
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);
		label.setText(description);
		Spinner spinner = new Spinner(composite, SWT.BORDER);
		GridData gd_spinner = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_spinner.widthHint = 120;
		spinner.setLayoutData(gd_spinner);
		spinner.setMaximum(max);
		return spinner;
	}

	/**
	 * 
	 * 创建Combo组件，只读
	 * 
	 * @param container
	 * @param description
	 * @param items
	 * @return
	 */
	public static Combo createComboComposite(Composite container,
			String description, String[] items) {
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
		gd.widthHint = 250;
		composite.setLayoutData(gd);
		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setText(description);
		label.setLayoutData(data);
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		GridData gd_combo = new GridData(GridData.FILL_BOTH);
		gd_combo.widthHint = 150;
		combo.setLayoutData(gd_combo);
		for (String item : items) {
			combo.add(item);
		}
		return combo;
	}

	/**
	 * 
	 * 创建ComboViewer组件，只读
	 * 
	 * @param container
	 * @param description
	 * @return
	 */
	public static ComboViewer createComboViewerComposite(Composite container,
			String description) {
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
		gd.widthHint = 250;
		composite.setLayoutData(gd);
		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setText(description);
		label.setLayoutData(data);
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		ComboViewer comboViewer = new ComboViewer(combo);
		GridData gd_combo = new GridData(GridData.FILL_BOTH);
		gd_combo.widthHint = 150;
		combo.setLayoutData(gd_combo);
		return comboViewer;
	}

	/**
	 * 
	 * 创建Combo组件，只读
	 * 
	 * @param container
	 * @param description
	 * @param items
	 * @return
	 */
	public static Combo createHFillComboComposite(Composite container,
			String description, String[] items) {
		Composite composite = new Composite(container, SWT.NONE);

		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 25;
		composite.setLayoutData(gd);
		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setText(description);
		label.setLayoutData(data);
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		GridData gd_combo = new GridData(GridData.FILL_BOTH);
		gd_combo.widthHint = 150;
		combo.setLayoutData(gd_combo);
		for (String item : items) {
			combo.add(item);
		}
		return combo;
	}

	/**
	 * 创建Text组件，文字描述与Text处于水平排序位置
	 * 
	 * @param container
	 * @param description
	 * @param style
	 * @return
	 */
	public static Text createHorizontalTextComposite(Composite container,
			String description, int style) {

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);

		label.setText(description);

		Text text = new Text(composite, style);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return text;
	}

	/**
	 * 创建Text组件，文字描述与Text处于水平排序位置
	 * 
	 * @param container
	 * @param description
	 * @param addtion
	 * @param style
	 * @return
	 */
	public static Text createExamTextComposite(Composite container,
			String description, String addtion, int style) {

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.EXAM_FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);

		label.setText(description);

		Text text = new Text(composite, style);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label2 = new Label(composite, SWT.NONE);
		GridData data2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data2.horizontalAlignment = SWT.RIGHT;
		data2.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label2.setLayoutData(data2);

		label2.setText(addtion);
		return text;
	}

	/**
	 * 创建DateTime组件
	 * 
	 * @param container
	 * @param description
	 * @param style
	 * @return
	 */
	public static DateTime createDateTimeComposite(Composite container,
			String description, int style) {

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);

		label.setText(description);

		DateTime dateTime = new DateTime(composite, style);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		return dateTime;
	}

	/**
	 * 创建Text组件，文字描述与Text处于垂直排序位置
	 * 
	 * @param container
	 * @param description
	 * @param style
	 * @return
	 */
	public static Text createVerticalTextComposite(Composite container,
			String description, int style) {

		Composite composite = new Composite(container, SWT.NONE);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd.heightHint = MUTIL_TEXT_COMPOSITE_HEIGHT;
		composite.setLayoutData(gd);
		composite.setLayout(new GridLayout(1, false));

		Label label = new Label(composite, SWT.NONE);
		label.setText(description);

		Text text = new Text(composite, style);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		text.setLayoutData(gd);
		return text;
	}

	/**
	 * 创建Text组件，文字描述与Text处于垂直排序位置
	 * 
	 * @param container
	 * @param description
	 * @param style
	 * @return
	 */
	public static Text createFullVerticalTextComposite(Composite container,
			String description, int style) {

		Composite composite = new Composite(container, SWT.NONE);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		composite.setLayoutData(gd);
		composite.setLayout(new GridLayout(1, false));

		Label label = new Label(composite, SWT.NONE);
		label.setText(description);

		Text text = new Text(composite, style);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		text.setLayoutData(gd);
		return text;
	}

	/**
	 * 创建Text组件，文字描述与Text处于垂直排序位置
	 * 
	 * @param container
	 * @param description
	 * @param style
	 * @return
	 */
	public static Text createFormulaTextComposite(Composite container,
			String description, int style) {

		Composite composite = new Composite(container, SWT.NONE);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		composite.setLayoutData(gd);
		composite.setLayout(new GridLayout(2, false));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);
		label.setText(description);

		Text text = new Text(composite, style);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		text.setLayoutData(gd);
		return text;
	}

	/**
	 * 创建check box 样式的组件
	 * 
	 * @param container
	 * @param description
	 * @param buttonDescription
	 * @return
	 */
	public static Button createCheckboxComposite(Composite container,
			String description, String buttonDescription) {
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);

		label.setText(description);

		Button button = new Button(composite, SWT.CHECK);
		button.setText(buttonDescription);
		return button;
	}

	/**
	 * 创建check box 样式的组件
	 * 
	 * @param container
	 * @param description
	 * @param buttonDescription
	 * @return
	 */
	public static Button createExamButtonComposite(Composite container,
			String description, String buttonDescription) {
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.EXAM_FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);

		label.setText(description);

		Button button = new Button(composite, SWT.PUSH);
		button.setText(buttonDescription);
		data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = 80;
		button.setLayoutData(data);
		return button;
	}

	/**
	 * 创建表格列
	 * 
	 * @param columnDesc
	 * @param width
	 * @param tableViewer
	 * @return
	 */
	public static TableViewerColumn createTableViewColumn(
			TableViewer tableViewer, String columnDesc, int width) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tableColumn.setWidth(width);
		tableColumn.setText(columnDesc);
		return tableViewerColumn;
	}

	/**
	 * 创建树列
	 * 
	 * @param columnDesc
	 * @param width
	 * @param treeViewer
	 * @return
	 */
	public static TreeViewerColumn createTreeViewColumn(TreeViewer treeViewer,
			String columnDesc, int width) {
		TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE);
		TreeColumn treeColumn = column.getColumn();
		treeColumn.setAlignment(SWT.CENTER);
		treeColumn.setWidth(width);
		treeColumn.setText(columnDesc);
		return column;
	}

	/**
	 * 创建文件路径Text组件
	 * 
	 * @param composite
	 * @param textDesc
	 * @param buttonDesc
	 * @param style
	 * @return
	 */
	public static Text createFileBrowserText(Composite composite,
			String textDesc, String buttonDesc, int style) {
		final Composite attachComposite = new Composite(composite, SWT.NONE);
		attachComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		attachComposite.setLayout(new GridLayout(3, false));

		Label label = new Label(attachComposite, SWT.NONE);
		GridData gd_attachLabel = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_attachLabel.widthHint = 85;
		label.setLayoutData(gd_attachLabel);
		label.setText(textDesc);

		final Text text = new Text(attachComposite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button browseButton = new Button(attachComposite, SWT.NONE);
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(attachComposite.getShell(),
						SWT.OPEN);
				dialog.setText("选择一个附件");
				String path = dialog.open();
				if (path != null) {
					if (text.getText() != null && text.getText().length() > 0) {
						text.append(";" + path);
					} else {
						text.setText(path);
					}

				}
			}
		});
		GridData gd_browseButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_browseButton.widthHint = 100;
		browseButton.setLayoutData(gd_browseButton);
		browseButton.setText(buttonDesc);

		return text;
	}

	/**
	 * 
	 * @return
	 */
	public static IStructuredContentProvider getDefaultListContentProvider() {
		return new ContentProvider();
	}

	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				return ((List<?>) inputElement).toArray();
			}
			return new Object[0];
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private static class SetContentProvider implements
			IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Set) {
				return ((Set<?>) inputElement).toArray();
			}
			return new Object[0];
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	public static Text createLongSpinnerComposite(Composite container,
			String description, int style) {
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label = new Label(composite, SWT.NONE);
		GridData data = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		data.horizontalAlignment = SWT.RIGHT;
		data.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(data);

		label.setText(description);

		Text text = new Text(composite, style);
		// text.addVerifyListener(new VerifyListener() {
		//
		// public void verifyText(VerifyEvent arg0) {
		// char ch = arg0.character;
		// if(Character.isDigit(ch)){
		// arg0.doit = true;
		// }else if(ch == SWT.BS){
		// arg0.doit = true;
		// }else{
		// arg0.doit = false;
		// }
		// }
		// });

		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return text;
	}

	public static IContentProvider getDefaultSetContentProvider() {
		return new SetContentProvider();
	}

	/**
	 * 创建URL Text组件
	 * 
	 * @param container
	 * @param description
	 * @param style
	 * @return
	 */
	public static Text createURLTextComposite(Composite container,
			String description, int style) {

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		composite.setLayout(new GridLayout(3, false));

		Label label = new Label(composite, SWT.NONE);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1);
		gd_label.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		label.setLayoutData(gd_label);
		label.setText(description);

		final Text text = new Text(composite, style);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button button = new Button(composite, SWT.NONE);

		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button.widthHint = 80;
		button.setLayoutData(gd_button);
		button.setText("预览");
		new Label(composite, SWT.NONE);

		final Browser browser = new Browser(composite, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String url = text.getText();
				if (url != null) {
					browser.setUrl(url);
				}

			}
		});

		return text;
	}
}
