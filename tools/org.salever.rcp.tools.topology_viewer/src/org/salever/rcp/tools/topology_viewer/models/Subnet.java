/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-5-4 上午11:00:01
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description
 * 
 * @author salever@126.com
 * 
 */
public class Subnet extends TopoModel {

	public static final String P_ROUERS = "p_routers";

	public static final String P_SUBNETS = "p_subnets";

	public static final Object P_CHILDREN = "p_children";

	private List<Router> routers;

	private List<Subnet> subnets;

	private List<Integer> subnetRefs;

	public Subnet() {
		routers = new ArrayList<Router>();
		subnets = new ArrayList<Subnet>();
		subnetRefs = new ArrayList<Integer>();
	}

	public void addRouter(Router obj) {
		this.routers.add(obj);
		obj.setParent(this);
		obj.setLevel(level + 1);
		firePropertyChange(P_ROUERS, null, null);
	}

	public void addSubnet(Subnet obj) {
		this.subnets.add(obj);
		obj.setParent(this);
		obj.setLevel(level + 1);
		firePropertyChange(P_SUBNETS, null, null);
	}

	public void addSubnetRef(Integer obj) {
		this.subnetRefs.add(obj);
	}

	/**
	 * @return the routers
	 */
	public List<Router> getRouters() {
		return routers;
	}

	/**
	 * @return the subnetRefs
	 */
	public List<Integer> getSubnetRefs() {
		return subnetRefs;
	}

	/**
	 * @return the subnets
	 */
	public List<Subnet> getSubnets() {
		return subnets;
	}

	public void removeRouter(Router obj) {
		this.routers.remove(obj);
		obj.setParent(null);
		firePropertyChange(P_ROUERS, null, null);
	}

	public void removeSubnet(Subnet obj) {
		this.subnets.remove(obj);
		firePropertyChange(P_SUBNETS, null, null);
	}

	/**
	 * @param routers
	 *            the routers to set
	 */
	public void setRouters(List<Router> routers) {
		this.routers = routers;
		firePropertyChange(P_ROUERS, null, null);
	}

	/**
	 * @param subnets
	 *            the subnets to set
	 */
	public void setSubnets(List<Subnet> subnets) {
		this.subnets = subnets;
		firePropertyChange(P_SUBNETS, null, null);
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public boolean hasChild(TopoModel model) {
		for(Iterator<Router> it = routers.iterator(); it.hasNext();){
			if(it.next() == model){
				return true;
			}
		}
		
		for(Iterator<Subnet> it = subnets.iterator(); it.hasNext();){
			if(it.next() == model){
				return true;
			}
		}
		return false;
	}
}
