package org.salever.rcp.tech.chapter11.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.salever.rcp.tech.chapter11.View;

public class TestPerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.setFixed(false);
		layout.addView(View.ID, IPageLayout.LEFT, 0.33f, editorArea);
	}

}
