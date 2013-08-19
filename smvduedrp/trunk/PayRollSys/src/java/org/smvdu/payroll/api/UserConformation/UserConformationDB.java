/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserConformation;
import java.sql.*;
import org.smvdu.payroll.beans.db.CommonDB;
/**
 *
 * @author sumit
 */
public class UserConformationDB {
    private Connection cn;
    public String getConformationText(String emailId)
    {
        try
        {
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("update user_master set flag='"+1+"' where user_name = '"+emailId+"'");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return "success";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
