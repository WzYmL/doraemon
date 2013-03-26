package org.salever.j2se.common.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2656320239845927768L;

	private String firstName;

	private String lastName;

	private int age;

	private Person friend;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param age
	 */
	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the friend
	 */
	public Person getFriend() {
		return friend;
	}

	/**
	 * @param friend
	 *            the friend to set
	 */
	public void setFriend(Person friend) {
		this.friend = friend;
	}

	public String toString() {
		return "[Person: firstName=" + firstName + " lastName=" + lastName
				+ " age=" + age + " friend=" + friend.getFirstName() + "]";
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		age = age << 2;
		stream.defaultWriteObject();
	}

	private Object writeReplace() throws ObjectStreamException {
		return new PersonProxy(this);
	}

	/**
	 * 
	 * @param stream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject();
		age = age >> 2;
	}
}

class PersonProxy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306891229644914932L;

	private String data;

	public PersonProxy(Person p) {
		data = p.getFirstName() + "," + p.getLastName() + "," + p.getAge();
		if (p.getFriend() != null) {
			Person f = p.getFriend();
			data = data + "," + f.getFirstName() + "," + f.getLastName() + ","
					+ f.getAge();
		}
	}

	private Object readResolve() throws ObjectStreamException {
		String[] strings = data.split(",");
		Person person = new Person(strings[0], strings[1], Integer
				.parseInt(strings[2]));
		if (strings.length > 3) {
			Person friend = new Person(strings[3], strings[4], Integer
					.parseInt(strings[5]));
			person.setFriend(friend);
			friend.setFriend(person);
		}
		return person;
	}

}
