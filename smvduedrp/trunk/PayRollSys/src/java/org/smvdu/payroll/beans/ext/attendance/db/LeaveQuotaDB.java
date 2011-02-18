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
import org.smvdu.payroll.beans.ext.attendance.LeaveQuota;

/**
 *
 * @author Algox
 */
public class LeaveQuotaDB {
    private PreparedStatement ps;
    private ResultSet rs;

    public void update(ArrayList<LeaveQuota> data,int type)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from leave_quota_master where lq_emp_type=?");
            ps.setInt(1, type);
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("insert into leave_quota_master values(?,?,?)");
            for(LeaveQuota lq:data)
            {
                ps.setInt(1, type);
                ps.setInt(2, lq.getLeaveType());
                ps.setInt(3, lq.getCount());
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
    public ArrayList<LeaveQuota> getQuota(int type)  {
        System.err.println(" >>>> Code "+type);
        try
        {
            String q = "select lt_id,lt_name,lq_count from leave_type_master left"
                    + " join leave_quota_master on lq_leave_type = lt_id where lq_emp_type=?";
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement(q);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            ArrayList<LeaveQuota> data = new ArrayList<LeaveQuota>();
            while(rs.next())
            {
                LeaveQuota lq = new LeaveQuota();
                lq.setLeaveType(rs.getInt(1));
                lq.setLeaveTypeName(rs.getString(2));
                lq.setCount(rs.getInt(3));
                //System.out.println(">>>> "+lq.getEmpTypename()+","+lq.getCount());
                data.add(lq);
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
