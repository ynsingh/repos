/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Edrp-04
 */
public class Election_Manager_RegistrationActionForm extends ValidatorForm  {
    
    private String first_name;
    private String last_name;
    private String address1;
    private String city1;
    private String state1;
    private String country1;
    private String gender;
    private String contact_no;
    private String mobile_no;
    private String department;
    private String staff_id;
    private String manager_id;
    private String institute_id;
    private String user_id;
    private String password;
    private String zip1;
    private String repassword;
    private String email_id;
    private String status;

    /**
     * @return
     */
    

    /**
     *
     */
    public Election_Manager_RegistrationActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

   
   /* public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }*/

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the address
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address the address to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the city
     */
    public String getCity1() {
        return city1;
    }

    /**
     * @param city the city to set
     */
    public void setCity1(String city1) {
        this.city1 = city1;
    }

    /**
     * @return the state
     */
    public String getState1() {
        return state1;
    }

    /**
     * @param state the state to set
     */
    public void setState1(String state1) {
        this.state1 = state1;
    }

    /**
     * @return the country
     */
    public String getCountry1() {
        return country1;
    }

    /**
     * @param country the country to set
     */
    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the contact_no
     */
    public String getContact_no() {
        return contact_no;
    }

    /**
     * @param contact_no the contact_no to set
     */
    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    /**
     * @return the mobile_no
     */
    public String getMobile_no() {
        return mobile_no;
    }

    /**
     * @param mobile_no the mobile_no to set
     */
    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    /**
     * @return the department_id
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department_id the department_id to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the staff_id
     */
    public String getStaff_id() {
        return staff_id;
    }

    /**
     * @param staff_id the staff_id to set
     */
    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    /**
     * @return the manager_id
     */
    public String getManager_id() {
        return manager_id;
    }

    /**
     * @param manager_id the manager_id to set
     */
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    /**
     * @return the institute_id
     */
    public String getInstitute_id() {
        return institute_id;
    }

    /**
     * @param institute_id the institute_id to set
     */
    public void setInstitute_id(String institute_id) {
        this.institute_id = institute_id;
    }

    /**
     * @return the login_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param login_id the login_id to set
     */
    public void setUser_id(String login_id) {
        this.user_id = login_id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the pin
     */
    public String getZip1() {
        return zip1;
    }

    /**
     * @param pin the pin to set
     */
    public void setZip1(String zip1) {
        this.zip1 = zip1;
    }

    /**
     * @return the repassword
     */
    public String getRepassword() {
        return repassword;
    }

    /**
     * @param repassword the repassword to set
     */
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    /**
     * @return the email_id
     */
    public String getEmail_id() {
        return email_id;
    }

    /**
     * @param email_id the email_id to set
     */
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }



}
