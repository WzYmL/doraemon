/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午10:38:07
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author salever@126.com
 * 
 * @name ContentsModel
 * 
 */
public class Network extends AbstractModel {

	public static final String P_CHILDREN = "_children";

	public static final String P_NAME = "_name";

	public static final int TOP_LEVEL = 0;

	private String mappingFile;

	private String name;

	private int level = TOP_LEVEL;

	private List<TopoModel> children = new ArrayList<TopoModel>();

	private int id;

	private Subnet content;
	
	public void addChild(TopoModel child) {
		child.setParent(this);
		child.setLevel(level + 1); 
		children.add(child);
		this.firePropertyChange(P_CHILDREN, null, null);
	}

	/**
	 * @return the children
	 */
	public List<TopoModel> getChildren() {
		return children;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the mappingFile
	 */
	public String getMappingFile() {
		return mappingFile;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void removeAllChild() {
		children.clear();
		this.firePropertyChange(P_CHILDREN, null, null);
	}

	public void removeChild(TopoModel child) {
		child.setParent(null);
		children.remove(child);
		this.firePropertyChange(P_CHILDREN, null, null);
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<TopoModel> children) {
		this.children = children;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param mappingFile
	 *            the mappingFile to set
	 */
	public void setMappingFile(String mappingFile) {
		this.mappingFile = mappingFile;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
		this.firePropertyChange(P_NAME, null, null);
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public TopoModel getChildById(int id) {
		return getChildrenById(id, children);
	}

	@SuppressWarnings("unchecked")
	private TopoModel getChildrenById(int id, List list) {
		TopoModel model = null;
		for (Iterator<TopoModel> it = list.iterator(); it.hasNext();) {
			model = it.next();
			if (model.getId() == id) {
				return model;
			} else if (model instanceof Subnet) {
				return getChildrenById(id, ((Subnet) model).getRouters());

			}
		}
		return null;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public boolean containsChild(TopoModel model){
		for(Iterator<TopoModel> it = children.iterator(); it.hasNext();){
			if(it.next() == model){
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the content
	 */
	public Subnet getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Subnet content) {
		this.content = content;
	}
}
