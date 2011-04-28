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
public class SubMember2ActionForm extends org.apache.struts.action.ActionForm {
    
    private String sub_emptype_id;
    private String emptype_id;

    public String getEmptype_id() {
        return emptype_id;
    }

    public void setEmptype_id(String emptype_id) {
        this.emptype_id = emptype_id;
    }

    private String button;
    private String emptype_full_name;

    public String getEmptype_full_name() {
        return emptype_full_name;
    }

    public void setEmptype_full_name(String emptype_full_name) {
        this.emptype_full_name = emptype_full_name;
    }
    

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getSub_emptype_id() {
        return sub_emptype_id;
    }

    public void setSub_emptype_id(String sub_emptype_id) {
        this.sub_emptype_id = sub_emptype_id;
    }
    

    
    public SubMember2ActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
