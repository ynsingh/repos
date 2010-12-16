/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.smvdu.payroll.beans.InstituteProfile;

/**
 *
 * @author Algox
 */








public class OrgProfileDB {

    private PreparedStatement ps;
    private ResultSet rs;



    public void update(String name)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update org_profile set org_name=? ");
            ps.setString(1, name);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getProfile()
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select org_name from org_profile");
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

    public Exception save(InstituteProfile org)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into org_profile(org_name,org_tagline,org_logo) values(?,?,?)");
            ps.setString(1, org.getName());
            ps.setString(2, org.getTagLine());           
            FileInputStream fin = new FileInputStream(org.getTheFile());
            ps.setBinaryStream(3, fin,fin.available());
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            return e;
        }
    }

}
