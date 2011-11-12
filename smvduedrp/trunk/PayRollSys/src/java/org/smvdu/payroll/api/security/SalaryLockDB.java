/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
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
public class SalaryLockDB {

    private UserInfo user;

    public SalaryLockDB()
    {
        user =  user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }

    private PreparedStatement ps;
    private ResultSet rs;

    public void doUnLock()
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_lock_master where sl_lock_month=? and sl_lock_year=?");
            ps.setInt(1, user.getCurrentMonth());
            ps.setInt(2, user.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salary Unlocked!", ""));
        }
        catch(Exception e)
        {
            e.printStackTrace();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Locking Failed", ""));
        }
    }

    public void doLock()
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_lock_master where sl_lock_month=? and sl_lock_year=?");
            ps.setInt(1, user.getCurrentMonth());
            ps.setInt(2, user.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into salary_lock_master values(?,?,?)");
            ps.setInt(1, user.getCurrentMonth());
            ps.setInt(2, user.getCurrentYear());
            ps.setBoolean(3, true);
            ps.executeUpdate();
            ps.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salary Locked!", ""));
        }
        catch(Exception e)
        {
            e.printStackTrace();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Locking Failed", ""));
        }
    }

    public boolean isLocked()  {
        System.out.println("----------> Checking Lock Status...");
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sl_status from salary_lock_master "
                    + " where  sl_lock_month=? and sl_lock_year=?");
            ps.setInt(1, user.getCurrentMonth());
            ps.setInt(2, user.getCurrentYear());
            rs=ps.executeQuery();
            boolean status = false;
            if(rs.next())
            {
                status = true;
            }
            rs.close();
            ps.close();
            c.close();
            return status;

            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
