package org.aicici.grails.groupdeal

class City {

    static constraints = {
    }
	
	/**
	 * City name
	 */
	String zoneName
	
	/**
	 * Image
	 */
	Object PhotoZone
	
	public String toString(){
		if(zoneName != null){
			return zoneName;
		}
		return super.toString();
	}
}
