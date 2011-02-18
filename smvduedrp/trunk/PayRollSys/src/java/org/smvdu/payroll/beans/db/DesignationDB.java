/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.Designation;

/**
 *
 * @author Algox
 */
public class DesignationDB {

    private PreparedStatement ps;
    private ResultSet rs;


    public Designation convert(String code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select desig_code,desig_name from designation_master where desig_name=?");
            ps.setString(1, code);
            rs =ps.executeQuery();
            rs.next();
            Designation d = new Designation();
            d.setCode(rs.getInt(1));
            d.setName(rs.getString(2));
            rs.close();
            ps.close();
            c.close();
            return d;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void update(ArrayList<Designation> depts)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update designation_master set desig_name=? where desig_code=?");
            for(Designation dp : depts)
            {
                ps.setString(1, dp.getName().toUpperCase());
                ps.setInt(2, dp.getCode());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            //Logger.getAnonymousLogger().log(Log., e.getMessage());
        }
    }
    public ArrayList<Designation> loadDesignations()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from designation_master");
            rs=ps.executeQuery();
            ArrayList<Designation> data = new ArrayList<Designation>();
            while(rs.next())
            {
                Designation dept = new Designation();
                dept.setCode(rs.getInt(1));
                dept.setName(rs.getString(2));
                data.add(dept);
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public Exception save(String desigName)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into designation_master(desig_name) values(?)",1);
            ps.setString(1, desigName.toUpperCase());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }

    }

}
