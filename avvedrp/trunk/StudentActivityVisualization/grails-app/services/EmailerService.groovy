import javax.mail.MessagingException

import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.*;
import javax.activation.*;
import groovy.sql.Sql;


/**
 * Simple service for sending emails.
 *
 * Work is planned in the Grails roadmap to implement first-class email
 * support, so there's no point in making this code any more sophisticated.
 *
 * @author Haotian Sun
 */
class EmailerService {

	boolean transactional = false

	def mailSender
	def mailMessage // a "prototype" email instance
    def dataSource
	/**
	 * Send a list of emails.
	 *
	 * @param mails a list of maps
	 */
	def sendEmails(def emailId,def fromId,def subject,def mailmessage) {

	
									
									/*	// Build the mail messages
										def messages = []
										for (mail in mails) {
											// create a copy of the default message
											def message = new SimpleMailMessage()
											//SimpleMailMessage()
											message.to = mail.to
											message.from = mail.from
											message.subject = mail.subject
											message.text = mail.text
											messages << message
										}
										// Send them all together
										try {
											mailSender.send(messages as SimpleMailMessage[])
										}
										catch (MailException e) {
											log.error "Failed to send emails: $e.message", e
										}
										catch (MessagingException e) {
											log.error "Failed to send emails: $e.message", e
										}
									}   */
									
/*------------------------------------sending email message start------------------------------------*/									
		
		
def sql=new Sql(dataSource);						
String host = ""; String port = ""; String username = ""; String password = "";	String 	isSSL="";		
	sql.eachRow("SELECT smtp_host as HOST,smtp_port as PORT,smtp_username as USERNAME,smtp_password as PASSWORD,isSSL as ISSSL  FROM email_settings")
	{ 
	    row ->				
	                host = row.HOST
	                port = row.PORT
	                username = row.USERNAME
					password = row.PASSWORD
					isSSL = row.ISSSL
								
	}					

def mailSubject=subject
def mailContent= mailmessage
String from = fromId
String[] to={emailId};
    		        
    Properties props = new Properties();
    props.put("mail.smtp.user", username);
    props.put("mail.smtp.host", host);
        if(!"".equals(port))
			props.put("mail.smtp.port", port);
    		        
    	if(!"false".equals(isSSL))
		 {
		    props.put("mail.smtp.starttls.enable","true");  
		    props.put("mail.smtp.socketFactory.port", port);
		    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.socketFactory.fallback", "false");
		 }
    		        	    		        
	        props.put("mail.smtp.auth", "true");
	            if(true){
	            props.put("mail.smtp.debug", "true");
	            }else{
	            props.put("mail.smtp.debug", "false");          
	            }
    		              
    		    String mailMessage="";
			    mailMessage= mailmessage
    		 
    		        try
    		        {
    		                Session session = Session.getDefaultInstance(props, null);
    		               	session.setDebug(true); 
    		            		MimeMessage msg = new MimeMessage(session);
    	                    		msg.setText(mailMessage);
    		            			msg.setSubject(mailSubject);
    	                  			msg.setFrom(new InternetAddress(from));
    		                        	for(int i=0;i<to.length;i++){
    		            						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
    		                        			}
	                         Transport transport = session.getTransport("smtp");
	                         transport.connect(host, username, password);
	                         transport.sendMessage(msg, msg.getAllRecipients());
	                         transport.close();
	                       return true;
    		        }
    		       catch (Exception mex)
    		        {
    		            mex.printStackTrace();
    		                        return false;
    	            } 
  
    return true;
}

/*------------------------------------sending email message end------------------------------------*/

}
