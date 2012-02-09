/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Edrp-04
 */
public class EmailSetupActionForm extends org.apache.struts.action.ActionForm {
    
    private String emailid;
private String repass;
    private String password;

    
  

    /**
     * @return the emailid
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * @param emailid the emailid to set
     */
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public EmailSetupActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the repass
     */
    public String getRepass() {
        return repass;
    }

    /**
     * @param repass the repass to set
     */
    public void setRepass(String repass) {
        this.repass = repass;
    }

}
