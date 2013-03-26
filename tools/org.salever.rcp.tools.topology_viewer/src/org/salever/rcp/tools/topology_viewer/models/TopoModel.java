/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午10:33:33
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class TopoModel extends AbstractModel {

	public static final String P_CONSTRAINT = "_constraint";

	public static final String P_LEVEL = "_level";

	public static final String P_ID = "_id";

	public static final String P_NAME = "_name";

	public static final String P_TYPE = "_type";

	public static final String P_CONFIG_FILE = "_config_file";

	public static final String P_HEIGHT = "_height";

	public static final String P_WIDTH = "_width";

	public static final String P_ICON_TYPE = "_icon_type";

	public static final String P_SOURCE_CONNECTION = "_source_connection";

	public static final String P_TARGET_CONNECTION = "_target_connection";

	protected int level;

	protected int id = CustomFactory.getID();

	protected String name = id + "";

	protected String type;

	protected Rectangle constraint = new Rectangle();

	protected AbstractModel parent;

	protected List<Object> sourceConnection = new ArrayList<Object>();

	protected List<Object> targetConnection = new ArrayList<Object>();

	/**
	 * 
	 */
	public void addSourceConnection(Object obj) {
		sourceConnection.add(obj);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void addTargetConnection(Object obj) {
		targetConnection.add(obj);
		firePropertyChange(P_TARGET_CONNECTION, null, null);
	}

	/**
	 * @return the constraint
	 */
	public Rectangle getConstraint() {
		return constraint;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the sourceConnection
	 */
	public List<Object> getSourceConnection() {
		return sourceConnection;
	}

	/**
	 * @return the targetConnection
	 */
	public List<Object> getTargetConnection() {
		return targetConnection;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void removeSourceConnection(Object obj) {
		sourceConnection.remove(obj);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void removeTargetConnection(Object obj) {
		targetConnection.remove(obj);
		firePropertyChange(P_TARGET_CONNECTION, null, null);
	}

	/**
	 * @param constraint
	 *            the constraint to set
	 */
	public void setConstraint(Rectangle constraint) {
		this.constraint = constraint;
		firePropertyChange(P_CONSTRAINT, null, null);
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
		firePropertyChange(P_ID, null, null);
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
		firePropertyChange(P_NAME, null, name);
	}

	/**
	 * @param sourceConnection
	 *            the sourceConnection to set
	 */
	public void setSourceConnection(List<Object> sourceConnection) {
		this.sourceConnection = sourceConnection;
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}

	/**
	 * @param targetConnection
	 *            the targetConnection to set
	 */
	public void setTargetConnection(List<Object> targetConnection) {
		this.targetConnection = targetConnection;
		firePropertyChange(P_TARGET_CONNECTION, null, null);
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
		firePropertyChange(P_TYPE, null, null);
	}

	/**
	 * @return the parent
	 */
	public AbstractModel getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(AbstractModel parent) {
		this.parent = parent;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] descriptor = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(P_ID, Messages
						.getString("Property.Id")),
				new TextPropertyDescriptor(P_NAME, Messages
						.getString("Property.Name")),
				new TextPropertyDescriptor(P_TYPE, Messages
						.getString("Property.Type")),
				new TextPropertyDescriptor(P_LEVEL, "Level"),
				new TextPropertyDescriptor(P_CONSTRAINT, "Bounds") };
		return descriptor;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(P_NAME)) {
			return name;
		} else if (id.equals(P_TYPE)) {
			return type;
		} else if (id.equals(P_ID)) {
			return this.id;
		} else if (id.equals(P_CONSTRAINT)) {
			return "(" + constraint.x + " , " + constraint.y + ")";
		}else if(id.equals(P_LEVEL)){
			return level;
		}
		return null;
	}
}
