/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class MemberActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTMEMID;

    /**
     *
     */
    public MemberActionForm() {
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
        ActionErrors errors = new ActionErrors();
        if (getTXTMEMID() == null || getTXTMEMID().length() < 1) {
            //errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }

    /**
     * @return the TXTMEMID
     */
    public String getTXTMEMID() {
        return TXTMEMID;
    }

    /**
     * @param TXTMEMID the TXTMEMID to set
     */
    public void setTXTMEMID(String TXTMEMID) {
        this.TXTMEMID = TXTMEMID;
    }
}
