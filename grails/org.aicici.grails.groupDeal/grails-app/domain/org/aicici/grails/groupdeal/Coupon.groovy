package org.aicici.grails.groupdeal


class Coupon {

    static constraints = {
    }
	
	Date DateExpiration
	long IdDeal
	
	long IdPrestataire
	Date DateCreation
	String CodeCoupon
	String PrenomBeneficaire
	String NomBeneficaire
	String NumeroMobile
	
	
}
