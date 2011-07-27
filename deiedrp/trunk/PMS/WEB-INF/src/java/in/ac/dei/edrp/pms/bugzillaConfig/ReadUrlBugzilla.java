package in.ac.dei.edrp.pms.bugzillaConfig;

import in.ac.dei.edrp.pms.adminConfig.ReadPropertiesFile;
import in.ac.dei.edrp.pms.myMail.SendingMail;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This class is related with sending mail
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 *
 */
public class ReadUrlBugzilla {
	static String url=null;
//	static String username=null;
//	static String password=null;
	/**
	 * Used for sending mail
	 * @param bodyText It holds message body.
	 * @param mailTo It holds the email_id of mail Recipient
	 * @param mailData It holds the mail server port,mail server name,email_id of sender and password of email_id.
	 * @return boolean 
	 * @throws Exception
	 */
	
	public static String getUrl(String filePath) throws Exception {
		
//	   public static boolean sendMail(String bodyText,String mailTo,String subject,
//			  Map<String
//		, String> mailData) throws Exception {  
		Map<String, String> mailData = ReadPropertiesFile.mailConfig(filePath);
	boolean sent=false;
//	String url="";
		   try {  
		          			
			   Properties props = System.getProperties();  
//		          props.setProperty("proxySet","true");
//		          props.setProperty("socksProxyHost","192.10.40.250");
//		          props.setProperty("socksProxyPort","8080");
			   
			   //this is working fine
//			   props.setProperty("http.proxyHost", "10.151.0.16");
//			   props.setProperty("http.proxyPort", "80");

//		          props.setProperty("mail.smtp.port", mailData.get("smtpServerPort").toString());
//		          props.setProperty("mail.smtp.socketFactory.port", mailData.get("smtpServerPort").toString()); 
//		          props.setProperty("mail.smtp.host", mailData.get("smtpServerName").toString());
//		          props.setProperty("mail.smtp.startssl.enable","true");
//		          props.setProperty("mail.smtp.starttls.enable", "true");  
//		          props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		          props.setProperty("mail.smtp.auth", "true");  
//		          props.put("mail.store.protocol", "pop3");//for incoming mail
//		          props.put("mail.transport.protocol", "smtp");//for outgoing mail

		           final String url = mailData.get("url").toString();
//		           final String password = mailData.get("userPassword").toString();

//		           Session smtpSession = Session.getDefaultInstance(props, 
//                           new Authenticator(){
//                       protected PasswordAuthentication getPasswordAuthentication() {
//                          return new PasswordAuthentication(username, password);
//                       }});
//  
//		          smtpSession.setDebug(false);  
//		          String mailFrom=username;
//		          MimeMessage message = new MimeMessage(smtpSession);  
//		          message.setFrom(new InternetAddress(mailFrom)); 
//		          message.setRecipients(Message.RecipientType.TO, 
//                          InternetAddress.parse(mailTo,false));
//		           final String encoding = "UTF-8";  
//		      
//		           message.setSubject(subject, encoding);  
//		           message.setText(bodyText, encoding); 
//		           System.out.println("Mail to:aniltiwari08@gmail.com");
//		           Transport.send(message);
		           System.out.println("url is ="+url);
//		         if(url.equalsIgnoreCase(null)){
//		        	 sent=false;
//		         }
//		         else{
//		        	 sent=true; 
//		         }		           
		       } catch (Exception e) {  
		          System.out.println("error in sending mail="+e);
		       }
		       return url;
		   }  
	}
