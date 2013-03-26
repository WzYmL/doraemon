/**
 * @file ContentsTreeEditPart.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-14  ����03:23:29
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.parts.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.salever.rcp.tools.topology_viewer.models.Network;


/**
 * @author salever@126.com
 * 
 * @name ContentsTreeEditPart
 * 
 */
public class NetworkTreeEditPart extends TreeEditPart {

	@SuppressWarnings("unchecked")
	@Override
	public List getModelChildren() {
		return ((Network) getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Network.P_CHILDREN)) {
			refreshChildren();
		}
	}
}
