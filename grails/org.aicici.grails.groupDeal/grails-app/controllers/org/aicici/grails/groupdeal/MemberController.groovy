package org.aicici.grails.groupdeal

class MemberController {
	def scaffold = Member

//    def index = { redirect(action:scaffold) }
    def login = {
        def members = new Member()
        members.properties = params
        return ['members':members]
    }

    def logOut = {
        session.invalidate()
        redirect(controller:'members',action:'login')
    }
	def authenticate = {
		def user = Member.findByUsernameAndPassword(params.login, params.password)
		if(user){
		session.user = user
		flash.message = "Hello ${user.username}!"
		redirect(controller:"deal", action:"list")
		}else{
		flash.message = "Sorry, ${params.login}. Please try again."
		redirect(action:"login")
		}
		}
	 
}
