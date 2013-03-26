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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.salever.rcp.tools.topology_viewer.descriptor.ReadOnlyTextPropertyDescriptor;
import org.salever.rcp.tools.topology_viewer.system.CustomConstants;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 * @name AbstractConnectionModel
 * 
 */
public abstract class AbstractConnection extends AbstractModel {

	/**
	 * 
	 */
	public static final String P_BEND_POINT = "_bend_point";

	/*
	 * public static final String P_SOURCE_NODE ="_source_node";
	 * 
	 * public static final String P_TARGET_NODE = "_target_node";
	 */

	/**
	 * 
	 */
	private TopoModel source, target;

	/**
	 * 
	 */
	private List<Object> bendpoints = new ArrayList<Object>();

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void addBendpoint(int index, Point point) {
		bendpoints.add(index, point);
		firePropertyChange(P_BEND_POINT, null, null);
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void attachSource() {
		if (!source.getSourceConnection().contains(this)) {
			source.addSourceConnection(this);
		}
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void attachTarget() {
		if (!target.getTargetConnection().contains(this)) {
			target.addTargetConnection(this);
		}
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void detachSource() {
		source.removeSourceConnection(this);
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void detachTarget() {
		target.removeTargetConnection(this);
	}

	public List<Object> getBendpoint() {
		return bendpoints;
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

		IPropertyDescriptor[] descriptor = new IPropertyDescriptor[] {
		/*
		 * new TextPropertyDescriptor(CustomConstants.P_SOURCE_NODE,
		 * Messages.getString("Property.Source_Node")), new
		 * TextPropertyDescriptor(CustomConstants.P_TARGET_NODE,
		 * Messages.getString("Property.Target_Node")),
		 */
		tpdSource, tpdTarget };

		return descriptor;
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if (id.equals(CustomConstants.P_SOURCE_NODE)) {
			return source.getId();
		} else if (id.equals(CustomConstants.P_TARGET_NODE)) {
			return target.getId();
		}
		return super.getPropertyValue(id);
	}

	/**
	 * @return the source
	 */
	public TopoModel getSource() {
		return source;
	}

	/**
	 * @return the target
	 */
	public TopoModel getTarget() {
		return target;
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void removeBendpoint(int index) {
		bendpoints.remove(index);
		firePropertyChange(P_BEND_POINT, null, null);
	}

	public void replaceBendpoint(int index, Point point) {
		bendpoints.set(index, point);
		firePropertyChange(P_BEND_POINT, null, null);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		super.setPropertyValue(id, value);
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(TopoModel source) {
		this.source = source;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(TopoModel target) {
		this.target = target;
	}

}
