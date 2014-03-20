/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.db.CommonDB;

/**
 *
 * @author Shaista
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
     
    public boolean saveRequestStatus(OrgProfile org, String code)
    {
        try
        {
            connection = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = connection.prepareStatement("insert into college_pending_status(org_code,org_request_status,org_pen_email) values('"+code+"',0,'"+org.getOrgAdminemailid()+"')");
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
