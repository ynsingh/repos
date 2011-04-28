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
 * @author edrp01
 */
public class BookCategoryDecideActionForm1 extends org.apache.struts.action.ActionForm {
    
   
       private String emptype_id;
       private String sub_emptype_id;
       private String book_type;
       private String button;


       private String full_name;
       private String fineRs;
       private String finePs;
       private String permitday;

    public String getPermitday() {
        return permitday;
    }

    public void setPermitday(String permitday) {
        this.permitday = permitday;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

   
    public String getFinePs() {
        return finePs;
    }

    public void setFinePs(String finePs) {
        this.finePs = finePs;
    }

    public String getFineRs() {
        return fineRs;
    }

    public void setFineRs(String fineRs) {
        this.fineRs = fineRs;
    }

   


    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getEmptype_id() {
        return emptype_id;
    }

    public void setEmptype_id(String emptype_id) {
        this.emptype_id = emptype_id;
    }

    public String getSub_emptype_id() {
        return sub_emptype_id;
    }

    public void setSub_emptype_id(String sub_emptype_id) {
        this.sub_emptype_id = sub_emptype_id;
    }
    /**
     * @return
     */
   
    /**
     * @param i
     */
    
    /**
     *
     */
    public BookCategoryDecideActionForm1() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
   
}
