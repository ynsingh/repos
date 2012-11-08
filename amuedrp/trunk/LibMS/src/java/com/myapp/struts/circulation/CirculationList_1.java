/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.hbm.AcqBibliographyDetails;
import com.myapp.struts.hbm.AcqApproval;
//import com.myapp.struts.hbm.AcqBudgetAllocation;
import java.io.Serializable;

/**
 *
 * @author System Administrator
 */
public class CirculationList_1 implements Serializable{
    private String memId,returning_date,fname,mname,lname,main_entry,title,member_id,paydate,paymod;

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getPaymod() {
        return paymod;
    }

    public void setPaymod(String paymod) {
        this.paymod = paymod;
    }

    
     private String faculty_name;
    private String dept_name;
    private String course_name;
    private String req_date,user_name,library_name,expiry_date;

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

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getLibrary_name() {
        return library_name;
    }

    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }
    private Float fine_amt;
    private Double paid;

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    

    /**
     * @return the member_id
     */
    public String getMember_id() {
        return member_id;
    }

    /**
     * @param member_id the member_id to set
     */
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    /**
     * @return the returning_date
     */
    public String getReturning_date() {
        return returning_date;
    }

    /**
     * @param returning_date the returning_date to set
     */
    public void setReturning_date(String returning_date) {
        this.returning_date = returning_date;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * @param mname the mname to set
     */
    public void setMname(String mname) {
        this.mname = mname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the fine_amt
     */
   
    /**
     * @return the main_entry
     */
    public String getMain_entry() {
        return main_entry;
    }

    /**
     * @param main_entry the main_entry to set
     */
    public void setMain_entry(String main_entry) {
        this.main_entry = main_entry;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the fine_amt
     */
    public Float getFine_amt() {
        return fine_amt;
    }

    /**
     * @param fine_amt the fine_amt to set
     */
    public void setFine_amt(Float fine_amt) {
        this.fine_amt = fine_amt;
    }
    
}
