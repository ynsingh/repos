package org.smvdu.payroll.api.email;

import javax.mail.*;
import java.util.*;


public class Mail {

    String d_email = "from",
            d_password = "secret",
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "to email",
            m_subject = "Testing",
            m_text = "Hey, this is the testing email using smtp.gmail.com.";

    public static void main(String[] args) {
        String[] to = {""};
        String[] cc = {""};
        String[] bcc = {""};
        //This is for google
       // Task task = new Task();
        //Mail.sendMail(task );
    }

    public synchronized static boolean sendMail(String sender) {
        Properties props = new Properties();
        //Properties props=System.getProperties();
        props.put("mail.smtp.user", " user email id");
        props.put("mail.smtp.host", "smtp.gmail.com");
        {
            props.put("mail.smtp.port", "465");
        }
        {
            props.put("mail.smtp.starttls.enable", "true");
        }
        props.put("mail.smtp.auth", "true");
         {
            props.put("mail.smtp.debug", "true");
        } 
         
        {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        

        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
            
           return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
    }
}