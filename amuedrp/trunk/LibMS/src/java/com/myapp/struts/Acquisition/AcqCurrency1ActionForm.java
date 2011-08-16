/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

/**
 *
 * @author maqbool
 */
public class AcqCurrency1ActionForm extends org.apache.struts.action.ActionForm {

           
  private int conid;
  private String button;
  private String sCountry;
  private String dCountry;
  private String firstBox;
  private String thirdBox;
  private float secondBox;
  private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  



    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
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

    public float getSecondBox() {
        return secondBox;
    }

    public void setSecondBox(float secondBox) {
        this.secondBox = secondBox;
    }

    public String getThirdBox() {
        return thirdBox;
    }

    public void setThirdBox(String thirdBox) {
        this.thirdBox = thirdBox;
    }

  
}
