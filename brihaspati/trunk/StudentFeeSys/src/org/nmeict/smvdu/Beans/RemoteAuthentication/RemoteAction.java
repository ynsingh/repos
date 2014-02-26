/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.RemoteAuthentication;

//import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
//import org.iitk.brihaspati.modules.utils.security.RemoteAuth;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;

@ManagedBean (name="remoteActionBeans")
@SessionScoped
/**
 *
 * @author Shaista Bano
 */


public class RemoteAction {
    
    public void RemoteAction(){}
    
    private String emailId;
    
    public String getEmailId() {
        return this.emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    

    public void chkAuthenticate(){
    //public String chkAuthenticate(){
         
    try{
        emailId = this.getEmailId();
        System.out.print("\n\nEmail==="+emailId);
   
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String sourceid = "student_fees";
         ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
         
         String resp = RemoteAuth.AuthR(emailId,request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/faces/RemoteAuthentication/VerifiedLoginPage.xhtml","student_fees");
         //String resp = RemoteAuth.AuthR(emailId,request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/faces/RemoteAuthentication/VerifiedLoginPage.xhtml?action=VerifiedLoginPage","student_fees");
        //String resp = RemoteAuth.AuthR(emailId,request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"","student_fees");
    
        System.out.print("\n\nresp=="+resp);
        
    
        //HttpServletResponse response = ((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse());
        //response.sendRedirect(resp);
        //response.sendRedirect(request.getContextPath()+"/faces/success.jsp"); );
        //return "RemoteAction.xhtml?faces-redirect=true";
        
        context.redirect(resp);
        //return;
        
    }
    catch (Exception e) {
        //return "Brihaspati Server seems to be unreachable or there is some other problem. Actual exception is: " + e.getMessage();
       
    }
  
  }
       
}

