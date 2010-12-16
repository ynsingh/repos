/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

/**
 *
 * @author Algox
 */
public class PersonalProfileForm  {
    
    private String name;
    private String empcode;
    private String doj;
    private String dol;

    private String message ="";
    private String error = " <font color='green'>* Department name Cannot be empty </font>";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankaccNo() {
        return bankaccNo;
    }

    public void setBankaccNo(String bankaccNo) {
        this.bankaccNo = bankaccNo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getDesignation() {
        return designation;
    }

    public void setDesignation(int designation) {
        this.designation = designation;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getDol() {
        return dol;
    }

    public void setDol(String dol) {
        this.dol = dol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPfaccNo() {
        return pfaccNo;
    }

    public void setPfaccNo(String pfaccNo) {
        this.pfaccNo = pfaccNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    private String dob;
    private int department;
    private int designation;
    private String bankaccNo;
    private String pfaccNo;
    private String panNo;
    private String address;
    private String phone;
    private String email;
    private int code;

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * @return
     */
   

    /**
     *
     */
    public PersonalProfileForm() {
        super();
        // TODO Auto-generated constructor stub
    }

}
