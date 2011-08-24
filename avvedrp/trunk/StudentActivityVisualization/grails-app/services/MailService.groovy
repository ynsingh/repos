import javax.mail.internet.*;
import javax.mail.*;

class MailService {

    boolean transactional = true

            def serviceMethod() {

            }

            
			
			
            public boolean sendMessage(def emailId,def mailMessage)
            {
                def mailServerStatus = true
                def mailSubject="New Registration at DIVE"
                def mailContent="Test Content"
                String host = "192.168.36.10";
                String username = "" // your authsmtp username
                String password = "" // your authsmtp password
                String port = "25"
                String from = "no-reply@yourdomain.com"

                Properties props = System.getProperties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.user", username);
                props.put("mail.smtp.password", password);
                props.put("mail.smtp.port", port); // thish is the port recommended by authsmtp
                props.put("mail.smtp.auth", "true");

                Session session = Session.getDefaultInstance(props, null);
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                
				println("To ID ->"+emailId);

                InternetAddress to_address = new InternetAddress(emailId);
                message.addRecipient(Message.RecipientType.TO, to_address);

               /* String mailMessage="";
                mailMessage="Dear "+name+", \n \n "+mailContent+".";
                mailMessage+="\n \n LoginName    : "+emailId;
                mailMessage+="\n Password     : "+pass;
                mailMessage+="\n \n \n To activate your account,click on the following link   \t:"+urlPath+personId;*/

                message.setSubject(mailSubject);
                message.setText(mailMessage);
                Transport transport = session.getTransport("smtp");
                try
                {
                         println "transport connected now"
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
}
