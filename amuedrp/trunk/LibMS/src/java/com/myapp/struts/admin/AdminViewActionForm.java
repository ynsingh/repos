/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Dushyant
 */
public class AdminViewActionForm   extends org.apache.struts.action.ActionForm {
    
    private String library_id;
    private String query_id;
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
 @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        //if (getStaff_id() == null ) {
           // errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        //}
        return null;
    }
    
@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// reset properties
		institute_name = "";
                abbreviated_name="";


	}
    /**
     * @return
     */
    public String getLibrary_id() {
        return library_id;
    }

     public String getInstitute_name() {
        return institute_name;
    }

    public int getRegistration_request_id() {
        return registration_request_id;
    }
    public String getAbbreviated_name() {
        return abbreviated_name;
    }

        public String getInstitute_address() {
        return institute_address;
    }
        public String getCity() {
        return city;
    }

         public String getState() {
        return state;
    }
         public String getCountry() {
        return country;
    }

        public String getPin() {
        return pin;
    }

        public String getLand_line_no() {
        return land_line_no;
    }
        public String getMobile_no() {
        return mobile_no;
    }

        public String getInstitute_domain() {
        return institute_domain;
    }
        public String getInstitute_website() {
        return institute_website;
    }

        public String getAdmin_fname() {
        return admin_fname;
    }
        public String getAdmin_lname() {
        return admin_lname;
    }
        public String getAdmin_designation() {
        return admin_designation;
    }
        public String getType_of_institute() {
        return type_of_institute;
    }
        public String getAdmin_email() {
        return admin_email;
    }
        public String getAdmin_password() {
        return admin_password;
    }


    /**
     * @param string
     */
        public void setRegistration_request_id(int i) {
        registration_request_id = i;
    }

    public void setInstitute_name(String string) {
        institute_name = string;
    }
    public void setAbbreviated_name(String string) {
        abbreviated_name= string;
    }

        public void setInstitute_address(String string) {
        institute_address =string;
    }
        public void setCity(String string) {
        city=string;
    }

         public void setState(String string) {
        state=string;
    }
         public void setCountry(String string) {
        country=string;
    }


        public void setPin(String string) {
        pin=string;
    }

        public void setLand_line_no(String string) {
        land_line_no=string;
    }
        public void setMobile_no(String string) {
        mobile_no=string;
    }

        public void setInstitute_domain(String string) {
        institute_domain=string;
    }
        public void setInstitute_website(String string) {
        institute_website=string;
    }

        public void setAdmin_fname(String string) {
        admin_fname=string;
    }
        public void setAdmin_lname(String string) {
       admin_lname=string;
    }
        public void setAdmin_designation(String string) {
        admin_designation=string;
    }
        public void setType_of_institute( String string) {
        type_of_institute=string;
    }
        public void setAdmin_email(String string) {
        admin_email=string;
    }
        public void setAdmin_password(String string) {
         admin_password=string;
    }

    /**
     * @param string
     */
    public void setLibrary_id(String string) {
        library_id = string;
    }
     
    /**
     * @return
     */


    /**
     *
     */
    public AdminViewActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
     
   

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
     * @return the query_id
     */
    public String getQuery_id() {
        return query_id;
    }

    /**
     * @param query_id the query_id to set
     */
    public void setQuery_id(String query_id) {
        this.query_id = query_id;
    }
}
