/**
 * @created on 2010-12-29 下午04:37:44
 */
package org.salever.swtjface.demo.statistics;

/**
 * 统计项
 * @author 
 */
public class StatisticsOption {

	private String name;
	
	private StatisticsCategory category;
	

	/**
	 * @param name
	 */
	public StatisticsOption(String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public StatisticsCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(StatisticsCategory category) {
		this.category = category;
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

}
