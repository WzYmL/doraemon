package org.salever.swtjface.demo.dirty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.salever.swtjface.demo.tree.MainDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class DirtyDialog extends TitleAreaDialog implements PropertyChangeListener{

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}

	private static final String PROP_DIRTY = "dirty";

	private Text text;
	private boolean dirty;

	private Button applyButton;

	private Button okButton;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public DirtyDialog(Shell parentShell) {
		super(parentShell);
		addPropertyChangeListener(this);
	}

	@Override
	public boolean close() {
		removePropertyChangeListener(this);
		return super.close();
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

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				buttonSelect();
			}
		});
		button.setText("New Button");

		text = new Text(container, SWT.BORDER);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				textChanged();
				
			}
		});
		text
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
						1, 1));

		return area;
	}

	protected void textChanged() {
		setDirty(true);
		
	}

	protected void buttonSelect() {
		setDirty(true);
		
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		applyButton = createButton(parent, -1, "Apply", false);
		okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		applyButton.setEnabled(false);
		okButton.setEnabled(false);
	}

	@Override
	protected void buttonPressed(int buttonId) {

		if (buttonId == -1) {
			setDirty(false);
			MessageDialog.openInformation(getShell(), "Apply", "Apply!");
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(532, 404);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				DirtyDialog dialog = new DirtyDialog(null);
				dialog.open();
			}
		});
	}

	/**
	 * @param dirty
	 *            the dirty to set
	 */
	public void setDirty(boolean dirty) {
		firePropertyChange(PROP_DIRTY, this.dirty, this.dirty = dirty);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if(prop.equals(PROP_DIRTY)){
			Object obj = evt.getNewValue();
			boolean b = (Boolean) obj;
			applyButton.setEnabled(b);
			okButton.setEnabled(b);
		}
		
	}
}
