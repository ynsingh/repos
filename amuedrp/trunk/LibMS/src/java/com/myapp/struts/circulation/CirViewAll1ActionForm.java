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
 * @author edrp01
 */
public class CirViewAll1ActionForm extends org.apache.struts.action.ActionForm {
    

    
    
    private String TXTTITLE;
    private String CMBSORT;
    private String CMBLib;
    private String CMBSUBLib;
     private String Status;
    private String registrationdate;
     private String registrationdatefrom;

    public String getRegistrationdatefrom() {
        return registrationdatefrom;
    }

    public void setRegistrationdatefrom(String registrationdatefrom) {
        this.registrationdatefrom = registrationdatefrom;
    }
     private String expirydate;
   private String  TXTFACULTY;
   private String  TXTDEPT;
  private String   TXTCOURSE;
 private String  checkbox;
private String  checkbox1;
private String  requestdate;
private String  requestdate1;

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

    public String getRequestdate1() {
        return requestdate1;
    }

    public void setRequestdate1(String requestdate1) {
        this.requestdate1 = requestdate1;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
private String button;

    public String getCheckbox1() {
        return checkbox1;
    }

    public void setCheckbox1(String checkbox1) {
        this.checkbox1 = checkbox1;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
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

    public String getTXTFACULTY() {
        return TXTFACULTY;
    }

    public void setTXTFACULTY(String TXTFACULTY) {
        this.TXTFACULTY = TXTFACULTY;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(String registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

   

    public String getCMBLib() {
        return CMBLib;
    }

    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }

    public String getCMBSORT() {
        return CMBSORT;
    }

    public void setCMBSORT(String CMBSORT) {
        this.CMBSORT = CMBSORT;
    }

    public String getCMBSUBLib() {
        return CMBSUBLib;
    }

    public void setCMBSUBLib(String CMBSUBLib) {
        this.CMBSUBLib = CMBSUBLib;
    }

    public String getTXTTITLE() {
        return TXTTITLE;
    }

    public void setTXTTITLE(String TXTTITLE) {
        this.TXTTITLE = TXTTITLE;
    }

    /**
     * @return
     */
  

    /**
     *
     */
    public CirViewAll1ActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
   
}
