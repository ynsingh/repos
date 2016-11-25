/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.smvdu.payroll.Admin.AdminManagedBean;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.api.EncryptionUtil;
import org.smvdu.payroll.api.email.OrgConformationEmail;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;
import org.smvdu.payroll.beans.db.UserDB;
import org.smvdu.payroll.beans.setup.EdrpUser;
import org.smvdu.payroll.beans.setup.EdrpUserProfile;
import org.smvdu.payroll.beans.setup.Org;
import org.smvdu.payroll.beans.setup.SmtpConfiguration;
import org.smvdu.payroll.beans.setup.UserMaster;
import org.smvdu.payroll.user.UserRegistration;
import org.smvdu.payroll.user.changePassword;

/**
 *
 *  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
 *  Copyright (c) 2014, 2015, 2016 ETRG, IITK. 
 *  All Rights Reserved.
 ** Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met: 
 ** Redistributions of source code must retain the above copyright 
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
 * @author KESU
 * GUI Modified date 21 July 2015, Om Prakash (omprakashkgp@gmail.com), IITK
 * Modified date 21 March 2016
 * Hibernate conversion of multiple database : November, 2016, Om Prakash. 
 */

@ManagedBean
@RequestScoped

public class CollegeList {
    Connection cn;
    String adminUserId;
    private HibernateUtil helper;
    private org.hibernate.Session sess;
    private org.hibernate.Session seslogin;
  
    public CollegeList()
    {
        
        try
        {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
           // Org admin = (Org) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Organization Profile Bean");
            adminUserId = uf.getUserName();
            //System.out.println("ID =======seema: "+adminUserId);
        }
        catch(Exception ex)
        {
            
        }
    }
    
    /**
     * @Load All Pending Details of College List Org Name, Org Id, Org Request Status, Pending Status 
     * @return  List of Pending College List
     */
    
    public ArrayList<Org> getPendingCollegeList()  
    {
           ArrayList<Org> pendingLt = new ArrayList<Org>();
           try{
                sess = helper.getSessionFactory().openSession();
                sess.beginTransaction();
                Query query = sess.createQuery("select o.id, o.name, o.web, o.email, o.phone, cps.orgstatus from Org o, CollegePendingStatus cps where o.id=cps.orgcode and cps.orgstatus='"+0+"'");
                List<Object[]> pending = query.list();
                int k=1;
                for(Object[] it: pending){
                   Org orgt = new Org();
                   orgt.setSrNo(k);
                   orgt.setId((Integer)it[0]);
                   orgt.setName(it[1].toString());
                   orgt.setWeb(it[2].toString());
                   orgt.setEmail(it[3].toString());
                   orgt.setPhone(it[4].toString());
                   orgt.setStatus(false);
                   orgt.setImgUrl("InActive.png");
                   pendingLt.add(orgt);
                   k++;
                }
                sess.getTransaction().commit();
                return pendingLt;
              }  
              catch(Exception e){
              sess.beginTransaction().rollback();
              e.printStackTrace();
       }

  /*   try
        {
            
            ArrayList<Org> pendingList = new ArrayList<Org>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
    //        pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_request_status from org_profile inner join college_pending_status on org_email=org_pen_email where org_request_status = '"+0+"'");
            pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_request_status from org_profile left join college_pending_status on org_id=org_code where org_request_status = '"+0+"'");
            
            rst = pst.executeQuery();
            int k=1;
            while(rst.next())
            {
                Org o = new Org();
                o.setId(rst.getInt(1)); 
                o.setName(rst.getString(2)); 
                o.setWeb(rst.getString(3)); 
                o.setEmail(rst.getString(4)); 
                o.setPhone(rst.getString(5)); 
                if(rst.getInt(6) == 1)
                {
                    o.setImgUrl("Active.png");
                    o.setStatus(true); 
                }
                else
                {
                    o.setImgUrl("InActive.png"); 
                    o.setStatus(false);  
                }
                o.setSrNo(k);
                pendingList.add(o);
                k++;
            }
            return pendingList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    */   
        return null;
             
        
    }
    
    /*
     * Deactivate College / Institute details 
     */
    public ArrayList<Org> getDeactivateCollegeList()
    {
       try{
           ArrayList<Org> deactivateList = new ArrayList<Org>();
           Connection conection = new CommonDB().getConnection();
           PreparedStatement pst;
           pst=conection.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_reg_date,org_status from org_profile left join user_master on org_email=user_name where org_status='"+0+"' and flag='"+0+"'");
           ResultSet rst = pst.executeQuery();
           int k=1;
           while(rst.next()){
               Org org = new Org();
               org.setId(rst.getInt(1));
               org.setName(rst.getString(2));
               org.setWeb(rst.getString(3));
               org.setEmail(rst.getString(4));
               org.setPhone(rst.getString(5));
               org.setDate(rst.getDate(6));
               if(rst.getInt(7) == 1)
                {
                    org.setImgUrl("Active.png");
                    org.setStatus(true); 
                }
                else
                {
                    org.setImgUrl("InActive.png"); 
                    org.setStatus(false);  
                }
                org.setSrNo(k);
                deactivateList.add(org);
                k++;
           }
           conection.close();
           return deactivateList;
       } 
       catch(Exception ex){
           ex.printStackTrace();
           return null;
       }
    }        
    
     public int getDateDIff(String fromDate,String toDate)
    {
        try
        {
            String[] d1 = fromDate.split("-");
            String[] d2 = toDate.split("-");
            int dayDiff = 0;
            int counter = 0;
            int fixDate = 0;
            if(Integer.parseInt(d1[0]) == Integer.parseInt(d2[0]))
            {
                if(Integer.parseInt(d2[1]) > Integer.parseInt(d1[1]))
                {
                    fixDate = Integer.parseInt(d2[2]);
                    while(fixDate!=0)
                    {
                        counter++;
                        fixDate--;
                    }
                    dayDiff = counter;
                }
                else
                {
                    dayDiff = Integer.parseInt(d2[2]) - Integer.parseInt(d1[2]);
                }
                //dayDiff = Integer.parseInt(d2[2]) - Integer.parseInt(d1[2]);
            }
            if(Integer.parseInt(d2[0]) > Integer.parseInt(d1[0]))
            {
                fixDate=0;
                counter=0;
                if(Integer.parseInt(d1[1])>Integer.parseInt(d2[1]))
                {
                    if(Integer.parseInt(d1[2])>Integer.parseInt(d2[2]))
                    {
                        fixDate = Integer.parseInt(d2[2]);
                        while(fixDate!=0)
                        {
                            counter++;
                            fixDate--;
                        }
                        dayDiff=counter;
                    }
                }
            }
            return dayDiff;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    
     /**
      * Load College List which is Registered
      * @return
      * @throws SQLException 
      * 
      */
     
     public ArrayList<Org> getCollegeList() throws SQLException
    {
        try
        {
            ArrayList<Org> cList = new ArrayList<Org>();
            cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            java.util.Date d = new java.util.Date();
        //    PreparedStatement pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_reg_date,flag from org_profile inner join user_master on org_id = user_org_id");
            PreparedStatement pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_reg_date,org_status from org_profile join user_master where org_email=user_name");
            
            ResultSet rst = pst.executeQuery();
            String toDate = d.getYear()+"-"+d.getMonth()+"-"+d.getDay();
            int k=1;
            while(rst.next())
            {
                if(this.getDateDIff(rst.getString(6), toDate) >= 7)
                {
                    this.deleteExtendeRegistration(rst.getString(4)); 
                }
                Org o = new Org();
                o.setId(rst.getInt(1)); 
                o.setName(rst.getString(2)); 
                o.setWeb(rst.getString(3)); 
                o.setEmail(rst.getString(4)); 
                o.setPhone(rst.getString(5)); 
                o.setDate(rst.getDate(6)); 
                if(rst.getInt(7) == 1)
                {
                    o.setImgUrl("Active.png");
                    o.setStatus(true); 
                }
                else
                {
                    o.setImgUrl("InActive.png"); 
                    o.setStatus(false);  
                }
                o.setSrNo(k);
                cList.add(o); 
                k++;
            }
            pst.close();
            rst.close();
            cn.commit();
            cn.close();
            return cList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            cn.close();
        }
    }
    
    public boolean deleteExtendeRegistration(String orgEmail)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            PreparedStatement pst1;
            pst1 = cn.prepareStatement("delete from org_profile where org_email = '"+orgEmail+"'");
            pst = cn.prepareStatement("delete from college_pending_status where org_pen_email = '"+orgEmail+"'");
            pst1.executeUpdate();
            pst1.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
   
    
    /**
     * Update the Status of College which is Active/Deactive
     * @param org
     * @return 
     * 
     */
    
    public Exception update(ArrayList<Org> org)
    {
       try {
           sess = helper.getSessionFactory().openSession();
           seslogin = helper.getLoginSF().openSession();
           //SessionFactory factory = new Configuration().configure("logindb.cfg.xml").buildSessionFactory(); 
          // Session session = factory.openSession();
           Transaction tx = null;
           for(Org or : org)
           {
              if(or.isStatus()==false)
              {
                sess.beginTransaction();
                Query query1 = sess.createQuery("update Org set status = '"+0+"' where id = '"+or.getId()+"'");
                query1.executeUpdate();
                UserMaster um = new UserMaster();
                um.setUid(or.getId());
                um.setUsname(or.getEmail());
                Query query2 = sess.createQuery("update UserMaster set flag = '0' where usname ='"+or.getEmail()+"'");
                query2.executeUpdate();
                sess.getTransaction().commit();
                String emailId=or.getEmail();
                ArrayList totalComponents = new UserRegistration().getTotalComponent(emailId);
                boolean flag = totalComponents.contains("BGAS");
                if(flag)
                {    
                      String component="BGAS";
                      tx= seslogin.beginTransaction();
                      Query query3 = seslogin.createQuery("update EdrpUser set comptreg='"+component+"' , status='"+1+"' where username ='"+emailId+"'");
                      EdrpUser edu = new EdrpUser();
                      edu.setUsername(emailId);
                      query3.executeUpdate();
                      tx.commit();
                 }   
                else{
                    String component=" ";
                    tx= seslogin.beginTransaction();
                    Query query4 = seslogin.createQuery("update EdrpUser set comptreg='"+component+"' , status='"+0+"' where username ='"+emailId+"'");
                    EdrpUser edu = new EdrpUser();
                    edu.setUsername(or.getEmail());
                    query4.executeUpdate();
                    tx.commit();
                }
               new OrgConformationEmail().sendDeActivationCollegeMail(or);
              }
           }
                              
       }
       catch(Exception e){
           sess.beginTransaction().rollback();
           e.printStackTrace();
       }
     finally {
             sess.close();
             seslogin.close();
                        
        }
        
        /*
        try
        {
            
            Connection connection = new CommonDB().getConnection();
            Connection connectionLogin = new CommonDB().getLoginDBConnection();
            PreparedStatement pst = null; 
            PreparedStatement pst1 = null;
            PreparedStatement pst2 = null;
            PreparedStatement pst3 = null;
            int st;
            for(Org or : org)
            {
                if(or.isStatus() == false)
                {
                    st = 0;
                
                    pst = connection.prepareStatement("update org_profile set org_status ='"+st+"' where org_id ='"+or.getId()+"'");
                    pst1 = connection.prepareStatement("update user_master set flag ='"+st+"' where user_name ='"+or.getEmail()+"'");
                    pst1.executeUpdate();
                    pst1.clearParameters();
                    pst.executeUpdate();
                    pst.clearParameters();
                    String emailId=or.getEmail();
                    ArrayList totalComponents = new UserRegistration().getTotalComponent(emailId);
                    boolean flag = totalComponents.contains("BGAS");
                    if(flag)
                    {    
                        String component="BGAS";
                        pst2 = connectionLogin.prepareStatement("update edrpuser set componentreg=? , status='"+1+"' where username =? ");
                        pst2.setString(1, component);
                        pst2.setString(2, emailId);
                        pst2.executeUpdate();
                        pst2.clearParameters();
                        pst2.close();
                    }
                    else{
                        String component=" ";
                        pst3 = connectionLogin.prepareStatement("update edrpuser set componentreg=? , status='"+st+"' where username =? ");
                        pst3.setString(1, component);
                        pst3.setString(2, emailId);
                        pst3.executeUpdate();
                        pst3.clearParameters();
                        pst3.close();
                    
                    }
                    new OrgConformationEmail().sendDeActivationCollegeMail(or); 
                }
            }
            pst.close();
            pst1.close();
            connection.close();
            connectionLogin.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        } */
        return null;
       
    }
   
    /**
     * 
     * Update/Insert details for User to access the Payroll with Particular Task 
     * @param org
     * @return 
     * 
     */
    
//    public Exception updateRequest(ArrayList<Org> org)
//    {
//        try
//        {
//            Connection connection = new CommonDB().getConnection();
//            PreparedStatement pst = null; 
//            PreparedStatement pst1 = null;
//            PreparedStatement pst2 = null;
//            ResultSet rst = null;
//            int st;
//            String password = null;
//            for(Org or : org)
//            {
//                if(or.isStatus() == true)
//                {
//                    st =1;
//                    pst2 = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+or.getEmail()+"'");
//                    rst = pst2.executeQuery();
//                    if(rst.next())
//                    {
//                        password = rst.getString(1);
//                    }
//                    rst.close();
//                    pst2.close();
//                    pst1 = connection.prepareStatement("insert into user_master(user_name,user_pass,user_org_id,user_grp_id,user_profile_id,flag) values('"+or.getEmail()+"','"+password+"','"+or.getId()+"','"+4+"','"+0+"','"+1+"')");
//                    pst1.executeUpdate();
//                    pst1.clearParameters();
//                    
//                    new OrgConformationEmail().sendMail(or);
//                    pst = connection.prepareStatement("delete from college_pending_status where org_pen_email = '"+or.getEmail()+"'");
//                    pst.executeUpdate();
//                    pst.clearParameters();
//                }
//                else
//                {
//                    st = 0;
//                }
//            }
//            pst.close();
//            pst1.close();
//            
//            connection.close();
//            return null;
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            return ex;
//        } 
//    }
    
//    public Exception updateRequest(ArrayList<Org> org)
//    {
//        try
//        {
//            Connection connection = new CommonDB().getConnection();
//            Connection connectLogin = new CommonDB().getLoginDBConnection();
//            PreparedStatement pst = null; 
//            PreparedStatement pst1 = null;
//            PreparedStatement pst2 = null;
//            PreparedStatement pst3 = null;
//            PreparedStatement pst4 = null;
//            PreparedStatement pst5 = null;
//            ResultSet rst = null;
//            int st;
//            String password = null;
//            for(Org or : org)
//            {
//                if(or.isStatus() == true)
//                {
//                    st =1;
//                    pst2 = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+or.getEmail()+"'");
//                    rst = pst2.executeQuery();
//                    if(rst.next())
//                    {
//                        password = rst.getString(1);
//                    }
//                    rst.close();
//                    pst2.close();
//                            
//                    pst1 = connection.prepareStatement("insert into user_master(user_name,user_pass,user_org_id,user_grp_id,user_profile_id,flag) values('"+or.getEmail()+"','"+password+"','"+or.getId()+"','"+4+"','"+0+"','"+1+"')");
//                    pst1.executeUpdate();
//                    pst1.clearParameters();
//                    
//                    pst3 = connectLogin.prepareStatement("insert into users(username,password,email,status) values('"+or.getEmail()+"','"+password+"','"+or.getEmail()+"','"+1+"')");
//                    pst3.executeUpdate();
//                    pst3.clearParameters();
//                    
//                    UserDB ud = new UserDB();
//                    int userId = ud.getUserId(or.getEmail(), or.getId());
//                    
//                    pst4 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+userId+"','"+2+"','"+or.getId()+"')");
//                    pst4.executeUpdate();
//                    pst4.clearParameters();
//                    
//                    pst5 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+userId+"','"+3+"','"+or.getId()+"')");
//                    pst5.executeUpdate();
//                    pst5.clearParameters();
//                    
//                    new OrgConformationEmail().sendMail(or);
//                    pst = connection.prepareStatement("delete from college_pending_status where org_code = '"+or.getId()+"'");
//                    pst.executeUpdate();
//                    pst.clearParameters();
//                }
//                else
//                {
//                    st = 0;
//                }
//            }
//            pst.close();
//            pst1.close();
//            
//            connection.close();
//            return null;
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            return ex;
//        } 
//    }
//    
   /* 
    * this method is use for Activate the Deactivated College List.
    */
    public Exception updateDeactivate(ArrayList<Org> orgDA)
    {
       try {
           sess = helper.getSessionFactory().openSession();
           seslogin = helper.getLoginSF().openSession();
           Transaction tx = null;
           for(Org or : orgDA)
           {
              if(or.isStatus()==true)
              {
                sess.beginTransaction();
                Query query1 = sess.createQuery("update Org set status = '"+1+"' where id = '"+or.getId()+"'");
                query1.executeUpdate();
                UserMaster um = new UserMaster();
                um.setUid(or.getId());
                um.setUsname(or.getEmail());
                Query query2 = sess.createQuery("update UserMaster set flag = '1' where usname ='"+or.getEmail()+"'");
                query2.executeUpdate();
                sess.getTransaction().commit();
                String emailId=or.getEmail();
                ArrayList totalComponents = new UserRegistration().getTotalComponent(emailId);
                boolean flag = totalComponents.contains("BGAS");
                if(flag)
                {    
                      String components="BGAS";
                      components = components.concat(",payroll");
                      tx = seslogin.beginTransaction();
                      Query query3 = seslogin.createQuery("update EdrpUser set comptreg='"+components+"' , status='"+1+"' where username ='"+emailId+"'");
                      EdrpUser edu = new EdrpUser();
                      edu.setUsername(emailId);
                      query3.executeUpdate();
                      tx.commit();
                 }   
                else{
                    String components = "payroll";
                    tx = seslogin.beginTransaction();
                    Query query4 = seslogin.createQuery("update EdrpUser set comptreg='"+components+"' , status='"+1+"' where username ='"+emailId+"'");
                    EdrpUser edu = new EdrpUser();
                    edu.setUsername(or.getEmail());
                    query4.executeUpdate();
                    tx.commit();
                } 
                new OrgConformationEmail().sendMail(or);
             }
         }
      }
      catch(Exception e){
           sess.beginTransaction().rollback();
           e.printStackTrace();
      }
      finally {
         sess.close();
         seslogin.close();
      }

  /*
       Connection connection = new CommonDB().getConnection();
       Connection connectionLogin = new CommonDB().getLoginDBConnection();
       try{
                PreparedStatement pst = null;
                PreparedStatement pst1= null;
                PreparedStatement pst2= null;
                PreparedStatement pst3 = null;
                int st;
                for(Org org : orgDA)
                {   
                    if(org.isStatus()==true)
                    {
                        st=1;
  
                        pst = connection.prepareStatement("update org_profile set org_status ='"+st+"' where org_id ='"+org.getId()+"'");
                        pst1 = connection.prepareStatement("update user_master set flag ='"+st+"' where user_name ='"+org.getEmail()+"'");
                        pst1.executeUpdate();
                        pst1.clearParameters();
                        pst.executeUpdate();
                        pst.clearParameters();
                    
                        String emailId=org.getEmail();
                        ArrayList<String> totalComponents =new UserRegistration().getTotalComponent(emailId);
                        boolean flag=totalComponents.contains("BGAS");
                        if(flag)
                        {
                            String components = "BGAS";
                            components = components.concat(",payroll");
                            pst2=connectionLogin.prepareStatement("update edrpuser set componentreg=? , status='"+st+"' where username=?");
                            pst2.setString(1, components);
                            pst2.setString(2, emailId);
                            pst2.executeUpdate();
                            pst2.clearParameters();
                            pst2.close();
                            
                        }
                        else{
                            String components = "payroll";
                            pst3=connectionLogin.prepareStatement("update edrpuser set componentreg=? , status='"+st+"' where username=?");
                            pst3.setString(1, components);
                            pst3.setString(2, emailId);
                            pst3.executeUpdate();
                            pst3.clearParameters();
                            pst3.close();
                        }
                        new OrgConformationEmail().sendMail(org);
                   }
                        
                }
                pst.close();
                pst1.close();
                connection.close();
                connectionLogin.close();
                return null;
            } 
            catch(Exception ex){
                ex.getStackTrace();
            }*/
           return null;
    }

    // Method for delete Institute/College
    
    public Exception deleteDeactivate(ArrayList<Org> orgD)
    {
        try{
           sess = helper.getSessionFactory().openSession();
           seslogin = helper.getLoginSF().openSession();
           Transaction tx = null;
           for(Org or : orgD)
           {
              if(or.isStatus()==true)
              {
                sess.beginTransaction();
                Query query1 = sess.createQuery("delete Org where id = '"+or.getId()+"'");
                query1.executeUpdate();
                UserMaster um = new UserMaster();
                um.setUid(or.getId());
                um.setUsname(or.getEmail());
                Query query2 = sess.createQuery("delete UserMaster where usname ='"+or.getEmail()+"'");
                query2.executeUpdate();
                sess.getTransaction().commit();
                UserDB ud = new UserDB();
                String emailId=or.getEmail();
                ArrayList totalComponents = new UserRegistration().getTotalComponent(emailId);
                boolean flag = totalComponents.contains("BGAS");
                if(flag)
                {    
                    System.out.println("The User Entry can not be delete from login database. ");
                }   
               else{
                    int userIdInLoginDB = ud.CheckUserExistInLoginDB(emailId);
                    tx= seslogin.beginTransaction();
                    Query query3 = seslogin.createQuery("delete EdrpUser where status='"+0+"' and username ='"+emailId+"'");
                    EdrpUser edu = new EdrpUser();
                    edu.setUsername(or.getEmail());
                    query3.executeUpdate();
                    Query query4 = seslogin.createQuery("delete EdrpUserProfile where userid ='"+userIdInLoginDB+"'");
                    EdrpUserProfile edup= new EdrpUserProfile();
                    edup.setUserid(userIdInLoginDB);
                    query4.executeUpdate();
                    tx.commit();
                  }
            }
         }
        }
        catch(Exception e){
           sess.beginTransaction().rollback();
           e.printStackTrace();
       }
       finally {
           sess.close();
           seslogin.close();
     }
        
        /*
       Connection connection = new CommonDB().getConnection();
       Connection connectionLogin = new CommonDB().getLoginDBConnection();
       try{
                PreparedStatement pst = null;
                PreparedStatement pst1= null;
                PreparedStatement pst2= null;
                PreparedStatement pst3 = null;
                int st;
                for(Org org : orgD)
                {   
                    if(org.isStatus()==true)
                    {
                        st=1;
  
                        pst = connection.prepareStatement("delete from org_profile where org_id ='"+org.getId()+"'");
                        pst1 = connection.prepareStatement("delete from user_master where user_name ='"+org.getEmail()+"'");
                        pst1.executeUpdate();
                        pst1.clearParameters();
                        pst.executeUpdate();
                        pst.clearParameters();
                        
                        UserDB ud = new UserDB();
                        
                        String emailId=org.getEmail();
                        ArrayList<String> totalComponents =new UserRegistration().getTotalComponent(emailId);
                        boolean flag=totalComponents.contains("BGAS");
                        if(flag)
                        {
                            System.out.println("The User Entry can not be delete from login database. ");

                        }
                        else{
                            int userIdInLoginDB = ud.CheckUserExistInLoginDB(emailId);
                   
                            pst3=connectionLogin.prepareStatement("delete from edrpuser where username=?");
                            pst3.setString(1, emailId);
                            pst3.executeUpdate();
                            pst3.clearParameters();
                            pst3.close();
                            
                            pst2=connectionLogin.prepareStatement("delete from userprofile where userid=?");
                            pst2.setInt(1, userIdInLoginDB);
                            pst2.executeUpdate();
                            pst2.clearParameters();
                            pst2.close();
                        }
                      
                   }
                        
                }
                pst.close();
                pst1.close();
                connection.close();
                connectionLogin.close();
                return null;
            } 
            catch(Exception ex){
                ex.getStackTrace();
            }*/
        return null;
    }        
    /*
     * This method is for accept the pending college list.
     */
    public Exception updateRequest(ArrayList<Org> org)
    {
        try{
           sess = helper.getSessionFactory().openSession();
           String password = null;
           for(Org or : org)
            {
                if(or.isStatus() == true)
                {
                    sess.beginTransaction();
                    Query query = sess.createQuery("select masterPassword from Org  where email = '"+or.getEmail()+"'");
                    ArrayList<Org> data = (ArrayList<Org>) query.list();
                    for (Object obj:data)
                    {
                        password = obj.toString();
                    }
                    
                    updateStatus(or);
                    
                    Employee emp = new Employee();
                    
                    Org orginfo =  new OrgProfileDB().loadOrgProfileByName(or.getName());
                    Exception eloginmachanism;
                    eloginmachanism = new UserRegistration().EmployeeRegistration(or.getEmail(),password,or.getPhone(),orginfo.getAdminfn(),orginfo.getAdminln(),orginfo.getAddress1(),emp.getCategoryT(),or.getId(),"InstAdminReg");
                   
                    deleteCps(or);
                    UserDB ud = new UserDB();
                    String emailId = or.getEmail();
                    int orgId = or.getId(); 
                    String userType = null;
                    int UserId = ud.getUserId(emailId);
                    int roleId=ud.getRoleExists(emailId,orgId);
                    Exception adduser_role;
                    //** Add InstituiteAdmin  role if InstituteAdmin  role not exists in institute   
                    if(roleId!= 4){
                        adduser_role = new UserRegistration().AddUserRole(emailId, orgId,"InstAdminReg",UserId );
                    }    
                    new OrgConformationEmail().sendMail(or);
                }
            }
        }
        catch(Exception e){
            sess.beginTransaction().rollback();
            e.printStackTrace();
        }
       return null;
        
        /*
        Connection connection = new CommonDB().getConnection();
        Connection connectLogin = new CommonDB().getLoginDBConnection();
        try
        {
            
            PreparedStatement pst = null; 
            PreparedStatement pst9 = null;
            ResultSet rst = null;
            int st;
            String password = null;
            for(Org or : org)
            {
                if(or.isStatus() == true)
                {
                    st =1;
    
                    pst = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+or.getEmail()+"'");
                    rst = pst.executeQuery();
                    if(rst.next())
                    {
                        password = rst.getString(1);
                      
                    }
                    rst.close();
                    pst.close();
                    
                    new CollegeList().updateStatus(or);
                    
                    //** this method follow the common data base mechanism for (InstituteAdmin) user registration process
                    //* and check user exists or not if not then insert the entry.
                    //* get institute detail from OrgProfileDB() for profile information.
                    //* @see UserRegistration() and OrgProfileDB().
                    
                    Employee emp = new Employee();
                    
                    Org orginfo =  new OrgProfileDB().loadOrgProfileByName(or.getName());
                    Exception eloginmachanism;
                    eloginmachanism = new UserRegistration().EmployeeRegistration(or.getEmail(),password,or.getPhone(),orginfo.getAdminfn(),orginfo.getAdminln(),orginfo.getAddress1(),emp.getCategoryT(),or.getId(),"InstAdminReg");
                    pst9 = connection.prepareStatement("delete from college_pending_status where org_code = '"+or.getId()+"'");
                    pst9.executeUpdate();
                    pst9.clearParameters();
                    pst9.close();
                    /////////new
                    UserDB ud = new UserDB();
                    String emailId = or.getEmail();
                    int orgId = or.getId();
                   
                    //** get userId from user emailId 
                    //* check for role exists or not
                    //
                    int UserId = ud.getUserId(emailId);
                    int roleId=ud.getRoleExists(emailId,orgId);
                                                              
                    Exception adduser_role;
                   
                    //** Add InstituiteAdmin  role if InstituteAdmin  role not exists in institute   
                    if(roleId!= 4 ){
                        adduser_role = new UserRegistration().AddUserRole(emailId, orgId,"InstAdminReg",UserId );
                    }    
                  
                    new OrgConformationEmail().sendMail(or);
                    
                }
                else
                {
                    st = 0;
                }
                
            }
            connection.close();
            connectLogin.close();

            return null;
        }
        
        catch (SQLException e)
        {
            if (connection != null || connectLogin != null)
            {
                try
                {
                    connection.rollback();
                    connectLogin.rollback();
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
                  //con.rollback();
                    connection.close();
                //    connectLogin.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        } */
    }
    
    
    
   /**Method for change Admin password
    * Update user_master and edrpuser in LoginDB.  
    * @param admin
    * @return 
    */
    
    public Exception changePass(Org admin)
    {
        try
        {
           /* System.out.println("Admin ID : "+adminUserId+"\npassword===="+admin.getAdPassword());
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst; 
            pst = connection.prepareStatement("update admin_records set admin_pass = '"+admin.getAdPassword()+"' where user_id='"+adminUserId+"'and flag='"+1+"'");
            pst.executeUpdate();*/
            String pwd= new EncryptionUtil().createDigest("MD5",admin.getAdPassword());
            String changepassinDb=new changePassword().changePaswordInLoginDB(pwd, adminUserId);
            System.out.println("pwd-====="+pwd);
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    /**
     * 
     * Active Admin List/Records
     * @return 
     */
    
    public ArrayList<Org> activeAdminList()
    {   
        try
        {
            ArrayList<Org> adminBean = new ArrayList<Org>();
            Connection connection = new CommonDB().getConnection(); 
            PreparedStatement pst;
            ResultSet rst;
            //pst = connection.prepareStatement("select * from admin_records");
            pst = connection.prepareStatement("select user_name, flag from user_master left join user_roles on user_roles.user_id=user_master.user_id where role_id='"+3+"'");
            
            rst = pst.executeQuery();
            while(rst.next())
            {
                Org admin =new Org();
                admin.setAdUserId(rst.getString(1));
                if(rst.getInt(2)==1){
                    admin.setStatus(true);
                    admin.setImgUrl("Active.png");
                 }
                if(rst.getInt(2)==0){
                    admin.setStatus(false);
                    admin.setImgUrl("InActive.png");
                }
                adminBean.add(admin);
            
            }
          /*  while(rst.next())
            {
                Org admin = new Org();
                admin.setAdUserId(rst.getString(2)); 
                if(rst.getInt(4) == 1)
                {
                    admin.setStatus(true);
                    admin.setImgUrl("Active.png");
                }
                if(rst.getInt(4) == 0)
                {
                    admin.setStatus(false);
                    admin.setImgUrl("InActive.png");
                }
               // admin.setDate(rst.getString(5)); 
                adminBean.add(admin); 
            }*/
            rst.close();
            pst.close();
            connection.close();
            return adminBean;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
   /**
    * Update Admin Status which Active or Inactive
    * @param admin
    * @return 
    * 
    */
    
    public Exception updateAdminStatus(ArrayList<Org> admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            int st;
            for(Org adm : admin)
            {
                System.out.println("ID : "+adm.getAdUserId());
                if(adm.isStatus() == true)
                {
                    st = 1;
                }
                else
                {
                    st = 0;
                }
                pst = cn.prepareStatement("update admin_records set flag = '"+st+"' where user_id='"+adm.getAdUserId()+"'");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
    
    /**
     * Update Admin Email Status
     * @param admin
     * @return 
     * 
     */
    
    public Exception updateAdminEmailStatus(ArrayList<Org> admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            int st;
            for(Org adm : admin)
            {
          //      System.out.println("ID : "+adm.getAdUserId());
                if(adm.isStatus() == true)
                {
                    st = 1;
                }
                else
                {
                    st = 0;
                }
                pst = cn.prepareStatement("update admin_email_config set admin_email_status = '"+st+"' where admin_emailid='"+adm.getEmail()+"'");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
    
    /**
     * Check Admin Id for user
     * @param admin
     * @return 
     * 
     */
    
    public Exception checkAdminId(AdminManagedBean admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select user_id from admin_records where user_id = '"+admin.getUserId()+"'");
            rst = pst.executeQuery();
            rst.next();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
    
    /**
     * Insert the Admin Records of User Id and password
     * @param man
     * @return 
     * 
     */
    
    public Exception adminDB(Org man)
    {
        try
        {
            java.util.Date date = new java.util.Date();
            java.util.Date dat = new java.util.Date();
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("yy-MM-dd");
            
            /*int month = date.getMonth();
            int year = date.getYear();
            int day = date.getDate();*/
            String d = String.valueOf(date.getDate())+"-"+String.valueOf(date.getMonth())+"-"+String.valueOf(date.getDate());
            dat = (java.util.Date) dateFormat.parse(d);
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into admin_records(user_id,admin_pass,flag,add_date) values('"+man.getAdUserId()+"','"+man.getAdPassword()+"','"+0+"','"+new java.sql.Date(dat.getTime())+"')");
            pst.executeUpdate();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    public boolean featchDetails(Org admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select user_id,admin_pass,flag from admin_records where user_id='"+admin.getAdUserId()+"' and admin_pass='"+admin.getAdPassword()+"' and flag='"+1+"'");
            rst = pst.executeQuery();
            if(rst.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    public int getTotalNoCollege()
    {
        try
        {
            int totalCollege=0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            //pst = connection.prepareStatement("select count(user_name) as total from user_master");
            pst = connection.prepareStatement("select count(org_name) as total from org_profile");
            rst = pst.executeQuery();
            rst.next();
            totalCollege = rst.getInt("total");
            return totalCollege;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    public int getTotalNoAcCollege()
    {
        try
        {
            int totalCollege=0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
           // pst = connection.prepareStatement("select count(user_name) as total from user_master where flag = '"+1+"'");
            pst = connection.prepareStatement("select count(org_name) as total from org_profile where org_status = '"+1+"'");
            rst = pst.executeQuery();
            rst.next();
            totalCollege = rst.getInt("total");
            return totalCollege;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    public int getTotalDeAcNoCollege()
    {
        try
        {
            int totalCollege=0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            //pst = connection.prepareStatement("select count(user_name) as total from user_master where flag='"+0+"'");
            pst = connection.prepareStatement("select count(org_name) as total from org_profile join user_master on org_email=user_name where org_status = '"+0+"'");
            rst = pst.executeQuery();
            rst.next();
            totalCollege = rst.getInt("total");
            return totalCollege;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    
   /**
    * Insert Admin Email Configuration 
    * @param org
    * @return 
    * 
    */
    
    public Exception saveEmailConfig(Org org)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into admin_email_config(admin_emailid,admin_config_pass,admin_email_status) values('"+org.getEmail()+"','"+org.getAdPassword()+"','"+0+"')");
            pst.executeUpdate();
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    public ArrayList<Org> adminEmaiIdList()
    {
        try
        {
            ArrayList<Org> adminEmail = new ArrayList<Org>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = cn.prepareStatement("select * from admin_email_config");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                Org o = new Org();
                o.setEmail(rst.getString(2)); 
                 if(rst.getInt(4) == 1)
                {
                    o.setStatus(true);
                    o.setImgUrl("Active.png");
                }
                if(rst.getInt(4) == 0)
                {
                    o.setStatus(false);
                    o.setImgUrl("InActive.png");
                }
                
                adminEmail.add(o); 
            }
            
            return adminEmail;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
   /**
    * Update Org Profile
    * @param org
    * @return 
    * 
    */
    
    public Exception updateRow(Org org)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            cn.setAutoCommit(false); 
            pst = cn.prepareStatement("update org_profile set org_name = '"+org.getName()+"',org_email = '"+org.getEmail()+"',org_web = '"+org.getWeb()+"' where org_id = '"+org.getId()+"'");
            pst.executeUpdate();
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
  
    /**
     * update the Password for user for Specific organization
     * @param org
     * @return 
     * 
     */
    public Exception updatePassword(Org editedRecord)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            String pass= new EncryptionUtil().createDigest("MD5",editedRecord.getAdPassword());
            String changepassinDb=new changePassword().changePaswordInLoginDB(pass, editedRecord.getEmail());
            pst = cn.prepareStatement("update org_profile set org_master_password = '"+pass+"' where org_email = '"+editedRecord.getEmail()+"'");
            pst.executeUpdate();
            pst.close();
            cn.close();
            new OrgConformationEmail().sendChangePasswordMail(editedRecord); 
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
   /**
    * 
    * Update Client Details
    * @param o
    * @return 
    * 
    */
    
    public Exception updateIpList(Org o)
    {
        try
        {
            int st;
            if(o.isIpStatus() == true)
            {
                st = 1;
            }
            else
            {
                st=0;
            }
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            cn.setAutoCommit(false); 
            pst = cn.prepareStatement("update client_details set flag = '"+st+"' where ip_address = '"+o.getIpAddress()+"'");
            pst.executeUpdate();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }
    
    public boolean isPending()
    {
        try
        {
            boolean flag;
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            cn.setAutoCommit(false);
            pst = cn.prepareStatement("select COUNT(org_pen_email) as cp from college_pending_status");
            rst = pst.executeQuery();
            if(rst.getInt("cp") > 0)
            {
                flag = true;
            }
            else
            {
                flag = false;
            }
            return flag;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    public ArrayList<SmtpConfiguration> getSMTPDetails()
    {
        sess = helper.getSessionFactory().openSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from SmtpConfiguration");
        ArrayList<SmtpConfiguration> smtpList = (ArrayList<SmtpConfiguration>) query.list();
        sess.getTransaction().commit();
        sess.close();
        return smtpList;

        /* try
        {
            ArrayList<SmtpConfiguration> smtpList = new ArrayList<SmtpConfiguration>();
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false);
            PreparedStatement pst;
            pst = cn.prepareStatement("select * from admin_smtp_details");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                    SmtpConfiguration o = new SmtpConfiguration();
                    //o.setSmtpServerName(rst.getString(2));
                    o.setSeqId(rst.getInt(1));
                    o.setSmtpPort(rst.getInt(2)); 
                    o.setFromEmailId(rst.getString(3)); 
                    o.setFromPassword(rst.getString(4));
                 if(rst.getInt(5) == 1)
                {
                    o.setSmtpStatus(true);
                    o.setImgUrl("Active.png");
                }
                if(rst.getInt(5) == 0)
                {
                    o.setSmtpStatus(false);
                    o.setImgUrl("InActive.png");
                }
                    o.setHostName(rst.getString(6)); 
                smtpList.add(o); 
            }
            rst.close();
            pst.close();
            cn.commit();
            return smtpList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
     */
    }
   
    /**
     * 
     * Insert Admin SMTP Details
     * 
     * @param o
     * @return 
     * 
     */
    
    public Exception saveSMTPDetails(SmtpConfiguration o)
    {
        SmtpConfiguration smtpCon = new SmtpConfiguration();
        smtpCon.setHostName(o.getHostName());
        smtpCon.setSmtpPort(o.getSmtpPort());
        smtpCon.setFromEmailId(o.getFromEmailId());
        smtpCon.setFromPassword(o.getFromPassword());
        smtpCon.setMailfrom(o.getMailfrom());
        smtpCon.setSmtpStatus(o.isSmtpStatus());
        sess = helper.getSessionFactory().openSession();
        sess.beginTransaction();
        sess.save(smtpCon);
        sess.getTransaction().commit();
        sess.close();
        return null;

       /* try
        {
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false);
            PreparedStatement pst,pst1;
            pst1 = cn.prepareStatement("update admin_smtp_details set smtp_status = '"+0+"' where smtp_status = '"+1+"'");
            pst1.executeUpdate();
            pst1.close();
            pst = cn.prepareStatement("insert into admin_smtp_details(smtp_port,auth_emailid,auth_password,smtp_status,smtp_host_name) values('"+o.getSmtpPort()+"','"+o.getFromEmailId()+"','"+o.getFromPassword()+"','"+1+"','"+o.getHostName()+"')");
            pst.executeUpdate();
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
      */     
       
    }
    
    /**
     * Update Admin SMTP Details
     * @param admin
     * @return 
     * 
     */
    
    public Exception updateAdminSMTP(ArrayList<SmtpConfiguration> admin)
    {
        try{
            sess = helper.getSessionFactory().openSession();
            for(SmtpConfiguration adm : admin)
            {
                sess.beginTransaction();
                SmtpConfiguration smtpCon = (SmtpConfiguration) sess.get(SmtpConfiguration.class, adm.getSeqId());
                smtpCon.setHostName(adm.getHostName());
                smtpCon.setFromEmailId(adm.getFromEmailId());
                smtpCon.setFromPassword(adm.getFromPassword());
                smtpCon.setSmtpPort(adm.getSmtpPort());
                smtpCon.setMailfrom(adm.getMailfrom());
                smtpCon.setSmtpStatus(adm.isSmtpStatus());
                sess.update(smtpCon);
                sess.getTransaction().commit();
            }
        }
        catch (Exception e )
        {
            sess.getTransaction().rollback();
            e.printStackTrace();
             
        }
        finally {
            sess.close(); 
        }
        
     /* try
        {
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst = null;
            int st;
            for(SmtpConfiguration adm : admin)
            {
                if(adm.isSmtpStatus() == true)
                {
                    st = 1;
                }
                else
                {
                    st = 0;
                }
                //pst = cn.prepareStatement("update admin_smtp_details set smtp_status = '"+st+"' where auth_emailid='"+adm.getFromEmailId()+"'");
                pst = cn.prepareStatement("update admin_smtp_details set smtp_host_name=?, auth_password=?, smtp_port=?, auth_emailid=?, smtp_status = '"+st+"' where seq_id='"+adm.getSeqId()+"'");
                System.out.println("========This is SMTP id =======>"+adm.getSeqId());
                pst.setString(1, adm.getHostName());
                pst.setString(2, adm.getFromPassword());
                pst.setInt(3, adm.getSmtpPort());
                pst.setString(4, adm.getFromEmailId());
                pst.executeUpdate();
                pst.clearParameters();
                
            }
            pst.close();
            cn.commit();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
       */
        return null;
    }
    
    public String getSMTPAuthDetails()
    {
        try
        {
            String authDetails;
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false); 
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select smtp_port,auth_emailid,auth_password,smtp_host_name,mail_from from admin_smtp_details where smtp_status = '"+1+"'");
            rst = pst.executeQuery();
            if(rst.next() == true)
            {
                authDetails = rst.getInt(1)+"-"+rst.getString(2)+"-"+rst.getString(3)+"-"+rst.getString(4)+"-"+rst.getString(5); 
            }
            else
            {
                authDetails = "";
            }
            rst.close();
            pst.close();
            cn.commit();
            return authDetails;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    /*
     * This method has been generated for update the org profile status after admin accept.
     */
    public void updateStatus(Org orgs)
    {
        
        try{
            sess = helper.getSessionFactory().openSession();
            sess.beginTransaction();
            Query query = sess.createQuery("update Org set status = '"+1+"' where email = '"+orgs.getEmail()+"'");
            query.executeUpdate();
            sess.getTransaction().commit();
        }
        catch(Exception e){
            sess.getTransaction().rollback();
            e.printStackTrace();
        }
        finally{
        sess.close();
        }
       /* Connection cn = new CommonDB().getConnection();
        try{
                PreparedStatement pst;
                pst = cn.prepareStatement("update org_profile set org_status='"+1+"' where org_email='"+orgs.getEmail()+"'");
                pst.executeUpdate();
                cn.close();
                pst.close();
                
        }
        catch(Exception ex)
        {
             ex.printStackTrace();
                         
        }*/
     }
    /*
     * This method has been generated for delete the college pending status after accepting the college. 
     */
    public void deleteCps(Org cps)
    {
        
        try{
            sess = helper.getSessionFactory().openSession();
            sess.beginTransaction();
            Query query = sess.createQuery("delete  CollegePendingStatus where orgcode = '"+cps.getId()+"'");
            query.executeUpdate();
            sess.getTransaction().commit();
        }
        catch(Exception e){
            sess.getTransaction().rollback();
            e.printStackTrace();
        }
        finally{
            sess.close();
        }
    }
}
