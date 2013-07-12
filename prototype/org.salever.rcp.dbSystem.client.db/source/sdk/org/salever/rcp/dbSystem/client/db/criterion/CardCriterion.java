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
package org.salever.rcp.dbSystem.client.db.criterion;

import java.io.Serializable;

/**
 * Created by @author LiXiaopeng
 * 
 * Comments.
 * 
 */
public class CardCriterion implements IPageableCriterion, Serializable {

	private String code;

	private Integer type;

	private Integer fromWidth;

	private Integer toWidth;

	private Integer status;

	private long pageCount;

	private int currentPage;

	private int pageSize;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.criterion.IPageableCriterion#getCurrentPage
	 * ()
	 */
	@Override
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the fromWidth
	 */
	public Integer getFromWidth() {
		return fromWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.readidtech.data.client.db.criterion.IPageableCriterion#getPageCount()
	 */
	@Override
	public long getPageCount() {
		return pageCount;
	}

	/**
	 * @return the pageSize
	 */
	@Override
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @return the toWidth
	 */
	public Integer getToWidth() {
		return toWidth;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	@Override
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @param fromWidth
	 *            the fromWidth to set
	 */
	public void setFromWidth(Integer fromWidth) {
		this.fromWidth = fromWidth;
	}

	/**
	 * @param pageCount
	 *            the pageCount to set
	 */
	@Override
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @param toWidth
	 *            the toWidth to set
	 */
	public void setToWidth(Integer toWidth) {
		this.toWidth = toWidth;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

}
