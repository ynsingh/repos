/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class AdditionalSearchActionForm extends org.apache.struts.action.ActionForm {
    
    private String CMBDB;
    private String CMBYR;
    private String TXTYR1;
    private String TXTYR2;
    private String TXTAUTHOR;
    private String TXTTITLE;
    private String TXTSUBJECT;
    private String TXTOTHER;
    private String CMBCONN1;
    private String CMBCONN2;
    private String CMBCONN3;
    private String CMBCONN4;
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

    public String getCMBSORT() {
        return CMBSORT;
    }

    public void setCMBSORT(String CMBSORT) {
        this.CMBSORT = CMBSORT;
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
    public AdditionalSearchActionForm() {
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
        if (getTXTAUTHOR() == null || getTXTAUTHOR().length() < 1) {
            
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
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
     * @return the CMBCONN3
     */
    public String getCMBCONN3() {
        return CMBCONN3;
    }

    /**
     * @param CMBCONN3 the CMBCONN3 to set
     */
    public void setCMBCONN3(String CMBCONN3) {
        this.CMBCONN3 = CMBCONN3;
    }

    /**
     * @return the TXTAUTHOR
     */
    public String getTXTAUTHOR() {
        return TXTAUTHOR;
    }

    /**
     * @param TXTAUTHOR the TXTAUTHOR to set
     */
    public void setTXTAUTHOR(String TXTAUTHOR) {
        this.TXTAUTHOR = TXTAUTHOR;
    }

    /**
     * @return the TXTSUBJECT
     */
    public String getTXTSUBJECT() {
        return TXTSUBJECT;
    }

    /**
     * @param TXTSUBJECT the TXTSUBJECT to set
     */
    public void setTXTSUBJECT(String TXTSUBJECT) {
        this.TXTSUBJECT = TXTSUBJECT;
    }

    /**
     * @return the TXTOTHER
     */
    public String getTXTOTHER() {
        return TXTOTHER;
    }

    /**
     * @param TXTOTHER the TXTOTHER to set
     */
    public void setTXTOTHER(String TXTOTHER) {
        this.TXTOTHER = TXTOTHER;
    }

    /**
     * @return the CMBCONN1
     */
    public String getCMBCONN1() {
        return CMBCONN1;
    }

    /**
     * @param CMBCONN1 the CMBCONN1 to set
     */
    public void setCMBCONN1(String CMBCONN1) {
        this.CMBCONN1 = CMBCONN1;
    }

    /**
     * @return the CMBCONN2
     */
    public String getCMBCONN2() {
        return CMBCONN2;
    }

    /**
     * @param CMBCONN2 the CMBCONN2 to set
     */
    public void setCMBCONN2(String CMBCONN2) {
        this.CMBCONN2 = CMBCONN2;
    }

    /**
     * @return the CMBCONN4
     */
    public String getCMBCONN4() {
        return CMBCONN4;
    }

    /**
     * @param CMBCONN4 the CMBCONN4 to set
     */
    public void setCMBCONN4(String CMBCONN4) {
        this.CMBCONN4 = CMBCONN4;
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

    public String getCMBSUBLib() {
        return CMBSUBLib;
    }

    /**
     * @param CMBYR the CMBSUBLib to set
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
