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
public class CirDelinquentMemberActionForm extends org.apache.struts.action.ActionForm {
    

    private String memid;

   

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

    public String getTXTOFFICE() {
        return TXTOFFICE;
    }

    public void setTXTOFFICE(String TXTOFFICE) {
        this.TXTOFFICE = TXTOFFICE;
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

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    private String member_name;
    private String MEMCAT;
    private String MEMSUBCAT;
    private String TXTDESG1;
    private String TXTOFFICE;
    private String TXTDEPT;
    private String TXTFACULTY;
    private String TXTCOURSE;
    private String TXTSEM;
    private String TXTREG_DATE;
    private String TXTEXP_DATE;
    private String card_id;
    private String password;
    private String changestatus;

    public String getChangestatus() {
        return changestatus;
    }

    public void setChangestatus(String changestatus) {
        this.changestatus = changestatus;
    }
    private String reason;
    private String button;

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
    public CirDelinquentMemberActionForm() {
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
       
        return null;
    }
}
