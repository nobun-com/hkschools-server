package com.hkschool.rest.common;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

    final static String userName = "mindnervesdemo@gmail.com";// change accordingly
    final static String password = "mntadmin";// change accordingly

    public static void sendEmail(String subject, String messageBody, String to) {
	Properties properties = System.getProperties();
	properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.port", "587");
	properties.put("mail.smtp.starttls.enable", "true");

	Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	    }
	});
	try {
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress("mindnervesdemo@gmail.com"));
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	    message.setSubject(subject);
	    BodyPart messageBodyPart1 = new MimeBodyPart();
	    Multipart multipart = new MimeMultipart();
	    messageBodyPart1.setText(messageBody);
	    multipart.addBodyPart(messageBodyPart1);

	    message.setContent(multipart);

	    Transport.send(message);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String args[]) {
	try {
	    sendEmail("", "", "rhtkorade97@gmail.com");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
