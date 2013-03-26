package org.salever.tools.friends.conference.system;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.salever.tools.friends.conference.models.Country;

public class ExtendViewerLabelProvider extends LabelProvider {
	public Image getImage(Object element) {
		return super.getImage(element);
	}

	public String getText(Object element) {
		if (element instanceof Country) {
			return ((Country) element).getName() + "(点到"
					+ (((Country) element).getCallCount()) + "次)";
		}
		return super.getText(element);
	}
}