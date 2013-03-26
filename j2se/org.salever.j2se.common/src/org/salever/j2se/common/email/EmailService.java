/**
 * 
 */
package org.salever.j2se.common.email;

import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * Email sending service, provide the basic simple text message email and HTML
 * format message email, and also the email with attachments.
 * 
 * Need activation.jar, commons-email-1.2.jar, mail.jar
 * 
 * @author LiXiaopeng
 * 
 */
public class EmailService {

	/**
	 * User email account address
	 */
	private String account;

	/**
	 * User email account password
	 */
	private String password;

	/**
	 * Email host name, for example "smtp.163.com"
	 */
	private String hostName;

	/**
	 * SMTP port
	 */
	private int port = 25;

	/**
	 * @param account
	 * @param password
	 * @param hostName
	 * @param port
	 */
	public EmailService(String account, String password, String hostName,
			int port) {
		this.account = account;
		this.password = password;
		this.hostName = hostName;
		this.port = port;
	}

	/**
	 * @param account
	 * @param password
	 * @param hostName
	 */
	public EmailService(String account, String password, String hostName) {
		this.account = account;
		this.password = password;
		this.hostName = hostName;
	}

	/**
	 * Send simple format text message, to one address or more addresses.
	 * 
	 * @param toAddresses
	 *            - the to addresses
	 * @param subject
	 *            - the email title
	 * @param content
	 *            - the email content
	 * @return true if successful, otherwise false.
	 */
	public boolean sendSimpleMail(List<String> toAddresses, String subject,
			String content) {

		SimpleEmail email = new SimpleEmail();
		email.setTLS(true);
		email.setHostName(hostName);
		email.setAuthentication(account, password); // 用户名密码设置
		email.setSmtpPort(port);

		try {
			for (String address : toAddresses) {
				email.addTo(address); // 收件人地址地址
			}
			email.setFrom(account); // 发件人地址
			email.setSubject(subject); // 主题
			email.setMsg(content); // 内容
			email.setCharset("utf-8");
			email.send();

		} catch (EmailException e) {
			return false;

		}
		return true;
	}

	/**
	 * Send HTML format text message, to one address or more addresses.
	 * 
	 * @param toAddresses
	 *            - the to addresses
	 * @param subject
	 *            - the email title
	 * @param content
	 *            - the email content
	 * @return true if successful, otherwise false.
	 */
	public boolean sendHtmlMail(List<String> toAddresses, String subject,
			String content) {

		HtmlEmail email = new HtmlEmail();
		email.setHostName(hostName);
		email.setAuthentication(account, password); // 用户名密码设置
		email.setSmtpPort(port);

		try {
			for (String address : toAddresses) {
				email.addTo(address); // 收件人地址地址
			}
			email.setFrom(account); // 发件人地址
			email.setSubject(subject); // 主题
			email.setMsg(content); // 内容
			email.setCharset("utf-8");
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
			return false;

		}
		return true;
	}

	/**
	 * Send HTML format text message with attachments, to one address or more
	 * addresses.
	 * 
	 * @param toAddresses
	 *            - the to addresses
	 * @param subject
	 *            - the email title
	 * @param content
	 *            - the email content
	 * 
	 * @param attachments
	 *            - attachment list
	 * 
	 * @return true if successful, otherwise false.
	 */
	public boolean sendMailWithAttachments(List<String> toAddresses,
			String subject, String content, List<EmailAttachment> attachments) {

		HtmlEmail email = new HtmlEmail();
		email.setTLS(true);
		email.setHostName(hostName);
		email.setAuthentication(account, password);
		email.setSmtpPort(port);

		try {
			// 多个收件人地址
			for (String address : toAddresses) {
				email.addTo(address);
			}
			// 多个附件
			for (EmailAttachment attachment : attachments) {
				email.attach(attachment);
			}
			email.setFrom(account);
			email.setSubject(subject);
			email.setMsg(content);
			email.setCharset("utf-8");

			email.send();

		} catch (EmailException e) {
			return false;

		}
		return true;
	}
}
