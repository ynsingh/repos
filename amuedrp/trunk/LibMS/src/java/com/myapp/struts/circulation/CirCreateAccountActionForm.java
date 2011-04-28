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
public class CirCreateAccountActionForm extends org.apache.struts.action.ActionForm {
    

    private String mem_id;
    private String mem_name;
    private String mail_id;
    private String password;
    private String button;
    private String card_type;
    private String MEMCAT;
    private String MEMSUBCAT;
    private String TXTDESG1;
    private String TXTFACULTY;
    private String TXTDEPT;
    private String TXTSEM;
    private String library;
    private String TXTREG_DATE;
    private String TXTEXP_DATE;

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

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }
private String card_id;
    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getMail_id() {
        return mail_id;
    }

    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
   
    /**
     *
     */
    public CirCreateAccountActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the card_id
     */
    public String getCard_id() {
        return card_id;
    }

    /**
     * @param card_id the card_id to set
     */
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
   
}
