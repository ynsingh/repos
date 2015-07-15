package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.mail.internet.InternetAddress;
import org.apache.commons.lang.StringUtils;

/**
 *This class is used to sending the mail.
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>
 */ 
public class sendMail {
	
	public static String sendMail(String emailFrom, String emailUser, String emailFromPasswd, String emailID, String cc, String subj, String message) throws Exception {
		String email_new="";
                boolean flag = false;
                try{
                        if(StringUtils.isNotBlank(emailID)){
                                email_new=emailID;
                        }
                        String mail_smtp=emailFrom;
                        String host_name="smtp.cc.iitk.ac.in";
                        String mail_smtp_port="25";
                        String mail_uname=emailFrom;
                        String mail_pass=emailFromPasswd;
                        if(StringUtils.isNotBlank(mail_smtp)){
                        	if(StringUtils.isNotBlank(email_new)){
                                	Properties props = System.getProperties();
                                        props.put("mail.smtp.host", host_name);
                                        if(!mail_pass.equals("")){
                                        	props.put("mail.smtp.auth", "true");
                                        }
                                        if(!mail_smtp_port.equals("25")){
                                        	props.put("mail.smtp.starttls.enable", "true");
                                                props.put("mail.smtp.port",mail_smtp_port);
                                                props.put("mail.smtp.socketFactory.port", mail_smtp_port);
                                                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                                                props.put("mail.smtp.socketFactory.fallback", "false");
                                        }
                                        Session l_session = Session.getDefaultInstance(props,  null);
                                        l_session.setDebug(false);
                                        try {
                                        	MimeMessage l_msg = new MimeMessage(l_session);
                                                l_msg.setFrom(new InternetAddress(mail_smtp));
                                                l_msg.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email_new, false));
						l_msg.setSubject(subj);
						MimeBodyPart l_mbp = new MimeBodyPart();
                                                Multipart l_mp = new MimeMultipart();
                                                l_mbp.setContent(message, "text/html");
                                                l_mp.addBodyPart(l_mbp);
                                                l_msg.setContent(l_mp);
                                                Transport tr = l_session.getTransport("smtp");
                                                if(!mail_pass.equals("")){
                                                	if(!mail_smtp_port.equals("25")){
                                                        	int smtpPortInt = Integer.parseInt(mail_smtp_port);
                                                        	tr.connect(host_name, smtpPortInt, mail_uname, mail_pass);
                                                        }
                                                        else{
                                                        	tr.connect(host_name, mail_uname, mail_pass);
                                                        }
						}
                                                else{
                                                tr.connect();
                                                }
                                                l_msg.saveChanges();
                                                tr.sendMessage(l_msg, l_msg.getAllRecipients());
                                                tr.close();
					} catch (MessagingException mex) {System.out.println("The error in sending Mail Message at 94--"+mex.toString());}
				}
                                else{
                                	System.out.println("mailId not found");
                                }
			}
                        else{
                        	System.out.println("smtp not found");
                        }
		}
		catch (Exception e) {
			System.out.println("Error in sending email: " + e.getMessage());
                        return "Error in sending email: " + e.getMessage().toString();
		}
                return "Success";

	}
}
