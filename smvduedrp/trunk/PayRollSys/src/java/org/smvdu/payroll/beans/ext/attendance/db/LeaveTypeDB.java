/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.ext.attendance.LeaveType;
import org.smvdu.payroll.beans.ext.attendance.LeaveValue;

/**
 *
 * @author Algox
 */
public class LeaveTypeDB {

    private PreparedStatement ps;
    private ResultSet rs;


    public void update(ArrayList<LeaveType> data)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update leave_value_master set lv_name=?,lv_value=? where lv_id=?");
            for(LeaveType lv : data)
            {
                ps.setString(1, lv.getName());
                ps.setFloat(2, lv.getValue());
                ps.setInt(1, lv.getCode());
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
    public void save(LeaveType lv)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into leave_value_master(lv_name,lv_value) values(?,?)");
            ps.setString(1, lv.getName());
            ps.setFloat(2, lv.getValue());
            ps.executeUpdate();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<LeaveType> getAll()  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from leave_type_master");
            rs =ps.executeQuery();
            ArrayList<LeaveType> data = new ArrayList<LeaveType>();
            while(rs.next())
            {
                LeaveType lv = new LeaveType();
                lv.setCode(rs.getInt(1));
                lv.setName(rs.getString(2));
                data.add(lv);
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

}
