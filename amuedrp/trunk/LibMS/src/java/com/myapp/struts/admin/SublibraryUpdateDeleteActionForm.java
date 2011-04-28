/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class SublibraryUpdateDeleteActionForm extends org.apache.struts.action.ActionForm {
    private String  button;
    private String faculty;
    private String sublib_name;
    private String sublibrary_id;
    private String department_head_name;
    private String department_address;
    private String country;
    private String state;
    private String district;
    private String pincode;
    private String department_email;
    private String faculty1;
    private String sublib_name1;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment_address() {
        return department_address;
    }

    public void setDepartment_address(String department_address) {
        this.department_address = department_address;
    }

    public String getDepartment_email() {
        return department_email;
    }

    public void setDepartment_email(String department_email) {
        this.department_email = department_email;
    }

    public String getDepartment_head_name() {
        return department_head_name;
    }

    public void setDepartment_head_name(String department_head_name) {
        this.department_head_name = department_head_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSublib_name() {
        return sublib_name;
    }

    public void setSublib_name(String sublib_name) {
        this.sublib_name = sublib_name;
    }

    public String getSublibrary_id() {
        return sublibrary_id;
    }

    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

    /**
     * @return
     */
    
    /**
     *
     */
    public SublibraryUpdateDeleteActionForm() {
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

    /**
     * @return the faculty1
     */
    public String getFaculty1() {
        return faculty1;
    }

    /**
     * @param faculty1 the faculty1 to set
     */
    public void setFaculty1(String faculty1) {
        this.faculty1 = faculty1;
    }

    /**
     * @return the sublib_name1
     */
    public String getSublib_name1() {
        return sublib_name1;
    }

    /**
     * @param sublib_name1 the sublib_name1 to set
     */
    public void setSublib_name1(String sublib_name1) {
        this.sublib_name1 = sublib_name1;
    }
}
