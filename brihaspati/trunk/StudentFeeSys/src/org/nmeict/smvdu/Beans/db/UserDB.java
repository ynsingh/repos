/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author guest
 */
public class UserDB {
    public int validate(String user, String pass, String userCode)  {
        String empCode =null;
        try{
            Connection c = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            
            pst = c.prepareStatement("select user_id,admin_pass,flag from admin_records where user_id='"+user+"' and admin_pass='"+pass+"' and flag='"+1+"'");;
            rst = pst.executeQuery();
            if(rst.next() == true)
            {
                return 2;
            }
            else{
                pst = c.prepareStatement("select  org_code, org_pen_email from  college_pending_status where org_request_status='"+0+"' and org_code='"+userCode+"' and org_pen_email='"+user+"'");;
                rst = pst.executeQuery();
                if(rst.next() == true)
                {
                    return 3;
                }
                else
                    return -1;
            }
                
                
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
