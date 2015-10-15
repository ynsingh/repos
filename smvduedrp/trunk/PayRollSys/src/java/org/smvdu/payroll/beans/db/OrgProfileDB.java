/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.smvdu.payroll.Admin.ServerDetails;
import org.smvdu.payroll.api.Administrator.CollegeRequestStatus;
import org.smvdu.payroll.api.EncryptionUtil;
import org.smvdu.payroll.api.email.Mail;
import org.smvdu.payroll.api.email.OrgConformationEmail;

import org.smvdu.payroll.beans.UserGroup;
import org.smvdu.payroll.beans.setup.Org;
import org.smvdu.payroll.beans.UserInfo;

public class OrgProfileDB {


    private PreparedStatement ps;
    private ResultSet rs;
    public Org loadOrgProfile(int code) {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from org_profile where org_id=?");
            ps.setInt(1, code);
            rs=ps.executeQuery();
            rs.next();
            Org o = new Org();
            o.setId(rs.getInt(1));
            o.setName(rs.getString(2));
            o.setTagLine(rs.getString(3));
            o.setEmail(rs.getString(4));
            o.setWeb(rs.getString(5));
            o.setPhone(rs.getString(6));
            o.setAddress1(rs.getString(7));
            o.setAddress2(rs.getString(8));
            o.setCity(rs.getString(13));
            o.setPincode(rs.getString(14));
            o.setState(rs.getString(15));
            rs.close();
            ps.close();
            c.close();
            return o;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public Org loadOrgProfileByName(String name) {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from org_profile where org_name=?");
            ps.setString(1, name);
            rs=ps.executeQuery();
            rs.next();
            Org o = new Org();
            o.setId(rs.getInt(1));
            o.setName(rs.getString(2));
            o.setTagLine(rs.getString(3));
            o.setEmail(rs.getString(4));
            o.setWeb(rs.getString(5));
            o.setPhone(rs.getString(6));
            o.setAddress1(rs.getString(7));
            o.setAddress2(rs.getString(8));
            o.setAdminfn(rs.getString(20));
            o.setAdminln(rs.getString(21));
            rs.close();
            ps.close();
            c.close();
            return o;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public ArrayList<Org> loadOrgs() {
        try
        {
            Connection c = new CommonDB().getConnection();
        //    ps=c.prepareStatement("select org_id,org_name from org_profile inner join user_master on org_email = user_name ");
            ps=c.prepareStatement("select org_id,org_name from org_profile where org_status = 1");
            rs=ps.executeQuery();
            ArrayList<Org> data = new ArrayList<Org>();
            while(rs.next())
            {
                Org o = new Org();
                o.setId(rs.getInt(1));
                o.setName(rs.getString(2));
                data.add(o);
            }
            rs.close();
            ps.close();
            c.close();
            System.err.println("Result Size : "+data.size());
            return data;            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void update(String name,int code)  {
        try
        {
            //System.out.println("update method is calling======");
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update org_profile set org_name=? where org_id=? ");
            ps.setString(1, name);
            ps.setInt(2, code);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getProfileName(int code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select org_name from org_profile where org_id=?");
            ps.setInt(1, code);
            rs=ps.executeQuery();
            rs.next();
            String s = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return s;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public Exception save(Org org)  {
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
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into org_profile(org_name,"
                    + "org_email,org_web,org_phone,org_address1,"
                    + "org_master_password, org_city, org_pincode, org_state, org_countrycode, org_institutedomain, org_toi, org_affiliation, org_adminfn, org_adminln, org_admindesig,org_status,org_reg_date) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'"+0+"','"+new java.sql.Date(dat.getTime())+"')",1);
            ps.setString(1, org.getName());
        //    ps.setString(2, org.getTagLine());
            ps.setString(2, org.getEmail());
            ps.setString(3, org.getWeb());
            ps.setString(4, org.getPhone());
            ps.setString(5, org.getAddress1());
           // ps.setString(7, org.getAddress2());
            
            ps.setString(6, new EncryptionUtil().createDigest("MD5",org.getMasterPassword()));
        //    ps.setString(9, org.getRecoveryEMailId());
         //   ps.setString(7,org.getTanno());
            ps.setString(7, org.getCity());
            ps.setInt(8, Integer.parseInt(org.getPincode()));
            ps.setString(9, org.getState());
           // ps.setInt(10, org.getLl());
            ps.setString(10, org.getCountryCode());
            //ps.setInt(12, org.getRegionCode());
            ps.setString(11, org.getInstDomain());
            ps.setString(12, org.getToi());
            ps.setString(13, org.getAffiliation());
            ps.setString(14, org.getAdminfn());
            ps.setString(15, org.getAdminln());
            ps.setString(16, org.getAdminDesig());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
           // System.out.println("rs===="+rs);
            int code = rs.getInt(1);
            //System.out.println("rs==code=="+code);
            ps.close();
            c.close();
            UserInfo info = new UserInfo();
            info.setGroupCode(4);
            info.setUserName(org.getEmail());
            info.setPassword(org.getMasterPassword());
            UserGroup ug = new UserGroup();
            ug.setId(4);    
            info.setGroupCode(4);
            info.setUserOrgCode(code);
            //new UserDB().save(info);
            new CollegeRequestStatus().saveRequestStatus(org,code,info); 
            new ServerDetails().saveServerDetail(org,code);
            //new OrgConformationEmail().sendPendingCollegeMail(org);
            return null;
        }
        catch(Exception e)
        {
            return e;
        }
    }

    
    public boolean updateFlagStatus(String emailid)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("update college_pending_status set org_request_status = '"+1+"' where org_pen_email= '"+emailid+"'");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean updatePendingStatus(String emailid)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("update user_master set flag = '"+1+"' where user_name= '"+emailid+"'");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
