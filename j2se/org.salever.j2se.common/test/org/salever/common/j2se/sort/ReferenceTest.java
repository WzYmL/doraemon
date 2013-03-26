/**
 * 
 */
package org.salever.common.j2se.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXiaopeng
 *
 */
public class ReferenceTest {

	public static void main(String[] args){
		List<Person> list = new ArrayList<Person>();
		Person old = new Person();
		old.age = 100;
		list.add(old);
		Person young = new Person();
		young.age = 10;
		list.add(young);
		
		Person p = list.get(0);
		p.age = 50;
		p = null;
		
		System.out.println("TesT");
	}
	
}

class Person{
	int age;
}
