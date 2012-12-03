import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.*;
import javax.activation.*;
class NotificationsEmailsService {
	def gmsSettingsService
    boolean transactional = true
    //def mailService 
    def serviceMethod() {

    }
	/*
	 * Function to change the status of party,
	 * select the party id from party and compare with corresponding id in NotificationsEmails
 	 */
 def savePartyStatus(def notificationId){
	 println "saveparty"
	 def notific = 2
	 def partyInstanceList =Party.findAll("from Party P  ")
	 println "party--"+partyInstanceList
	 for(e in partyInstanceList){
		def notificationInstance = NotificationsEmails.find("from NotificationsEmails NE where NE.notification="+notificationId+" and NE.party="+e.id)
 		def partyInstanceStatus = Party.get(e.id)
 		if (notificationInstance)
 		{
 			partyInstanceStatus.statusEmail = true
 			println "Notif--"+notificationInstance
 			
 		}
 		else{
 		partyInstanceStatus.statusEmail = false
 		}
 		partyInstanceStatus.save()
 	}
    	
    }
    /*
     * Function to send mail to multiple user
     */
    public List contactUser(def message,def notificationId,def email,def fromEmailId)
    {
    	println "sendem--"+email  
    	println "from id"+fromEmailId
    	def emailList = []
    	 
    	email.each 
    	{  
    		def notificationsEmailsInstance = new NotificationsEmails()
    		def partyEmailId=it
    		def partyEmails = Party.find("from Party P where P.id="+partyEmailId)
    		println "partyemailsinner-"+partyEmails.email
    		if(partyEmails.email != null)
    		{
    			sendMail{
    				to partyEmails.email
    				from fromEmailId
    				subject "Hello"
    				body (view:"mail",model:[partyEmails:partyEmails,message:message],plugin:'email-confirmation')
    			}
    			println "mail send to "+partyEmails.email
    			println "notificationId="+notificationId
    			def p=Notification.find("from Notification N where id="+notificationId)
    			notificationsEmailsInstance.notification=p
    			notificationsEmailsInstance.party=partyEmails
    			notificationsEmailsInstance.save()
    			emailList.add(partyEmails.email)
    	  	}
    		else
    		{
    			println "emails null=="
    			//flash.message "Invalid emailaddress"
    			emailList.add(null)
    			//redirect(action:list)
    		}
    	}
    	return emailList
	}
    /*
     * Function to get all party
     */
    public List getAllParty()
    {
    	def partyInstanceList =Party.findAll("from Party P  ")
    	println "party from=="+partyInstanceList
    	
		return partyInstanceList
    }
    public List getNotificationsByParty(def PartyId)
    {	
    	println "Partyser="+PartyId
    	def notifictionsInstanceList = NotificationsEmails.findAll("from NotificationsEmails NE where NE.party="+PartyId)
    	println "Notificationservice=="+notifictionsInstanceList
    	return notifictionsInstanceList
    }
    
    /*
     * Function to send mail to users
     */
    
    public boolean sendMessage(def emailId,def mailMessage,def contentType)
    {
    	def mailServerStatus = true
    	def mailSubject=gmsSettingsService.getGmsSettingsValue("MailSubject")
    	def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
        String host = gmsSettingsService.getGmsSettingsValue("MailHost")
        String username = gmsSettingsService.getGmsSettingsValue("MailUserName") // your authsmtp username
        String password = gmsSettingsService.getGmsSettingsValue("MailPassword") // your authsmtp password
        String port = gmsSettingsService.getGmsSettingsValue("MailPort")
        String from = gmsSettingsService.getGmsSettingsValue("MailFrom")
        String isSSL = gmsSettingsService.getGmsSettingsValue("isSSL")
        
 
 
 
       /*   
 
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", port); // thish is the port recommended by authsmtp
        props.put("mail.smtp.auth", "true");
 
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
 
        InternetAddress to_address = new InternetAddress(emailId);
        message.addRecipient(Message.RecipientType.TO, to_address);
        
         message.setSubject(mailSubject);
        message.setText(mailMessage);
        Transport transport = session.getTransport("smtp");
        try 
        {
        	println "transport connect"
        transport.connect(host, username, password);
        }    
        catch(Exception e)
        {
        	System.out.println ("SocketException e "+e)
        	mailServerStatus=false
        }
        try
        {
        transport.sendMessage(message, message.getAllRecipients());
        }
        catch(Exception e)
        {
        	println "e"+e
        	mailServerStatus=false
        }
        transport.close();
        
        */
        
          
	                   
	            
	                String[] to={emailId};
		       
	         
	               Properties props = new Properties();
		               //Properties props=System.getProperties();
		        props.put("mail.smtp.user", username);
		        props.put("mail.smtp.host", host);
		                if(!"".equals(port))
		        props.put("mail.smtp.port", port);
		               
		     
		      
		                
		               
		               
		               
		                if(!"false".equals(isSSL))
			       				  {
			       				  System.out.println("SSL");
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
	    		                
		               
		               
		               
		                 props.put("mail.smtp.auth", "true");
		               
		               
		               
		               
		               
		               
		        try
		        {
		                Session session = Session.getDefaultInstance(props, null);
		               session.setDebug(true); 
		            MimeMessage msg = new MimeMessage(session);
	                    //msg.setText(mailMessage);
	                    msg.setContent(mailMessage, contentType);
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
	        
	        
	        
        
        
        
        
        
        
        return mailServerStatus;
    }
    
    public boolean sendChangePassword(def emailId,def pass,def name,def personId,def urlPath)
    {
    	def mailSubject=gmsSettingsService.getGmsSettingsValue("MailSubject")
    	def mailContent=gmsSettingsService.getGmsSettingsValue("MailContent")
        String host = gmsSettingsService.getGmsSettingsValue("MailHost")
        String username = gmsSettingsService.getGmsSettingsValue("MailUserName") // your authsmtp username
        String password = gmsSettingsService.getGmsSettingsValue("MailPassword") // your authsmtp password
        String port = gmsSettingsService.getGmsSettingsValue("MailPort")
        String from = gmsSettingsService.getGmsSettingsValue("MailFrom")
        String isSSL = gmsSettingsService.getGmsSettingsValue("isSSL")
        
        
        
            
	    	                String[] to={emailId};
	    		       
	    	         
	    	               Properties props = new Properties();
	    		               //Properties props=System.getProperties();
	    		        props.put("mail.smtp.user", username);
	    		        props.put("mail.smtp.host", host);
	    		                if(!"".equals(port))
	    		        props.put("mail.smtp.port", port);
	    		               
	    		        
	    		        
	    		          if(!"false".equals(isSSL))
				  {
				  System.out.println("SSL");
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
				        mailMessage="Dear "+name+", \n \nYour Password changed successfully .";
				        mailMessage+="\n \n LoginName    : "+name;
				        mailMessage+="\n Password     : "+pass;
        mailMessage+="\n \n \n To activate your account,click on the following link   \t:"+urlPath+personId+"?passwd=true";
	    		        
	    		 
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
	    	        
	        
        
        
        
        
        
 
        /*Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", port); // thish is the port recommended by authsmtp
        props.put("mail.smtp.auth", "true");
 
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
 
        InternetAddress to_address = new InternetAddress(emailId);
        message.addRecipient(Message.RecipientType.TO, to_address);
        
        String mailMessage="";
        mailMessage="Dear "+name+", \n \nYour Password changed successfully .";
        mailMessage+="\n \n LoginName    : "+name;
        mailMessage+="\n Password     : "+pass;
        mailMessage+="\n \n \n To activate your account,click on the following link   \t:"+urlPath+personId;
 
        message.setSubject(mailSubject);
        message.setText(mailMessage);
        Transport transport = session.getTransport("smtp");
        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();*/
        return true;
    }
    
    /*
     * Function to send mail to Site Admin
     */
    
    public boolean sendConfirmation(def emailId,def mailSubject,def mailMessage,def contentType)
    {
    	def mailServerStatus = true
    	String host = gmsSettingsService.getGmsSettingsValue("MailHost")
        String username = gmsSettingsService.getGmsSettingsValue("MailUserName") 
        String password = gmsSettingsService.getGmsSettingsValue("MailPassword")
        String port = gmsSettingsService.getGmsSettingsValue("MailPort")
        String from = gmsSettingsService.getGmsSettingsValue("MailFrom")
        String isSSL = gmsSettingsService.getGmsSettingsValue("isSSL")
         
	    String[] to={emailId};
		Properties props = new Properties();
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.host", host);
		if(!"".equals(port))
		props.put("mail.smtp.port", port);
		if(!"false".equals(isSSL))
		{
		System.out.println("SSL");
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
		 try
		{
	    	Session session = Session.getDefaultInstance(props, null);
		    session.setDebug(true); 
		    MimeMessage msg = new MimeMessage(session);
	        msg.setContent(mailMessage, contentType);
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
	      return mailServerStatus;
    }
    
     def getGmsSettingsValueForMessage(def nameInstance)
    {
    	def gmsSettingsInstance = GmsSettings.find("from GmsSettings where name='"+nameInstance+"' and activeYesNo='Y'")
    	return gmsSettingsInstance?.value
    }
    
  public boolean sendEmailToSiteAdmin(def emailId,def fromEmailId,def mailMessage,def mailSubject,def contentType)
    {
      
    	def mailContent=getGmsSettingsValueForMessage("MailContent")
        String host = getGmsSettingsValueForMessage("MailHost")
        String username = getGmsSettingsValueForMessage("MailUserName") // your authsmtp username
        String password = getGmsSettingsValueForMessage("MailPassword") // your authsmtp password
        String port = getGmsSettingsValueForMessage("MailPort")
        String isSSL = getGmsSettingsValueForMessage("isSSL")
     
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
			                
	               
	               
	               
	                 props.put("mail.smtp.auth", "true");
           
		        try
		        {
		               Session session = Session.getDefaultInstance(props, null);
		               session.setDebug(true); 
		               MimeMessage msg = new MimeMessage(session);
	                   msg.setContent(mailMessage, contentType);
		               msg.setSubject(mailSubject);
	                   msg.setFrom(new InternetAddress(fromEmailId));
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
		            mex.getCause() 
		            mex.printStackTrace();
		                        return false;
	       } 
       
        return mailServerStatus;
    
    }  
 
 
}
