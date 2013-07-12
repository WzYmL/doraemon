/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation 
 *     Email:salever@126.com
 *
 * Create on 2011-10-12 下午07:36:55
 *******************************************************************************/
package org.salever.rcp.remoteSystem.server.model.ceriterion;

import java.io.Serializable;

/**
 * Created by @author LiXiaopeng
 * 
 * Comments.
 * 
 */
public class CardCriterion extends AbstractCriterion implements
		IPageableCriterion, Serializable {

	private String code;

	private Integer[] types;

	private Integer[] statuses;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(Integer[] types) {
		this.types = types;
	}

	/**
	 * @return the types
	 */
	public Integer[] getTypes() {
		return types;
	}

	/**
	 * @param statuses the statuses to set
	 */
	public void setStatuses(Integer[] statuses) {
		this.statuses = statuses;
	}

	/**
	 * @return the statuses
	 */
	public Integer[] getStatuses() {
		return statuses;
	}
}
