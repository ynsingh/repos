    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.api.Administrator.CollegeList;
import org.smvdu.payroll.api.EncryptionUtil;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.ForgotPassword;


/**
*
*  Copyright (c) 2010 - 2011.2014 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
*
* @author Om Prakash<omprakashkgp@gmail.com> , IITK 
*/

public class ForgotPasswordDB {
    
    private UserInfo userBean;
    private String activationLink;
    public  String rkey;
    private HibernateUtil helper;
    private org.hibernate.Session sess;

    

    public ForgotPasswordDB() {
        userBean = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
    
    public boolean sendPassResetMail(ForgotPassword fp){
    try{
            String url = new String();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            url = request.getRequestURL().toString();
            String ipAddress = request.getRemoteAddr();
            String sport = String.valueOf(request.getServerPort()); 
            rkey=UUID.randomUUID().toString();
            activationLink = "http://"+ipAddress+":"+sport+"/adminLogin"+"/ResetPassword.jsf"+"?rkey="+rkey;  
            System.out.printf("Request URL==========>"+activationLink);
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
            String to = fp.getEmail();
            String from = f[1];
            String subject = "Payroll Password Reset Mail !";
            MimeMessage msg = new MimeMessage(session);
            try {
                  msg.setFrom(new InternetAddress(from));
                  msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                  msg.setSubject(subject);
                  MimeBodyPart l_mbp = new MimeBodyPart();
                  Multipart l_mp = new MimeMultipart();
                                  
                  l_mbp.setContent("","text/html");
                         l_mbp.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:12px;font-weight:bold;'>User ID / EmailID : '"+fp.getEmail()+"'</font><br><br>"    
                                    +"<font style='color:#4B4B4B;font-size:13px;'>Click Following Link At Billow</font><br><br><hr>"
                                    +"<font style='color:red;font-size:13px;font-weight:bold;'>"+"<a href='"+activationLink+"'>"+activationLink+"'</a>"+"</font><br><hr>"
                          + "<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'>Thanks And Regards</font><br>"
                          + "<font style='color:#4B4B4B;font-size:15px;font-weight:bold;'>Payroll Administration<br></font>" 
                          + "<image src="+path+File.separator+"img/pls1.png/></html>","text/html");
                  l_mp.addBodyPart(l_mbp);
                  msg.setContent(l_mp);
                  Date date=new Date();
                  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                  Calendar cal = Calendar.getInstance();
                  Date now = new Date();
                  cal.setTime(now);
                  cal.add(Calendar.DAY_OF_YEAR, 1); // <--
                  Date tomorrow = cal.getTime();
               /**  
                  Connection connection = new CommonDB().getConnection();
                  PreparedStatement pst;
                  pst = connection.prepareStatement("insert into forgot_password(user_name, r_key, pass_date, expiry_date) values(?,?,?,?)");
                  pst.setString(1,fp.getEmail());
                  pst.setString(2, rkey);
                  pst.setString(3, dateFormat.format(date));
                  pst.setString(4, dateFormat.format(tomorrow));
                  pst.executeUpdate();
                  pst.close();
                */
                  
                    ForgotPassword fpass = new ForgotPassword();
                    fpass.setEmail(fp.getEmail());
                    fpass.setRkey(rkey);
                    fpass.setPassdate(dateFormat.format(date));
                    fpass.setExpdate(dateFormat.format(tomorrow));
                    sess = helper.getSessionFactory().openSession();
                    sess.beginTransaction();
                    sess.save(fpass);
                    sess.getTransaction().commit();
                    sess.close();
                    
                  msg.setSentDate(date);
                  Transport transport = session.getTransport("smtp");
                  transport.connect();
                  msg.saveChanges();     // don't forget this
                  transport.sendMessage(msg, msg.getAllRecipients());
                  //transport.send(msg);
                  transport.close();            
                  //transport.send(msg);
                  }
                  catch(Exception exc) 
                  {
                        exc.printStackTrace();
                  }  
                
            return true;
       }
       catch(Exception e)
       {
            e.printStackTrace();
            return false;
       }
    }
    
    
    public String getUserEmail(String rkey){
       try{
            sess = helper.getSessionFactory().openSession();
            sess.beginTransaction();
            Query query = sess.createQuery("from ForgotPassword where r_key='"+rkey+"'");
            ForgotPassword rkeyUser = (ForgotPassword)query.uniqueResult();
            sess.getTransaction().commit();
            if(rkeyUser.getEmail() == null){
                return null;
            }
               return rkeyUser.getEmail();
            
        }
        catch(Exception e)
        {
            sess.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            sess.close();  
        }
        
        
        
   /*
         try{
                
                Connection connection = new CommonDB().getConnection();
                PreparedStatement pst;
                ResultSet rst;
                pst = connection.prepareStatement("select user_name from forgot_password where r_key='"+rkey+"'");
                rst = pst.executeQuery();
                rst.next();
                String email = rst.getString(1);
                //System.out.print(" Om Prakash is trying to get user name from forgot password table=====> "+email);
                return email;
                      
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    */      
        
        
             
    }
    
    public boolean changePassword(String email,String newPass)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            Connection connectionLogin = new CommonDB().getLoginDBConnection();
            newPass=new EncryptionUtil().createDigest("MD5",newPass);
            PreparedStatement pst;
            PreparedStatement pst1;
            pst=c.prepareStatement("update user_master set user_pass=? where "
                    + "user_name=?");
            pst.setString(1, newPass);
            pst.setString(2, email);
            pst.executeUpdate();
            pst.close();
            
            boolean dbExist = new CommonDB().checkLoginDBExists();
            if(dbExist){
                //System.out.println("This is updating password of Login Db ====>"+email);
                pst1=connectionLogin.prepareStatement("update edrpuser set password=? where "
                    + "username=?");
                pst1.setString(1, newPass);
                pst1.setString(2, email);
                pst1.executeUpdate();
                pst1.close();
            }
            c.close();
            connectionLogin.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteEntry(String rkey)
    {
       try{
            sess = helper.getSessionFactory().openSession();
            sess.beginTransaction();
            Query query = sess.createQuery("delete ForgotPassword where r_key ='"+rkey+"'");
            query.executeUpdate();
            sess.getTransaction().commit();
        }
        catch(Exception e)
        {
            sess.getTransaction().rollback();
            e.printStackTrace();
            
        }
        finally {
            sess.close();  
        }   
        return false;

        
       /* try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("delete from forgot_password where r_key = '"+rkey+"'");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }*/
    }
    
    
    public boolean deleteExpiry()
    {
       try{
            sess = helper.getSessionFactory().openSession();
            sess.beginTransaction();
            Query query = sess.createQuery("delete ForgotPassword where expiry_date < now()");
            query.executeUpdate();
            sess.getTransaction().commit();
        }
        catch(Exception e)
        {
            sess.getTransaction().rollback();
            e.printStackTrace();
            
        }
        finally {
            sess.close();  
        }   
        return false;

        /**try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("delete from forgot_password where expiry_date < now()" );
            pst.executeUpdate();
            pst.close();
            cn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Exception Error in expiry ====>"+ex);
        }
        return false;
        */                
    }
    
          
    /*
    * Check if user's Email Address exist in user_master table;  
    *
    */    
    public int CheckUserExist(String email )
    {
     try
        {
            int userId = 0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select count(user_id) from user_master where user_name='"+email+"' and flag = '"+1+"'");
            rst = pst.executeQuery();
            while(rst.next()){
                userId = rst.getInt(1);
            }
            return userId;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
        
    }
}    

