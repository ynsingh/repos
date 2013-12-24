/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class AdminViewActionForm   extends org.apache.struts.action.ActionForm {
    
    private String institute_id;
    private int registration_request_id ;
private String institute_name ;
private String abbreviated_name ;
private String institute_address ;
private String city ;
private String state ;
private String country ;
private String pin ;
private String land_line_no ;
private String mobile_no ;
private String institute_domain ;
private String institute_website ;
private String admin_fname ;
private String admin_lname ;
private String admin_designation ;
private String type_of_institute;
private String admin_email;
private String admin_password;
private String library_name;
private String courtesy;
private String gender;
private String user_id;
private String password;
private String button;
private String website;

public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
 
/**
     *
     */
    public AdminViewActionForm() {
        super();
        // TODO Auto-generated constructor stub
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
     * @return the registration_request_id
     */
    public int getRegistration_request_id() {
        return registration_request_id;
    }

    /**
     * @param registration_request_id the registration_request_id to set
     */
    public void setRegistration_request_id(int registration_request_id) {
        this.registration_request_id = registration_request_id;
    }

    /**
     * @return the institute_name
     */
    public String getInstitute_name() {
        return institute_name;
    }

    /**
     * @param institute_name the institute_name to set
     */
    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

    /**
     * @return the abbreviated_name
     */
    public String getAbbreviated_name() {
        return abbreviated_name;
    }

    /**
     * @param abbreviated_name the abbreviated_name to set
     */
    public void setAbbreviated_name(String abbreviated_name) {
        this.abbreviated_name = abbreviated_name;
    }

    /**
     * @return the institute_address
     */
    public String getInstitute_address() {
        return institute_address;
    }

    /**
     * @param institute_address the institute_address to set
     */
    public void setInstitute_address(String institute_address) {
        this.institute_address = institute_address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the land_line_no
     */
    public String getLand_line_no() {
        return land_line_no;
    }

    /**
     * @param land_line_no the land_line_no to set
     */
    public void setLand_line_no(String land_line_no) {
        this.land_line_no = land_line_no;
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
     * @return the institute_domain
     */
    public String getInstitute_domain() {
        return institute_domain;
    }

    /**
     * @param institute_domain the institute_domain to set
     */
    public void setInstitute_domain(String institute_domain) {
        this.institute_domain = institute_domain;
    }

    /**
     * @return the institute_website
     */
    public String getInstitute_website() {
        return institute_website;
    }

    /**
     * @param institute_website the institute_website to set
     */
    public void setInstitute_website(String institute_website) {
        this.institute_website = institute_website;
    }

    /**
     * @return the admin_fname
     */
    public String getAdmin_fname() {
        return admin_fname;
    }

    /**
     * @param admin_fname the admin_fname to set
     */
    public void setAdmin_fname(String admin_fname) {
        this.admin_fname = admin_fname;
    }

    /**
     * @return the admin_lname
     */
    public String getAdmin_lname() {
        return admin_lname;
    }

    /**
     * @param admin_lname the admin_lname to set
     */
    public void setAdmin_lname(String admin_lname) {
        this.admin_lname = admin_lname;
    }

    /**
     * @return the admin_designation
     */
    public String getAdmin_designation() {
        return admin_designation;
    }

    /**
     * @param admin_designation the admin_designation to set
     */
    public void setAdmin_designation(String admin_designation) {
        this.admin_designation = admin_designation;
    }

    /**
     * @return the type_of_institute
     */
    public String getType_of_institute() {
        return type_of_institute;
    }

    /**
     * @param type_of_institute the type_of_institute to set
     */
    public void setType_of_institute(String type_of_institute) {
        this.type_of_institute = type_of_institute;
    }

    /**
     * @return the admin_email
     */
    public String getAdmin_email() {
        return admin_email;
    }

    /**
     * @param admin_email the admin_email to set
     */
    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    /**
     * @return the admin_password
     */
    public String getAdmin_password() {
        return admin_password;
    }

    /**
     * @param admin_password the admin_password to set
     */
    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    /**
     * @return the library_name
     */
    public String getLibrary_name() {
        return library_name;
    }

    /**
     * @param library_name the library_name to set
     */
    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    /**
     * @return the courtesy
     */
    public String getCourtesy() {
        return courtesy;
    }

    /**
     * @param courtesy the courtesy to set
     */
    public void setCourtesy(String courtesy) {
        this.courtesy = courtesy;
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
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

   
   

}
