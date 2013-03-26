package org.salever.tools.friends.conference.system;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.salever.tools.friends.conference.models.Country;

public class TableLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Country) {
			switch (columnIndex) {
			case 0:
				return ((Country) element).getName();
			default:
				break;
			}
		}
		return element.toString();
	}
}
