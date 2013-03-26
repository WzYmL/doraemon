package org.salever.common.j2se.email;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.junit.Before;
import org.junit.Test;
import org.salever.j2se.common.email.EmailService;

public class EmailServiceTest {

	EmailService service;

	@Before
	public void setUp() throws Exception {
		service = new EmailService("postmaster@hxky001.com", "r2m2d2q3", "mail.hxky001.com");
	}

	// @Test
	public void testSendSimpleMail() {
		List<String> addresses = new ArrayList<String>();
		addresses.add("prettybai@yahoo.cn");
		addresses.add("prettybai@yeah.net");
		addresses.add("salever@126.com");
		String subject = "Simple mail test 简单邮件测试";
		String content = "Simple mail test 简单邮件测试";
		assertTrue(service.sendHtmlMail(addresses, subject, content));
	}

	 @Test
	public void testSendHtmlMail() {
		List<String> addresses = new ArrayList<String>();
		// addresses.add("prettybai@yahoo.cn");
		// addresses.add("prettybai@yeah.net");
		addresses.add("hxky_receive@163.com");
		String subject = "HTML mail test HTML邮件测试";
		String content = "<a href='www.google.com'>google.com</a> <h1>Simple mail test</h1> <b>HTML邮件测试<b>";
		assertTrue(service.sendHtmlMail(addresses, subject, content));
	}

//	@Test
	public void testSendMailWithAttachments() {
		List<String> addresses = new ArrayList<String>();
		addresses.add("prettybai@yahoo.cn");
		addresses.add("prettybai@yeah.net");
		addresses.add("salever@126.com");

		List<EmailAttachment> attachments = new ArrayList<EmailAttachment>();
		EmailAttachment att = new EmailAttachment();
		att.setPath("C:\\a.doc");
		att.setName("A doc attachment");
		attachments.add(att);
		att = new EmailAttachment();
		att.setPath("C:\\a.docx");
		att.setDescription("A docx attachment");
		attachments.add(att);

		String subject = "HTML mail test HTML邮件测试";
		String content = "<a href='www.google.com'>google.com</a> <h1>Simple mail test</h1> <b>HTML邮件测试<b>";
		assertTrue(service.sendMailWithAttachments(addresses, subject, content,
				attachments));
	}

}
