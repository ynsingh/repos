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
public class AdvanceSearchActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTYR1;
    private String TXTYR2;
    private String TXTPHRASE1;
    private String TXTPHRASE2;
    private String TXTPHRASE3;
    private String CMBFIELD1;
    private String CMBFIELD2;
    private String CMBFIELD3;
    private String CMBYR;
    private String CMBDB;
    private String CMBF1;
    private String CMBF2;
    private String CMBF3;
    private String CMB1;
    private String CMB2;
    private String CMB3;
    private String CMBLib;
    private String CMBSUBLib;
    private String CMBSORT;
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
    public String getCMBLib() {
        return CMBLib;
    }

    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }
    /**
     *
     */
    public AdvanceSearchActionForm() {
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
        if (getCMBDB() == null || getCMBDB().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
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
        System.out.println("txtyr1="+TXTYR1);
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
     * @return the CMBFIELD1
     */
    public String getCMBFIELD1() {
        return CMBFIELD1;
    }

    /**
     * @param CMBFIELD1 the CMBFIELD1 to set
     */
    public void setCMBFIELD1(String CMBFIELD1) {
        this.CMBFIELD1 = CMBFIELD1;
    }

    /**
     * @return the CMBFIELD2
     */
    public String getCMBFIELD2() {
        return CMBFIELD2;
    }

    /**
     * @param CMBFIELD2 the CMBFIELD2 to set
     */
    public void setCMBFIELD2(String CMBFIELD2) {
        this.CMBFIELD2 = CMBFIELD2;
    }

    /**
     * @return the CMBFIELD3
     */
    public String getCMBFIELD3() {
        return CMBFIELD3;
    }

    /**
     * @param CMBFIELD3 the CMBFIELD3 to set
     */
    public void setCMBFIELD3(String CMBFIELD3) {
        this.CMBFIELD3 = CMBFIELD3;
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
        System.out.println("cmbdb="+CMBDB);
    }

    /**
     * @return the CMBF1
     */
    public String getCMBF1() {
        return CMBF1;
    }

    /**
     * @param CMBF1 the CMBF1 to set
     */
    public void setCMBF1(String CMBF1) {
        this.CMBF1 = CMBF1;
    }

    /**
     * @return the CMBF2
     */
    public String getCMBF2() {
        return CMBF2;
    }

    /**
     * @param CMBF2 the CMBF2 to set
     */
    public void setCMBF2(String CMBF2) {
        this.CMBF2 = CMBF2;
    }

    /**
     * @return the CMBF3
     */
    public String getCMBF3() {
        return CMBF3;
    }

    /**
     * @param CMBF3 the CMBF3 to set
     */
    public void setCMBF3(String CMBF3) {
        this.CMBF3 = CMBF3;
    }

    /**
     * @return the CMB1
     */
    public String getCMB1() {
        return CMB1;
    }

    /**
     * @param CMB1 the CMB1 to set
     */
    public void setCMB1(String CMB1) {
        this.CMB1 = CMB1;
    }

    /**
     * @return the CMB2
     */
    public String getCMB2() {
        return CMB2;
    }

    /**
     * @param CMB2 the CMB2 to set
     */
    public void setCMB2(String CMB2) {
        this.CMB2 = CMB2;
    }

    /**
     * @return the CMB3
     */
    public String getCMB3() {
        return CMB3;
    }

    /**
     * @param CMB3 the CMB3 to set
     */
    public void setCMB3(String CMB3) {
        this.CMB3 = CMB3;
    }

    /**
     * @return the TXTPHRASE1
     */
    public String getTXTPHRASE1() {
        return TXTPHRASE1;
    }

    /**
     * @param TXTPHRASE1 the TXTPHRASE1 to set
     */
    public void setTXTPHRASE1(String TXTPHRASE1) {
        this.TXTPHRASE1 = TXTPHRASE1;
    }

    /**
     * @return the TXTPHRASE2
     */
    public String getTXTPHRASE2() {
        return TXTPHRASE2;
    }

    /**
     * @param TXTPHRASE2 the TXTPHRASE2 to set
     */
    public void setTXTPHRASE2(String TXTPHRASE2) {
        this.TXTPHRASE2 = TXTPHRASE2;
    }

    /**
     * @param TXTPHRASE3 the TXTPHRASE3 to set
     */
    public void setTXTPHRASE3(String TXTPHRASE3) {
        this.TXTPHRASE3 = TXTPHRASE3;
    }

    /**
     * @return the TXTPHRASE3
     */
    public String getTXTPHRASE3() {
        return TXTPHRASE3;
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
     * @return the CMBSUBLib
     */
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
