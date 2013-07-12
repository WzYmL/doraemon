/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation 
 *     Email:salever@126.com
 *
 * Create on 2011-10-12 下午09:19:15
 *******************************************************************************/
package org.salever.rcp.dbSystem.client.db.criterion;

/**
 * Created by @author LiXiaopeng
 * 
 * Comments.
 * 
 */
public class AbstractCriterion implements IPageableCriterion {

	private int pageSize;

	private long pageCount;

	private int currentPage;

	/**
	 * @return the currentPage
	 */
	@Override
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the pageCount
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
	 * @param currentPage
	 *            the currentPage to set
	 */
	@Override
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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

}
