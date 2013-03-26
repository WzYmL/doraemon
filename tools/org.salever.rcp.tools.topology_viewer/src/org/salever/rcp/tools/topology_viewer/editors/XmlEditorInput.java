/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-6 上午10:22:23
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.editors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class XmlEditorInput implements IEditorInput {

	private IPath path;

	public XmlEditorInput(IPath path) {
		this.path = path;
	}
	
	public XmlEditorInput(String path) {
		this.path = new Path(path);
	}

	@Override
	public boolean exists() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return path.toString();
	}

	/**
	 * @return the path
	 */
	public IPath getPath() {
		return path;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return path.toString();
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = new Path(path);
	}

}
