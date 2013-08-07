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
import java.util.List;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Vinay
 * @version 2
 * @since 09-08-2012
 */
public class DEIPersonalInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private String hDir = System.getProperty("user.home");
    private String pPath = hDir + "/Remote_auth/dei-remote-access.properties";
    private String line, skey, xmlFile;
    private String src_id = ReadPropertyFile("deiScourceId");
    private String xfPath = ReadPropertyFile("xmlStudentPath");
    private String universityCode = "0001";
    private String RemotePersonalInfo, msg;
    private String studentName, fatherName, motherName, dob, enrollmentNo, gender, category, nationality;
    private String hindiName, hindiFatherName, hindiMotherName;
    private String randomNumber, hashCode;

    public DEIPersonalInfoAction() {
    }
    /* Fetch Personal Informaftion from Remote Location(DEI)*/

    public String getPersonalInfo() throws Exception {
        // line = ReadNWriteInTxt.readLin(pPath, src_id);
        //skey = org.apache.commons.lang.StringUtils.substringBetween(line, ";", ";");
        RemotePersonalInfo = DEIRemoteAccessAPI.getStudentInfo(user_id, universityCode, src_id);
        xmlFile = hDir + xfPath + user_id + ".xml";
        SAXBuilder builder = new SAXBuilder();
        /*Read XML File and get TagValues*/
        try {
            File fXmlFile = new File(xmlFile);
            if (fXmlFile.exists()) {
                Document document = (Document) builder.build(fXmlFile);
                Element rootNode = document.getRootElement();
                List PAlist = rootNode.getChildren("StudentInfo");
                for (int i = 0; i < PAlist.size(); i++) {
                    Element node = (Element) PAlist.get(i);
                    studentName = node.getChildText("studentName");
                    fatherName = node.getChildText("fatherName");
                    motherName = node.getChildText("motherName");
                    gender = node.getChildText("gender");
                    nationality = node.getChildText("nationality");
                }
                hashCode = rootNode.getChildText("hashCode");
                randomNumber = rootNode.getChildText("randomPassword");
                /*         
                 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                 Document doc = dBuilder.parse(fXmlFile);
                 doc.getDocumentElement().normalize();
                 NodeList nList = doc.getElementsByTagName("StudentInfo");
                 for (int temp = 0; temp < nList.getLength(); temp++) {
                 Node nNode = nList.item(temp);
                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                 Element eElement = (Element) nNode;
                 studentName = getTagValue("studentName", eElement);
                 fatherName = getTagValue("fatherName", eElement);
                 motherName = getTagValue("motherName", eElement);
                 gender = getTagValue("gender", eElement);
                 nationality = getTagValue("nationality", eElement);
                 randomNumber = getTagValue("randomPassword", eElement);
                 hashCode = getTagValue("hashCode", eElement);
                 }
                 }
                 */
            } else {
                msg = "File not found!";
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }

        if (createKeyedHash(src_id, skey, randomNumber, hashCode).equals(true)) {
            return SUCCESS;
        } else {
            msg = "Hash Code Doesn't Matched.";
            return ERROR;
        }
    }

    /*Create HashCode and Math with existing HasCode in XML file.*/
    private Boolean createKeyedHash(String src_id, String sKey, String randPassword, String hashCode) {
        String ePortfolioHashCode = EncrptDecrpt.keyedHash(src_id, sKey, randPassword.replace("\n", ""));
        if (ePortfolioHashCode.contentEquals(hashCode.replace("\n", ""))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /*Edit EditPersonalInfo*/
    public String EditPersonalInfo() throws Exception {
        line = ReadNWriteInTxt.readLin(pPath, src_id);
        skey = org.apache.commons.lang.StringUtils.substringBetween(line, ";", ";");
        xmlFile = hDir + xfPath + user_id + ".xml";
        SAXBuilder builder = new SAXBuilder();
        /*Read XML File and get TagValues*/
        try {
            File fXmlFile = new File(xmlFile);
            if (fXmlFile.exists()) {
                Document document = (Document) builder.build(fXmlFile);
                Element rootNode = document.getRootElement();
                List PAlist = rootNode.getChildren("StudentInfo");
                for (int i = 0; i < PAlist.size(); i++) {
                    Element node = (Element) PAlist.get(i);
                    studentName = node.getChildText("studentName");
                    fatherName = node.getChildText("fatherName");
                    motherName = node.getChildText("motherName");
                    gender = node.getChildText("gender");
                    nationality = node.getChildText("nationality");
                }

            } else {
                msg = "File not found!";
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        if (createKeyedHash(src_id, skey, randomNumber, hashCode).equals(true)) {
            return SUCCESS;
        } else {
            msg = "Hash Code Doesn't Matched.";
            return ERROR;
        }
    }

    /*Update Personal Informaftion into Remote Location(DEI)*/
    public String UpdatePersonalInfo() throws Exception {
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
        smBean.setCategoryCode(category);
        smBean.setNationality(nationality);
        smBean.setGender(gender);
        smBean.setUserId(src_id);
        smBean.setStatus("ACT");
        msg = DEIRemoteAccessAPI.updateStudentInfo(smBean, src_id, "sudhish@pcs.dei.ac.in", universityCode);
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
     * @return the hDir
     */
    public String gethDir() {
        return hDir;
    }

    /**
     * @param hDir the hDir to set
     */
    public void sethDir(String hDir) {
        this.hDir = hDir;
    }

    /**
     * @return the pPath
     */
    public String getpPath() {
        return pPath;
    }

    /**
     * @param pPath the pPath to set
     */
    public void setpPath(String pPath) {
        this.pPath = pPath;
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
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
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
}
