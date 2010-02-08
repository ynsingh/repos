package myMail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class NewMail {
	
	   public static boolean sendMail(String text, String mailFrom, String mailTo, String subject) throws Exception {  
	boolean sent=false;
		   try {  
		          Properties props = System.getProperties();  
		          //props.setProperty("proxySet","true");
		          //props.setProperty("socksProxyHost","192.168.155.1");
		          //props.setProperty("socksProxyPort","1080");
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
		           System.out.println("anil");
		           Transport.send(message);
		           sent=true;
		       } catch (Exception e) {  
		          System.out.println("error="+e);
		       }
		       return sent;
		   }  
		      
		   static class MyAuthenticator extends Authenticator {  
		       public PasswordAuthentication getPasswordAuthentication() {  
		           String username = "mailuser@gmail.com";  
		           String password = "mailpassword";  
		      
		           return new PasswordAuthentication(username, password);  
		       }  
		   }  
}
