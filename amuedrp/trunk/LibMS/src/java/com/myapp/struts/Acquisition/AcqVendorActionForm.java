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
 * @author maqbool
 */
public class AcqVendorActionForm extends org.apache.struts.action.ActionForm {
    
    private String vendor_no;
    private String button;
    private String  vendor;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pin;
    private String phone;
    private String fax;
    private String email;
    private String entry_date;
    private String library_id;
    private String sub_library_id;
    private String vendor_id;
    private String contact_person;
    private String vendor_currency;

   




    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
   

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getVendor_no() {
        return vendor_no;
    }

    public void setVendor_no(String vendor_no) {
        this.vendor_no = vendor_no;
    }
   

        public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

     public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getSub_library_id() {
        return sub_library_id;
    }

    public void setSub_library_id(String sub_library_id) {
        this.sub_library_id = sub_library_id;
    }



    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

     public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getVendor_currency() {
        return vendor_currency;
    }

    public void setVendor_currency(String vendor_currency) {
        this.vendor_currency = vendor_currency;
    }


    /**
     * @return
     */
   

    /**
     *
     */
    public AcqVendorActionForm() {
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
