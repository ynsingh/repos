package org.iitk.brihaspatisync;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.util.Properties;
import java.util.Vector;
import org.apache.turbine.Turbine;
import java.io.FileOutputStream;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.activation.FileDataSource;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.mail.Transport;

import javax.servlet.ServletContext;

import org.iitk.brihaspatisync.util.ServerLog;
import java.sql.Date;

public class MailNotification {

	private Thread thread;
	private ServletContext context=null;
	private String subject="";
	private String lectTime="";
	private String lectDuration="";
	private String lectName="";
	private String lectCouseName="";
	private String ins_std="";
	private String mail_id[]=null;
	private StringBuffer url=null;
	
	private String key=null;
	private Date date;
	private static MailNotification  mailNotificationThread=null;
		
        public static MailNotification getController(){
                if(mailNotificationThread==null)
                        mailNotificationThread=new MailNotification();
                return mailNotificationThread;
        }
	

	protected void sendMail( ServletContext context1,String subject1,String mail_id1[],Date date1,String lectTime1,String lectDuration1,String lectName1,String lectCouseName1,String ins_std1,String key1,StringBuffer url1)  {
		
		this.subject=subject1;
		this.mail_id=mail_id1;
		this.lectTime=lectTime1;
		this.lectDuration=lectDuration1;
		this.lectName=lectName1;
		this.lectCouseName=lectCouseName1;
		this.date=date1;
		this.context=context1;
		this.key=key1;
		this.ins_std=ins_std1;
		this.url=url1;				
		(thread=new Thread(){
                        public void run(){
					String msg = "";
					try {
						lectCouseName=lectCouseName.substring(0,lectCouseName.lastIndexOf("_"));
		               			String url_new=(url+"?req=getjnlp&key="+key).toString();
		                                String message="<font size=3> Dear "+mail_id[1]+" ,<br><br> This message is to informed you that the following lecture will take place "+date+" "+lectTime +" "+lectDuration+".<br><br> Name of the lecture ("+lectCouseName+" to "+lectName+" )<br> <br> &nbsp;&nbsp;&nbsp;&nbsp; 1. Click here to join "+url_new +" <br> &nbsp;&nbsp;&nbsp;&nbsp; This link should not be shared with others, it is unique to you.<br> <br> &nbsp;&nbsp;&nbsp;&nbsp; 2. You will be connected to audio using your computer's microphone and speakers. A headset is recommended. <br> <br> &nbsp;&nbsp;&nbsp;&nbsp; 3. System Requirements :- Any OS compatiable with your microphone and speaker.<br> <br> &nbsp;&nbsp;&nbsp;&nbsp; 4. Please send your suggestion, comment and feedback to brihspti@iitk.ac.in</font>";
				 		String email_new=mail_id[0];
						String path=context.getRealPath("WEB-INF")+"/../../brihaspati2/WEB-INF/conf/Admin.properties"; 
                        			String mail_smtp=AdminProperties.getValue(path,"brihaspati.mail.smtp.from");
                        			String host_name=AdminProperties.getValue(path,"brihaspati.mail.server");
                        			String mail_smtp_port=AdminProperties.getValue(path,"brihaspati.mail.smtp.port");
                			        String mail_uname=AdminProperties.getValue(path,"brihaspati.mail.username");
                        			String mail_pass=AdminProperties.getValue(path,"brihaspati.mail.password");
                        			String local_domain=AdminProperties.getValue(path,"brihaspati.mail.local.domain.name");
                       				if((!mail_smtp.equals("")) && (!mail_smtp.equals(null))){
                         				if((!email_new.equals("")) && (!email_new.equals(null))){
                                				Properties l_props = System.getProperties();
                                       				l_props.put("mail.smtp.host", host_name);
                                       				if(!mail_pass.equals("")){
                                       					l_props.put("mail.smtp.auth", "true");
                                				}
                                       				if(!mail_smtp_port.equals("25")){
                                       					l_props.put("mail.smtp.starttls.enable", "true");
                                                			l_props.put("mail.smtp.port",mail_smtp_port);
                                                			// Use the following if you need SSL
                                                			l_props.put("mail.smtp.socketFactory.port", mail_smtp_port);
                                                			l_props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                                                			l_props.put("mail.smtp.socketFactory.fallback", "false");
                                     				}
                                        			Session l_session = Session.getDefaultInstance(l_props,  null);
                                        			l_session.setDebug(true);
								try {
									MimeMessage l_msg = new MimeMessage(l_session); // Create a New message
									l_msg.setFrom(new InternetAddress(mail_smtp)); // Set the From address
									// Setting the "To recipients" addresses
									l_msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email_new, false));
									l_msg.setSubject(subject); // Sets the Subject
									//Create and fill the first message part
									MimeBodyPart l_mbp = new MimeBodyPart();
                                                			Multipart l_mp = new MimeMultipart();
									l_mbp.setContent(message, "text/html");
                                               				l_mp.addBodyPart(l_mbp);
									// Create the Multipart and add bodyparts to it
									l_msg.setContent(l_mp);
									// Set the Date: header
									java.util.Date date=new java.util.Date();
									l_msg.setSentDate(date);
									// Send the message
									// Transport.send(l_msg);
									Transport tr = l_session.getTransport("smtp");
									//Send the message
									if(!mail_pass.equals("")){
			        	                               	        if(!mail_smtp_port.equals("25")){
                        	        			               		int smtpPortInt = Integer.parseInt(mail_smtp_port);
				                                                        tr.connect(host_name, smtpPortInt, mail_uname, mail_pass);
                                				              	} else
                                                       				tr.connect(host_name, mail_uname, mail_pass);
									} else
										tr.connect();
                                            				l_msg.saveChanges();     // don't forget this
                                                			tr.sendMessage(l_msg, l_msg.getAllRecipients());
                                                			tr.close();
								} catch (MessagingException mex) { // Trap the MessagingException Error
                                                			// If here, then error in sending Mail. Display Error message.
                                                			msg=msg+"The error in sending Mail Message "+mex.toString();
                                        			}
                                        			msg="Mail send succesfully!!";
                              				} else{ msg="Mail can't send since your mail id is null!!"; }
				          	} else{ msg=msg;}
						
					} catch(Exception ex) { 	ServerLog.getController().Log("The error in mail send !!!"+ex); 	}
					ServerLog.getController().Log("mail notofication "+msg);
				}
			
		}).start();
	}
}

