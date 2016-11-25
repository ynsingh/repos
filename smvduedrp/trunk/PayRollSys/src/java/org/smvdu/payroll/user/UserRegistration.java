/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.user;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.smvdu.payroll.api.Administrator.CollegeList;
import org.smvdu.payroll.api.email.MassageProperties;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.UserDB;

/**
  *
 * Copyright (c) 2010 - 2011 SMVDU, Katra.
 * Copyright (c) 2014 - 2016 ETRG, IITK.
 *  All Rights Reserved.
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met: 
 *  Redistributions of source code must retain the above copyright 
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
 *  Contributors: Members of ERP Team @ SMVDU, Katra
 *
 * @author Seema Pal
 * User Verification code added by Om Prakash (omprakashkgp@gmail.com), IITK, 19 April 2016.
 * Last Modification : August 29, 2016
 */
public class UserRegistration {
    private String vcode;
    private String userverificationlink;
    
    public Exception EmployeeRegistration(String emailId,String password,String phoneno,String firstname,String lastname,String address,String categoryT,int orgId,String userType)
    {
            String url = new String();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            url = request.getRequestURL().toString();
            String ipAddress = String.valueOf(request.getServerName());
            String sport = String.valueOf(request.getServerPort());
            //String dir = String.valueOf(request.getServletPath());
            vcode=UUID.randomUUID().toString().replaceAll("-", "");
            userverificationlink = "http://"+ipAddress+":"+sport+"/adminLogin"+"/UserVerification.jsf"+"?vcode="+vcode+"&email="+emailId;  
            //System.out.printf("User Verificvation URL==========>"+userverificationlink);
            String serverUrl="http://"+ipAddress+":"+sport+"/index.jsp";
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
            String to = emailId;
            String from = f[4];
            
            MassageProperties msgprop = new MassageProperties();
            MimeMessage msg = new MimeMessage(session);

            Connection connection = new CommonDB().getConnection();
            Connection connectionLogin = new CommonDB().getLoginDBConnection();
            try {
                  msg.setFrom(new InternetAddress(from));
                  msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                  //msg.setSubject(subject);
                  msg.setSubject(msgprop.getPropertieValue("uveri1"));
                  MimeBodyPart l_mbp = new MimeBodyPart();
                  Multipart l_mp = new MimeMultipart();
                                  
                  l_mbp.setContent("","text/html");
                         l_mbp.setContent("<html>" 
                                    +"<font style='color:#4B4B4B;font-size:14px;font-weight:bold;'>"+msgprop.getPropertieValue("fgtp3")+""+to+"<br><br>"
                                    +"<font style='color:#4B4B4B;font-size:14px;font-weight:bold;'>"+msgprop.getPropertieValue("uveri2")+"</font><br><br>"    
                                    +"<font style='color:#4B4B4B;font-size:14px;'>"+msgprop.getPropertieValue("uveri3")+"</font><br><br><hr>"
                                    +"<font style='color:red;font-size:13px;font-weight:bold;'>"+"<a href='"+userverificationlink+"'>"+userverificationlink+"</a>"+"</font><br><hr>"
                          + "<br><br><font style='color:#4B4B4B;font-size:14px;font-weight:bold;'>"+msgprop.getPropertieValue("fgtp7")+"</font><br>"
                          + "<br><font style='color:#4B4B4B;font-size:14px;font-weight:bold;'> "+msgprop.getPropertieValue("fgtp8") +" "+serverUrl+"</font><br> "       
                          + "<br><br><br><font style='color:#4B4B4B;font-size:15px;font-weight:bold;'>"+msgprop.getPropertieValue("reg")+"<br></font>" 
                          + "<image src="+path+File.separator+"img/pls1.png/></html>","text/html");
                  l_mp.addBodyPart(l_mbp);
                  msg.setContent(l_mp);
            Date date=new Date();
            
            PreparedStatement pst = null; 
            PreparedStatement pst1 = null; 
            PreparedStatement pst2 = null; 
            PreparedStatement pst3 = null; 
            PreparedStatement pst4 = null; 
            //ResultSet rst = null;
            UserDB ud = new UserDB();
            int userid = ud.CheckUserExistInUserMaster(emailId);
            if(!(userid > 0)){
              //  pst = connection.prepareStatement("insert into user_master(user_name,user_pass,user_profile_id,flag) values('"+emailId+"','"+password+"','"+0+"','"+1+"')");
                pst = connection.prepareStatement("insert into user_master(user_name,user_pass,user_profile_id,flag,verification_code,is_verified) values('"+emailId+"','"+password+"','"+0+"','"+0+"','"+vcode+"','"+0+"')");
                pst.executeUpdate();
                pst.clearParameters();
                pst.close();
                           
                boolean dbExist = new CommonDB().checkLoginDBExists();
                if(dbExist){
                    int id = ud.CheckUserExistInLoginDB(emailId);
                    if(!(id > 0)){
                        String component = "payroll";
                        //pst1 = connectionLogin.prepareStatement("insert into edrpuser(username,password,email,componentreg,category_type,mobile,status) values('"+emailId+"','"+password+"','"+emailId+"','"+component+"','"+categoryT+"','"+phoneno+"','"+1+"')");
                        pst1 = connectionLogin.prepareStatement("insert into edrpuser(username,password,email,componentreg,category_type,mobile,status,verification_code,is_verified) values('"+emailId+"','"+password+"','"+emailId+"','"+component+"','"+categoryT+"','"+phoneno+"','"+0+"','"+vcode+"','"+0+"')");
                        pst1.executeUpdate();
                        pst1.clearParameters();
                        pst1.close();
                       
                        msg.setSentDate(date);
                        Transport transport = session.getTransport("smtp");
                        transport.connect();
                        msg.saveChanges();     // don't forget this
                        transport.sendMessage(msg, msg.getAllRecipients());
                        //transport.send(msg);
                        transport.close();  
                        int userIdInLoginDB = ud.CheckUserExistInLoginDB(emailId);
                        
                        pst2 = connectionLogin.prepareStatement("insert into userprofile(userid,firstname,lastname,address,secmail,mobile,lang,status)"
                           + " values('"+userIdInLoginDB+"','"+firstname+"','"+lastname+"','"+address+"','"+""+"', '"+phoneno+"','"+"english"+"','"+1+"')");
                        pst2.executeUpdate();
                        pst2.clearParameters();
                        pst2.close();
                                                                  
                    }
                    else
                    {
                         ArrayList<String> totalComponents = getTotalComponent(emailId);
                         String components ="BGAS";
                                
                            /**write code for retrieving component details. split that details saprating by commma into
                             *an arraylist.
                             *if that arraylist contain payroll. than do nothing otherwise insert payroll into that
                             *column.
                             */
                                
                         boolean flag = totalComponents.contains("payroll");
                         if(flag){
                            System.out.println("User is already Registered with payroll system");
                            System.out.println("Do nothing in LoginDB");
                         }
                         else{
                            components = components.concat(",payroll");
                            pst3 = connectionLogin.prepareStatement("update edrpuser set componentreg=? where username=?");
                            pst3.setString(1,components);
                            pst3.setString(2, emailId);
                            pst3.executeUpdate();
                            pst3.clearParameters();
                            pst3.close();
                            
                         }      
                                    
                     }//close else part if user exists in userLogindb           
                }//close if condition userLogin db exists     
            } //close if condtion when user is not exists in user master table
                       
            /*User already exists in user_master table;*/
            else{
                System.out.println("Entry already Exist in user_master for - "+emailId);
                System.out.println("Do nothing in user master table");
            }
            /** get userId from user emailId 
             * check for role exists or not
             */
            int UserId = ud.getUserId(emailId);
            //int roleId=ud.getRoleExists(emailId,orgId);
            int roleId=getRoleExists(emailId,orgId);
                       
            Exception adduser_role;
            /** Add employee role if employee role not exists in institute */ 
            if(roleId!= 6 && userType.equals("EmpReg")){
                adduser_role = AddUserRole(emailId, orgId,"EmpReg",UserId );
            }
            else{
                /** Add InstituiteAdmin  role if InstituteAdmin  role not exists in institute  */ 
                if(roleId!= 4 && userType.equals("InstAdminReg")){
                    adduser_role = AddUserRole(emailId, orgId,"InstAdminReg",UserId );
                }    
            }
            connection.close();
            connectionLogin.close();        
            return null;

       } 
        catch (SQLException e)
        {
            if (connection != null || connectionLogin != null)
            {
                try
                {
                    connection.rollback();
                    connectionLogin.rollback();
                } // rollback on error
                catch (SQLException i)
                {
                }
            }
            e.printStackTrace();
        } 
        finally
        {
            if (connection != null)
            {
                try
                {
                  connection.close();
                
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }
           
    }
    public ArrayList<String> getTotalComponent(String emailId){  
        Connection connL = new CommonDB().getLoginDBConnection();
        ArrayList<String> totalComponents = new ArrayList<String>();
        PreparedStatement pst = null; 
        ResultSet rst;
        try
        {
      //      pst = connL.prepareStatement("select componentreg from bgasuser where username='"+emailId+"'");
            pst = connL.prepareStatement("select componentreg from edrpuser where username='"+emailId+"'");
            rst = pst.executeQuery();
            rst.next();
            String components = rst.getString(1);
            for (String componentName : components.split(",")){
                totalComponents.add(componentName);
              
            }
            rst.close();
            pst.close();
            connL.close();
            return totalComponents;
            
        }
         catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        } 
    }

    /**
     *
     * @param emailId
     * @param orgId
     * @param userType
     * @param UserId
     * @return
     * @throws java.lang.Exception
     */
    public Exception AddUserRole(String emailId,int orgId,String userType, int UserId )throws Exception
    {   
       PreparedStatement pst5 = null;
       PreparedStatement pst6 = null;
       PreparedStatement pst7 = null;
       PreparedStatement pst8 = null;
       
       Connection connection = new CommonDB().getConnection();
       try{
            if(userType.equals("EmpReg")){
                pst5 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+UserId+"','"+6+"','"+orgId+"')");
                pst5.executeUpdate();
                pst5.clearParameters();
                pst5.close();
            }
            else{
                pst6 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+UserId+"','"+4+"','"+orgId+"')");
                pst6.executeUpdate();
                pst6.clearParameters();
                pst6.close();
                              
                pst7 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+UserId+"','"+6+"','"+orgId+"')");
                pst7.executeUpdate();
                pst7.clearParameters();
                pst7.close();
                    
                pst8 = connection.prepareStatement("update org_profile set org_status = 1 where org_id='"+orgId+"'");
                pst8.executeUpdate(); 
                pst8.clearParameters();
                pst8.close();
                                                
            } 
            return null;
        }
         catch (SQLException e)
        {
            if (connection != null)
            {
                try
                {
                    connection.rollback();
                } // rollback on error
                catch (SQLException i)
                {
                }
            }
        } 
        finally
        {
            if (connection != null)
            {
                try
                {
                  connection.close();
                
                } catch (SQLException e)
                {
                }
            }
            return null;
        }
        
    }
    public  int getRoleExists(String userName ,int orgId){   
        
        try
        {
            int roleid=0;
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            int uid=new UserDB().getUserIDfromUserName(userName);
            pst  = cn.prepareStatement("select role_id from user_roles where user_id ='"+uid+"' and org_id = '"+orgId+"' ");
            rst = pst.executeQuery();
            while(rst.next()){;
            roleid=rst.getInt(1);
            }
            rst.close();
            pst.close();
            cn.close();
            return roleid;
         
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        } 
    } 
    /*
     Use for update user login status when clicking on verification link.   
    */
    
    public boolean updateUserVerification(String email, String vcode)
    {
        try
        {   
            String sc=null;
            Connection c = new CommonDB().getConnection();
            Connection connectionLogin = new CommonDB().getLoginDBConnection();
            PreparedStatement pst;
            PreparedStatement pst1;
            pst=c.prepareStatement("update user_master set flag='"+1+"', verification_code='"+sc+"', is_verified='"+1+"' where "
                    + "user_name=? and verification_code=?");
            pst.setString(1, email);
            pst.setString(2, vcode);
            pst.executeUpdate();
            pst.close();
            
            boolean dbExist = new CommonDB().checkLoginDBExists();
            if(dbExist){
                pst1=connectionLogin.prepareStatement("update edrpuser set status='"+1+"', verification_code='"+sc+"', is_verified='"+1+"' where "
                    + "username=? and verification_code=?");
                pst1.setString(1, email);
                pst1.setString(2, vcode);
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
    
}
