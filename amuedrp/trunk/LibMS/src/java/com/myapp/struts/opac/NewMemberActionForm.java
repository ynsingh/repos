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
public class NewMemberActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTLIBNAME;
    private String TXTLIBID;
    private String CMBCAT;
    private String TXTFNAME;
    private String TXTMNAME;
    private String TXTLNAME;
    private String TXTADD1;
    private String TXTADD2;
    private String TXTCITY;
    private String TXTSTATE;
    private String TXTPIN;
    private String TXTCOUNTRY;
    private String TXTEMAIL;
    private String TXTFAX;
    private String TXTPH1;
    private String TXTPH2;
    private String TXTFACULTY;
    private String TXTDEPT;
    private String TXTROLL;
    private String TXTCOURSE;

   
 /**
     *
     */
    public NewMemberActionForm() {
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
        if (getTXTEMAIL() == null || getTXTEMAIL().length() < 1) {
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
     * @return the TXTLIBID
     */
    public String getTXTLIBID() {
        return TXTLIBID;
    }

    /**
     * @param TXTLIBID the TXTLIBID to set
     */
    public void setTXTLIBID(String TXTLIBID) {
        this.TXTLIBID = TXTLIBID;
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
     * @return the TXTFNAME
     */
    public String getTXTFNAME() {
        return TXTFNAME;
    }

    /**
     * @param TXTFNAME the TXTFNAME to set
     */
    public void setTXTFNAME(String TXTFNAME) {
        this.TXTFNAME = TXTFNAME;
    }

    /**
     * @return the TXTMNAME
     */
    public String getTXTMNAME() {
        return TXTMNAME;
    }

    /**
     * @param TXTMNAME the TXTMNAME to set
     */
    public void setTXTMNAME(String TXTMNAME) {
        this.TXTMNAME = TXTMNAME;
    }

    /**
     * @return the TXTLNAME
     */
    public String getTXTLNAME() {
        return TXTLNAME;
    }

    /**
     * @param TXTLNAME the TXTLNAME to set
     */
    public void setTXTLNAME(String TXTLNAME) {
        this.TXTLNAME = TXTLNAME;
    }

    /**
     * @return the TXTADD1
     */
    public String getTXTADD1() {
        return TXTADD1;
    }

    /**
     * @param TXTADD1 the TXTADD1 to set
     */
    public void setTXTADD1(String TXTADD1) {
        this.TXTADD1 = TXTADD1;
    }

    /**
     * @return the TXTADD2
     */
    public String getTXTADD2() {
        return TXTADD2;
    }

    /**
     * @param TXTADD2 the TXTADD2 to set
     */
    public void setTXTADD2(String TXTADD2) {
        this.TXTADD2 = TXTADD2;
    }

    /**
     * @return the TXTCITY
     */
    public String getTXTCITY() {
        return TXTCITY;
    }

    /**
     * @param TXTCITY the TXTCITY to set
     */
    public void setTXTCITY(String TXTCITY) {
        this.TXTCITY = TXTCITY;
    }

    /**
     * @return the TXTSTATE
     */
    public String getTXTSTATE() {
        return TXTSTATE;
    }

    /**
     * @param TXTSTATE the TXTSTATE to set
     */
    public void setTXTSTATE(String TXTSTATE) {
        this.TXTSTATE = TXTSTATE;
    }

    /**
     * @return the TXTPIN
     */
    public String getTXTPIN() {
        return TXTPIN;
    }

    /**
     * @param TXTPIN the TXTPIN to set
     */
    public void setTXTPIN(String TXTPIN) {
        this.TXTPIN = TXTPIN;
    }

    /**
     * @return the TXTCOUNTRY
     */
    public String getTXTCOUNTRY() {
        return TXTCOUNTRY;
    }

    /**
     * @param TXTCOUNTRY the TXTCOUNTRY to set
     */
    public void setTXTCOUNTRY(String TXTCOUNTRY) {
        this.TXTCOUNTRY = TXTCOUNTRY;
    }

    /**
     * @return the TXTEMAIL
     */
    public String getTXTEMAIL() {
        return TXTEMAIL;
    }

    /**
     * @param TXTEMAIL the TXTEMAIL to set
     */
    public void setTXTEMAIL(String TXTEMAIL) {
        this.TXTEMAIL = TXTEMAIL;
    }

    /**
     * @return the TXTFAX
     */
    public String getTXTFAX() {
        return TXTFAX;
    }

    /**
     * @param TXTFAX the TXTFAX to set
     */
    public void setTXTFAX(String TXTFAX) {
        this.TXTFAX = TXTFAX;
    }

    /**
     * @return the TXTPH1
     */
    public String getTXTPH1() {
        return TXTPH1;
    }

    /**
     * @param TXTPH1 the TXTPH1 to set
     */
    public void setTXTPH1(String TXTPH1) {
        this.TXTPH1 = TXTPH1;
    }

    /**
     * @return the TXTPH2
     */
    public String getTXTPH2() {
        return TXTPH2;
    }

    /**
     * @param TXTPH2 the TXTPH2 to set
     */
    public void setTXTPH2(String TXTPH2) {
        this.TXTPH2 = TXTPH2;
    }

    /**
     * @return the TXTFACULTY
     */
    public String getTXTFACULTY() {
        return TXTFACULTY;
    }

    /**
     * @param TXTFACULTY the TXTFACULTY to set
     */
    public void setTXTFACULTY(String TXTFACULTY) {
        this.TXTFACULTY = TXTFACULTY;
    }

    /**
     * @return the TXTDEPT
     */
    public String getTXTDEPT() {
        return TXTDEPT;
    }

    /**
     * @param TXTDEPT the TXTDEPT to set
     */
    public void setTXTDEPT(String TXTDEPT) {
        this.TXTDEPT = TXTDEPT;
    }

    /**
     * @return the TXTROLL
     */
    public String getTXTROLL() {
        return TXTROLL;
    }

    /**
     * @param TXTROLL the TXTROLL to set
     */
    public void setTXTROLL(String TXTROLL) {
        this.TXTROLL = TXTROLL;
    }

    /**
     * @return the TXTCOURSE
     */
    public String getTXTCOURSE() {
        return TXTCOURSE;
    }

    /**
     * @param TXTCOURSE the TXTCOURSE to set
     */
    public void setTXTCOURSE(String TXTCOURSE) {
        this.TXTCOURSE = TXTCOURSE;
    }
}
