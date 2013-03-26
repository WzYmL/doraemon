/**
 * 
 */
package com.talend.demo.spring_demo.bean;

import com.talend.demo.spring_demo.Flyable;

/**
 * @author LiXP
 *
 */
public class Bird implements Flyable {

	private String name;
	
	/* (non-Javadoc)
	 * @see com.talend.demo.spring_demo.bean.Flyable#fly()
	 */
	public void fly() {
		System.out.println("Bird " + name + " is flying!");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
