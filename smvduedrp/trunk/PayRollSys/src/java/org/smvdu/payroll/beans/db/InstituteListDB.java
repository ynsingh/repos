/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
//import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.UserInfo;

/**
 *
 * @author yuvraj
 */
public class InstituteListDB {
    
    private UserInfo userBean;
    
    public InstituteListDB() {
        userBean = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }    
    
    public ArrayList<UserInfo> loadInstituteDetail(){   
        
        try
        {
            ArrayList<UserInfo> insDetail = new ArrayList<UserInfo>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
        //    pst  = cn.prepareStatement("select org_id, org_name from org_profile left join user_master on org_id = user_org_id where user_name = '"+userBean.getUserName()+"'");
            pst  = cn.prepareStatement("select org_id, org_name from org_profile where org_email = '"+userBean.getUserName()+"' and org_status = '"+1+"'");
          
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                UserInfo ins = new UserInfo();
                ins.setUserOrgCode(rst.getInt(1));
                ins.setOrgName(rst.getString(2));
                insDetail.add(ins);
            }
            rst.close();
            pst.close();
            cn.close();
            return insDetail;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        } 
    }
}
