package org.salever.rcp.tech.chapter5.view;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView("org.salever.rcp.tech.chapter5.view.view1", IPageLayout.TOP,
				IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);
	}

}
