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
public class AddSubMemberActionForm extends org.apache.struts.action.ActionForm {
    
    private String sub_emptype_id;
    private String sub_emptype_full_name;
    private String emptype_full_name;
    private String no_of_issueable_book;
    private String emptype_id;

    public String getEmptype_id() {
        return emptype_id;
    }

    public void setEmptype_id(String emptype_id) {
        this.emptype_id = emptype_id;
    }

    public String getNo_of_issueable_book() {
        return no_of_issueable_book;
    }

    public void setNo_of_issueable_book(String no_of_issueable_book) {
        this.no_of_issueable_book = no_of_issueable_book;
    }

    

    public String getEmptype_full_name() {
        return emptype_full_name;
    }

    public void setEmptype_full_name(String emptype_full_name) {
        this.emptype_full_name = emptype_full_name;
    }



    public String getSub_emptype_full_name() {
        return sub_emptype_full_name;
    }

    public void setSub_emptype_full_name(String sub_emptype_full_name) {
        this.sub_emptype_full_name = sub_emptype_full_name;
    }

    public String getSub_emptype_id() {
        return sub_emptype_id;
    }

    public void setSub_emptype_id(String sub_emptype_id) {
        this.sub_emptype_id = sub_emptype_id;
    }


    
    public AddSubMemberActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
