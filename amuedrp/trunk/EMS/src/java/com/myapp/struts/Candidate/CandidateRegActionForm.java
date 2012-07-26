/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;


import org.apache.struts.upload.FormFile;

/**
 *
 * @author akhtar
 */
public class CandidateRegActionForm extends org.apache.struts.action.ActionForm {
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
private String reason;
private String rejectedReason;
private String requestDate;
private String acceptedDate;
private String proposedBy;
private String secondedBy;
private String positionAccepted;
private String candidateid;

    public String getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(String candidateid) {
        this.candidateid = candidateid;
    }

    public String getPositionAccepted() {
        return positionAccepted;
    }

    public void setPositionAccepted(String positionAccepted) {
        this.positionAccepted = positionAccepted;
    }

    public String getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(String acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public String getProposedBy() {
        return proposedBy;
    }

    public void setProposedBy(String proposedBy) {
        this.proposedBy = proposedBy;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSecondedBy() {
        return secondedBy;
    }

    public void setSecondedBy(String secondedBy) {
        this.secondedBy = secondedBy;
    }

  private byte[] uploadedFile;
    private FormFile img;
    private String filename;
    private String alternateemail;

    public String getAlternateemail() {
        return alternateemail;
    }

    public void setAlternateemail(String alternateemail) {
        this.alternateemail = alternateemail;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }



private String enrolled_in;
private String p_marks;
private String p_attendence;
private String backlog;
private String criminal;
private String indisc;
private String position;
private String status;
private String elections;

    public String getBacklog() {
        return backlog;
    }

    public void setBacklog(String backlog) {
        this.backlog = backlog;
    }

    public String getCriminal() {
        return criminal;
    }

    public void setCriminal(String criminal) {
        this.criminal = criminal;
    }

    public String getEnrolled_in() {
        return enrolled_in;
    }

    public void setEnrolled_in(String enrolled_in) {
        this.enrolled_in = enrolled_in;
    }

    public String getIndisc() {
        return indisc;
    }

    public void setIndisc(String indisc) {
        this.indisc = indisc;
    }

    public String getP_attendence() {
        return p_attendence;
    }

    public void setP_attendence(String p_attendence) {
        this.p_attendence = p_attendence;
    }

    public String getP_marks() {
        return p_marks;
    }

    public void setP_marks(String p_marks) {
        this.p_marks = p_marks;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
    public String getButton() {
        return button;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    /**
     * @param button the button to set
     */
    public void setButton(String button) {
        this.button = button;
    }

    /**
     * @return the enrollment
     */
    public String getEnrollment() {
        return enrollment;
    }

    /**
     * @param enrollment the enrollment to set
     */
    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
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
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the f_name
     */
    public String getF_name() {
        return f_name;
    }

    /**
     * @param f_name the f_name to set
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * @return the m_name
     */
    public String getM_name() {
        return m_name;
    }

    /**
     * @param m_name the m_name to set
     */
    public void setM_name(String m_name) {
        this.m_name = m_name;
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
     * @return the c_add
     */
    public String getC_add() {
        return c_add;
    }

    /**
     * @param c_add the c_add to set
     */
    public void setC_add(String c_add) {
        this.c_add = c_add;
    }

    /**
     * @return the p_add
     */
    public String getP_add() {
        return p_add;
    }

    /**
     * @param p_add the p_add to set
     */
    public void setP_add(String p_add) {
        this.p_add = p_add;
    }

    /**
     * @return the v_name
     */
    public String getV_name() {
        return v_name;
    }

    /**
     * @param v_name the v_name to set
     */
    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the j_date
     */
    public String getJ_date() {
        return j_date;
    }

    /**
     * @param j_date the j_date to set
     */
    public void setJ_date(String j_date) {
        this.j_date = j_date;
    }

    /**
     * @return the b_date
     */
    public String getB_date() {
        return b_date;
    }

    /**
     * @param b_date the b_date to set
     */
    public void setB_date(String b_date) {
        this.b_date = b_date;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * @return the m_number
     */
    public String getM_number() {
        return m_number;
    }

    /**
     * @param m_number the m_number to set
     */
    public void setM_number(String m_number) {
        this.m_number = m_number;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the zipcode1
     */
    public String getZipcode1() {
        return zipcode1;
    }

    /**
     * @param zipcode1 the zipcode1 to set
     */
    public void setZipcode1(String zipcode1) {
        this.zipcode1 = zipcode1;
    }

    /**
     * @return the elections
     */
    public String getElections() {
        return elections;
    }

    /**
     * @param elections the elections to set
     */
    public void setElections(String elections) {
        this.elections = elections;
    }
}