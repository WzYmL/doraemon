/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午10:36:39
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.salever.rcp.tools.topology_viewer.descriptor.ReadOnlyTextPropertyDescriptor;
import org.salever.rcp.tools.topology_viewer.system.CustomConstants;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 * @name LineConnectionModel
 * 
 */
public class DoubleArrowConnection extends AbstractConnection {

	public static final String LINE_WIDTH_PROP = "Line.Width";

	private int lineWidth = 1;

	/**
	 * @return the lineWidth
	 */
	public int getLineWidth() {
		return lineWidth;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		TextPropertyDescriptor tpdSource = new ReadOnlyTextPropertyDescriptor(
				CustomConstants.P_SOURCE_NODE, Messages
						.getString("Property.Source_Node"));
		TextPropertyDescriptor tpdTarget = new ReadOnlyTextPropertyDescriptor(
				CustomConstants.P_TARGET_NODE, Messages
						.getString("Property.Target_Node"));
		TextPropertyDescriptor lineWidth = new TextPropertyDescriptor(
				LINE_WIDTH_PROP, "width");
		IPropertyDescriptor[] descriptor = new IPropertyDescriptor[] {
		/*
		 * new TextPropertyDescriptor(CustomConstants.P_SOURCE_NODE,
		 * Messages.getString("Property.Source_Node")), new
		 * TextPropertyDescriptor(CustomConstants.P_TARGET_NODE,
		 * Messages.getString("Property.Target_Node")),
		 */
		tpdSource, tpdTarget, lineWidth };

		return descriptor;
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if (id.equals(LINE_WIDTH_PROP)) {
			return this.lineWidth;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		if (id.equals(LINE_WIDTH_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	/**
	 * @param lineWidth
	 *            the lineWidth to set
	 */
	public void setLineWidth(Object lineWidth) {
		this.lineWidth = Integer.parseInt(lineWidth.toString());
		firePropertyChange(LINE_WIDTH_PROP, null, lineWidth);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		if (id.equals(LINE_WIDTH_PROP)) {
			this.setLineWidth(value.toString());
		}
		super.setPropertyValue(id, value);

	}
}
