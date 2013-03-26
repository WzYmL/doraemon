package com.talend.demo.spring_demo;

import java.util.prefs.Preferences;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		Preferences preferences = Preferences.userRoot();
		preferences.put("fly.name", "Eagle");
		
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "/META-INF/spring/spring-context.xml" });
		
		
		Flyable fly = (Flyable) applicationContext.getBean("bird");
		fly.fly();
	}
}
