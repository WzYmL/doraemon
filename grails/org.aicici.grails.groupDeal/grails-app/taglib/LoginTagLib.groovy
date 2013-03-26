
class LoginTagLib {
	def loginControl = {
	if(session.login){
		out << "Hello ${session.member.username} "
		out << """[${link(action:"logout", controller:"member"){"Logout"}}]"""
	  } else {
		out << """[${link(action:"login", controller:"member"){"Login"}}]"""
	  }
	}
}
