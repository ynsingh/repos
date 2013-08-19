/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserOperationBeans;

import java.sql.*;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

/**
 *
 * @author ERP
 */
public class UserBeanDB {

    private int orgCode;

    public UserBeanDB() {
        LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if (le == null) {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            orgCode = uf.getUserOrgCode();
        } else {
            orgCode = le.getUserOrgCode();
        }

    }

    public Exception saveUser(UserBeans ub) {
        try {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into user_task_group(user_task_id,user_org_code,user_task_password,user_grp_id,user_task_flag) values('" + ub.getUserId().trim()+ "','"+orgCode+"','" +ub.getUserRePassword()+ "','"+4+"','"+1+"')");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex;
        }
    }

    public ArrayList<UserBeans> getUserId(Object obj) {
        try {
            ArrayList<UserBeans> user = new ArrayList<UserBeans>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select emp_name,emp_code from employee_master where emp_org_code = '"+orgCode+"' and emp_name like '"+obj.toString()+"%'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                UserBeans ub = new UserBeans();
                ub.setUserName(rst.getString(1));
                ub.setUserId(rst.getString(2));
                user.add(ub); 
            }
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean loginUser(UserBeans ub)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("select user_name,password from user_group where user_id = '"+ub.getUserId()+"' and password = '"+new EncryPassword().encryptPass(ub.getUserPassword())+"' and orgCode = '"+orgCode+"'");
            ResultSet rst;
            rst = pst.executeQuery();
            if(rst.next())
            {
                new UserBeans().setLogiDailog("Richfaces.hideModalPanel('pn1l')");
            }
            else
            {

            }
            rst.close();
            pst.close();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

}
