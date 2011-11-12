/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.module.attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.CommonDB;

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
public class EmployeeLoginController {
    
    private PreparedStatement ps;
    private ResultSet rs;





    public boolean changePassword(String empId,String newPass)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update employee_login_master set el_password=? where "
                    + "el_login_name=?");
            ps.setString(1, newPass);
            ps.setString(2, empId);
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void invalidate(String empCode,String date)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update attendance_master set att_time_out=now() "
                    + "where att_emp_id=? and att_date=?");
            ps.setString(1, empCode);
            ps.setString(2, date);
            ps.executeUpdate();
            ps.close();
            c.close();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public String validate(String user,String password,int orgId)  {
        try
        {
            String empCode = null;
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select el_id,att_id from employee_login_master "
                    + "left join attendance_master on att_emp_id = el_id and att_date=now()"
                    + "  where el_login_name=? and el_password=? and el_org_id=?");
            ps.setString(1, user);
            ps.setString(2, password);
            ps.setInt(3, orgId);
            rs=ps.executeQuery();
            if(rs.next())
            {   
                String code = rs.getString(1);
                int markId = rs.getInt(2);
                System.out.println("Emp Code : "+code+", Mark ID "+markId);
                empCode=code;
                if(markId<=0)
                {
                    ps.close();
                    ps=c.prepareStatement("insert into attendance_master(att_emp_id,"
                        + "att_date,att_time_in,att_time_out) values(?,now(),(now()),(now()))");
                    ps.setString(1, code);
                    ps.executeUpdate();
                }
            }
            rs.close();
            ps.close();
            c.close();
            return empCode;
        }
        catch(Exception e)
        {
            
            e.printStackTrace();
            return null;
        }
    }
    

}
