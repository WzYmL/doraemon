package org.salever.rcp.tools.topology_viewer;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public static final String PROPERTIES = "org.eclipse.ui.views.PropertySheet";
	final String outline = "org.eclipse.ui.views.ContentOutline";

	public void createInitialLayout(IPageLayout layout) {

		IFolderLayout right = layout.createFolder("Right", IPageLayout.RIGHT,
				0.3f, layout.getEditorArea());
		IFolderLayout bottom = layout.createFolder("Bottom",
				IPageLayout.BOTTOM, 0.35f, layout.getEditorArea());

		right.addView(outline);
		bottom.addView(PROPERTIES);

		layout.setEditorAreaVisible(true);
	}
}
