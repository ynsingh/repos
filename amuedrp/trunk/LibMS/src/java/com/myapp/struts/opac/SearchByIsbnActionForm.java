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
public class SearchByIsbnActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTKEY;
    private String CMBLib;
    private String CMBSUBLib;

    public String getCMBLib() {
        return CMBLib;
    }

    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }

    public String getCMBSUBLib() {
        return CMBSUBLib;
    }

    public void setCMBSUBLib(String CMBSUBLib) {
        this.CMBSUBLib = CMBSUBLib;
    }

    public String getTXTKEY() {
        return TXTKEY;
    }

    public void setTXTKEY(String TXTKEY) {
        this.TXTKEY = TXTKEY;
    }
    /**
     * @return the TXTKEY
     */
    
   
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
   
}
