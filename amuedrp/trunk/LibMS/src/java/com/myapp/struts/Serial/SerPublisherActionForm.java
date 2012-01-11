/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Serial;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class SerPublisherActionForm extends org.apache.struts.action.ActionForm {
    
    private String pub_id;
    private String pub_name;
    private String pub_add;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String email;
    private String fax;
    private String pub_url;
    private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPub_add() {
        return pub_add;
    }

    public void setPub_add(String pub_add) {
        this.pub_add = pub_add;
    }

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
    }

    public String getPub_name() {
        return pub_name;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public String getPub_url() {
        return pub_url;
    }

    public void setPub_url(String pub_url) {
        this.pub_url = pub_url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
   
   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
