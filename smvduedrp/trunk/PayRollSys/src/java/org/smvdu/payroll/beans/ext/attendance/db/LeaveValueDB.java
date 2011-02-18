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
import org.smvdu.payroll.beans.ext.attendance.LeaveValue;

/**
 *
 * @author Algox
 */
public class LeaveValueDB {

    private PreparedStatement ps;
    private ResultSet rs;


    public void update(ArrayList<LeaveValue> data)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update leave_value_master set lv_name=?,lv_value=? where lv_id=?");
            for(LeaveValue lv : data)
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
    public void save(LeaveValue lv)  {
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
    public ArrayList<LeaveValue> getAll()  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from leave_value_master");
            rs =ps.executeQuery();
            ArrayList<LeaveValue> data = new ArrayList<LeaveValue>();
            while(rs.next())
            {
                LeaveValue lv = new LeaveValue();
                lv.setCode(rs.getInt(1));
                lv.setName(rs.getString(2));
                lv.setValue(rs.getFloat(3));
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
