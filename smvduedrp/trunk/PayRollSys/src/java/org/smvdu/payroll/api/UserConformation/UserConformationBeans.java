/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserConformation;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.smvdu.payroll.beans.db.OrgProfileDB;

/**
 *
 * @author sumit
 */
public class UserConformationBeans {
    private String emailId;
    private boolean authenticate;
    private String imageUrl;
    private String requestUrl;
    private String userEvent;
    private String message;
    private String eventPage;
    
    public String redirect()
    {
        return "/Login.jsf";
    }
    
    public String getEmailId() {
        return emailId;
    }

    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getUserEvent() {
        return userEvent;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public UserConformationBeans() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        requestUrl = request.getRequestURL().toString();
        System.out.println("Url : "+requestUrl);
       boolean flag =  new OrgProfileDB().updatePendingStatus(emailId); 
       if(flag == true)
       {
           imageUrl = "success.png";
           message = "Your College Registration Is Successfuly Registered";
           eventPage = "Click Here For Login/Registration";
       }
       else
       {
           imageUrl = "err.png";
           message = "Try Again For College Registration ";
           eventPage = "Click Here For Login/Registration";
       }
    }

    public String getEventPage() {
        return eventPage;
    }

    public void setEventPage(String eventPage) {
        this.eventPage = eventPage;
    }
    
    
}
