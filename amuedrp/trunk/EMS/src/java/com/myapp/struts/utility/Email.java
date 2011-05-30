/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;


import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {
private String to;
private String subject;
private String password;
private String text;
String userid;
String host;


public Email(String to,String password,String subject,String body){this.to = to; this.password = password;this.subject=subject;this.text=body;}

public void send(){
 host = "smtp.gmail.com";
 userid = "electionms@gmail.com";
password = "electionms@192";
try
{
Properties props = System.getProperties();
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", host);
props.setProperty("mail.transport.protocol", "smtp");
props.put("mail.smtp.user", userid);
props.put("mail.smtp.password", password);
props.put("mail.smtp.port", "587");
props.put("mail.smtp.auth", "true");
Session session = Session.getDefaultInstance(props, null);
MimeMessage message = new MimeMessage(session);
InternetAddress fromAddress = null;
InternetAddress toAddress = null;

try {
fromAddress = new InternetAddress(userid);
toAddress = new InternetAddress(to);
} catch (AddressException e) {

e.printStackTrace();
}
message.setFrom(fromAddress);
message.setRecipient(RecipientType.TO, toAddress);
message.setSubject(subject);
message.setText(text);

//SMTPSSLTransport transport =(SMTPSSLTransport)session.getTransport("smtp");

Transport transport = session.getTransport("smtp");
transport.connect(host, userid, password);
transport.sendMessage(message, message.getAllRecipients());
transport.close();
} catch (MessagingException e) {
e.printStackTrace();
}
}
}
