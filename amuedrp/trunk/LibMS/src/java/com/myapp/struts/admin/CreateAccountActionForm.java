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
 * @author Dushyant
 */
public class CreateAccountActionForm extends org.apache.struts.action.ActionForm {
    
    private String user_name;
    private String staff_id;
    private String password;
    private String email_id;
    private String button;

    /**
     * @return
     */


    /**
     *
     */
    public CreateAccountActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
   
            // TODO: add 'error.name.required' key to your resources

    return null;
    }
    

    /**
     * @return the first_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setUser_name(String first_name) {
        this.user_name = first_name;
    }

    /**
     * @return the last_name
     */

    public String getStaff_id() {
        return staff_id;
    }

    /**
     * @param staff_id the staff_id to set
     */
    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
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

    /**
     * @return the email_id
     */
    public String getEmail_id() {
        return email_id;
    }

    /**
     * @param email_id the email_id to set
     */
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    /**
     * @return the button
     */
    public String getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(String button) {
        this.button = button;
    }
}
