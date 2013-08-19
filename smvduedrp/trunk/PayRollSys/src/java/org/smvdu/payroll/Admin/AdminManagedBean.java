/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.Admin;

import java.io.IOException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.smvdu.payroll.api.Administrator.CollegeList;

/**
 *
 * @author KESU
 */

public class AdminManagedBean {

    /**
     * Creates a new instance of AdminManagedBean
     */
    public AdminManagedBean() {
    }
    private UIData dataGrid;
    private String userId;
    private String password;
    private String rePassword;
    private boolean status;
    private String imgUrl;
    private String date;
    private boolean imgerDraw;

    public boolean isImgerDraw() {
        imgerDraw = new CollegeList().isPending();
        return imgerDraw;
    }

    public void setImgerDraw(boolean imgerDraw) {
        this.imgerDraw = imgerDraw;
    }
    private ArrayList<AdminManagedBean> adminList;
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<AdminManagedBean> getAdminList() {
        try
        {
//            adminList = new CollegeList().activeAdminList();
            dataGrid.setValue(adminList); 
            return adminList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public void setAdminList(ArrayList<AdminManagedBean> adminList) {
        this.adminList = adminList;
    }
    
    /*public void changePassword(){
        try
        {
            System.out.println("User ID : "+this.getUserId());
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            if(this.getPassword().equals(this.getRePassword()) == false)
            {
                 message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("Password Are Not Matching.....Please Try Again.");
                 fc.addMessage("", message);
                 return;
            }
            if(new CollegeList().changePass(this) == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                 message.setSummary("Password Are Updated");
                 fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                 message.setSummary("Error...");
                 fc.addMessage(""+new CollegeList().changePass(this), message); 
            }
        }
        catch(Exception ex)
        {
            
        }
    }*/
   /* public void save()
    {
        try
        {
            System.out.println("Addeing.............");
//            Exception ex = new CollegeList().adminDB(this);
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "New Admin Is Added : "+this.getUserId(), ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }*/
    
/*    public void updateAdmin()
    {
        try
        {
            System.out.println("Up Datong....");
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            ArrayList<AdminManagedBean> admin = (ArrayList<AdminManagedBean>) dataGrid.getValue();
            for(AdminManagedBean ad : admin)
            {
                System.out.println("Klop : "+ad.getUserId()+" : Iolp : "+ad.getDate());
            }
            Exception ex = new CollegeList().updateAdminStatus(admin);
            if(ex == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                 message.setSummary("Status Updated...");
                 fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                 message.setSummary("Status Not Updated.....PleaseTry Again");
                 fc.addMessage(""+ex , message);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }*/
   /* public String admin()
    {
        try
        {
            if(new CollegeList().featchDetails(this) == true)
            {
                return "Admin.jsf";
            }
            else
            {
                return "adminLogin.jsf";
            }
        }
        catch(Exception ex)
        {
            return "adminLogin.jsf";
        }
    }*/
    public void logout() throws IOException {
        //ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.jsf");  
    }
    public String logOut()
    {
        try
        {
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            ectx.invalidateSession();
            System.out.println("Hi......");
            return "adminLogin.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    
}}
