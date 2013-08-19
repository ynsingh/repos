/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.user;

import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

/**
 *
 * @author ERP
 */
public class UserWellcomeName {

    public UserWellcomeName()
    {
        try {
            LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            if (le == null) {
                UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                userName = uf.getUserName();
                this.setUserName(userName); 
                System.out.println("DAta Should Be Write Here :  : --" +userName);
            } else {
                //orgCode = le.getUserOrgCode();
                System.out.println("DAta Should Be Write Here : 32142323 : " +userName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

}
