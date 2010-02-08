package myMail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

class SMTPAuthenticator extends Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("aniltiwari08@gmail.com", "anil2008mca");
    }
}

public class MailSend //extends HttpServlet
{
	public boolean sendMail(String replyTo, String from_email, String to_email, String subject, String body, String type) {
		boolean sent = false;
		try{
		String d_email = "aniltiwari08@gmail.com",
        d_uname = "aniltiwari08@gmail.com",
        d_password = "anil2008mca",
        d_host = "smtp.gmail.com",
        d_port  = "587", //465,587
        m_to = "deepak2rok@gmail.com",
        m_subject = "Testing",
        m_text = "Hey, this is the testing email.";

Properties props = new Properties();
props.put("mail.smtp.user", d_email);
props.put("mail.smtp.host", d_host);
props.put("mail.smtp.port", d_port);
props.put("mail.transport.protocol", "smtp");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.debug", "true");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.socketFactory.port", d_port);
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.socketFactory.fallback", "false");
java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
Authenticator auth = new SMTPAuthenticator();
System.out.println("authentication ="+auth);
Session session = Session.getInstance(props, auth);
session.setDebug(true);

Message msg = new MimeMessage(session);
msg.setText(m_text);
msg.setSubject(m_subject);
msg.setFrom(new InternetAddress(d_email));
msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));

Transport transport = session.getTransport("smtp");
transport.connect(d_host,d_uname, d_password);
transport.sendMessage(msg, msg.getAllRecipients());
transport.close();
sent=true;
		}
		catch(Exception e)
		{
			System.out.println("error="+e);
		}
return sent;
	}
}
