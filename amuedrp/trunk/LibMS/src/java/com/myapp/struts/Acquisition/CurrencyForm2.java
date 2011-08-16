/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author mca53amu
 */
public class CurrencyForm2 extends org.apache.struts.action.ActionForm {
    
    private String sCountry,dCountry,firstBox,thirdBox,button;
private float secondBox;
private int conid;

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
    }

    public float getSecondBox() {
        return secondBox;
    }

    public void setSecondBox(float secondBox) {
        this.secondBox = secondBox;
    }
   

   
    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getdCountry() {
        return dCountry;
    }

    public void setdCountry(String dCountry) {
        this.dCountry = dCountry;
    }

    public String getFirstBox() {
        return firstBox;
    }

    public void setFirstBox(String firstBox) {
        this.firstBox = firstBox;
    }

    public String getsCountry() {
        return sCountry;
    }

    public void setsCountry(String sCountry) {
        this.sCountry = sCountry;
    }


    public String getThirdBox() {
        return thirdBox;
    }

    public void setThirdBox(String thirdBox) {
        this.thirdBox = thirdBox;
    }

    
}
