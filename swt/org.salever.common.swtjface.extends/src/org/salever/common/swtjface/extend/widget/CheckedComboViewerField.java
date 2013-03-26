/**
 * @author LiXiaopeng
 * @created on 2010-11-24 下午09:11:46
 */
package org.salever.common.swtjface.extend.widget;

import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * @param <T>
 * 
 */
public class CheckedComboViewerField<T> extends Composite {

	private Combo combo;
	private ComboViewer comboViewer;
	private List<T> models;
	private String description;
	private Button button;
	private IBaseLabelProvider labelProvider;

	/**
	 * @param parent
	 * @param style
	 * @param description
	 */
	public CheckedComboViewerField(Composite parent, int style,
			String description, List<T> models, IBaseLabelProvider labelProvider) {
		super(parent, style);
		this.description = description;
		this.models = models;
		this.labelProvider = labelProvider;
		init();
	}

	/**
	 * 初始化面板
	 */
	private void init() {

		// GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false,
		// 1, 1);
		// gd.widthHint = 230;
		// setLayoutData(gd);

		GridLayout gl_composite = new GridLayout(2, false);
		// gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		this.setLayout(gl_composite);

		button = new Button(this, SWT.CHECK);
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_button.widthHint = GUIConstants.FIELD_DESCEPTION_LABEL_WIDTH;
		button.setLayoutData(gd_button);
		button.setText(description);

		combo = new Combo(this, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_text = new GridData(GridData.FILL_BOTH);
		comboViewer = new ComboViewer(combo);
		comboViewer.setLabelProvider(labelProvider);
		comboViewer.setContentProvider(new ContentProvider());
		comboViewer.setInput(models);
		combo.setLayoutData(gd_text);
	}

	public T getSelectModel() {
		ISelection selection = comboViewer.getSelection();
		IStructuredSelection sselection = (IStructuredSelection) selection;
		return (T) sselection.getFirstElement();
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	public boolean isEnable() {
		return button.getSelection();
	}

	public String getValue() {
		return combo.getText();
	}

	public ComboViewer getComboViewer() {
		return comboViewer;
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
}
