package org.aicici.grails.groupdeal

import java.sql.Timestamp

class Member {

	String Prenom
	String Nom
	Date DateCreation

	String username
	String MobilePhone
	String password
	
	int  Role
	
	City city;
	String toString() {
		"$username"
	}
  
	static constraints = {
		username(email: true,blank:false,nullable:false)
		password(blank: false, password: true,nullable:false)
	}
}
