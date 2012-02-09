/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

public class ChangeWorkingStatusActionForm extends org.apache.struts.action.ActionForm {
    
    private String working_status;
    private String institute_name;
    private String institute_id;
    private int registration_request_id;

   
    public ChangeWorkingStatusActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }



    public String getWorking_status() {
        return working_status;
    }

   
    public void setWorking_status(String working_status) {
        this.working_status = working_status;
    }

 
    public int getRegistration_request_id() {
        return registration_request_id;
    }

 
    public void setRegistration_request_id(int registration_request_id) {
        this.registration_request_id = registration_request_id;
    }

  
    public String getInstitute_name() {
        return institute_name;
    }

 
    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

 
    public String getInstitute_id() {
        return institute_id;
    }

    
    public void setInstitute_id(String institute_id) {
        this.institute_id = institute_id;
    }
}
