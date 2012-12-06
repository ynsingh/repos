/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Requests;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ProgrammeCourseDao;
import org.IGNOU.ePortfolio.Model.PersonalInfo;
import org.IGNOU.ePortfolio.Model.UserPersonalRequest;
import org.IGNOU.ePortfolio.DAO.RequestDao;
import org.json.JSONObject;

/**
 *
 * @author Vinay Kr. Sharma
 */
public class RequestAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private RequestDao dao = new RequestDao();
    private List<UserPersonalRequest> UsrReqList;
    private Integer requestId;
    private String requestorId, requestType, reason, newRecord, recordArchive, recordProof;
    private Date requestDate, updatedDate;
    private Boolean status;
    private String msg, noRequestFound = getText("msg.request.notFound");
    /*Personal Inforamtion*/
    private List<PersonalInfo> personalInfoList;
    private long personalInfoId, aadhaarNo;
    private String emailId, firstName, lastName, fatherName, motherName, otherGuardian, gender, pbirth, mstatus, passportNo, panNo, castCategory, religion, nationality, languageKnown;
    private Date dateOfBirth;
    private Integer activeStatus;
    private String Headtitle;

    public RequestAction() {
    }

    /*List of all user's Request*/
    public String UserRequestList() throws Exception {
        UsrReqList = dao.userRequestList();
        Headtitle = "New Requests";
        return SUCCESS;
    }

    /*Edit User Personal Information Individually by Admin*/
    public String EditUserDetails() throws Exception {
        personalInfoList = dao.EditPersonalInfo(requestorId);
        UsrReqList = dao.RequestInfoList(requestId);
        return SUCCESS;
    }

    /*Update User Personal Information Individually by Admin.*/
    public String UpdateUserDetials() throws Exception {
        personalInfoList = dao.EditPersonalInfo(requestorId);
        JSONObject JsonObject = new JSONObject();
        JsonObject.put("firstName", personalInfoList.iterator().next().getFirstName());
        JsonObject.put("lastName", personalInfoList.iterator().next().getLastName());
        JsonObject.put("fatherName", personalInfoList.iterator().next().getFatherName());
        JsonObject.put("motherName", personalInfoList.iterator().next().getMotherName());
        JsonObject.put("otherGuardian", personalInfoList.iterator().next().getOtherGuardian());
        JsonObject.put("gender", personalInfoList.iterator().next().getGender());
        JsonObject.put("dateOfBirth", personalInfoList.iterator().next().getDateOfBirth());
        JsonObject.put("pbirth", personalInfoList.iterator().next().getPbirth());
        JsonObject.put("mstatus", personalInfoList.iterator().next().getMstatus());
        JsonObject.put("aadhaarNo", personalInfoList.iterator().next().getAadhaarNo());
        JsonObject.put("passportNo", personalInfoList.iterator().next().getPassportNo());
        JsonObject.put("panNo", personalInfoList.iterator().next().getPanNo());
        JsonObject.put("activeStatus", personalInfoList.iterator().next().getActiveStatus());
        JsonObject.put("castCategory", personalInfoList.iterator().next().getCastCategory());
        JsonObject.put("religion", personalInfoList.iterator().next().getReligion());
        JsonObject.put("nationality", personalInfoList.iterator().next().getNationality());
        recordArchive = JsonObject.toString();
        // recordArchive = "Temp Value";
        dao.UpdatePersonalInfo(requestId, recordArchive, personalInfoList.iterator().next().getPersonalInfoId(), requestorId, firstName, lastName, fatherName, motherName, otherGuardian, gender, dateOfBirth, pbirth, mstatus, aadhaarNo, passportNo, panNo, activeStatus, castCategory, religion, nationality, languageKnown);
        return SUCCESS;
    }

    public String UserRequestUpdatedList() throws Exception {
        UsrReqList = dao.userRequestUpdatedList();
        if (UsrReqList.isEmpty()) {
            msg = noRequestFound;
        }
        Headtitle="Processed Requests";
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
     * @return the dao
     */
    public RequestDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(RequestDao dao) {
        this.dao = dao;
    }

    /**
     * @return the UsrReqList
     */
    public List<UserPersonalRequest> getUsrReqList() {
        return UsrReqList;
    }

    /**
     * @param UsrReqList the UsrReqList to set
     */
    public void setUsrReqList(List<UserPersonalRequest> UsrReqList) {
        this.UsrReqList = UsrReqList;
    }

    /**
     * @return the requestId
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the requestorId
     */
    public String getRequestorId() {
        return requestorId;
    }

    /**
     * @param requestorId the requestorId to set
     */
    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the newRecord
     */
    public String getNewRecord() {
        return newRecord;
    }

    /**
     * @param newRecord the newRecord to set
     */
    public void setNewRecord(String newRecord) {
        this.newRecord = newRecord;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * @return the recordArchive
     */
    public String getRecordArchive() {
        return recordArchive;
    }

    /**
     * @param recordArchive the recordArchive to set
     */
    public void setRecordArchive(String recordArchive) {
        this.recordArchive = recordArchive;
    }

    /**
     * @return the recordProof
     */
    public String getRecordProof() {
        return recordProof;
    }

    /**
     * @param recordProof the recordProof to set
     */
    public void setRecordProof(String recordProof) {
        this.recordProof = recordProof;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
     * @return the noRequestFound
     */
    public String getNoRequestFound() {
        return noRequestFound;
    }

    /**
     * @param noRequestFound the noRequestFound to set
     */
    public void setNoRequestFound(String noRequestFound) {
        this.noRequestFound = noRequestFound;
    }

    /**
     * @return the personalInfoList
     */
    public List<PersonalInfo> getPersonalInfoList() {
        return personalInfoList;
    }

    /**
     * @param personalInfoList the personalInfoList to set
     */
    public void setPersonalInfoList(List<PersonalInfo> personalInfoList) {
        this.personalInfoList = personalInfoList;
    }

    /**
     * @return the personalInfoId
     */
    public long getPersonalInfoId() {
        return personalInfoId;
    }

    /**
     * @param personalInfoId the personalInfoId to set
     */
    public void setPersonalInfoId(long personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    /**
     * @return the aadhaarNo
     */
    public long getAadhaarNo() {
        return aadhaarNo;
    }

    /**
     * @param aadhaarNo the aadhaarNo to set
     */
    public void setAadhaarNo(long aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @return the otherGuardian
     */
    public String getOtherGuardian() {
        return otherGuardian;
    }

    /**
     * @param otherGuardian the otherGuardian to set
     */
    public void setOtherGuardian(String otherGuardian) {
        this.otherGuardian = otherGuardian;
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
     * @return the castCategory
     */
    public String getCastCategory() {
        return castCategory;
    }

    /**
     * @param castCategory the castCategory to set
     */
    public void setCastCategory(String castCategory) {
        this.castCategory = castCategory;
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
     * @return the activeStatus
     */
    public Integer getActiveStatus() {
        return activeStatus;
    }

    /**
     * @param activeStatus the activeStatus to set
     */
    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    /**
     * @return the Headtitle
     */
    public String getHeadtitle() {
        return Headtitle;
    }

    /**
     * @param Headtitle the Headtitle to set
     */
    public void setHeadtitle(String Headtitle) {
        this.Headtitle = Headtitle;
    }
}
