package com.myapp.struts.hbm;
// Generated Mar 18, 2011 7:00:13 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * AcqVendor generated by hbm2java
 */
public class AcqVendor  implements java.io.Serializable {


     private AcqVendorId id;
     private String vendor;
     private String vendorId;
     private String address;
     private String city;
     private String state;
     private String country;
     private String pin;
     private String phone;
     private String fax;
     private String email;
     private String entryDate;
     private String vendorCurrency;
     private String contactPerson;


     private Set acqApprovalHeaders = new HashSet(0);

    public AcqVendor() {
    }

	
    public AcqVendor(AcqVendorId id) {
        this.id = id;
    }
    public AcqVendor(AcqVendorId id, String vendor, String address, String city, String state, String country, String pin, String phone, String fax, String email, String entryDate, Set acqApprovalHeaders) {
       this.id = id;
       this.vendor = vendor;
       this.address = address;
       this.city = city;
       this.state = state;
       this.country = country;
       this.pin = pin;
       this.phone = phone;
       this.fax = fax;
       this.email = email;
       this.entryDate = entryDate;
       this.acqApprovalHeaders = acqApprovalHeaders;
    }
   
    public AcqVendorId getId() {
        return this.id;
    }
    
    public void setId(AcqVendorId id) {
        this.id = id;
    }
    public String getVendor() {
        return this.vendor;
    }
    
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPin() {
        return this.pin;
    }
    
    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEntryDate() {
        return this.entryDate;
    }
    
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
    public Set getAcqApprovalHeaders() {
        return this.acqApprovalHeaders;
    }

     public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getVendorCurrency() {
        return vendorCurrency;
    }

    public void setVendorCurrency(String vendorCurrency) {
        this.vendorCurrency = vendorCurrency;
    }

 public void setAcqApprovalHeaders(Set acqApprovalHeaders) {
        this.acqApprovalHeaders = acqApprovalHeaders;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }



}


