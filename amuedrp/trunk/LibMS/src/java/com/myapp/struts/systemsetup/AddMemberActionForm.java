/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class AddMemberActionForm extends org.apache.struts.action.ActionForm {
    
    private String emptype_id;
    private String emptype_full_name;

    public String getEmptype_full_name() {
        return emptype_full_name;
    }

    public void setEmptype_full_name(String emptype_full_name) {
        this.emptype_full_name = emptype_full_name;
    }

    public String getEmptype_id() {
        return emptype_id;
    }

    public void setEmptype_id(String emptype_id) {
        this.emptype_id = emptype_id;
    }
    

    public AddMemberActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
