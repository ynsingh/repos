/*
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

package in.ac.dei.edrp.cms.daoimpl.employee;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;


import org.apache.log4j.Logger;


public class sendmail {
	/**
     * @param args
     */
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("in"+File.separator+"ac"+File.separator+"dei"+File.separator+"edrp"+File.separator+"cms"+File.separator+"databasesetting"+File.separator+"MessageProperties",
			new Locale("en", "US"));
    public static void main(String text, String to, String from, String subject)
        throws Exception {
    	
    	// Function Changed By Dheeraj
    	
    	String mailTo = to;
        
        Properties properties = System.getProperties();
        
        Logger loggerObject = Logger.getLogger(sendmail.class);

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
        final String password = resourceBundle.getString("password");
        
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
        
        loggerObject.info("Message Sent.....");
    }

    static class MyAuthenticator extends Authenticator {
    	public PasswordAuthentication getPasswordAuthentication() {
            String emailId = resourceBundle.getString("emailId");
            String password = resourceBundle.getString("password");
            return new PasswordAuthentication(emailId, password);
        }
    }
    
    
    /** 
     * This method send mail with attached file
     * @param message body,recepient email id,message subject,attached file path
     * @use property file for sender email id and password
     * @return void
     * @throws exception
     * @date 18 Jan 2012
     * @author Ashish Mohan 
     **/
    public static void mainWithAttachment(String text, String to, String subject,String myFile)
    throws Exception {
    	
    // Get system properties
    String from = resourceBundle.getString("emailId");
    Properties properties = System.getProperties();
    
    Logger loggerObject = Logger.getLogger(sendmail.class);

    // Setup mail server
    properties.setProperty("mail.smtp.port", "25");
    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
    properties.setProperty("mail.smtp.starttls.enable", "true");
    properties.setProperty("mail.smtp.startssl.enable", "true");
    properties.setProperty("mail.smtp.auth", "true");



    // Get the default Session object.
    Authenticator auth = new MyAuthenticator();
    Session session = Session.getDefaultInstance(properties, auth);

    
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

        // Set the RFC 822 "From" header field using the 
        // value of the InternetAddress.getLocalAddress method.
        message.setFrom(new InternetAddress(from));

        // Add the given addresses to the specified recipient type.
        message.addRecipient(Message.RecipientType.TO,
            new InternetAddress(to));

        message.setSubject(subject);
    

        // set message BODY
        MimeBodyPart mimebodypart = new MimeBodyPart();
        mimebodypart.setText(text);

        // attach message BODY
        MimeMultipart mimemultipart = new MimeMultipart();
        mimemultipart.addBodyPart(mimebodypart);

        // attach FILE
        mimebodypart = new MimeBodyPart();
        try
        {
            FileDataSource filedatasource = new FileDataSource(myFile);
            mimebodypart.setDataHandler(new DataHandler(filedatasource));
        }
        catch(Exception exception3)
        {
            System.out.println("\tError in sending file not been able to attach ......\t" 
                            + exception3.getMessage());
        }
        
        mimebodypart.setFileName(myFile.substring(myFile.lastIndexOf(File.separator)+1)); // set FILENAME
        mimemultipart.addBodyPart(mimebodypart);
        
        //setting message content
        message.setContent(mimemultipart);

        message.setSentDate(new Date());
        System.out.println(message.getSentDate());

        // Send message
        Transport.send(message);

        loggerObject.info("Message Send.....");
}
}
