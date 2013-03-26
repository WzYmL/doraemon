package org.aicici.grails.groupdeal

import java.security.MessageDigest
import sun.misc.BASE64Encoder

class PasswordCodec {
	static encode = { str ->
		MessageDigest md = MessageDigest.getInstance('SHA')
		md.update(str.getBytes('UTF-8'))
		return (new BASE64Encoder()).encode(md.digest())
	}
}
