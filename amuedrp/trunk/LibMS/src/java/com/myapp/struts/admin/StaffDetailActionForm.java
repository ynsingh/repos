/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class StaffDetailActionForm extends ValidatorForm {
    private String staff_id;
    private String library_id;
    private String employee_id;
    private String sublibrary_id;
    private String first_name;
    private String last_name;
    private String contact_no;
    private String mobile_no;
    private String email_id;
    private String do_joining;
    private String do_releaving;
    private String date_of_birth;
    private String address1;
    private String city1;
    private String state1;
    private String country1;
    private String zip1;
    private String address2;
    private String city2;
    private String state2;
    private String country2;
    private String zip2;
    private String father_name;
    private String gender;
    private String courtesy;
    private String button;
    private String role;
 private byte[] uploadedFile;
    

   
    public StaffDetailActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
  
     */
    
    /**
     * @return the employee_id
     */
    public String getEmployee_id() {
        return employee_id;
    }

    /**
     * @param employee_id the employee_id to set
     */
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

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
     * @return the do_joining
     */
    public String getDo_joining() {
        return do_joining;
    }

    /**
     * @param do_joining the do_joining to set
     */
    public void setDo_joining(String do_joining) {
        this.do_joining = String.valueOf(do_joining);
    }

    /**
     * @return the do_releaving
     */
    public String getDo_releaving() {
        return do_releaving;
    }

    /**
     * @param do_releaving the do_releaving to set
     */
    public void setDo_releaving(String do_releaving) {
        this.do_releaving = do_releaving;
    }

    /**
     * @return the date_of_birth
     */
    public String getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * @param date_of_birth the date_of_birth to set
     */
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth= date_of_birth;
        
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the city1
     */
    public String getCity1() {
        return city1;
    }

    /**
     * @param city1 the city1 to set
     */
    public void setCity1(String city1) {
        this.city1 = city1;
    }

    /**
     * @return the state1
     */
    public String getState1() {
        return state1;
    }

    /**
     * @param state1 the state1 to set
     */
    public void setState1(String state1) {
        this.state1 = state1;
    }

    /**
     * @return the country1
     */
    public String getCountry1() {
        return country1;
    }

    /**
     * @param country1 the country1 to set
     */
    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    /**
     * @return the zip1
     */
    public String getZip1() {
        return zip1;
    }

    /**
     * @param zip1 the zip1 to set
     */
    public void setZip1(String zip1) {
        this.zip1 = zip1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the city2
     */
    public String getCity2() {
        return city2;
    }

    /**
     * @param city2 the city2 to set
     */
    public void setCity2(String city2) {
        this.city2 = city2;
    }

    /**
     * @return the state2
     */
    public String getState2() {
        return state2;
    }

    /**
     * @param state2 the state2 to set
     */
    public void setState2(String state2) {
        this.state2 = state2;
    }

    /**
     * @return the country2
     */
    public String getCountry2() {
        return country2;
    }

    /**
     * @param country2 the country2 to set
     */
    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    /**
     * @return the zip2
     */
    public String getZip2() {
        return zip2;
    }

    /**
     * @param zip2 the zip2 to set
     */
    public void setZip2(String zip2) {
        this.zip2 = zip2;
    }

    /**
     * @return the father_name
     */
    public String getFather_name() {
        return father_name;
    }

    /**
     * @param father_name the father_name to set
     */
    public void setFather_name(String father_name) {
        this.father_name = father_name;
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

    
    public String getCourtesy() {
        return courtesy;
    }

    
    public void setCourtesy(String courtesy) {
        this.courtesy = courtesy;
    }

    /**
     * @return the button
     */
    public String getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(String button) {
        this.button = button;
    }

     public void reset(ActionMapping mapping, HttpServletRequest request) {


			
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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the sublibrary_id
     */
    public String getSublibrary_id() {
        return sublibrary_id;
    }

    /**
     * @param sublibrary_id the sublibrary_id to set
     */
    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

    /**
     * @return the library_id
     */
    public String getLibrary_id() {
        return library_id;
    }

    /**
     * @param library_id the library_id to set
     */
    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    /**
     * @return the uploadedFile
     */
    public byte[] getUploadedFile() {
        return uploadedFile;
    }

    /**
     * @param uploadedFile the uploadedFile to set
     */
    public void setUploadedFile(byte[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
