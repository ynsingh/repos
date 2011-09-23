/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class GenerateCardActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTMEMID;
    private String TXTFNAME;
    private String TXTLNAME;
    private String TXTMNAME;
    private String TXTEMAILID;
    private String TXTPASS;
    private String TXTADD1;
    private String TXTCITY1;
    private String TXTSTATE1;
    private String TXTCOUNTRY1;
    private String TXTPH1;
    private String TXTPH2;
    private String TXTFAX;
    private String TXTADD2;
    private String TXTCITY2;
    private String TXTPIN1;
    private String TXTPIN2;
    private String TXTSTATE2;
    private String TXTCOUNTRY2;
    private String MEMCAT;
    private String MEMSUBCAT;
    private String TXTFACULTY;
    private String TXTDEPT;
    private String TXTCOURSE;
    private String TXTREG_DATE;
    private String TXTEXP_DATE;
    private String TXTSEM;
     private String button;
    private String TXTDESG1;
    private String TXTOFFICE;
  private byte[] uploadedFile;

    public String getMEMCAT() {
        return MEMCAT;
    }

    public void setMEMCAT(String MEMCAT) {
        this.MEMCAT = MEMCAT;
    }

    public String getMEMSUBCAT() {
        return MEMSUBCAT;
    }

    public void setMEMSUBCAT(String MEMSUBCAT) {
        this.MEMSUBCAT = MEMSUBCAT;
    }

    public String getTXTADD1() {
        return TXTADD1;
    }

    public void setTXTADD1(String TXTADD1) {
        this.TXTADD1 = TXTADD1;
    }

    public String getTXTADD2() {
        return TXTADD2;
    }

    public void setTXTADD2(String TXTADD2) {
        this.TXTADD2 = TXTADD2;
    }

    public String getTXTCITY1() {
        return TXTCITY1;
    }

    public void setTXTCITY1(String TXTCITY1) {
        this.TXTCITY1 = TXTCITY1;
    }

    public String getTXTCITY2() {
        return TXTCITY2;
    }

    public void setTXTCITY2(String TXTCITY2) {
        this.TXTCITY2 = TXTCITY2;
    }

    public String getTXTCOUNTRY1() {
        return TXTCOUNTRY1;
    }

    public void setTXTCOUNTRY1(String TXTCOUNTRY1) {
        this.TXTCOUNTRY1 = TXTCOUNTRY1;
    }

    public String getTXTCOUNTRY2() {
        return TXTCOUNTRY2;
    }

    public void setTXTCOUNTRY2(String TXTCOUNTRY2) {
        this.TXTCOUNTRY2 = TXTCOUNTRY2;
    }

    public String getTXTCOURSE() {
        return TXTCOURSE;
    }

    public void setTXTCOURSE(String TXTCOURSE) {
        this.TXTCOURSE = TXTCOURSE;
    }

    public String getTXTDEPT() {
        return TXTDEPT;
    }

    public void setTXTDEPT(String TXTDEPT) {
        this.TXTDEPT = TXTDEPT;
    }

    public String getTXTDESG1() {
        return TXTDESG1;
    }

    public void setTXTDESG1(String TXTDESG1) {
        this.TXTDESG1 = TXTDESG1;
    }

    public String getTXTEMAILID() {
        return TXTEMAILID;
    }

    public void setTXTEMAILID(String TXTEMAILID) {
        this.TXTEMAILID = TXTEMAILID;
    }

    public String getTXTEXP_DATE() {
        return TXTEXP_DATE;
    }

    public void setTXTEXP_DATE(String TXTEXP_DATE) {
        this.TXTEXP_DATE = TXTEXP_DATE;
    }

    public String getTXTFACULTY() {
        return TXTFACULTY;
    }

    public void setTXTFACULTY(String TXTFACULTY) {
        this.TXTFACULTY = TXTFACULTY;
    }

    public String getTXTFAX() {
        return TXTFAX;
    }

    public void setTXTFAX(String TXTFAX) {
        this.TXTFAX = TXTFAX;
    }

    public String getTXTFNAME() {
        return TXTFNAME;
    }

    public void setTXTFNAME(String TXTFNAME) {
        this.TXTFNAME = TXTFNAME;
    }

    public String getTXTLNAME() {
        return TXTLNAME;
    }

    public void setTXTLNAME(String TXTLNAME) {
        this.TXTLNAME = TXTLNAME;
    }

    public String getTXTMNAME() {
        return TXTMNAME;
    }

    public void setTXTMNAME(String TXTMNAME) {
        this.TXTMNAME = TXTMNAME;
    }

    public String getTXTOFFICE() {
        return TXTOFFICE;
    }

    public void setTXTOFFICE(String TXTOFFICE) {
        this.TXTOFFICE = TXTOFFICE;
    }

    public String getTXTPASS() {
        return TXTPASS;
    }

    public void setTXTPASS(String TXTPASS) {
        this.TXTPASS = TXTPASS;
    }

    public String getTXTPH1() {
        return TXTPH1;
    }

    public void setTXTPH1(String TXTPH1) {
        this.TXTPH1 = TXTPH1;
    }

    public String getTXTPH2() {
        return TXTPH2;
    }

    public void setTXTPH2(String TXTPH2) {
        this.TXTPH2 = TXTPH2;
    }

    public String getTXTPIN1() {
        return TXTPIN1;
    }

    public void setTXTPIN1(String TXTPIN1) {
        this.TXTPIN1 = TXTPIN1;
    }

    public String getTXTPIN2() {
        return TXTPIN2;
    }

    public void setTXTPIN2(String TXTPIN2) {
        this.TXTPIN2 = TXTPIN2;
    }

    public String getTXTREG_DATE() {
        return TXTREG_DATE;
    }

    public void setTXTREG_DATE(String TXTREG_DATE) {
        this.TXTREG_DATE = TXTREG_DATE;
    }

    public String getTXTSEM() {
        return TXTSEM;
    }

    public void setTXTSEM(String TXTSEM) {
        this.TXTSEM = TXTSEM;
    }

    public String getTXTSTATE1() {
        return TXTSTATE1;
    }

    public void setTXTSTATE1(String TXTSTATE1) {
        this.TXTSTATE1 = TXTSTATE1;
    }

    public String getTXTSTATE2() {
        return TXTSTATE2;
    }

    public void setTXTSTATE2(String TXTSTATE2) {
        this.TXTSTATE2 = TXTSTATE2;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public byte[] getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(byte[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getTXTMEMID() {
        return TXTMEMID;
    }

    public void setTXTMEMID(String TXTMEMID) {
        this.TXTMEMID = TXTMEMID;
    }

    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
