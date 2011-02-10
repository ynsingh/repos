/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Faraz
 */
public class NewArrivalActionForm extends org.apache.struts.action.ActionForm {
    
    private String r;

    private String CMBPERIOD;
    private String CMBLib;
    
    /**
     *
     */
    public NewArrivalActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
    
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getR() == null || getR().length() < 1) {   
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }

   
    public String getR() {
        return r;
    }

   
    public void setR(String r) {
        this.r = r;
    }

    /**
     * @return the CMBPERIOD
     */
    public String getCMBPERIOD() {
        return CMBPERIOD;
    }

    /**
     * @param CMBPERIOD the CMBPERIOD to set
     */
    public void setCMBPERIOD(String CMBPERIOD) {
        this.CMBPERIOD = CMBPERIOD;
    }
    public String getCMBLib() {
        return CMBLib;
    }

    /**
     * @param CMBLib the CMBLib to set
     */
    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }
}
