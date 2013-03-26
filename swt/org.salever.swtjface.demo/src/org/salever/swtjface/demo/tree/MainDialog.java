package org.salever.swtjface.demo.tree;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;

public class MainDialog extends TitleAreaDialog {

	private static class TreeContentProvider implements ITreeContentProvider {
		public void dispose() {
		}

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof List) {
				return ((List<?>) parentElement).toArray();
			}
			return new Object[] { "item1", "item2", "item3" };
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			return super.getText(element);
		}
	}

	public static final int DEFAULT_SIZE = 0x01;

	public static int postTag = 0;


	private Text cId;
	private Text cBgImage;
	private Text cImage;
	private Text cText;
	private TreeViewer treeViewer;
	private Spinner cTransparency;
	private Combo cTextPosition;
	private Button cBorder;

	private Button btnBrowse_1;

	private Button btnBrowse;

	private Object root;
	private Table table;
	private Table table_1;

	private Button applyButton;

	private Button okButton;

	/**
	 * @param parentShell
	 */
	public MainDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite area = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) area.getLayout();
		gridLayout.marginLeft = 5;

		Label label = new Label(area, SWT.NONE);
		label.setText("New Label");

		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		Composite vfComposite = new Composite(sashForm, SWT.BORDER);
		vfComposite.setLayout(new GridLayout(1, false));
		ViewForm viewForm = new ViewForm(vfComposite, SWT.NULL);
		viewForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		viewForm.setLayout(new FillLayout(SWT.HORIZONTAL));
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT | SWT.RIGHT);
		treeViewer = new TreeViewer(viewForm, SWT.NONE);
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		MainActionGroup actionGroup = new MainActionGroup(treeViewer, this);
		actionGroup.fillContextMenu(new MenuManager());
		ToolBarManager toolBarManager = new ToolBarManager(toolBar); // 创建一个toolBar
		// 的管理器
		actionGroup.fillActionToolBars(toolBarManager); //
		viewForm.setContent(treeViewer.getControl()); // 主体：Tree
		viewForm.setTopLeft(toolBar);
		treeViewer.setInput(new Object[0]);

		// select(root.getStyleBoxList().get(0));
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, false));

		Group grpMain = new Group(composite_1, SWT.NONE);
		grpMain.setText("Main");
		grpMain.setLayout(new GridLayout(1, false));
		grpMain.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("New Label");

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(new GridLayout(2, false));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_composite_2.heightHint = 200;
		composite_2.setLayoutData(gd_composite_2);

		TableViewer tableViewer = new TableViewer(composite_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));
		composite_4.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));

		Button button = new Button(composite_4, SWT.NONE);
		button.setText("New Button");

		Button button_1 = new Button(composite_4, SWT.NONE);
		button_1.setText("New Button");

		Button button_2 = new Button(composite_4, SWT.NONE);
		button_2.setText("New Button");

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("New Label");

		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(new GridLayout(2, false));
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		TableViewer tableViewer_1 = new TableViewer(composite_3, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
						1));

		Composite composite_5 = new Composite(composite_3, SWT.NONE);
		composite_5.setLayout(new GridLayout(1, false));
		composite_5.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));

		Button button_3 = new Button(composite_5, SWT.NONE);
		button_3.setText("New Button");

		Button button_4 = new Button(composite_5, SWT.NONE);
		button_4.setText("New Button");
		sashForm.setWeights(new int[] { 200, 583 });

		return container;
	}

	public void select(Object obj) {
		if (obj == null) {
			;
			return;
		}
		StructuredSelection s = new StructuredSelection(obj);
		treeViewer.setSelection(s, true);
	}

	protected void initDataBindings() {
		final IObservableValue treeViewerSelection = ViewersObservables
				.observeSingleSelection(treeViewer);
		final IObservableValue idValue = SWTObservables.observeText(cId,
				SWT.Modify);
		IObservableValue borderValue = SWTObservables.observeSelection(cBorder);
		IObservableValue transparencyValue = SWTObservables
				.observeSelection(cTransparency);
		IObservableValue imageValue = SWTObservables.observeText(cImage,
				SWT.Modify);
		IObservableValue bgImageValue = SWTObservables.observeText(cBgImage,
				SWT.Modify);
		IObservableValue textValue = SWTObservables.observeText(cText,
				SWT.Modify);
		IObservableValue textPositionValue = SWTObservables
				.observeSelection(cTextPosition);
		IObservableValue itemSelected = new ComputedValue(Boolean.TYPE) {
			protected Object calculate() {
				return Boolean.valueOf(treeViewerSelection.getValue() != null);
			}
		};

		IObservableValue transparencyProp = BeansObservables
				.observeDetailValue(treeViewerSelection, "transparency", //$NON-NLS-1$
						Integer.class);
		IObservableValue borderProp = BeansObservables.observeDetailValue(
				treeViewerSelection, "border", Boolean.class); //$NON-NLS-1$
		IObservableValue buttonTextProp = BeansObservables.observeDetailValue(
				treeViewerSelection, "buttonText", String.class); //$NON-NLS-1$
		IObservableValue textPositionProp = BeansObservables
				.observeDetailValue(treeViewerSelection, "textPosition", //$NON-NLS-1$
						String.class);
		final IObservableValue idProp = BeansObservables.observeDetailValue(
				treeViewerSelection, "id", String.class); //$NON-NLS-1$
		final IObservableValue bgImageProp = BeansObservables
				.observeDetailValue(treeViewerSelection, "backgroundImage", //$NON-NLS-1$
						String.class);
		final IObservableValue buttonImageProp = BeansObservables
				.observeDetailValue(treeViewerSelection, "buttonImage", //$NON-NLS-1$
						String.class);

		ViewerSupport.bind(treeViewer, root, BeanProperties.list(
				"styleBoxList", String.class), BeanProperties.value(
				String.class, "id"));
		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeEnabled(cTransparency),
				itemSelected);
		bindingContext.bindValue(idValue, idProp);

		bindingContext.bindValue(transparencyValue, transparencyProp,
				new CustomUpdateValueStrategy(),
				new CustomUpdateValueStrategy());
		bindingContext.bindValue(bgImageValue, bgImageProp);
		bindingContext.bindValue(imageValue, buttonImageProp);
		bindingContext.bindValue(borderValue, borderProp);
		bindingContext.bindValue(textValue, buttonTextProp);
		bindingContext.bindValue(textPositionValue, textPositionProp);

		bindingContext.bindValue(SWTObservables.observeEnabled(cId),
				itemSelected);
		bindingContext.bindValue(SWTObservables.observeEnabled(cBgImage),
				itemSelected);
		bindingContext.bindValue(SWTObservables.observeEnabled(cBorder),
				itemSelected);
		bindingContext.bindValue(SWTObservables.observeEnabled(cImage),
				itemSelected);
		bindingContext.bindValue(SWTObservables.observeEnabled(cText),
				itemSelected);
		bindingContext.bindValue(SWTObservables.observeEnabled(cTextPosition),
				itemSelected);

		bindingContext.bindValue(SWTObservables.observeEnabled(btnBrowse),
				itemSelected);
		bindingContext.bindValue(SWTObservables.observeEnabled(btnBrowse_1),
				itemSelected);

		

		MultiValidator validator = new MultiValidator() {
			protected IStatus validate() {
				return null;
			}
		};

		bindingContext.addValidationStatusProvider(validator);

	}

	// @Override
	// public boolean validate() {
	// setErrorMessage(null);
	// return true;
	// }

	private class CustomUpdateValueStrategy extends UpdateValueStrategy {
		protected IStatus doSet(IObservableValue observableValue, Object value) {
			if (value != null) {
				IStatus result = super.doSet(observableValue, value);
				return result;
			} else
				return Status.OK_STATUS;
		}
	}

	
}
