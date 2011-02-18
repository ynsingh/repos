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

    public Department convert(String code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select dept_code,dept_name from department_master where dept_name=?");
            ps.setString(1, code);
            rs =ps.executeQuery();
            rs.next();
            Department d = new Department();
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
    public void update(ArrayList<Department> depts)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update department_master set dept_name=? where dept_code=?");
            for(Department dp : depts)
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
            //e.printStackTrace();
            return null;
        }
    }
    public Exception save(String dptName)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into department_master(dept_name) values(?)",1);
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
            //e.printStackTrace();
            return e;
        }

    }

}
