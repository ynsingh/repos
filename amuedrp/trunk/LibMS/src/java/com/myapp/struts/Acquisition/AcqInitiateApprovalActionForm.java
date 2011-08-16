/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author maqbool
 */
public class AcqInitiateApprovalActionForm extends org.apache.struts.action.ActionForm {
    
    private String approval_no;

    private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    /**
     * @return
     */
    

    /**
     *
     */
    public AcqInitiateApprovalActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }

    public String getApproval_no() {
        return approval_no;
    }

    public void setApproval_no(String approval_no) {
        this.approval_no = approval_no;
    }

    
}
