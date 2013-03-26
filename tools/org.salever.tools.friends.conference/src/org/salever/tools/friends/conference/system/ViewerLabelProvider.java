package org.salever.tools.friends.conference.system;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.salever.tools.friends.conference.models.Country;

public class ViewerLabelProvider extends LabelProvider {
	public Image getImage(Object element) {
		return super.getImage(element);
	}

	public String getText(Object element) {
		if (element instanceof Country) {
			return ((Country) element).getName();
		}
		return super.getText(element);
	}
}