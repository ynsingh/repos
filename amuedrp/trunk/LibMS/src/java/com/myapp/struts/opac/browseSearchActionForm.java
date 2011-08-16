/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Faraz
 */
public class browseSearchActionForm extends org.apache.struts.action.ActionForm {
    
    private String CMBFIELD;
    private String CMBDB;
    private String TXTTITLE;
    private String CMBSORT;
    private String CMBLib;
    private String CMBSUBLib;
 private String checkbox;
 private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }
    /**
     *
     */
    public browseSearchActionForm() {
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
        if (getCMBFIELD() == null || getCMBFIELD().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }

    /**
     * @return the CMBFIELD
     */
    public String getCMBFIELD() {
        return CMBFIELD;
    }

    /**
     * @param CMBFIELD the CMBFIELD to set
     */
    public void setCMBFIELD(String CMBFIELD) {
        this.CMBFIELD = CMBFIELD;
    }

    /**
     * @return the CMBDB
     */
    public String getCMBDB() {
        return CMBDB;
    }

    /**
     * @param CMBDB the CMBDB to set
     */
    public void setCMBDB(String CMBDB) {
        this.CMBDB = CMBDB;
    }

    /**
     * @return the TXTTITLE
     */
    public String getTXTTITLE() {
        return TXTTITLE;
    }

    /**
     * @param TXTTITLE the TXTTITLE to set
     */
    public void setTXTTITLE(String TXTTITLE) {
        this.TXTTITLE = TXTTITLE;
    }

    /**
     * @return the CMBSORT
     */
    public String getCMBSORT() {
        return CMBSORT;
    }

    /**
     * @param CMBSORT the CMBSORT to set
     */
    public void setCMBSORT(String CMBSORT) {
        this.CMBSORT = CMBSORT;
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

    public String getCMBSUBLib() {
        return CMBSUBLib;
    }

    /**
     * @param CMBSUBLib the CMBSUBLib to set
     */
    public void setCMBSUBLib(String CMBSUBLib) {
        this.CMBSUBLib = CMBSUBLib;
    }
    @Override
     public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
      request.setCharacterEncoding("UTF-8");
    }
    catch (UnsupportedEncodingException ex) {
    }
    }
}
