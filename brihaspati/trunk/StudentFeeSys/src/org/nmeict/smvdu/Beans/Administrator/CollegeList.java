/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;
//import org.nmeict.smvdu.Beans.UserInfo;
import org.nmeict.smvdu.Beans.OrgLoginDetails;
import org.nmeict.smvdu.Beans.db.CommonDB;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.email.OrgConformationEmail;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;

/**
 *
 * @author Shaista
 */
public class CollegeList {
    private OrgLoginDetails old;
    private String userId;
   
     public CollegeList()
    {

        try
        {
            //Below 2 lines are commented by shaista
            //UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            //adminUserId = uf.getUserName();
             old = (OrgLoginDetails) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("org");
           // Org admin = (Org) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Organization Profile Bean");
            
            userId = old.getOrgId();
            //System.out.println("ID : "+userId);
        }
        catch(Exception ex)
        {

        }
    }
     
         /**
     * 
     * Active Admin List/Records
     * @return 
     */

    public ArrayList<OrgProfile> activeAdminList()
    {
        try
        {
            ArrayList<OrgProfile> adminBean = new ArrayList<OrgProfile>();
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select * from admin_records");
            rst = pst.executeQuery();
            while(rst.next())
            {
                OrgProfile admin = new OrgProfile();
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
                admin.setDate(rst.getString(5));
                adminBean.add(admin);
            }
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

public String getSMTPAuthDetails()
    {
      
        try
        {
            String authDetails;
            Connection cn = new CommonDB().getConnection();
            cn.setAutoCommit(false);
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select smtp_port,auth_emailid,auth_password,smtp_host_name from admin_smtp_details where smtp_status = '"+1+"'");
            rst = pst.executeQuery();
            if(rst.next() == true)
            {
                //System.out.print("\n\n Collegelist class=="+rst.getInt(1)+"-"+rst.getString(2)+"-"+rst.getString(3)+"-"+rst.getString(4));
                authDetails = rst.getInt(1)+"-"+rst.getString(2)+"-"+rst.getString(3)+"-"+rst.getString(4);
            }
            else
            {
                authDetails = "";
            }
            rst.close();
            pst.close();
            cn.commit();
            return authDetails ="";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
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
    /**
     * Insert the Admin Records of User Id and password
     * @param man
     * @return 
     * 
     */

    public boolean adminDB(OrgProfile man)
    {
        boolean blFlag = false;
        try
        {
            Date date = new Date();
            Date dat = new Date();
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("yy-MM-dd");

            /*int month = date.getMonth();
            int year = date.getYear();
            int day = date.getDate();*/
            String d = String.valueOf(date.getDate())+"-"+String.valueOf(date.getMonth())+"-"+String.valueOf(date.getYear());
            //System.out.println("\n\nd----"+d);
            dat = (java.util.Date) dateFormat.parse(d);
             //System.out.println("\ndat============="+dat);
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
               /*         
            if(!man.getAdUserId().isEmpty() && !man.getAdPassword().isEmpty()){
                    
                pst = cn.prepareStatement("insert into admin_records(user_id,admin_pass,flag,add_date) values('"+man.getAdUserId()+"','"+man.getAdPassword()+"','"+0+"','"+new java.sql.Date(dat.getTime())+"')");
                pst.executeUpdate();
                cn.close();
                blFlag = true; 
            }
            */
            return blFlag;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return blFlag;
            //return ex;
        }
    }
    
    /**
    * Update Admin Status which Active or Inactive
    * @param admin
    * @return 
    * 
    */

    public Exception updateAdminStatus(ArrayList<OrgProfile> admin)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = null;
            int st;
            for(OrgProfile adm : admin)
            {
                //System.out.println("\n\n CollegeLsit Classs ID : "+adm.getAdUserId()+"\t adm.isStatus()=="+adm.isStatus());
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
     * @Load All Pending Details of College List Org Name, Org Id, Org Request Status, Pending Status 
     * @return  
     */

    public ArrayList<OrgProfile> getPendingCollegeList()
    {
        try
        {

            ArrayList<OrgProfile> pendingList = new ArrayList<OrgProfile>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select org_id,org_name,org_web,org_email,org_phone,org_request_status from org_profile inner join college_pending_status on org_email=org_pen_email where org_request_status = 0");
            rst = pst.executeQuery();
            while(rst.next())
            {
                OrgProfile o = new OrgProfile();
                //System.out.println("\n\nOrgID==="+rst.getString(1)+"\tOrgName =="+rst.getString(2)+"\t OrgWeb=="+rst.getString(3)+"\t OrgEmail=="+rst.getString(4)+"\tOrgPhone== "+rst.getString(5));
                o.setOrgId(rst.getString(1));
                o.setOrgName(rst.getString(2));
                o.setOrgWeb(rst.getString(3));
                o.setOrgEmail(rst.getString(4));
                o.setOrgPhone(rst.getString(5));
                
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
                pendingList.add(o);
            }
            pst.close();
            rst.close();
            cn.close();
            //System.out.println("\npendingList=== "+pendingList.size());
            return pendingList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     * Update/Insert details for User to access the Payroll with Particular Task 
     * @param org
     * @return 
     * 
     */

    public Exception updateRequest(ArrayList<OrgProfile> org)
    {
        try
        {
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst = null;
            PreparedStatement pst1 = null;
            PreparedStatement pst2 = null;
            ResultSet rst = null;
            String password = null;
            
            for(OrgProfile or : org)
            {
                if(or.isStatus() == true)
                {
                    //System.out.println("or.getOrgEmail()=="+or.getOrgEmail());
                    pst2 = connection.prepareStatement("select org_master_password from org_profile where org_email = '"+or.getOrgEmail()+"'");
                    rst = pst2.executeQuery();
                    if(rst.next())
                    {
                        password = rst.getString(1);
                       // System.out.println("password=="+password);
                    }
                    rst.close();
                    pst2.close();
                    connection.close();
                    /*
                    connection = new CommonDB().getConnection();
                    pst1 = connection.prepareStatement("insert into user_master(user_name,user_pass,user_org_id,user_grp_id,modifier_id,user_profile_id,flag) values('"+or.getOrgEmail()+"','"+password+"','"+or.getOrgId()+"',4,101,0,1)");
                    pst1.executeUpdate();
                    pst1.clearParameters();
                    connection.close();*/
                    
                    //new OrgConformationEmail().sendMail(or);
                    connection = new CommonDB().getConnection();
                    pst = connection.prepareStatement("update college_pending_status set org_request_status = 1 where org_pen_email = '"+or.getOrgEmail()+"'");
                    //pst = connection.prepareStatement("delete from college_pending_status where org_pen_email = '"+or.getOrgEmail()+"'");
                    pst.executeUpdate();
                    pst.clearParameters();
                }
            }
            pst.close();
            //pst1.close();

            connection.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
    }

    
}
