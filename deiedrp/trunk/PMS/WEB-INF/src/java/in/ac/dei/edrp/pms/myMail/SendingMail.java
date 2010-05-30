package in.ac.dei.edrp.pms.myMail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * It is used for sending mail.
 * @author Anil Kumar Tiwari <b>mailto:</b>aniltiwari08@gmail.com
 */

public class SendingMail {
	
    public static boolean sendMail(String text, String mailFrom, String mailTo, String subject) throws Exception {  
	boolean sent=false;
		   try {  
		          Properties props = System.getProperties();  
//		          props.setProperty("proxySet","true");
//		          props.setProperty("socksProxyHost","10.151.0.16");
//		          props.setProperty("socksProxyPort","80");
//		          props.setProperty("socksProxyUser","staff");
//		          props.setProperty("socksProxyPassword","staff@dei");


		           props.setProperty("mail.smtp.port", "465");  
		           props.setProperty("mail.smtp.socketFactory.port", "465");  
		           props.setProperty("mail.smtp.host", "smtp.gmail.com");
		           props.setProperty("mail.smtp.startssl.enable","true");
		           props.setProperty("mail.smtp.starttls.enable", "true");  
		           props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		           props.setProperty("mail.smtp.auth", "true");  
		       
		           Authenticator auth = new MyAuthenticator();  
		           Session smtpSession = Session.getInstance(props, auth);  
		           smtpSession.setDebug(true);  
		     
		           MimeMessage message = new MimeMessage(smtpSession);  
		          message.setFrom(new InternetAddress(mailFrom));  
		           message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));  
		      
		           final String encoding = "UTF-8";  
		      
		           message.setSubject(subject, encoding);  
		           message.setText(text, encoding); 
		           System.out.println("Mail to:aniltiwari08@gmail.com");
		           Transport.send(message);
		           sent=true;
		       } catch (Exception e) {  
		          System.out.println("error="+e);
		       }
		       return sent;
		   }  
		      
		   static class MyAuthenticator extends Authenticator {  
		       public PasswordAuthentication getPasswordAuthentication() {  
		           String username = "pms.dei2010@gmail.com";  
		           String password = "anilpms@dei";  
		           return new PasswordAuthentication(username, password);  
		       }  
		   }  
}
