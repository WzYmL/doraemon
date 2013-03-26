import org.aicici.grails.groupdeal.Member

class BootStrap {

    def init = { servletContext ->
		def member = new Member(id:1,username:'minDeng'
			,password:'12345')
		member.password = member.password.encodeAsPassword()
		member.save()
    }
    def destroy = {
    }
}
