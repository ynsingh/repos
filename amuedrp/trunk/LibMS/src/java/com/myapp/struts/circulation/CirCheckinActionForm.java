/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

/**
 *
 * @author edrp01
 */
public class CirCheckinActionForm extends org.apache.struts.action.ActionForm {
    
   
     private String TXTACCESSION;
     private String button;
     private String TXTTITLE;
     private String TXTAUTHOR;
     private String TXTCALL;
     private String TXTDUEDATE;
     private String TXTMEMID;
     private String TXTRETURNINGDATE;
     private String TXTFINE;
     private String TXTDELAYED;
     private String TXTMEMNAME;
     private String TXTDAMAGEDSTATUS;
     private String TXTLOSSSTATUS;
     private String TXTREASON;

    
    public String getTXTAUTHOR() {
        return TXTAUTHOR;
    }

    public void setTXTAUTHOR(String TXTAUTHOR) {
        this.TXTAUTHOR = TXTAUTHOR;
    }

    public String getTXTREASON() {
        return TXTREASON;
    }

    public void setTXTREASON(String TXTREASON) {
        this.TXTREASON = TXTREASON;
    }

    public String getTXTCALL() {
        return TXTCALL;
    }

    public void setTXTCALL(String TXTCALL) {
        this.TXTCALL = TXTCALL;
    }

    public String getTXTDUEDATE() {
        return TXTDUEDATE;
    }

    public void setTXTDUEDATE(String TXTDUEDATE) {
        this.TXTDUEDATE = TXTDUEDATE;
    }

    public String getTXTMEMID() {
        return TXTMEMID;
    }

    public void setTXTMEMID(String TXTMEMID) {
        this.TXTMEMID = TXTMEMID;
    }

    public String getTXTTITLE() {
        return TXTTITLE;
    }

    public void setTXTTITLE(String TXTTITLE) {
        this.TXTTITLE = TXTTITLE;
    }
    public String getTXTACCESSION() {
        return TXTACCESSION;
    }

    public void setTXTACCESSION(String TXTACCESSION) {
        this.TXTACCESSION = TXTACCESSION;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
    /**
     * @return
     */
   

    /**
     *
     */
    public CirCheckinActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the TXTRETURNINGDATE
     */
    public String getTXTRETURNINGDATE() {
        return TXTRETURNINGDATE;
    }

    /**
     * @param TXTRETURNINGDATE the TXTRETURNINGDATE to set
     */
    public void setTXTRETURNINGDATE(String TXTRETURNINGDATE) {
        this.TXTRETURNINGDATE = TXTRETURNINGDATE;
    }

    /**
     * @return the TXTFINE
     */
    public String getTXTFINE() {
        return TXTFINE;
    }

    /**
     * @param TXTFINE the TXTFINE to set
     */
    public void setTXTFINE(String TXTFINE) {
        this.TXTFINE = TXTFINE;
    }

    /**
     * @return the TXTDELAYED
     */
    public String getTXTDELAYED() {
        return TXTDELAYED;
    }

    /**
     * @param TXTDELAYED the TXTDELAYED to set
     */
    public void setTXTDELAYED(String TXTDELAYED) {
        this.TXTDELAYED = TXTDELAYED;
    }

    /**
     * @return the TXTMEMNAME
     */
    public String getTXTMEMNAME() {
        return TXTMEMNAME;
    }

    /**
     * @param TXTMEMNAME the TXTMEMNAME to set
     */
    public void setTXTMEMNAME(String TXTMEMNAME) {
        this.TXTMEMNAME = TXTMEMNAME;
    }


    public String getTXTDAMAGEDSTATUS () {
        return TXTDAMAGEDSTATUS;
    }

    /**
     * @param TXTMEMNAME the TXTMEMNAME to set
     */
    public void setTXTDAMAGEDSTATUS(String TXTDAMAGEDSTATUS) {
        this.TXTDAMAGEDSTATUS = TXTDAMAGEDSTATUS;
    }
/**
     * @return the TXTMEMNAME
     */
    public String getTXTLOSSSTATUS() {
        return TXTLOSSSTATUS;
    }

    /**
     * @param TXTMEMNAME the TXTMEMNAME to set
     */
    public void setTXTLOSSSTATUS(String TXTLOSSSTATUS) {
        this.TXTLOSSSTATUS= TXTLOSSSTATUS;
    }

   
}
