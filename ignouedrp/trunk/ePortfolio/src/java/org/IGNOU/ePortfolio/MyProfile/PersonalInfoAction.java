/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import in.ac.dei.edrp.api.DEIRemoteAccessAPI;
import in.ac.dei.edrp.api.StudentMasterBeanAPI;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.PersonalInfoDao;
import org.IGNOU.ePortfolio.DAO.UserListDao;
import org.IGNOU.ePortfolio.Model.PersonalInfo;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author IGNOU Team
 */
public class PersonalInfoAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private PersonalInfo pi = new PersonalInfo();
    private PersonalInfoDao dao = new PersonalInfoDao();
    private UserListDao udao = new UserListDao();
    private long registrationId;
    private String emailId;
    private String fname, mname, lname, fatherName, motherName, otherGardian, gender, pbirth, mstatus, castCategory, religion, nationality, languageKnown;
    private Date dateOfBirth;
    private Long aadhaarNo;
    private String passportNo, panNo;
    private Integer activeStatus;
    private List<User> UserInfoList;
    private String msg;
    private String infoUpdated = getText("msg.infoUpdated");
    /*Extra Variables used in Remote Access */
    private String skey = ReadPropertyFile("deiKey");
    private String src_id = ReadPropertyFile("deiScourceId");
    private String xfPath = ReadPropertyFile("xmlStudentPath");
    private String universityCode = "0001";
    private String xmlFile, RemotePersonalInfo, studentName, dob, UID, enrollmentNo, hindiName, hindiFatherName, hindiMotherName;
    private String randomNumber, hashCode;
    private SAXBuilder builder = new SAXBuilder();

    public PersonalInfoAction() {
    }

    public String ShowPersonalInfo() {
        setUserInfoList(udao.UserListByUserId(getUser_id()));
        if (getUserInfoList().isEmpty()) {
            try {
                getDEIPersonalInfo();
                // msg="hello";
            } catch (Exception ex) {
                Logger.getLogger(PersonalInfoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }
        return SUCCESS;
    }

    public String EditPersonal() {
        setUserInfoList(udao.UserListByUserId(getUser_id()));
        //setPersonalListList(getDao().PersonalInfoEdit(getPersonalInfoId()));
        return SUCCESS;
    }

    public String UpdatePersonalInformation() {
        //dao.PersonalInfoUpdate(registrationId, emailId, fname, lname, fatherName, motherName, otherGardian, gender, dateOfBirth, pbirth, mstatus, aadhaarNo, passportNo, panNo, activeStatus, castCategory, religion, nationality, languageKnown);
        udao.UserUpdate(registrationId, fname, mname, lname, fatherName, motherName, otherGardian, gender, dateOfBirth, pbirth, mstatus, aadhaarNo, passportNo, panNo, activeStatus, castCategory, religion, nationality, languageKnown);

        msg = infoUpdated;
        return SUCCESS;
    }

    /*Remote Data Access Start*/
    public String getDEIPersonalInfo() throws Exception {
        RemotePersonalInfo = DEIRemoteAccessAPI.getStudentInfo(user_id, universityCode, src_id);
        xmlFile = xfPath + user_id + ".xml";
        try {
            File fXmlFile = new File(xmlFile);
            if (fXmlFile.exists()) {
                readPersonalXML(fXmlFile);
            } else {
                msg = "File not found!";
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        return SUCCESS;
    }

    /*Edit EditDEIPersonalInfo*/
    public String EditDEIPersonalInfo() throws Exception {
        xmlFile = xfPath + user_id + ".xml";
        /*Read XML File and get TagValues*/
        try {
            File fXmlFile = new File(xmlFile);
            if (fXmlFile.exists()) {
                readPersonalXML(fXmlFile);
            } else {
                msg = "File not found!";
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        return SUCCESS;
    }

    /*Update Personal Informaftion into Remote Location(DEI)*/
    public String UpdateDEIPersonalInfo() throws Exception {
        StudentMasterBeanAPI smBean = new StudentMasterBeanAPI();
        String fName = StringUtils.substringBefore(studentName, " ");
        String mName = StringUtils.substringBetween(studentName, " ", " ");
        String lName = StringUtils.substringAfterLast(studentName, " ");
        String ffName = StringUtils.substringBefore(fatherName, " ");
        String fmName = StringUtils.substringBetween(fatherName, " ", " ");
        String flName = StringUtils.substringAfterLast(fatherName, " ");
        String mfName = StringUtils.substringBefore(motherName, " ");
        String mmName = StringUtils.substringBetween(motherName, " ", " ");
        String mlName = StringUtils.substringAfterLast(motherName, " ");
        smBean.setStudentFirstName(fName);
        smBean.setStudentMiddleName(mName);
        smBean.setStudentLastName(lName);
        smBean.setHindiName(hindiName);
        smBean.setFatherFirstName(ffName);
        smBean.setFatherMiddleName(fmName);
        smBean.setFatherLastName(flName);
        smBean.setFatherHindiName(hindiFatherName);
        smBean.setMotherFirstName(mfName);
        smBean.setMotherMiddleName(mmName);
        smBean.setMotherLastName(mlName);
        smBean.setMotherHindiName(hindiMotherName);
        smBean.setCategoryCode(castCategory);
        smBean.setNationality(nationality);
        smBean.setGender(gender);
        smBean.setPanNo(panNo);
        smBean.setPassportNo(passportNo);
        smBean.setUID(UID);
        smBean.setReligion(religion);
        smBean.setMaritalStatus(mstatus);
        smBean.setDateOfBirth(dob);
        smBean.setPlaceOfBirth(pbirth);
        smBean.setGuardian(otherGardian);
        smBean.setUserId(src_id);
        smBean.setStatus("ACT");
        msg = DEIRemoteAccessAPI.updateStudentInfo(smBean, src_id, user_id, universityCode);
        return SUCCESS;
    }
    /*Create HashCode and Math with existing HasCode in XML file.*/

    private String readPersonalXML(File fXmlFile) throws Exception {
        Document document = (Document) builder.build(fXmlFile);
        Element rootNode = document.getRootElement();
        hashCode = rootNode.getChildText("hashCode");
        randomNumber = rootNode.getChildText("randomPassword");
        if (createKeyedHash(skey, randomNumber, hashCode).equals(true)) {
            enrollmentNo = rootNode.getChildText("EnrollmentNumber");
            studentName = rootNode.getChildText("studentName");
            fatherName = rootNode.getChildText("fatherName");
            motherName = rootNode.getChildText("motherName");
            dob = rootNode.getChildText("dateOfBirth");
            pbirth = rootNode.getChildText("placeOfBirth");
            gender = rootNode.getChildText("gender");
            castCategory = rootNode.getChildText("Category");
            religion = rootNode.getChildText("religion");
            nationality = rootNode.getChildText("nationality");
            mstatus = rootNode.getChildText("maritalStatus");
            panNo = rootNode.getChildText("panNo");
            passportNo = rootNode.getChildText("passportNo");
            UID = rootNode.getChildText("UID");
        } else {
            msg = "Hash Code Doesn't Matched.";
        }
        return SUCCESS;
    }

    private Boolean createKeyedHash(String skey, String randPassword, String hashCode) {
        String ePortfolioHashCode = EncrptDecrpt.keyedHash(src_id, randPassword, skey);
        if (ePortfolioHashCode.contentEquals(hashCode)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    /*End*/

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
     * @return the pi
     */
    public PersonalInfo getPi() {
        return pi;
    }

    /**
     * @param pi the pi to set
     */
    public void setPi(PersonalInfo pi) {
        this.pi = pi;
    }

    /**
     * @return the dao
     */
    public PersonalInfoDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(PersonalInfoDao dao) {
        this.dao = dao;
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
     * @return the UserInfoList
     */
    public List<User> getUserInfoList() {
        return UserInfoList;
    }

    /**
     * @param UserInfoList the UserInfoList to set
     */
    public void setUserInfoList(List<User> UserInfoList) {
        this.UserInfoList = UserInfoList;
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
     * @return the infoUpdated
     */
    public String getInfoUpdated() {
        return infoUpdated;
    }

    /**
     * @param infoUpdated the infoUpdated to set
     */
    public void setInfoUpdated(String infoUpdated) {
        this.infoUpdated = infoUpdated;
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
     * @return the src_id
     */
    public String getSrc_id() {
        return src_id;
    }

    /**
     * @param src_id the src_id to set
     */
    public void setSrc_id(String src_id) {
        this.src_id = src_id;
    }

    /**
     * @return the RemotePersonalInfo
     */
    public String getRemotePersonalInfo() {
        return RemotePersonalInfo;
    }

    /**
     * @param RemotePersonalInfo the RemotePersonalInfo to set
     */
    public void setRemotePersonalInfo(String RemotePersonalInfo) {
        this.RemotePersonalInfo = RemotePersonalInfo;
    }

    /**
     * @return the xmlFile
     */
    public String getXmlFile() {
        return xmlFile;
    }

    /**
     * @param xmlFile the xmlFile to set
     */
    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    /**
     * @return the studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the enrollmentNo
     */
    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    /**
     * @param enrollmentNo the enrollmentNo to set
     */
    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    /**
     * @return the randomNumber
     */
    public String getRandomNumber() {
        return randomNumber;
    }

    /**
     * @param randomNumber the randomNumber to set
     */
    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    /**
     * @return the hashCode
     */
    public String getHashCode() {
        return hashCode;
    }

    /**
     * @param hashCode the hashCode to set
     */
    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    /**
     * @return the xfPath
     */
    public String getXmlFilePath() {
        return xfPath;
    }

    /**
     * @param xfPath the xfPath to set
     */
    public void setXmlFilePath(String xmlFilePath) {
        this.xfPath = xmlFilePath;
    }

    /**
     * @return the hindiName
     */
    public String getHindiName() {
        return hindiName;
    }

    /**
     * @param hindiName the hindiName to set
     */
    public void setHindiName(String hindiName) {
        this.hindiName = hindiName;
    }

    /**
     * @return the hindiFatherName
     */
    public String getHindiFatherName() {
        return hindiFatherName;
    }

    /**
     * @param hindiFatherName the hindiFatherName to set
     */
    public void setHindiFatherName(String hindiFatherName) {
        this.hindiFatherName = hindiFatherName;
    }

    /**
     * @return the hindiMotherName
     */
    public String getHindiMotherName() {
        return hindiMotherName;
    }

    /**
     * @param hindiMotherName the hindiMotherName to set
     */
    public void setHindiMotherName(String hindiMotherName) {
        this.hindiMotherName = hindiMotherName;
    }

    /**
     * @return the universityCode
     */
    public String getUniversityCode() {
        return universityCode;
    }

    /**
     * @param universityCode the universityCode to set
     */
    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    /**
     * @return the UID
     */
    public String getUID() {
        return UID;
    }

    /**
     * @param UID the UID to set
     */
    public void setUID(String UID) {
        this.UID = UID;
    }

    /**
     * @return the udao
     */
    public UserListDao getUdao() {
        return udao;
    }

    /**
     * @param udao the udao to set
     */
    public void setUdao(UserListDao udao) {
        this.udao = udao;
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
}
