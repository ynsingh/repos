/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.NewUserOrgRegistration;

import javax.faces.model.SelectItem;


/**
 *
 * @author ERP
 */
public class UserOrgRegBeans {

    /** Creates a new instance of UserOrgRegBeans */
    public UserOrgRegBeans() {
    }
    private String fullName = new String();
    private String emailId = new String();
    private String gender = new String();
    private String address = new String();
    private int orgCode;
    private String phNum = new String();
    private String dob = new String();
    private String fatherName = new String();

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    SelectItem[] item;

    public SelectItem[] getItem() {
        return item;
    }

    public void setItem(SelectItem[] item) {
        this.item = item;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }
}
