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
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
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
