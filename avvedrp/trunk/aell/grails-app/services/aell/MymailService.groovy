package aell
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.*;

class MymailService {

  
        boolean transactional = false

  
	def serviceMethod() 
	{

 
	}
	public boolean sendMessage(String to, String msgSubject, String msgText,String host1,String username1,String password1,String from1,String port1,
					String userSubject, String userContent,params)
	{
	        //println "here in service1"
		String host = host1;
		String username = username1; // your authsmtp username
		String password = password1; // your authsmtp password
		String from = from1;
		String port = port1;

		try
		{
		//println "here in service2"
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", port); // this is the port recommended by authsmtp
		props.put("mail.smtp.auth", "true");
		//println "here in service3"
			 props.put("mail.smtp.starttls.enable","true");  
			 props.put("mail.smtp.socketFactory.port", port);
			 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			 props.put("mail.smtp.socketFactory.fallback", "false");
						
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			
			InternetAddress to_address = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, to_address);
			message.setSubject(msgSubject);
			message.setText(msgText);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			to_address = new InternetAddress(params.emailId);
			message.addRecipient(Message.RecipientType.TO, to_address);
			message.setSubject(userSubject);
			message.setText(userContent);
			//Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			return true;
			 
		}
		catch (Exception mex)
		 {
			// mex.printStackTrace();
						 return false;
	}
	}
		
}
