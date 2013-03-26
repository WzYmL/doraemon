package org.salever.swtjface.demo.binding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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

public class TVBindDialog extends Dialog {
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
			return null;
		}

		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}
	}

	private Text text;

	private List<Bean> input;

	private TreeViewer treeViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public TVBindDialog(Shell parentShell) {
		super(parentShell);
		input = new ArrayList<Bean>();

		Bean bean = new Bean("root1");
		bean.getList().add(new BeanItem("child1"));
		bean.getList().add(new BeanItem("child2"));
		input.add(bean);

		bean = new Bean("root2");
		bean.getList().add(new BeanItem("child1"));
		bean.getList().add(new BeanItem("child2"));
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
		gridLayout.numColumns = 2;

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		treeViewer = new TreeViewer(composite, SWT.BORDER);
		// treeViewer.addSelectionChangedListener(new
		// ISelectionChangedListener() {
		// public void selectionChanged(SelectionChangedEvent event) {
		// Object old = getSelectedElement();
		// if (old instanceof Bean) {
		// ((Bean) old).setText(text.getText());
		// }
		// if (old instanceof BeanItem) {
		// ((BeanItem) old).setText(text.getText());
		// }
		// }
		// });
		Tree tree = treeViewer.getTree();
		// tree.addSelectionListener(new SelectionAdapter() {
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// Object old = getSelectedElement();
		// if (old instanceof Bean) {
		// text.setText(((Bean) old).getText());
		// } else if (old instanceof BeanItem) {
		// text.setText(((BeanItem) old).getText());
		// }
		// }
		// });
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setInput(input);

		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));

		text = new Text(composite_1, SWT.BORDER);

		return container;
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
		m_bindingContext = initDataBindings();
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
				new TVBindDialog(null).open();
			}
		});
	}

	static class Bean extends AbstractModelObject {

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
//			firePropertyChange("text", this.text, this.text = value);
		}

		public List<BeanItem> getList() {
			return list;
		}

		public void setList(List<BeanItem> list) {
			if (list != null)
				list = new ArrayList<BeanItem>(list);
			firePropertyChange("list", this.list, this.list = list);
		}
	}

	static class BeanItem extends AbstractModelObject {
		String text;

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
//			firePropertyChange("text", this.text, this.text = text);
		}

	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue treeViewerObserveSingleSelection = ViewersObservables
				.observeSingleSelection(treeViewer);
		IObservableValue textTextObserveValue = SWTObservables.observeText(
				text, SWT.Modify);
		bindingContext.bindValue(BeansObservables.observeDetailValue(
				treeViewerObserveSingleSelection, "text", String.class),
				textTextObserveValue, new UpdateValueStrategy() {
					@Override
					public Object convert(Object value) {
						// TODO Auto-generated method stub
						return super.convert(value);
					}
				}, new UpdateValueStrategy() {
					@Override
					public Object convert(Object value) {
						Object old = getSelectedElement();
//						if (old instanceof Bean) {
//							((Bean) old).setText((String) value);
//						}
//						if (old instanceof BeanItem) {
//							((BeanItem) old).setText((String) value);
//						}
//						treeViewer.refresh(old);
						return super.convert(value);
					}
					
					@Override
					protected IStatus doSet(IObservableValue observableValue,
							Object value) {
						IStatus status = super.doSet(observableValue, value);
						treeViewer.refresh(getSelectedElement());
						return status;
					}
				});
		//
		return bindingContext;
	}
}
