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
public class ChangeWorkingStatusActionForm extends org.apache.struts.action.ActionForm {
    
    private String working_status;
    private String library_name;
    private String library_id;
    private int registration_request_id;

   
    public ChangeWorkingStatusActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
    
     */


    /**
     * @return the working_status
     */
    public String getWorking_status() {
        return working_status;
    }

    /**
     * @param working_status the working_status to set
     */
    public void setWorking_status(String working_status) {
        this.working_status = working_status;
    }

    /**
     * @return the registration_request_id
     */
    public int getRegistration_request_id() {
        return registration_request_id;
    }

    /**
     * @param  registration_request_id to set
     */
    public void setRegistration_request_id(int registration_request_id) {
        this.registration_request_id = registration_request_id;
    }

    /**
     * @return the library_name
     */
    public String getLibrary_name() {
        return library_name;
    }

    /**
     * @param library_name the library_name to set
     */
    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    /**
     * @return the library_id
     */
    public String getLibrary_id() {
        return library_id;
    }

    /**
     * @param library_id the library_id to set
     */
    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }
}
