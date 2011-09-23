/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.hbm.*;

//import com.myapp.struts.hbm.AcqBudgetAllocation;
import com.myapp.struts.hbm.CirCheckout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
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
   private java.awt.image.BufferedImage image1;
    private byte[] image;
    private CirCheckout cirCheckout;
    private Library library;
    private Login login;
    private CirMemberDetail cirMemberDetail;
    private CirMemberAccount cirMemberAccount;
    private DocumentDetails documentDetails;
    private Faculty faculty;
    private Department department;
    private Courses courses;

   
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        this.dept_name=department.getDeptName();
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        this.faculty_name=faculty.getFacultyName();
    }

     public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
        
    }


    public CirCheckout getCirCheckout() {
        return cirCheckout;
    }

    public void setCirCheckout(CirCheckout cirCheckout) {
        this.cirCheckout = cirCheckout;
        this.setMemid(cirCheckout.getMemid());
        this.issue_date = cirCheckout.getIssueDate();
        this.due_date = cirCheckout.getDueDate();
    }

    public CirMemberAccount getCirMemberAccount() {
        return cirMemberAccount;
    }

    public void setCirMemberAccount(CirMemberAccount cirMemberAccount) {
        this.cirMemberAccount = cirMemberAccount;
        this.setReq_date(cirMemberAccount.getReqDate());
        this.setExpiry_date(cirMemberAccount.getExpiryDate());
    }

    public CirMemberDetail getCirMemberDetail() {
        return cirMemberDetail;
    }

    public void setCirMemberDetail(CirMemberDetail cirMemberDetail) {
        this.cirMemberDetail = cirMemberDetail;
        this.setImage(cirMemberDetail.getImage());
        this.setFname(cirMemberDetail.getFname());
        this.mname = cirMemberDetail.getMname();
        this.lname = cirMemberDetail.getLname();
    }

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
        this.title = documentDetails.getTitle();
        this.main_entry = documentDetails.getMainEntry();
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
        this.setLibrary_name(library.getLibraryName());
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
        this.setUser_name(login.getUserName());
    }

    

  public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public BufferedImage getImage1() {
       if(image!=null)
        { InputStream in = new ByteArrayInputStream(this.image);
	try{
        image1 = ImageIO.read(in);
        }catch(Exception e){}

        return image1;
    }else{
    return null;
    }
    }

    public void setImage1(BufferedImage image1) {
        this.image1 = image1;
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
       // this.course_name=this.courses.getCourseName();
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
