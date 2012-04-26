/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class BarBookInformationActionForm extends org.apache.struts.action.ActionForm {
    
    private String accession_no;
    private String title;
    private String call_no;

    public String getAccession_no() {
        return accession_no;
    }

    public void setAccession_no(String accession_no) {
        this.accession_no = accession_no;
    }

    public String getCall_no() {
        return call_no;
    }

    public void setCall_no(String call_no) {
        this.call_no = call_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

      
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
