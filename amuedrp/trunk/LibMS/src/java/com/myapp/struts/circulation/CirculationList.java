/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.hbm.AcqBibliographyDetails;
import com.myapp.struts.hbm.AcqApproval;
//import com.myapp.struts.hbm.AcqBudgetAllocation;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author System Administrator
 */
public class CirculationList implements Serializable{
    private String memid;
     private String fname;
    private String mname;
     private String lname;
    private String title;
    private String main_entry;
    private String issue_date;
    private String due_date;
    private String faculty_name;
    private String dept_name;
    private String course_name;
    private String req_date,user_name,library_name,expiry_date;
    private ImageIcon image;
    

    
    

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(byte[] image) {
            
        this.image = new ImageIcon((byte[]) image);
    }
    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
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


    /**
     * @return the memid
     */
    public String getMemid() {
        return memid;
    }

    /**
     * @param memid the memid to set
     */
    public void setMemid(String memid) {
        this.memid = memid;
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
     * @return the issue_date
     */
    public String getIssue_date() {
        return issue_date;
    }

    /**
     * @param issue_date the issue_date to set
     */
    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    /**
     * @return the due_date
     */
    public String getDue_date() {
        return due_date;
    }

    /**
     * @param due_date the due_date to set
     */
    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    /**
     * @return the memId
     */
    

}
