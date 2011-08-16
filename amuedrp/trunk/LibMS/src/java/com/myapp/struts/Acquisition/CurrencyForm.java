/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

/**
 *
 * @author mca53amu
 */
public class CurrencyForm extends org.apache.struts.action.ActionForm {
    
    private String conid,button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getConid() {
        return conid;
    }

    public void setConid(String conid) {
        this.conid = conid;
    }

   
}
