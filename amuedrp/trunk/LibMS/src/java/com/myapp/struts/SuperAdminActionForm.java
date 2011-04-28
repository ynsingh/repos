/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author System Administrator
 */
public class SuperAdminActionForm extends org.apache.struts.action.ActionForm {
    
    private String user_id1;
    private String user_id2;

    private String password1;
    private String password2;

  //  private String role;
    //private String staff_id;
    

  
    /**
     *
     */
    public SuperAdminActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
    
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }

   
    /**
     * @return the user_id1
     */
    public String getUser_id1() {
        return user_id1;
    }

    /**
     * @param user_id1 the user_id1 to set
     */
    public void setUser_id1(String user_id1) {
        this.user_id1 = user_id1;
    }

    /**
     * @return the user_id2
     */
    public String getUser_id2() {
        return user_id2;
    }

    /**
     * @param user_id2 the user_id2 to set
     */
    public void setUser_id2(String user_id2) {
        this.user_id2 = user_id2;
    }

    /**
     * @return the password1
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * @param password1 the password1 to set
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2 the password2 to set
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
