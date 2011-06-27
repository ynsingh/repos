/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
String path;
StringBuffer buffer;
//


public Email(String path,String to,String password,String subject,String body){this.to = to; this.password = password;this.subject=subject;this.text=body;this.path=path;}

public void send(){
 host = "smtp.gmail.com";
 userid = "amuedrp@gmail.com";
 




try
{

  path=path.substring(0,path.lastIndexOf("/"));
  path=path.substring(0,path.lastIndexOf("/"));
  //path=path.substring(0,path.lastIndexOf("\\"));
  System.out.println(path);



  // Open the file that is the first
  // command line parameter
  FileInputStream fstream = new FileInputStream(path+"/web/admin/mail.txt");
  InputStreamReader isr = new InputStreamReader(fstream,"UTF8");
 buffer= new StringBuffer();
 Reader in = new BufferedReader(isr);
	int ch;
	while ((ch = in.read()) > -1) {
		buffer.append((char)ch);
	}
	in.close();



  System.out.println(buffer);
  
 


Properties props = System.getProperties();
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", host);
props.setProperty("mail.transport.protocol", "smtp");
props.put("mail.smtp.user", userid);
props.put("mail.smtp.password", "amuadmin123");
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
String pass=buffer.toString();
Transport transport = session.getTransport("smtp");
transport.connect(host, userid, pass);
transport.sendMessage(message, message.getAllRecipients());
transport.close();
} catch (Exception e) {
e.printStackTrace();
}
}
}
