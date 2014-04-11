/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;

import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author erp
 */
public class Email_Sing {
    private String host;
    private String userid;
    private String port;
    private String path;
    private String buffer;
    private String frmAdd;
    private MimeMessage message;
    private Session session ;
    private static Properties props;//=new Email_Sing();
    private static Email_Sing proper;
    private Email_Sing()
    {
    try{
			String os=(String)System.getProperty("os.name");
			Properties libmspro = new Properties();
			path=AppPath.getPropertiesFilePath();
                        libmspro.load(new FileInputStream(path+"ems.properties"));
			userid = libmspro.getProperty("webadmin");
        		buffer = libmspro.getProperty("webpass");
        		host = libmspro.getProperty("host");
        		port = libmspro.getProperty("port");
        		frmAdd = libmspro.getProperty("faddress");
			props = System.getProperties();
        		props.put("mail.smtp.starttls.enable", "true");
        		props.put("mail.smtp.host", host);
        		props.setProperty("mail.transport.protocol", "smtp");
        		props.put("mail.smtp.user", userid);
        		props.put("mail.smtp.password",buffer);
        		props.put("mail.smtp.port", port);
        		props.put("mail.smtp.auth", "true");
                        props.put("frmAdd", frmAdd);
                        
        		
                       
		}
		catch (Exception ex) {
	        	ex.printStackTrace();
                        UserLog.ErrorLog(ex.getMessage(),"MailSendLog.txt");
           	}

    }

    public static Email_Sing getInstance()
    {
        synchronized (Email_Sing.class){
        if (proper==null)
        {
           proper=new  Email_Sing();
        }
           return proper;
        }
    }

//     public static Properties getEmail_Sing(){
         public Properties getEmail_Sing(){
      return props;
     }


}
