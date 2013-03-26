/**
 * @author LiXiaopeng
 * @created on 2010-11-9 下午08:05:29
 */
package org.salever.swtjface.demo.utils;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

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
		data.widthHint = 85;
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
		data.widthHint = 85;
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
		data.widthHint = 85;
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
		data.widthHint = 85;
		label.setLayoutData(data);

		label.setText(description);

		Text text = new Text(composite, style);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		composite.setLayout(new GridLayout(1, false));

		Label label = new Label(composite, SWT.NONE);
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
		data.widthHint = 85;
		label.setLayoutData(data);

		label.setText(description);

		Button button = new Button(composite, SWT.CHECK);
		button.setText(buttonDescription);
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
	public static TableViewerColumn createTVColumn(TableViewer tableViewer,
			String columnDesc, int width) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tableColumn.setWidth(width);
		tableColumn.setText(columnDesc);
		return tableViewerColumn;
	}

	/**
	 * 
	 * @return
	 */
	public static IStructuredContentProvider getDefaultListContentProvider() {
		return contentProvider;
	}

	private static IStructuredContentProvider contentProvider = new ContentProvider();

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
}
