/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.email;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.HtmlEmail;
import org.smvdu.payroll.api.Administrator.CollegeList;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.setup.Org;
import org.smvdu.payroll.api.email.MassageProperties;

/**
 *
 * @author sumit
 * Mail body modification by adding properties file, 18 March 2016, Om Prakash <omprakashkgp@gmail.com> IITK
 */
public class OrgConformationEmail {
     
     private String activationLink;
    
     public boolean sendMail(Org org)  {
              try
              {
                  
                  String url = new String();
                  FacesContext facesContext = FacesContext.getCurrentInstance();
                //HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                  String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                  HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                  url = request.getRequestURL().toString();
                  String ipAddress = String.valueOf(request.getServerName());
                  String sport = String.valueOf(request.getServerPort()); 
                  //requestUrl = "http://localhost:8080/adminLogin" +"/UserConformation.jsf"+"?emailid="+org.getEmail();  
                  activationLink = "http://"+ipAddress+":"+sport+"/adminLogin"+"/UserConformation.jsf"+"?emailid="+org.getEmail();
                  String userPassword = new String();
                  Connection connection = new CommonDB().getConnection();
                  PreparedStatement pst;
                  ResultSet rst;
                  pst = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+org.getEmail()+"'");
                  rst = pst.executeQuery();
                  if(rst.next())
                  {
                      userPassword = rst.getString(1);
                  }
                  pst.close();
                  rst.close();
                  connection.close();
                  String fromEmail = new String();
                  String fromPassword = new String();
                  String smtpHostName;
                  int port;
                  final String[] f = new CollegeList().getSMTPAuthDetails().split("-");
                  port = Integer.parseInt(f[0]);
                  fromEmail = f[1];
                  fromPassword = f[2];
                  smtpHostName = f[3];
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpHostName); 
                props.put("mail.smtp.user", fromEmail);
                //To use TLS
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.password", fromPassword);
                props.put("mail.smtp.port",String.valueOf(port)); 
                Session session = Session.getDefaultInstance(props, new Authenticator() {
                                     @Override
                                     protected PasswordAuthentication getPasswordAuthentication() {
                                             String username = f[1];
                                             String password = f[2];
                                               return new PasswordAuthentication(username, password);
                                     }
                                  });
                                 String to = org.getEmail();
                    String from = f[1];
                    //String subject = "Registration in Payroll System";
                    MassageProperties msgprop = new MassageProperties();
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                        msg.setSubject(msgprop.getPropertieValue("reg1"));
                        msg.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:12px;font-weight:bold;'> "+msgprop.getPropertieValue("reg4")+" </font><br>"    
                                    +"<font style='color:#4B4B4B;font-size:12px;font-weight:bold;'> "+msgprop.getPropertieValue("reg6")+" '"+org.getEmail()+"'</font><br>"    
                                    +"<font style='color:#4B4B4B;font-size:13px;'> "+msgprop.getPropertieValue("reg5")+"</font><br><br><hr>"
                                    +"<font style='color:red;font-size:13px;font-weight:bold;'>"+"<a href='"+activationLink+"'>"+activationLink+"</a>"+"</font><br><hr>"
                          + "<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'></font><br>"
                          + "<font style='color:#4B4B4B;font-size:15px;font-weight:bold;'> "+msgprop.getPropertieValue("reg")+" <br></font>" 
                          + "<image src="+path+File.separator+"img/pls1.png/></html>","text/html"); 
                        Transport transport = session.getTransport("smtp");
                        transport.send(msg);
                   }  catch(Exception exc) 
                   {
                        System.out.println(exc);
                   }
                  return true;
              }
              catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
              }
    }
     public boolean sendPendingCollegeMail(Org org)  {
              try
              {
                  /*String url = new String();
                  FacesContext facesContext = FacesContext.getCurrentInstance();
                  HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                  url = request.getRequestURL().toString();
                  requestUrl = "http://localhost:8080/adminLogin" +"/UserConformation.jsf"+"?emailid="+org.getEmail(); */
                  String fromEmail = new String();
                  String fromPassword = new String();
                  String smtpHostName;
                  int port;
                  final String[] f = new CollegeList().getSMTPAuthDetails().split("-");
                  port = Integer.parseInt(f[0]);
                  fromEmail = f[1];
                  fromPassword = f[2];
                  smtpHostName = f[3];
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpHostName); 
                props.put("mail.smtp.user", fromEmail);
                //To use TLS
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.password", fromPassword);
                props.put("mail.smtp.port",String.valueOf(port)); 
                Session session = Session.getDefaultInstance(props, new Authenticator() {
                                     @Override
                                     protected PasswordAuthentication getPasswordAuthentication() {
                                             String username = f[1];
                                             String password = f[2];
                                               return new PasswordAuthentication(username, password);
                                     }
                                  });
                                 String to = org.getEmail();
                    String from = f[1];
                   // String subject = "Registration in Payroll System";
                    MassageProperties msgprop = new MassageProperties();
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                     //   msg.setSubject(subject);
                       msg.setSubject(msgprop.getPropertieValue("reg1"));
                        msg.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'> "+msgprop.getPropertieValue("reg2")+" '"+org.getEmail()+"'<br><br>"
                          + msgprop.getPropertieValue("reg3")+ "<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'> "+msgprop.getPropertieValue("reg")+" </font></font><br><hr>" 
                          + "</html>","text/html"); 
                        Transport transport = session.getTransport("smtp");
                        transport.send(msg);
                        
                   }  catch(Exception exc) 
                   {
                        System.out.println(exc);
                   }
                  return true;
              }
              catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
              }
    }
     public boolean sendDeActivationCollegeMail(Org org)  {
              try
              {
                     String fromEmail = new String();
                  String fromPassword = new String();
                  String smtpHostName;
                  int port;
                  final String[] f = new CollegeList().getSMTPAuthDetails().split("-");
                  port = Integer.parseInt(f[0]);
                  fromEmail = f[1];
                  fromPassword = f[2];
                  smtpHostName = f[3];
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpHostName); 
                props.put("mail.smtp.user", fromEmail);
                //To use TLS
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.password", fromPassword);
                props.put("mail.smtp.port",String.valueOf(port)); 
                Session session = Session.getDefaultInstance(props, new Authenticator() {
                                     @Override
                                     protected PasswordAuthentication getPasswordAuthentication() {
                                             String username = f[1];
                                             String password = f[2];
                                               return new PasswordAuthentication(username, password);
                                     }
                                  });
                                 String to = org.getEmail();
                    String from = f[1];
                   // String subject = "Payroll Adminstrator";
                    MassageProperties msgprop = new MassageProperties();
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                       //   msg.setSubject(subject);
                       msg.setSubject(msgprop.getPropertieValue("reg1"));
                       msg.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>"+msgprop.getPropertieValue("reg7")+",<br>"+msgprop.getPropertieValue("reg8")+" "+org.getName()+"<br> "+msgprop.getPropertieValue("reg9")+"<br>"+f[1]+""
                          + msgprop.getPropertieValue("reg12")+ "<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'><br>"+msgprop.getPropertieValue("reg")+"</font></font><br><hr>" 
                          + "</html>","text/html"); 
                        Transport transport = session.getTransport("smtp");
                        transport.send(msg);
                   }  catch(Exception exc) 
                   {
                        System.out.println(exc);
                   }
                  return true;
              }
              catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
              }
    }
     public boolean sendActivationCollegeMail(Org org)  {
              try
              {
                  String fromEmail = new String();
                  String fromPassword = new String();
                  String smtpHostName;
                  int port;
                  final String[] f = new CollegeList().getSMTPAuthDetails().split("-");
                  port = Integer.parseInt(f[0]);
                  fromEmail = f[1];
                  fromPassword = f[2];
                  smtpHostName = f[3];
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpHostName); 
                props.put("mail.smtp.user", fromEmail);
                //To use TLS
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.password", fromPassword);
                props.put("mail.smtp.port",String.valueOf(port)); 
                Session session = Session.getDefaultInstance(props, new Authenticator() {
                                     @Override
                                     protected PasswordAuthentication getPasswordAuthentication() {
                                             String username = f[1];
                                             String password = f[2];
                                               return new PasswordAuthentication(username, password);
                                     }
                                  });
                                 String to = org.getEmail();
                    String from = f[1];
                   // String subject = "Payroll Adminstrator";
                    MassageProperties msgprop = new MassageProperties();
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                        //   msg.setSubject(subject);
                        msg.setSubject(msgprop.getPropertieValue("reg1"));
                        msg.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>"+msgprop.getPropertieValue("reg10")+" <br>"
                          + "<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'><br>"+msgprop.getPropertieValue("reg")+"</font></font><br><hr>" 
                          + "</html>","text/html"); 
                        Transport transport = session.getTransport("smtp");
                        transport.send(msg);
                   }  catch(Exception exc) 
                   {
                        System.out.println(exc);
                   }
                  return true;
              }
              catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
              }
    }
     public boolean sendChangePasswordMail(Org org)  {
              try
              {
                  
                  String fromEmail = new String();
                  String fromPassword = new String();
                  String smtpHostName;
                  int port;
                  final String[] f = new CollegeList().getSMTPAuthDetails().split("-");
                  port = Integer.parseInt(f[0]);
                  fromEmail = f[1];
                  fromPassword = f[2];
                  smtpHostName = f[3];
                Properties props = new Properties();
                props.put("mail.smtp.host", smtpHostName); 
                props.put("mail.smtp.user", fromEmail);
                //To use TLS
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.password", fromPassword);
                props.put("mail.smtp.port",String.valueOf(port)); 
                Session session = Session.getDefaultInstance(props, new Authenticator() {
                                     @Override
                                     protected PasswordAuthentication getPasswordAuthentication() {
                                             String username = f[1];
                                             String password = f[2];
                                               return new PasswordAuthentication(username, password);
                                     }
                                  });
                                 String to = org.getEmail();
                    String from = f[1];
                   // String subject = "Payroll Adminstrator";
                    MassageProperties msgprop = new MassageProperties();
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                        //   msg.setSubject(subject);
                        msg.setSubject(msgprop.getPropertieValue("reg"));
                        msg.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>"+msgprop.getPropertieValue("reg11")+"<hr>"
                          + org.getAdPassword()+"<hr><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'><br>"+msgprop.getPropertieValue("reg")+"</font></font><br><hr>" 
                          + "</html>","text/html"); 
                        Transport transport = session.getTransport("smtp");
                        transport.send(msg);
                   }  catch(Exception exc) 
                   {
                        System.out.println(exc);
                   }
                  return true;
              }
              catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
              }
    }

}
