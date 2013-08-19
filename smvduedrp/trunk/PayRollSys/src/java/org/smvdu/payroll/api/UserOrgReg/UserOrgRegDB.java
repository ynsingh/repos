/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserOrgReg;
import java.sql.*;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author smvduerp
 */
public class UserOrgRegDB {
    public Exception save(UserOrgRegBeans kkr)
    {
        try
        {

             Connection c = new CommonDB().getConnection();
             PreparedStatement pst = null;
             //System.out.println("insert into user_registration(name,father_name,gender,email_id,dob,ph_number ,orgCode) values('"+kkr.getName()+"','"+kkr.getFatherName()+"','"+kkr.getGender()+"','"+kkr.getEmail()+"','"+kkr.getDateoffbirth()+"','"+kkr.getPhone()+"','"+kkr.getOrgCode()+"')");
             pst= c.prepareStatement("insert into user_registration(name,father_name,gender,email_id,dob,ph_number ,orgCode) values('"+kkr.getName()+"','"+kkr.getFatherName()+"','"+kkr.getGender()+"','"+kkr.getEmail()+"','"+kkr.getDateoffbirth()+"','"+kkr.getPhone()+"','"+kkr.getOrgCode()+"')");
            pst.executeUpdate();
            pst.close();
            c.close();
            return null;

        }
        catch(Exception rkk)
        {
            rkk.printStackTrace();
            return rkk;
        }

    }
    public boolean checkRegCode(UserOrgRegBeans uor)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            ResultSet rst;
            boolean flag;
            rst = cn.prepareStatement("select email_id,regCode from user_registration where email_id = '"+uor.getEmail()+"' and regCode = '"+uor.getRegCode()+"'").executeQuery();
            if(rst.next() == false)
            {
                flag=true;
            }
            else
            {
                flag= false;
            }
            rst.close();
            cn.close();
            return flag;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

}
