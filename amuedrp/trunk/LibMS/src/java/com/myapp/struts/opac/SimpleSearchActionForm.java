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
public class SimpleSearchActionForm extends org.apache.struts.action.ActionForm {
    
    
    private String CMBFIELD;
    private String CMBDB;
    private String TXTPHRASE;
    private String CMBCONN;
    private String CMBYR;
    private String TXTYR1;
    private String TXTYR2;
    private String CMBSORT;
    private String CMBLib;



    /**
     *
     */
    public SimpleSearchActionForm() {
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
        
            // TODO: add 'error.name.required' key to your resources
        
        return null;
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
     * @return the TXTPHRASE
     */
    public String getTXTPHRASE() {
        return TXTPHRASE;
    }

    /**
     * @param TXTPHRASE the TXTPHRASE to set
     */
    public void setTXTPHRASE(String TXTPHRASE) {
        this.TXTPHRASE = TXTPHRASE;
    }

    /**
     * @return the CMBCONN
     */
    public String getCMBCONN() {
        return CMBCONN;
    }

    /**
     * @param CMBCONN the CMBCONN to set
     */
    public void setCMBCONN(String CMBCONN) {
        this.CMBCONN = CMBCONN;
    }

    /**
     * @return the CMBYR
     */
    public String getCMBYR() {
        return CMBYR;
    }

    /**
     * @param CMBYR the CMBYR to set
     */
    public void setCMBYR(String CMBYR) {
        this.CMBYR = CMBYR;
    }

    /**
     * @return the TXTYR1
     */
    public String getTXTYR1() {
        return TXTYR1;
    }

    /**
     * @param TXTYR1 the TXTYR1 to set
     */
    public void setTXTYR1(String TXTYR1) {
        this.TXTYR1 = TXTYR1;
    }

    /**
     * @return the TXTYR2
     */
    public String getTXTYR2() {
        return TXTYR2;
    }

    /**
     * @param TXTYR2 the TXTYR2 to set
     */
    public void setTXTYR2(String TXTYR2) {
        this.TXTYR2 = TXTYR2;
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
