/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.NewUserOrgRegistration;
import java.sql.*;
import org.smvdu.payroll.beans.db.CommonDB;
/**
 *
 * @author ERP
 */
public class UserOrgRegBeansDB {

    public Exception saveUser(UserOrgRegBeans urb)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into user_registration(name,father_name,gender,email_id,dob,ph_number,orgCode) values('"+urb.getFullName()+"','"+urb.getFatherName()+"','"+urb.getGender()+"','"+urb.getEmailId()+"','"+urb.getDob()+"','"+urb.getPhNum()+"','"+urb.getOrgCode()+"')");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            return ex;
        }
    }
}
