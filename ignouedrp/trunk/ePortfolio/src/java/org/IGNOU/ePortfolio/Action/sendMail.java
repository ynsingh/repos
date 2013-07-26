/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.Action;

/**
 *
 * @author IGNOU Team
 */
import com.opensymphony.xwork2.ActionSupport;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class sendMail extends ActionSupport{

    private String SMTP_HOST_NAME = ReadPropertyFile("smtpHost");
    private String SMTP_PORT = ReadPropertyFile("smtpPort");
    private String SMTP_AUTH_USER = ReadPropertyFile("mailUser");
    private String SMTP_AUTH_PWD = ReadPropertyFile("mailPassword");
    private String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    final Logger logger = Logger.getLogger(this.getClass());

    private class smtpAuth extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

    public void SendMail(String mailFrom, String mailTo, String Sub, String Msg) throws Exception {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        Authenticator auth = new smtpAuth();
        Session mailSession = Session.getDefaultInstance(props, auth);
        Transport transport = mailSession.getTransport();
        MimeMessage message = new MimeMessage(mailSession);

        message.setContent(Msg, "text/html");
        message.setSubject(Sub);
        message.setFrom(new InternetAddress(mailFrom));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    public void SendMailToCcBcc(String mailFrom, String mailTo, String mailCc[], String mailBcc[], String Sub, String Msg){
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.port", SMTP_PORT);
        // props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        PropertyConfigurator.configure("log4j.properties");
        logger.warn(mailFrom +"Trying to send Mail to "+mailTo +"and"+mailCc+"and"+mailBcc);
        logger.warn("Subject of mail"+Sub);
        Authenticator auth = new smtpAuth();
        Session mailSession = Session.getDefaultInstance(props, auth);
        try{
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setContent(Msg, "text/html");
        message.setSubject(Sub);
        message.setFrom(new InternetAddress(mailFrom));
        if (mailCc!=null) {
            if (mailBcc!=null) {
                 // Send mail to To CC and BCC
                     message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
                     //Mail to cc setting
                     InternetAddress[] addresscc = new InternetAddress[mailCc.length];
                     for (int i = 0; i < mailCc.length; i++) {
                         addresscc[i] = new InternetAddress(mailCc[i]);
                     }
                     message.setRecipients(Message.RecipientType.CC, addresscc);
                     //mail to bcc setting
                     InternetAddress[] addressBcc = new InternetAddress[mailBcc.length];
                     for (int i = 0; i < mailBcc.length; i++) {
                         addressBcc[i] = new InternetAddress(mailBcc[i]);
                     }
                     message.setRecipients(Message.RecipientType.BCC, addressBcc);
     
                     transport.connect();
                     transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                     transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
                     transport.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
     
                transport.close();

            } else {
                 //send to only To and cc
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
                    InternetAddress[] addresscc = new InternetAddress[mailCc.length];
                    for (int i = 0; i < mailCc.length; i++) {
                        addresscc[i] = new InternetAddress(mailCc[i]);
                    }
                    message.setRecipients(Message.RecipientType.CC, addresscc);
                    transport.connect();
                    transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                    transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
                transport.close();
               
            }
        } else {
            if (mailBcc!=null) {
               //send mail to to and bcc
                   message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
                   InternetAddress[] addressBcc = new InternetAddress[mailBcc.length];
                   for (int i = 0; i < mailBcc.length; i++) {
                       addressBcc[i] = new InternetAddress(mailBcc[i]);
                   }
                   message.setRecipients(Message.RecipientType.BCC, addressBcc);
                   transport.connect();
                   transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                   transport.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
   
                transport.close();
            } else {
                //send mail only To
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
                transport.connect();
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                transport.close();
                
            }
        }
        }catch(Exception e){
            PropertyConfigurator.configure("log4j.properties");
        logger.error("Send Mail Function Error and Mail From "+mailFrom, e);
        }

        }

    }

//    @SuppressWarnings("empty-statement")
//    public static void main(String args[]) throws Exception {
//        String ccName[] = {"mca.vsharma@gmail.com"};
//        String bccName[] = null;
//        new sendMail().SendMailToCcBcc("eportfolio@egyankosh.ac.in", "amit@egyankosh.ac.in", ccName, bccName, "Eportfolio TEsting cc and bcc", "message");
//        System.out.println("mail Send Successfully");
//    }
//}