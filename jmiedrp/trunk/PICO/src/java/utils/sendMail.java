package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.mail.internet.InternetAddress;
public class sendMail {
	
    public static String sendMail(String emailFrom, String emailUser, String emailFromPasswd, String emailID, String subj, String message) throws Exception {
		String host = "smtp.gmail.com", user = emailUser, pass = emailFromPasswd;
		
                String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		String to = emailID;
		String from = emailFrom;
		String subject = subj;
		String messageText = message;
		boolean sessionDebug = true;

                      
		Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol.", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);

Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);
Address address1 = new InternetAddress(user, from);
		Message msg = new MimeMessage(mailSession);
		//msg.setFrom(new InternetAddress(from));
		msg.setFrom(address1);
                InternetAddress[] address = {new InternetAddress(to)};
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject);
		msg.setContent(messageText, "text/html");

		Transport transport = mailSession.getTransport("smtp");
	        transport.connect(host,user, pass);

		try {
			transport.sendMessage(msg, msg.getAllRecipients());
		}
		catch (Exception e) {
			System.out.println("Error in sending email: " + e.getMessage());
                        return "Error in sending email: " + e.getMessage().toString();
		}
		transport.close();
                return "Success";
	}
}
