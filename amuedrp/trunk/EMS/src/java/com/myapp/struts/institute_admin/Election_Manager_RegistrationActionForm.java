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

   
    public String getFirst_name() {
        return first_name;
    }

   
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

   
    public String getLast_name() {
        return last_name;
    }

   
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

   
    public String getAddress1() {
        return address1;
    }

    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

   
    public String getCity1() {
        return city1;
    }

   
    public void setCity1(String city1) {
        this.city1 = city1;
    }

    
    public String getState1() {
        return state1;
    }

   
    public void setState1(String state1) {
        this.state1 = state1;
    }

   
    public String getCountry1() {
        return country1;
    }

    
    public void setCountry1(String country1) {
        this.country1 = country1;
    }

  
    public String getGender() {
        return gender;
    }

   
    public void setGender(String gender) {
        this.gender = gender;
    }

   
    public String getContact_no() {
        return contact_no;
    }

    
    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

   
    public String getMobile_no() {
        return mobile_no;
    }

   
    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

   
    public String getDepartment() {
        return department;
    }

   
    public void setDepartment(String department) {
        this.department = department;
    }

   
    public String getStaff_id() {
        return staff_id;
    }

   
    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

   
    public String getManager_id() {
        return manager_id;
    }

   
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    
    public String getInstitute_id() {
        return institute_id;
    }

   
    public void setInstitute_id(String institute_id) {
        this.institute_id = institute_id;
    }

   
    public String getUser_id() {
        return user_id;
    }

   
    public void setUser_id(String login_id) {
        this.user_id = login_id;
    }

  
    public String getPassword() {
        return password;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }

   
    public String getZip1() {
        return zip1;
    }

    
    public void setZip1(String zip1) {
        this.zip1 = zip1;
    }

    
    public String getRepassword() {
        return repassword;
    }

   
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

   
    public String getEmail_id() {
        return email_id;
    }

   
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

   
    public String getStatus() {
        return status;
    }

   
    public void setStatus(String status) {
        this.status = status;
    }



}
