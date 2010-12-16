/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.SalaryProfile;

/**
 *
 * @author Algox
 */
public class SalaryProfileDB {

    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<SalaryProfile> loadProfiles()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from salary_profile_master");
            rs=ps.executeQuery();
            ArrayList<SalaryProfile> data = new ArrayList<SalaryProfile>();
            while(rs.next())
            {
                SalaryProfile dept = new SalaryProfile();
                dept.setNumber(rs.getInt(1));
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
    public Exception save(String dptName)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into salary_profile_master(sp_name) values(?)",1);
            ps.setString(1, dptName);
            ps.executeUpdate();
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
