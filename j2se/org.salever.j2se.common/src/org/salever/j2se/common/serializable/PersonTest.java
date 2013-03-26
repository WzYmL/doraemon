/**
 * 
 */
package org.salever.j2se.common.serializable;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * @author LiXiaopeng
 *
 */
public class PersonTest {

	@Test
	public  void test(){
		
		try {
			Person grubby = new Person("Grubby", "4K", 18);
			Person moon = new Person("Moon", "MYM", 22);
			grubby.setFriend(moon);
			moon.setFriend(grubby);
			
			FileOutputStream fos = new FileOutputStream("person.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(grubby);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
		try {
			FileInputStream fis = new FileInputStream("person.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Person grubby = (Person) ois.readObject();
			ois.close();
			
			assertEquals(grubby.getFirstName(), "Grubby");
			assertEquals(grubby.getLastName(),"4K");
			assertEquals(grubby.getAge(),18);
			
			System.out.println(grubby);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
