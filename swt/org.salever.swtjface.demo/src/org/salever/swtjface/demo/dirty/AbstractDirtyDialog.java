package org.salever.swtjface.demo.dirty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractDirtyDialog extends TitleAreaDialog implements
		PropertyChangeListener {

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
	public AbstractDirtyDialog(Shell parentShell) {
		super(parentShell);
		addPropertyChangeListener(this);
	}

	@Override
	public boolean close() {
		removePropertyChangeListener(this);
		return super.close();
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		applyButton = createButton(parent, -1, "Apply", false);
		okButton = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
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
		if (prop.equals(PROP_DIRTY)) {
			Object obj = evt.getNewValue();
			final boolean b = (Boolean) obj;
			getShell().getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					applyButton.setEnabled(b);
					okButton.setEnabled(b);

				}

			});

		}

	}
}
