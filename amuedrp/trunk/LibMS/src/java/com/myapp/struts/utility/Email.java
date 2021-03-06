//CLASS TO SEND MAIL

package com.myapp.struts.utility;


import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email
{
private String to;
private String subject;
private String text;
String userid;
String host;
String port;
String path;
String buffer;
String frmAdd;
Session session ;
MimeMessage message;

	public Email(String to,String password,String subject,String body){
		this.to = to; this.subject=subject;this.text=body;
        try
        {



            Properties libmspro = new Properties();

        path=AppPath.getPropertiesFilePath();
        libmspro.load(new FileInputStream(path+"libms.properties"));
        buffer = libmspro.getProperty("webpass");
        host = libmspro.getProperty("host");
        port = libmspro.getProperty("port");
        frmAdd = libmspro.getProperty("faddress");
        userid=libmspro.getProperty("webadmin");
	Properties props = System.getProperties();
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.setProperty("mail.transport.protocol", "smtp");
	props.put("mail.smtp.user", frmAdd);
	props.put("mail.smtp.password",buffer);
	props.put("mail.smtp.port", port);
	props.put("mail.smtp.auth", "true");
	session = Session.getDefaultInstance(props, null);
        message = new MimeMessage(session);

        } catch (Exception ex) {
                ex.printStackTrace();

            }

	}
//TO SEND MAIL to PRIMARY EMAIL
public  void send()
{

        try
        {
	InternetAddress fromAddress = null;
	InternetAddress toAddress = null;

	try {
		fromAddress = new InternetAddress(frmAdd);
		toAddress = new InternetAddress(to);
	} catch (AddressException e) {

		e.printStackTrace();
	}
	message.setFrom(fromAddress);
	message.setRecipient(RecipientType.TO, toAddress);
	message.setSubject(subject);
	message.setText(text);
        message.setHeader("Content-Type", "text/html");


        String pass=buffer.toString();
	Transport transport = session.getTransport("smtp");
       	transport.connect(host, userid, pass);
	transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    } catch (Exception e) {
	e.printStackTrace();
    }
}
//TO SEND MAIL to ALTERNATE EMAIL
public void sendAlternatemail()
{
        try{
                InternetAddress fromAddress = null;
                InternetAddress[] toAddress =null;
        try {
            fromAddress = new InternetAddress(frmAdd);
            toAddress =  InternetAddress.parse(to);
            } catch (AddressException e)
            {
                e.printStackTrace();
            }
        message.setFrom(fromAddress);
        message.setRecipients(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setText(text);
        message.setHeader("Content-Type", "text/html");
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