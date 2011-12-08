/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;


import org.apache.struts.upload.FormFile;

/**
 *
 * @author akhtar
 */
public class VoterRegActionForm extends org.apache.struts.action.ActionForm {
    
 private String button;
private String enrollment;
private String institute_id;
private String course;
private String department;
private String f_name;
private String m_name;
private String gender;
private String city;
private String city1;
private String state;
private String state1;
private String country;
private String country1;
private String c_add;
private String p_add;
private String v_name;
private String email;
private String j_date;
private String b_date;
private String year;
private String duration;
private String session;
private String m_number;
private String zipcode;
private String zipcode1;
private String page;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
private String status;

    private byte[] uploadedFile;
    private FormFile img;
    private String filename;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }


    public FormFile getImg() {
        return img;
    }

    public void setImg(FormFile img) {
        this.img = img;
    }
    public byte[] getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(byte[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }



  
    public String getJ_date() {
        return j_date;
    }

    public void setJ_date(String j_date) {
        this.j_date = j_date;
    }


    public String getB_date() {
        return b_date;
    }

    public void setB_date(String b_date) {
        this.b_date = b_date;
    }



    
    

    public String getC_add() {
        return c_add;
    }

    public void setC_add(String c_add) {
        this.c_add = c_add;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

  

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_number() {
        return m_number;
    }

    public void setM_number(String m_number) {
        this.m_number = m_number;
    }

    public String getP_add() {
        return p_add;
    }

    public void setP_add(String p_add) {
        this.p_add = p_add;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getZipcode1() {
        return zipcode1;
    }

    public void setZipcode1(String zipcode1) {
        this.zipcode1 = zipcode1;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getInstitute_id() {
        return institute_id;
    }

    public void setInstitute_id(String institute_id) {
        this.institute_id = institute_id;
    }

   
}
