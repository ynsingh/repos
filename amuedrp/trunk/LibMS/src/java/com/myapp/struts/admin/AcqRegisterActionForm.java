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
public class AcqRegisterActionForm extends org.apache.struts.action.ActionForm {
    
    private String staff_id;
    private String button;
    private String library_id;
    private String staff_name;
    private String sublibrary_id;

    

  
    public String getStaff_id() {
        return staff_id;
    }

    /**
     * @param string
     */
    public void setStaff_id(String string) {
        staff_id = string;
    }

     public String getButton() {
        return button;
    }

    /**
     * @param string
     */
    public void setButton(String string) {
        button = string;
    }

     public String getLibrary_id() {
        return library_id;
    }

    /**
     * @param string
     */
    public void setLibrary_id(String string) {
        library_id = string;
    }
    
 

   

    /**
     *
     */
    public AcqRegisterActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       // ActionErrors errors = new ActionErrors();
        //if (getStaff_id() == null ) {
           // errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        //}
        return null;
    }

    /**
     * @return the staff_name
     */
    public String getStaff_name() {
        return staff_name;
    }

    /**
     * @param staff_name the staff_name to set
     */
    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    /**
     * @return the sublibrary_id
     */
    public String getSublibrary_id() {
        return sublibrary_id;
    }

    /**
     * @param sublibrary_id the sublibrary_id to set
     */
    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }
}
