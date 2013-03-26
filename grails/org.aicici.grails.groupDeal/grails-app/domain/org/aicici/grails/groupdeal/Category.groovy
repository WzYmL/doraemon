package org.aicici.grails.groupdeal

class Category {

    static constraints = {
    }
	String name;
	String code
	String info

	@Override
	public String toString() {
		if(name != null){
			return name;
		}
		return super.toString();
	}		
}
