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
 * @author edrp02
 */
public class ReportActionForm extends org.apache.struts.action.ActionForm {
    
   private String library_id;
   private String library_name,staff_id;
   private String vendor_id;
   
    public ReportActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
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
     * @return the vendor_id
     */
    public String getVendor_id() {
        return vendor_id;
    }

    /**
     * @param vendor_id the vendor_id to set
     */
    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }
}
