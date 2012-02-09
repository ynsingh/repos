/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class AccountActionForm extends org.apache.struts.action.ActionForm {
    private String staff_id;
    private String button;
    private String institute_id;
private String role;
   
    /**
     *
     */
    public AccountActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
       
        return errors;
    }

    /**
     * @return the staff_id
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

    /**
     * @return the institute_id
     */
    public String getInstitute_id() {
        return institute_id;
    }

    /**
     * @param institute_id the institute_id to set
     */
    public void setInstitute_id(String institute_id) {
        this.institute_id = institute_id;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
