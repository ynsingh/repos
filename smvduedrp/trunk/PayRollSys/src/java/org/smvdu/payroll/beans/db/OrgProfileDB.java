/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.api.email.Mail;

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
            ps=c.prepareStatement("select org_id,org_name from org_profile");
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
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into org_profile(org_name,org_tagline,"
                    + "org_email,org_web,org_phone,org_address1,org_address2,"
                    + "org_master_password,org_recovery_id) "
                    + "values(?,?,?,?,?,?,?,password(?),aes_encrypt(?,'mysecretkey'))",1);
            ps.setString(1, org.getName());
            ps.setString(2, org.getTagLine());
            ps.setString(3, org.getEmail());
            ps.setString(4, org.getWeb());
            ps.setString(5, org.getPhone());
            ps.setString(6, org.getAddress1());
            ps.setString(7, org.getAddress2());
            ps.setString(8, org.getMasterPassword());
            ps.setString(9, org.getRecoveryEMailId());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            int code = rs.getInt(1);
            ps.close();
            c.close();
            UserInfo info = new UserInfo();
            info.setGroupCode(4);
            info.setUserName(org.getRecoveryEMailId());
            info.setPassword(org.getMasterPassword());
            UserGroup ug = new UserGroup();
            ug.setId(4);
            info.setGroupCode(4);
            info.setUserOrgCode(code);
            new UserDB().save(info);
            
            
            
            return null;
        }
        catch(Exception e)
        {
            return e;
        }
    }

}
