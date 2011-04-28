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
    private String institute_name;
    private String institute_id;
    private int registration_request_id;

   
    public ChangeWorkingStatusActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
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
     * @param the registration_request_id to set
     */
    public void setRegistration_request_id(int registration_request_id) {
        this.registration_request_id = registration_request_id;
    }

    /**
     * @return the library_name
     */
    public String getInstitute_name() {
        return institute_name;
    }

    /**
     * @param library_name the library_name to set
     */
    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

    /**
     * @return the library_id
     */
    public String getInstitute_id() {
        return institute_id;
    }

    /**
     * @param library_id the library_id to set
     */
    public void setInstitute_id(String institute_id) {
        this.institute_id = institute_id;
    }
}
