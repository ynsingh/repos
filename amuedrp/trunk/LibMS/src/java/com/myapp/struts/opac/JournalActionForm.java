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
 * @author Client
 */
public class JournalActionForm extends org.apache.struts.action.ActionForm {
    
    private String CMBLib;


    /**
     *
     */
    public JournalActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
    
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        return null;
    }

    /**
     * @return the CMBLib
     */
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
