/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class SalaryLockDB {

    private UserInfo user;

    public SalaryLockDB()
    {
        user =  user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }

    private PreparedStatement ps;
    private ResultSet rs;

    public boolean isLocked(String empCode)  {
        System.out.println("----------> Checking Lock Status...");
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sl_locked_on from salary_lock_master "
                    + " where sl_emp_code=? and sl_lock_month=? and sl_lock_year=?");
            ps.setString(1, empCode);
            ps.setInt(2, user.getCurrentMonth());
            ps.setInt(3, user.getCurrentYear());
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
