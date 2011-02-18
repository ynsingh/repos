/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.EmployeeType;

/**
 *
 * @author Algox
 */
public class EmployeeTypeDB {

    private PreparedStatement ps;
    private ResultSet rs;


     public void update(ArrayList<EmployeeType> grades)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update employee_type_master set emp_type_name=?"
                    + " where emp_type_id=?");
            for(EmployeeType sg : grades)
            {
                ps.setString(1, sg.getName().toUpperCase());
                ps.setInt(2, sg.getCode());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<EmployeeType> loadTypes()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from employee_type_master");
            rs=ps.executeQuery();
            ArrayList<EmployeeType> data = new ArrayList<EmployeeType>();
            while(rs.next())
            {
                EmployeeType dept = new EmployeeType();
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
    public Exception save(String dptName)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into employee_type_master(emp_type_name) values(?)",1);
            ps.setString(1, dptName.toUpperCase());
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
