/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.Department;

/**
 *
 * @author Algox
 */
public class DepartmentDB {

    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<Department> loadDepartments()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from department_master");
            rs=ps.executeQuery();
            ArrayList<Department> data = new ArrayList<Department>();
            while(rs.next())
            {
                Department dept = new Department();
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
            ps=c.prepareStatement("insert into department_master(dept_name) values(?)",1);
            ps.setString(1, dptName);
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
