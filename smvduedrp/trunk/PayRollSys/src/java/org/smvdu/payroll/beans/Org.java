/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class Org {
    private PreparedStatement ps;
    private ResultSet rs;

    public String email;
    public String phone;
    public String web;
    public String address1;
    public String address2;
    public String tagLine;


    public Org getProfile()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from org_profile");
            rs=ps.executeQuery();
            rs.next();
            this.tagLine = rs.getString(3);
            this.email = rs.getString(4);
            this.web = rs.getString(5);
            this.phone = rs.getString(6);
            this.address1 = rs.getString(7);
            this.address2 = rs.getString(8);
            return this;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public void update()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps= c.prepareStatement("update org_rofile set org_tagline=?,org_email=?,org_web=?,org_phone=?,"
                    + "org_address1=?,org_address2=?");
            ps.setString(1, tagLine);
            ps.setString(2, email);
            ps.setString(3, web);
            ps.setString(4, phone);
            ps.setString(5, address1);
            ps.setString(6, address2);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
