/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserOrgReg;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;
import org.smvdu.payroll.beans.db.CommonDB;
/**
 *
 * @author smvduerp
 */
public class RandomCharGen {
private SecureRandom random = new SecureRandom();
    /** Creates a new instance of RandomCharGen */
    public RandomCharGen() {
    }
    public String nextSessionId(UserOrgRegBeans uor)
    {
        try
        {
            String ranCode = new BigInteger(65, random).toString(32).toUpperCase();
            String ran = null;
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            PreparedStatement pst1;
            ResultSet rst;
            pst = cn.prepareStatement("insert into user_reg_code(emailid,code,orgCode) values('"+uor.getEmail()+"','"+ranCode+"','"+uor.getOrgCode()+"')");
            pst1 = cn.prepareStatement("select emailid, code from user_reg_code where code = '"+ranCode+"' and emailid = '"+uor.getEmail()+"'");
            rst = pst1.executeQuery();
            if(rst.next() == false)
            {
                ran = ranCode;
                pst.executeUpdate();
            }
            rst.close();
            pst.close();
            cn.close();

            return ran;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}

