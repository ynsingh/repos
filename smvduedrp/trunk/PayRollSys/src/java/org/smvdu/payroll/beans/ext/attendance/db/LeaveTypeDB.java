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
