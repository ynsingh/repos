/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.UserListDao;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author Amit
 */
public class PersonalInformationAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private String msg;
    private List<User> userlist;
    private UserListDao uldao = new UserListDao();
    private long registrationId;
    private String fname;
    private String mname;
    private String lname;
    private String fatherName;
    private String motherName;
    private String otherGardian;
    private Date dateOfBirth;
    private String pbirth;
    private String mstatus;
    private Long aadhaarNo;
    private String passportNo;
    private String panNo;
    private String religion;
    private String nationality;
    private String languageKnown;

    @Override
    public String execute() throws Exception {
        // userlist = uldao.UserListByUserId(user_id);
        return SUCCESS;
    }

    public String UpdateUserInfo() {
        //  uldao.UserUpdate(registrationId, fname, mname, lname, fatherName, motherName, otherGardian, dateOfBirth, pbirth, mstatus, aadhaarNo, passportNo, panNo, religion, nationality, languageKnown);
        return SUCCESS;
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
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the userlist
     */
    public List<User> getUserlist() {
        return userlist;
    }

    /**
     * @param userlist the userlist to set
     */
    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    /**
     * @return the uldao
     */
    public UserListDao getUldao() {
        return uldao;
    }

    /**
     * @param uldao the uldao to set
     */
    public void setUldao(UserListDao uldao) {
        this.uldao = uldao;
    }

    /**
     * @return the registrationId
     */
    public long getRegistrationId() {
        return registrationId;
    }

    /**
     * @param registrationId the registrationId to set
     */
    public void setRegistrationId(long registrationId) {
        this.registrationId = registrationId;
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
     * @return the fatherName
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * @param fatherName the fatherName to set
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     * @return the motherName
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     * @param motherName the motherName to set
     */
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    /**
     * @return the otherGardian
     */
    public String getOtherGardian() {
        return otherGardian;
    }

    /**
     * @param otherGardian the otherGardian to set
     */
    public void setOtherGardian(String otherGardian) {
        this.otherGardian = otherGardian;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the pbirth
     */
    public String getPbirth() {
        return pbirth;
    }

    /**
     * @param pbirth the pbirth to set
     */
    public void setPbirth(String pbirth) {
        this.pbirth = pbirth;
    }

    /**
     * @return the mstatus
     */
    public String getMstatus() {
        return mstatus;
    }

    /**
     * @param mstatus the mstatus to set
     */
    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    /**
     * @return the aadhaarNo
     */
    public Long getAadhaarNo() {
        return aadhaarNo;
    }

    /**
     * @param aadhaarNo the aadhaarNo to set
     */
    public void setAadhaarNo(Long aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    /**
     * @return the passportNo
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @param passportNo the passportNo to set
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return the panNo
     */
    public String getPanNo() {
        return panNo;
    }

    /**
     * @param panNo the panNo to set
     */
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the languageKnown
     */
    public String getLanguageKnown() {
        return languageKnown;
    }

    /**
     * @param languageKnown the languageKnown to set
     */
    public void setLanguageKnown(String languageKnown) {
        this.languageKnown = languageKnown;
    }
}
