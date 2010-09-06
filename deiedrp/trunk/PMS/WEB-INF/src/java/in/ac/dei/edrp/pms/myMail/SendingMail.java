package in.ac.dei.edrp.pms.myMail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * This class is related with sending mail
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 *
 */
public class SendingMail {
	static String username=null;
	static String password=null;
	/**
	 * Used for sending mail
	 * @param bodyText It holds message body.
	 * @param mailTo It holds the email_id of mail Recipient
	 * @param mailData It holds the mail server port,mail server name,email_id of sender and password of email_id.
	 * @return boolean 
	 * @throws Exception
	 */
	   public static boolean sendMail(String bodyText,String mailTo,
			  Map<String, String> mailData) throws Exception {  
	boolean sent=false;
	
		   try {  
		          			
			   Properties props = System.getProperties();  
//		          props.setProperty("proxySet","true");
//		          props.setProperty("socksProxyHost","192.10.40.250");
//		          props.setProperty("socksProxyPort","8080");
			   
			   //this is working fine
//			   props.setProperty("http.proxyHost", "10.151.0.16");
//			   props.setProperty("http.proxyPort", "80");

		          props.setProperty("mail.smtp.port", mailData.get("smtpServerPort").toString());
		          props.setProperty("mail.smtp.socketFactory.port", mailData.get("smtpServerPort").toString()); 
		          props.setProperty("mail.smtp.host", mailData.get("smtpServerName").toString());
		          props.setProperty("mail.smtp.startssl.enable","true");
		          props.setProperty("mail.smtp.starttls.enable", "true");  
		          props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		          props.setProperty("mail.smtp.auth", "true");  
		          props.put("mail.store.protocol", "pop3");//for incoming mail
		          props.put("mail.transport.protocol", "smtp");//for outgoing mail

		           final String username = mailData.get("mailFrom").toString();
		           final String password = mailData.get("userPassword").toString();

		           Session smtpSession = Session.getDefaultInstance(props, 
                           new Authenticator(){
                       protected PasswordAuthentication getPasswordAuthentication() {
                          return new PasswordAuthentication(username, password);
                       }});
  
		          smtpSession.setDebug(false);  
		          String mailFrom=username;
		          MimeMessage message = new MimeMessage(smtpSession);  
		          message.setFrom(new InternetAddress(mailFrom)); 
		          message.setRecipients(Message.RecipientType.TO, 
                          InternetAddress.parse(mailTo,false));
		           final String encoding = "UTF-8";  
		      
		           message.setSubject(mailData.get("subject").toString(), encoding);  
		           message.setText(bodyText, encoding); 
		           System.out.println("Mail to:aniltiwari08@gmail.com");
		           Transport.send(message);
		           sent=true;
		       } catch (Exception e) {  
		          System.out.println("error in sending mail="+e);
		       }
		       return sent;
		   }  
	   
	   public static boolean checkMailValidation(String serverName,String port,
			   String userid,String userpassword,String subject) throws Exception {  
		   boolean sent=false;
	try{
		 			Properties props = System.getProperties();  
//         	   props.setProperty("proxySet","true");
// 	    	   props.setProperty("http.proxyHost", "10.151.0.16");
//     		   props.setProperty("http.proxyPort", "80");
    		          username=userid.trim();
    		          password=userpassword.trim();
    		          props.setProperty("mail.smtp.port",port);
    		          props.setProperty("mail.smtp.socketFactory.port",port); 
       //smtp.mail.yahoo.com for yahoo
       //smtp.gmail.com for gmail
    		          props.setProperty("mail.smtp.host",serverName);
    		          props.setProperty("mail.smtp.startssl.enable","true");
    		          props.setProperty("mail.smtp.starttls.enable", "true");  
    		          props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    		          props.setProperty("mail.smtp.auth", "true");  
    		          props.put("mail.store.protocol", "pop3");//for incoming mail
    		          props.put("mail.transport.protocol", "smtp");//for outgoing mail
    		          Session smtpSession = Session.getDefaultInstance(props, 
                  new Authenticator(){
              protected PasswordAuthentication getPasswordAuthentication()
              {
                 return new PasswordAuthentication(SendingMail.username.trim(), SendingMail.password.trim());
              }});

    		          smtpSession.setDebug(false);  
    		          String mailFrom=username;
    		          MimeMessage message = new MimeMessage(smtpSession);  
    		          message.setFrom(new InternetAddress(mailFrom)); 
    		          message.setRecipients(Message.RecipientType.TO, 
    		          InternetAddress.parse(mailFrom,false));
    		          final String encoding = "UTF-8";  
     
    		          message.setSubject(subject, encoding);  
    		          message.setText("This is a system generated mail for confirmation of your email id and password for PMS", encoding); 
    		          System.out.println("Mail to:aniltiwari08@gmail.com");
          				Transport.send(message);
          				sent=true;
			   		} catch (Exception e) {  
			          System.out.println("errorin checkmail validation="+e);
			       }
			   		return sent;
	   		}
			  
	}
