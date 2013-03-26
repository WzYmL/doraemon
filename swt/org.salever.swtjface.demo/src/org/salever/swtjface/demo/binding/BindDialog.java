package org.salever.swtjface.demo.binding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.ComputedList;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.salever.swtjface.demo.SWTResourceManager;
import org.salever.swtjface.demo.dirty.AbstractDirtyDialog;
import org.salever.swtjface.demo.dirty.DirtyDialog;
import org.eclipse.swt.widgets.Label;

public class BindDialog extends AbstractDirtyDialog {
	
	public class DirtyUpdateValueStrategy extends UpdateValueStrategy {
		@Override
		protected IStatus doSet(IObservableValue observableValue, Object value) {

			Object oldValue = observableValue.getValue();
			if (oldValue != null) {
				if (!oldValue.equals(value)) {
					setDirty(true);
				}
			}
			return super.doSet(observableValue, value);
		}
	}

	private DataBindingContext m_bindingContext;

	private class TableLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return ((Person) element).getName();
			case 1:
				return ((Person) element).getAge();
			}
			return element.toString();
		}
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

	private Table table;
	private Text nameText;
	private Text ageText;

	private List<Person> persons;
	private ViewModel viewModel;
	private TableViewer tableViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public BindDialog(Shell parentShell) {
		super(parentShell);

		persons = new ArrayList<Person>();
		Person p = new Person();
		p.setAge("1");
		p.setName("LXP");
		persons.add(p);

		p = new Person();
		p.setAge("2");
		p.setName("LiXP");
		persons.add(p);

		viewModel = new ViewModel();
		viewModel.setPeople(persons);
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
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tableViewer = new TableViewer(container, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnName = tableViewerColumn.getColumn();
		tblclmnName.setWidth(100);
		tblclmnName.setText("name");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnAge = tableViewerColumn_1.getColumn();
		tblclmnAge.setWidth(100);
		tblclmnAge.setText("age");
		// tableViewer.setLabelProvider(new TableLabelProvider());
		// tableViewer.setContentProvider(new ContentProvider());
		// tableViewer.setInput(persons);
		// table.select(0);

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true,
				1, 1));
		composite.setLayout(new GridLayout(1, false));

		nameText = new Text(composite, SWT.BORDER);
		nameText.setSize(198, 19);

		ageText = new Text(composite, SWT.BORDER);
		ageText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		
		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label.setText("New Label");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	static class ViewModel extends AbstractModelObject {
		private List people = new ArrayList();

		public List getPeople() {
			return new ArrayList(people);
		}

		public void setPeople(List people) {
			firePropertyChange("people", this.people,
					this.people = new ArrayList(people));
		}
	}

	static class Person extends AbstractModelObject {
		private String name;
		private String age;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			firePropertyChange("name", this.name, this.name = name);
		}

		/**
		 * @return the age
		 */
		public String getAge() {
			return age;
		}

		/**
		 * @param age
		 *            the age to set
		 */
		public void setAge(String age) {
			this.age = age;
			firePropertyChange("age", null, age);
		}
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		final IObservableValue tableViewerObserveSingleSelection = ViewersObservables
				.observeSingleSelection(tableViewer);
		IObservableValue nameProp = BeansObservables.observeDetailValue(
				tableViewerObserveSingleSelection, "name", //$NON-NLS-1$
				String.class);
		IObservableValue nameTextTextObserveValue = SWTObservables.observeText(
				nameText, SWT.Modify);
		bindingContext.bindValue(nameProp, nameTextTextObserveValue,
				new UpdateValueStrategy() {

				}, new DirtyUpdateValueStrategy());

		IObservableValue ageProp = BeansObservables.observeDetailValue(
				tableViewerObserveSingleSelection, "age", //$NON-NLS-1$
				String.class);
		IObservableValue ageTextTextObserveValue = SWTObservables.observeText(
				ageText, SWT.Modify);
		bindingContext.bindValue(ageProp, ageTextTextObserveValue);

		final IObservableList people = new ComputedList() {
			@Override
			protected List calculate() {
				return persons;
			}
		};
		;
		;

		ViewerSupport.bind(tableViewer, people, BeanProperties //$NON-NLS-1$
				.values(Person.class, new String[] { "name", "age" })); //$NON-NLS-1$
		tableViewer.setSelection(new StructuredSelection(persons.get(0)));
		//
		// tableViewer.setInput(list);
		return bindingContext;
	}

	public static void main(String[] args) {
		Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {

				new BindDialog(null).open();
			}
		});
	}

	/**
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons
	 *            the persons to set
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
