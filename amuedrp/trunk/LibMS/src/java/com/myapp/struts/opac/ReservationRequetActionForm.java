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
public class ReservationRequetActionForm extends ValidatorForm {
    
    

    private String TXTCARDID;
    private String TXTTITLE;
    private String TXTAUTHOR;
    private String TXTISBN;
    private String TXTCALLNO;
    private String TXTPUBL;
    private String TXTREMARKS;
    private String CMBCAT;
    private String TXTEDITION;
    private String TXTVOL;
    private String issn;
    private String lang;
private String accessionno;
private String no_of_copy;
private String pub_year;
    

    /**
     *
     */
    public ReservationRequetActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

   

   
    /**
     * @return the TXTCARDID
     */
    public String getTXTCARDID() {
        return TXTCARDID;
    }

    /**
     * @param TXTCARDID the TXTCARDID to set
     */
    public void setTXTCARDID(String TXTCARDID) {
        this.TXTCARDID = TXTCARDID;
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
     * @return the TXTCALLNO
     */
    public String getTXTCALLNO() {
        return TXTCALLNO;
    }

    /**
     * @param TXTCALLNO the TXTCALLNO to set
     */
    public void setTXTCALLNO(String TXTCALLNO) {
        this.TXTCALLNO = TXTCALLNO;
    }

    /**
     * @return the TXTPUBL
     */
    public String getTXTPUBL() {
        return TXTPUBL;
    }

    /**
     * @param TXTPUBL the TXTPUBL to set
     */
    public void setTXTPUBL(String TXTPUBL) {
        this.TXTPUBL = TXTPUBL;
    }

    /**
     * @return the TXTREMARKS
     */
    public String getTXTREMARKS() {
        return TXTREMARKS;
    }

    /**
     * @param TXTREMARKS the TXTREMARKS to set
     */
    public void setTXTREMARKS(String TXTREMARKS) {
        this.TXTREMARKS = TXTREMARKS;
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
     * @return the accessionno
     */
    public String getAccessionno() {
        return accessionno;
    }

    /**
     * @param accessionno the accessionno to set
     */
    public void setAccessionno(String accessionno) {
        this.accessionno = accessionno;
    }

    /**
     * @return the no_of_copy
     */
    public String getNo_of_copy() {
        return no_of_copy;
    }

    /**
     * @param no_of_copy the no_of_copy to set
     */
    public void setNo_of_copy(String no_of_copy) {
        this.no_of_copy = no_of_copy;
    }

    /**
     * @return the pub_year
     */
    public String getPub_year() {
        return pub_year;
    }

    /**
     * @param pub_year the pub_year to set
     */
    public void setPub_year(String pub_year) {
        this.pub_year = pub_year;
    }
}
