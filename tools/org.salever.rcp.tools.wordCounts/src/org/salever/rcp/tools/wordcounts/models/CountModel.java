/**
 * 
 */
package org.salever.rcp.tools.wordcounts.models;

/**
 * @author LiXP
 *
 */
public class CountModel {

	private String word;
	
	private int counts;
	
	private int totalCounts;
	
	private double rate;

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the counts
	 */
	public int getCounts() {
		return counts;
	}

	/**
	 * @param counts the counts to set
	 */
	public void setCounts(int counts) {
		this.counts = counts;
	}

	/**
	 * @return the totalCounts
	 */
	public int getTotalCounts() {
		return totalCounts;
	}

	/**
	 * @param totalCounts the totalCounts to set
	 */
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
}
