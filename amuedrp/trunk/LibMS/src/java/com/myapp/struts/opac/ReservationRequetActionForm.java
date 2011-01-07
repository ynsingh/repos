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
public class ReservationRequetActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTLIBNAME;
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

    

    /**
     *
     */
    public ReservationRequetActionForm() {
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
            errors.add("name", new ActionMessage("error.name.required"));
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
}
