/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

/**
 *
 * @author EdRP-05
 */
public class AcqCurrencyActionForm extends org.apache.struts.action.ActionForm {

    String button;
    String currency_id;
    String base_currency_symbol;
    String formal_name;
    String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFormal_name() {
        return formal_name;
    }

    public void setFormal_name(String formal_name) {
        this.formal_name = formal_name;
    }
    

    public String getBase_currency_symbol() {
        return base_currency_symbol;
    }

    public void setBase_currency_symbol(String base_currency_symbol) {
        this.base_currency_symbol = base_currency_symbol;
    }


    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

}
