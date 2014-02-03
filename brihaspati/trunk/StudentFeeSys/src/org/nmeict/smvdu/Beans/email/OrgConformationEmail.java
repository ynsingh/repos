/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.email;

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
import org.nmeict.smvdu.Beans.Administrator.CollegeList;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.db.CommonDB;

//import org.smvdu.payroll.beans.setup.Org;



/**
 *
 * @author guest
 */
public class OrgConformationEmail {
    
     public boolean sendPendingCollegeMail(OrgProfile org)  {
              try
              {
                    String url = new String();
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                    url = request.getRequestURL().toString();
                    String requestUrl = "http://localhost:8085/adminLogin" +"/UserConformation.xhtml"+"?emailid="+org.getOrgEmail(); 
                    String fromEmail = new String();
                    String fromPassword = new String();
                    String smtpHostName;
                    int port;
                    final String[] f = new CollegeList().getSMTPAuthDetails().split("-");
                    System.out.print("\n\nf in Org ConformationEmail class="+f);
                    port = Integer.parseInt(f[0]);
                    fromEmail = f[1];
                    fromPassword = f[2];
                    smtpHostName = f[3];
                    Properties props = new Properties();
                    props.put("mail.smtp.host", smtpHostName);
                    props.put("mail.stmp.user", fromEmail);
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
                    
                //Commented By shaista
                //String to =org.getEmail();
                String to = org.getOrgAdminemailid();
                String from = f[1];
                String subject = "Adminstrator of Student Fee";
                MimeMessage msg = new MimeMessage(session);
                try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                        msg.setSubject(subject);
                        msg.setContent("<html>"
                                    +"<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>You will recieve a conformation mail/activation mail<br>"
                          + "containing your valid userid/emailid and password.<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'>Thanks And Regard<br>Administration of Student Fee Mangement</font></font><br><hr>"
                          + "</html>","text/html");
                        Transport transport = session.getTransport("smtp");
                        transport.send(msg);
                }catch(Exception exc){
                    System.out.println(exc);
                }
                return true;
              }
              catch(Exception e){
                  e.printStackTrace();
                  return false;
              }

     }
     
    private String requestUrl;
    public boolean sendMail(OrgProfile org)  {
              try
              {

                  String url = new String();
                  FacesContext facesContext = FacesContext.getCurrentInstance();
                //HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                  String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                  HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                  url = request.getRequestURL().toString();

                  requestUrl = "http://localhost:8085/adminLogin" +"/UserConformation.xhtml"+"?emailid="+org.getOrgEmail();
                  String userPassword = new String();
                  Connection connection = new CommonDB().getConnection();
                  PreparedStatement pst;
                  ResultSet rst;
                  pst = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+org.getOrgEmail()+"'");
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
                props.put("mail.stmp.user", fromEmail);
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
                String to = org.getOrgEmail();
                String from = f[1];
                    String subject = "Adminstrator of Student Fees";
                    MimeMessage msg = new MimeMessage(session);
                    try {
                        msg.setFrom(new InternetAddress(from));
                        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                        msg.setSubject(subject);
                        msg.setContent("<html>"
                                    +"<font style='color:#4B4B4B;font-size:12px;font-weight:bold;'>User ID / EmailID : '"+org.getOrgEmail()+"'</font><br>"
                                    +"<font style='color:#4B4B4B;font-size:12px;font-weight:bold;'>Password : '"+userPassword+"'</font>"
                                    +"<font style='color:#4B4B4B;font-size:13px;'>Click Following Link At Billow</font><br><hr>"
                                    +"<font style='color:red;font-size:13px;font-weight:bold;'>"+"<a href='"+requestUrl+"'>"+requestUrl+"'</a>"+"</font><br><hr>"
                          + "<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'>Thanks And Regards</font><br>"
                          + "<font style='color:#4B4B4B;font-size:15px;font-weight:bold;'> Administration of Student Fees<br></font>"
                          + "<image src="+path+File.separator+"img/headerLogo.png/></html>","text/html");
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
