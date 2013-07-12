/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation 
 *     Email:salever@126.com
 *
 * Create on 2011-10-12 下午07:34:11
 *******************************************************************************/
package org.salever.rcp.remoteSystem.server.model.ceriterion;

/**
 * Created by @author LiXiaopeng
 * 
 * Comments.
 * 
 */
public interface IPageableCriterion {

	/**
	 * 总页数
	 * 
	 * @return
	 */
	long getPageCount();

	/**
	 * 当前页数
	 * 
	 * @return
	 */
	int getCurrentPage();

	/**
	 * 
	 * @return
	 */
	int getPageSize();

	void setCurrentPage(int i);

	void setPageCount(long totalPage);
}
