/**
 * @file CustomDirectEditManager.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����07:02:04
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.widgets.Text;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;


/**
 * @author salever@126.com
 * 
 * @name CustomDirectEditManager
 * 
 */
public class CustomDirectEditManager extends DirectEditManager {

	private TopoModel model;

	@SuppressWarnings("unchecked")
	public CustomDirectEditManager(GraphicalEditPart source, Class editorType,
			CellEditorLocator locator) {
		super(source, editorType, locator);
		// TODO Auto-generated constructor stub
		model = (TopoModel) source.getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
	 */
	@Override
	protected void initCellEditor() {
		// TODO Auto-generated method stub
		this.getCellEditor().setValue(model.getName());

		Text text = (Text) getCellEditor().getControl();
		text.selectAll();
	}

}
