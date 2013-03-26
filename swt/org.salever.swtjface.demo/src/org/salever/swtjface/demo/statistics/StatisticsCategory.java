/**
 * @created on 2010-12-29 下午04:37:24
 */
package org.salever.swtjface.demo.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计类别
 * @author
 */
public class StatisticsCategory {

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private List<StatisticsOption> options = new ArrayList<StatisticsOption>();

	private StatisticsDimension dimension;
	
	/**
	 * @return the dimension
	 */
	public StatisticsDimension getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(StatisticsDimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * @param name
	 */
	public StatisticsCategory(String name) {
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
	 * @return the options
	 */
	public List<StatisticsOption> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<StatisticsOption> options) {
		this.options = options;
	}
	
	

}
