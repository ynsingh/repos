/*
 * @(#) SendMail.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.server.summarysheet;

import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class SendEmail {	
	static Log4JInitServlet logObj = new Log4JInitServlet();

	public static void send(String toEmail,String subject,String text) throws Exception{
//		try{		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("in"+File.separator+"ac"+File.separator+"dei"+File.separator+"edrp"+File.separator+"client"+File.separator+"Shared"+File.separator+"constants",
				new Locale("en", "US"));
		String mailTo = toEmail;
        
        Properties properties = System.getProperties();
        
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465"); 
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.startssl.enable","true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.store.protocol", "pop3");//for incoming mail
        properties.put("mail.transport.protocol", "smtp");//for outgoing mail
		
		final String username = resourceBundle.getString("emailId");
        final String password = resourceBundle.getString("emailpassword");
        System.out.println("username and password "+username+":"+password);
        Session smtpSession = Session.getDefaultInstance(properties, 
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
        
        message.setSubject(subject, encoding);  
        message.setText(text, encoding); 
        message.setContent(text,"text/html");
        message.setSentDate(new Date());
        
        
        System.out.println("Sending Message......");
        Transport.send(message);
        System.out.println("Message Sent.......");
		/*}
		catch(Exception e){
			e.printStackTrace();
			logObj.logger.error(e.getMessage());
		}*/
        
	}
}
