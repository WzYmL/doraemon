/**
 * @created on 2010-12-29 下午04:41:16
 */
package org.salever.swtjface.demo.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计维度
 * @author 
 */
public class StatisticsDimension {

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private List<StatisticsCategory> categories = new ArrayList<StatisticsCategory>();

	/**
	 * @param name
	 */
	public StatisticsDimension(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the categories
	 */
	public List<StatisticsCategory> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<StatisticsCategory> categories) {
		this.categories = categories;
	}

	
	

}
