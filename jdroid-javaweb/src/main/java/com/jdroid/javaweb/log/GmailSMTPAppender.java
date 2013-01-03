package com.jdroid.javaweb.log;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.net.SMTPAppender;
import com.jdroid.java.utils.EncodingUtils;
import com.sun.mail.smtp.SMTPTransport;

/**
 * Extension of Log4j {@link SMTPAppender} for Gmail support
 * 
 */
public class GmailSMTPAppender extends SMTPAppender {
	
	/**
	 * Cached session for later use i.e. while sending emails
	 */
	protected Session session;
	
	public GmailSMTPAppender() {
		super();
	}
	
	/**
	 * Create mail session.
	 * 
	 * @return mail session, may not be null.
	 */
	@Override
	protected Session createSession() {
		Properties props = new Properties();
		props.put("mail.smtps.host", getSMTPHost());
		props.put("mail.smtps.auth", "true");
		
		Authenticator auth = null;
		if ((getSMTPPassword() != null) && (getSMTPUsername() != null)) {
			auth = new Authenticator() {
				
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(getSMTPUsername(), getSMTPPassword());
				}
			};
		}
		session = Session.getInstance(props, auth);
		if (getSMTPProtocol() != null) {
			session.setProtocolForAddress("rfc822", getSMTPProtocol());
		}
		if (getSMTPDebug()) {
			session.setDebug(getSMTPDebug());
		}
		return session;
	}
	
	/**
	 * Send the contents of the cyclic buffer as an e-mail message.
	 */
	@Override
	protected void sendBuffer() {
		try {
			String s = formatBody();
			boolean allAscii = true;
			for (int i = 0; (i < s.length()) && allAscii; i++) {
				allAscii = s.charAt(i) <= 0x7F;
			}
			MimeBodyPart part;
			if (allAscii) {
				part = new MimeBodyPart();
				part.setContent(s, layout.getContentType());
			} else {
				try {
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					Writer writer = new OutputStreamWriter(MimeUtility.encode(os, "quoted-printable"),
							EncodingUtils.UTF8);
					writer.write(s);
					writer.close();
					InternetHeaders headers = new InternetHeaders();
					headers.setHeader("Content-Type", layout.getContentType() + "; charset=UTF-8");
					headers.setHeader("Content-Transfer-Encoding", "quoted-printable");
					part = new MimeBodyPart(headers, os.toByteArray());
				} catch (Exception ex) {
					StringBuilder contentBuilder = new StringBuilder(s);
					for (int i = 0; i < contentBuilder.length(); i++) {
						if (contentBuilder.charAt(i) >= 0x80) {
							contentBuilder.setCharAt(i, '?');
						}
					}
					part = new MimeBodyPart();
					part.setContent(contentBuilder.toString(), layout.getContentType());
				}
			}
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(part);
			msg.setContent(mp);
			
			msg.setSentDate(new Date());
			send(msg);
		} catch (MessagingException e) {
			LogLog.error("Error occured while sending e-mail notification.", e);
		} catch (RuntimeException e) {
			LogLog.error("Error occured while sending e-mail notification.", e);
		}
	}
	
	/**
	 * Pulled email send stuff i.e. Transport.send()/Transport.sendMessage(). So that on required this logic can be
	 * enhanced.
	 * 
	 * @param msg Email Message
	 * @throws MessagingException
	 */
	protected void send(Message msg) throws MessagingException {
		SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
		try {
			t.connect(getSMTPHost(), getSMTPUsername(), getSMTPPassword());
			t.sendMessage(msg, msg.getAllRecipients());
		} finally {
			t.close();
		}
	}
}