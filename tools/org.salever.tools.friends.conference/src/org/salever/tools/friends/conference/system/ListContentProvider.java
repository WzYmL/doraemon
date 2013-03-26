package org.salever.tools.friends.conference.system;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ListContentProvider implements IStructuredContentProvider {
	public void dispose() {
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof java.util.List<?>) {
			return ((java.util.List<?>) inputElement).toArray();
		}
		return new Object[0];
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}