/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserOperationBeans;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.smvdu.payroll.beans.UserInfo;

/**
 *
 * @author ERP
 */
public class UserBeans {

    /** Creates a new instance of UserBeans */
    UserInfo userBean = new UserInfo();
    private int orgCode;
    public UserBeans() {
        userBean = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = userBean.getUserOrgCode();
    }
    private String userId;
    private String userPassword;
    private String userRePassword;
    private String userName;
    private String logiDailog = new String();

    public String getLogiDailog() {
        System.out.println("DAta Should Be Write Here " +logiDailog);
        return logiDailog;
    }

    public void setLogiDailog(String logiDailog) {
        this.logiDailog = logiDailog;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    private int noti;

    public int getNoti() {
        return noti;
    }

    public void setNoti(int noti) {
        this.noti = noti;
    }

    public String getUserId() {
        System.out.println("DAta Should Be Write Here : "+userId);
        return userId;
    }

    public void setUserId(String userId) {
        System.out.println("DAta Should Be Write Here in set : "+userId);
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRePassword() {
        return userRePassword;
    }

    public void setUserRePassword(String userRePassword) {
        this.userRePassword = userRePassword;
    }
    
    //public ArrayList<UserBe>
    public void save() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest response = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            System.out.println("Remot Address : "+response.getRemoteAddr());
            if (this.getUserPassword().equals(this.getUserRePassword()) == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Correct Password ");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }
            Exception b = new UserBeanDB().saveUser(this);
            if (b == null) {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " User Id : " + this.getUserId(), " Saved"));
            } else {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, " " + b, ""));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<UserBeans> getSuggestion(Object obj)
    {
        try
        {
            return new UserBeanDB().getUserId(obj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public void login()
    {
        try
        {
            boolean b = new UserBeanDB().loginUser(this);
            if(b == true)
            {
                
                
            }
            else
            {
                //new UserBeans().setLogiDailog("Richfaces.showModalPanel('pn1l')");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
