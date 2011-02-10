/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
/**
 *
 * @author Faraz
 */
public class NewDemandActionForm extends ValidatorForm {
    
    
    private String TXTTITLE;
    private String CMBCAT;
    private String TXTAUTHOR;
    private String TXTISBN;
    private String issn;
    private String callno;
    private String TXTPUB;
    private String TXTPUBYR;
    private String TXTREMARK;
    private String lang;
    private String TXTCOPY;
    private String TXTVOL;
    private String TXTEDITION;
    

    /**
     *
     */
    public NewDemandActionForm() {
        super();
        // TODO Auto-generated constructor stub
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
     * @return the issn
     */
    public String getIssn() {
        return issn;
    }

    /**
     * @param issn the issn to set
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**
     * @return the callno
     */
    public String getCallno() {
        return callno;
    }

    /**
     * @param callno the callno to set
     */
    public void setCallno(String callno) {
        this.callno = callno;
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

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
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

  
}
