package org.aicici.grails.groupdeal

import java.sql.Blob
import java.sql.Time
import java.sql.Timestamp
import org.apache.tomcat.util.buf.TimeStamp

/*
 * http://zhq-tony.iteye.com/blog/211611
 */

class Deal {

    static constraints = {
    }
	
	static hasMany = [ cities : City ]
	
	String title
	long IdPrestataire
	Date DataCreation
	Object ImageDeal
	Object ToutesPhotoduDeal
	String DetailDeal
	String ConditionsDeal
	String AdresseDeal
	String ContactTel
	int ZoneIdDeal
	Date DebutDeal
	Date FinDeal
	String DeviseDeal
	float PrixStandard
	float PrixGroupe
	int NbMinimum
	int NbMaximum
	boolean FlagValidation
	int NombreAcheteurs
	Category category
	  	
}
