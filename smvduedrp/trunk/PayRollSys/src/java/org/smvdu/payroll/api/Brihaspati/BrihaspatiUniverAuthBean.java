/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.Brihaspati;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author smvdu
 */
public class BrihaspatiUniverAuthBean {

    /** Creates a new instance of BrihaspatiUniverAuthBean */
    public BrihaspatiUniverAuthBean() {
    }

    private String emailId;
    private String returnUrl = new String();
    
    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void brihaspatiAuth()
    {
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            System.out.println(request.getContextPath()+"?email="+this.getEmailId()+"&context="+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
             new  BrihaspatiUniverAuth().brihaspatiAuth(this);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
