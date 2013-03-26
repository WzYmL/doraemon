/**
 * 
 */
package org.salever.rcp.tools.topology_viewer.descriptor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author salever@126.com
 * 
 */
public class ReadOnlyTextPropertyDescriptor extends TextPropertyDescriptor {

	public ReadOnlyTextPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new TextCellEditor(parent);
		((Text) editor.getControl()).setEditable(false);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}
}
