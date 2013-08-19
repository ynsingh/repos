/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.Administrator;
import java.sql.*;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.setup.Org;
/**
 *
 * @author KESU
 */
public class CollegeRequestStatus {
    private Connection connection;
   
    /**
     * 
     * Insert College Pending Status 
     * @param org
     * @param code
     * @param info
     * @return 
     */
    public boolean saveRequestStatus(Org org,int code,UserInfo info)
    {
        try
        {
            connection = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = connection.prepareStatement("insert into college_pending_status(org_code,org_request_status,org_pen_email) values('"+code+"','"+0+"','"+org.getEmail()+"')");
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
