/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import java.io.Serializable;

/**
 *
 * @author edrp01
 */
public class MixCirMemberDetail implements Serializable {
    private String memId ;
  private String  fname;
  private String  mname;
  private String  lname;
  private String  email;
  private String  req_date;
  private String  expiry_date;
 private String    sublibrary_id;
private String    faculty_name;
private String    dept_name;
private String    course_name;
private String library_id;
private String requestdate;
private String sublib_name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
private String status;

    public String getSublib_name() {
        return sublib_name;
    }

    public void setSublib_name(String sublib_name) {
        this.sublib_name = sublib_name;
    }

private String library_name;
private String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getLibrary_name() {
        return library_name;
    }

    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

  

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

   

    public String getSublibrary_id() {
        return sublibrary_id;
    }

    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

//  private  CirMemberDetail cirmemberdetail;
//  private  CirMemberAccount cirmemberaccount;
//
//    public CirMemberAccount getCirmemberaccount() {
//        return cirmemberaccount;
//    }
//
//    public void setCirmemberaccount(CirMemberAccount cirmemberaccount) {
//        this.cirmemberaccount = cirmemberaccount;
//    }
//
//    public CirMemberDetail getCirmemberdetail() {
//        return cirmemberdetail;
//    }
//
//    public void setCirmemberdetail(CirMemberDetail cirmemberdetail) {
//        this.cirmemberdetail = cirmemberdetail;
//    }

}
