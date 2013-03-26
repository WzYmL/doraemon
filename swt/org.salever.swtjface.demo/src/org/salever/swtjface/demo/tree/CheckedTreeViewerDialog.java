package org.salever.swtjface.demo.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.swt.widgets.Display;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.swt.widgets.Button;

public class CheckedTreeViewerDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			if (element instanceof Bean) {
				return ((Bean) element).getText();
			}

			if (element instanceof BeanItem) {
				return ((BeanItem) element).getText();
			}

			return super.getText(element);
		}
	}

	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof List) {
				return ((List) parentElement).toArray();
			} else if (parentElement instanceof Bean) {
				return ((Bean) parentElement).getList().toArray();
			}
			return new Object[0];
		}

		public Object getParent(Object element) {
			if(element instanceof BeanItem){
				return ((BeanItem) element).getParent();
			}
			return null;
		}

		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}
	}

	private List<Bean> input;

	private ContainerCheckedTreeViewer treeViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public CheckedTreeViewerDialog(Shell parentShell) {
		super(parentShell);
		input = new ArrayList<Bean>();

		Bean bean = new Bean("root1");
		BeanItem bi = new BeanItem("child1");
		bi.setParent(bean);
		bean.getList().add(bi);
		bi = new BeanItem("child2");
		bi.setParent(bean);
		bean.getList().add(bi);
		input.add(bean);

		bean = new Bean("root2");
		bi = new BeanItem("child1");
		bi.setParent(bean);
		bean.getList().add(bi);
		bi = new BeanItem("child2");
		bi.setParent(bean);
		bean.getList().add(bi);
		input.add(bean);

	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		treeViewer = new ContainerCheckedTreeViewer(composite, SWT.BORDER);

		Tree tree = treeViewer.getTree();

		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(getShell(), "info", treeViewer.getCheckedElements().length + "");
			}
		});
		button.setText("New Button");
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setInput(input);
		
		check();

		return container;
	}

	private void check() {
		for(Bean bean: input){
			for(BeanItem bi: bean.getList()){
				treeViewer.setChecked(bi, true);
				break;
			}
			treeViewer.setChecked(bean, true);
		}
		
	}

	protected Object getSelectedElement() {
		IStructuredSelection selection = (IStructuredSelection) treeViewer
				.getSelection();
		if (selection.isEmpty())
			return null;
		return selection.getFirstElement();
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
		return new Point(450, 300);
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				new CheckedTreeViewerDialog(null).open();
			}
		});
	}

	static class Bean {

		private String text;
		private List<BeanItem> list = new ArrayList<BeanItem>();

		public Bean(String text) {
			this.text = text;
			list = new ArrayList<BeanItem>();
		}

		public String getText() {
			return text;
		}

		public void setText(String value) {
			this.text = value;
			// firePropertyChange("text", this.text, this.text = value);
		}

		public List<BeanItem> getList() {
			return list;
		}

		public void setList(List<BeanItem> list) {
			this.list = list;
		}
	}

	static class BeanItem {
		String text;
		Bean parent;

		public Bean getParent() {
			return parent;
		}

		public void setParent(Bean parent) {
			this.parent = parent;
		}

		public BeanItem(String name) {
			this.text = name;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text
		 *            the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}

	}

}
