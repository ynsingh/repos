/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.UserTask;

import java.util.ArrayList;
import java.sql.*;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.richfaces.component.UIDropDownMenu;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

/**
 *
 * @author ERP
 */
public class ReadUserTaskList {


    private String userName;
    private int orgCode;
    private String userTaskId;

    public String getUserTaskId() {
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext econtext =  facesContext.getExternalContext();
            HttpServletRequest response = (HttpServletRequest) facesContext.getExternalContext().getRequest();

            //UIDropDownMenu dm = facesContext.getViewRoot().getCompositeComponentParent(null)
            //String s = uIComponent.getClientId();
            //System.out.println("Component ID : "+s);
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("select task_id from user_task_list where user_id = '"+userName+"' and org_code = '"+orgCode+"'");
            ResultSet rst;
            //rst = pst.executeUpdate();
            //String id = econtext.getContext()
            return userTaskId;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public void setUserTaskId(String userTaskId) {
        this.userTaskId = userTaskId;
    }
    public ReadUserTaskList()
    {
        LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            if (le == null) {
                UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                orgCode = uf.getUserOrgCode();
                userName = uf.getUserName();
            } else {

            }
    }
    
}
