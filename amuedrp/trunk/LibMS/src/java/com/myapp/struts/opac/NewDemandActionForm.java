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
public class NewDemandActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTLIBNAME;
    private String TXTTITLE;
    private String CMBCAT;
    private String TXTAUTHOR;
    private String TXTPUB;
    private String TXTPUBYR;
    private String TXTISBN;
    private String TXTCOPY;
    private String TXTVOL;
    private String TXTEDITION;
    private String TXTREMARK;

    /**
     *
     */
    public NewDemandActionForm() {
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
        if (getTXTAUTHOR() == null || getTXTAUTHOR().length() < 1) {
            errors.add("author", new ActionMessage("error.author.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }

    /**
     * @return the TXTLIBNAME
     */
    public String getTXTLIBNAME() {
        return TXTLIBNAME;
    }

    /**
     * @param TXTLIBNAME the TXTLIBNAME to set
     */
    public void setTXTLIBNAME(String TXTLIBNAME) {
        this.TXTLIBNAME = TXTLIBNAME;
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
     * @return the CMBCAT
     */
    public String getCMBCAT() {
        return CMBCAT;
    }

    /**
     * @param CMBCAT the CMBCAT to set
     */
    public void setCMBCAT(String CMBCAT) {
        this.CMBCAT = CMBCAT;
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
     * @return the TXTPUB
     */
    public String getTXTPUB() {
        return TXTPUB;
    }

    /**
     * @param TXTPUB the TXTPUB to set
     */
    public void setTXTPUB(String TXTPUB) {
        this.TXTPUB = TXTPUB;
    }

    /**
     * @return the TXTPUBYR
     */
    public String getTXTPUBYR() {
        return TXTPUBYR;
    }

    /**
     * @param TXTPUBYR the TXTPUBYR to set
     */
    public void setTXTPUBYR(String TXTPUBYR) {
        this.TXTPUBYR = TXTPUBYR;
    }

    /**
     * @return the TXTISBN
     */
    public String getTXTISBN() {
        return TXTISBN;
    }

    /**
     * @param TXTISBN the TXTISBN to set
     */
    public void setTXTISBN(String TXTISBN) {
        this.TXTISBN = TXTISBN;
    }

    /**
     * @return the TXTCOPY
     */
    public String getTXTCOPY() {
        return TXTCOPY;
    }

    /**
     * @param TXTCOPY the TXTCOPY to set
     */
    public void setTXTCOPY(String TXTCOPY) {
        this.TXTCOPY = TXTCOPY;
    }

    /**
     * @return the TXTVOL
     */
    public String getTXTVOL() {
        return TXTVOL;
    }

    /**
     * @param TXTVOL the TXTVOL to set
     */
    public void setTXTVOL(String TXTVOL) {
        this.TXTVOL = TXTVOL;
    }

    /**
     * @return the TXTEDITION
     */
    public String getTXTEDITION() {
        return TXTEDITION;
    }

    /**
     * @param TXTEDITION the TXTEDITION to set
     */
    public void setTXTEDITION(String TXTEDITION) {
        this.TXTEDITION = TXTEDITION;
    }

    /**
     * @return the TXTREMARK
     */
    public String getTXTREMARK() {
        return TXTREMARK;
    }

    /**
     * @param TXTREMARK the TXTREMARK to set
     */
    public void setTXTREMARK(String TXTREMARK) {
        this.TXTREMARK = TXTREMARK;
    }
}
