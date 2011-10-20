import javax.mail.internet.*;
import javax.mail.*;

class MailService {

            boolean transactional = true

            def serviceMethod() {
            }

			// Function to send Email
            public boolean sendMessage(def host,def port,def username,def password,def emailId,def mailMessage)
            {
                def mailServerStatus = true
                def mailSubject="New Registration at DIVE"               
				String from = "no-reply@dive.com"
				String to = emailId				
				
				/*
				println("############## INSIDE MAIL SERVICE ###################")
				println("HOST ->"+host)
				println("PORT ->"+port)
				println("USERNAME ->"+username)
				println("PASSWORD ->"+password)
				println("EMAIL ->"+emailId)
				println("MESSAGE ->"+mailMessage)
			    */
				
				
				
               // String host = "192.168.36.10";
	       // String port = "25"
               // String username = "" // your authsmtp username
               // String password = "" // your authsmtp password    

                Properties props = System.getProperties();
                props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", port); // this is the port recommended by authsmtp            
                props.put("mail.smtp.user", username);
                props.put("mail.smtp.password", password);     
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
                 //println "transport connected now"
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
                return mailServerStatus;
            }
} //End of Service Class
