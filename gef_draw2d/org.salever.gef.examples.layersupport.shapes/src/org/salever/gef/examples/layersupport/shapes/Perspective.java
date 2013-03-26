package org.salever.gef.examples.layersupport.shapes;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {
	
	public void createInitialLayout(IPageLayout layout) {
		
		layout.addView("org.eclipse.ui.navigator.ProjectExplorer", IPageLayout.LEFT, 0.25f, layout.getEditorArea());
		layout.addView("org.eclipse.ui.views.PropertySheet", IPageLayout.BOTTOM, 0.76f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("org.eclipse.ui.views.ContentOutline", IPageLayout.RIGHT, 0.73f, IPageLayout.ID_EDITOR_AREA);
	}
}
