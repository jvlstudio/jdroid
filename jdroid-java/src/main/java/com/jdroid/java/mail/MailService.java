package com.jdroid.java.mail;

/**
 * 
 * @author Maxi Rosson
 */
public interface MailService {
	
	/**
	 * @param subject
	 * @param body
	 * @param sender
	 * @param recipient
	 * @throws MailException
	 */
	public void sendMail(String subject, String body, String sender, String recipient) throws MailException;
	
}
