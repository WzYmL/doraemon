/**
 * @file DiagramEditorInput.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����01:58:57
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.salever.rcp.tools.topology_viewer.models.Network;


/**
 * @author salever@126.com
 * 
 * @name DiagramEditorInput
 * 
 */
public class DiagramEditorInput implements IEditorInput {

	private Network network;

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
		return network.getName();
	}

	/**
	 * @return the network
	 */
	public Network getNetwork() {
		return network;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return network.getName();
	}

	/**
	 * @param network
	 *            the network to set
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}else if(obj instanceof DiagramEditorInput){
			DiagramEditorInput input = (DiagramEditorInput) obj;
			//return input.getName().equals(this.getName()) && input.getNetwork().getLevel() == network.getLevel();
			return input.getNetwork().getId() == network.getId() && input.getNetwork().getLevel() == network.getLevel();
		}
		return false;
	
	}
}
