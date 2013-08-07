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
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ContactDao;
import org.IGNOU.ePortfolio.Model.ProfileContact;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author IGNOU Team
 */
public class ContactInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private ContactDao dao = new ContactDao();
    private ProfileContact ProfileContact;
    private List<ProfileContact> contactListList;
    private long contactInfoId;
    private Long HTelephone, OTelephone, mobileNo, faxNo;
    private Integer pin;
    private String address1, address2, city, state, country, email1, email2, email3, owebsite, pwebsite;
    private String msg;
    private String deleteInfo = getText("msg.infoDeleted");
    private String updateInfo = getText("msg.infoUpdated");
    /*
     * Remote Access
     */
    private String skey = ReadPropertyFile("deiKey");
    private String src_id = ReadPropertyFile("deiScourceId");
    private String xfPath = ReadPropertyFile("xmlContactPath");
    private String universityCode = "0001";
    private String xmlFile, randomNumber, hashCode;
    private String RemoteContactInfo;
    private String PAaddress, PAcity, PAstate, PApinCode, PAofficePhone, PAhomePhone, PAotherPhone, PAfax;
    private String CAaddress, CAcity, CAstate, CApinCode, CAofficePhone, CAhomePhone, CAotherPhone, CAfax;
    private SAXBuilder builder = new SAXBuilder();
    private StudentMasterBeanAPI StdMasterModel = new StudentMasterBeanAPI();

    public ContactInfoAction() {
    }

    public String ShowContactInfo() throws Exception {
        contactListList = dao.ContactList(user_id);
        if (contactListList.isEmpty()) {
            try {
                getDEIContactInfo();
            } catch (NullPointerException ne) {
                msg = "";
                Logger.getLogger(ContactInfoAction.class.getName()).log(Level.SEVERE, null, ne);
                return INPUT;
            }
        } else {
        }
        return SUCCESS;
    }

    public String UpdateContactInfo() {
        dao.ContactUpdate(getContactInfoId(), getUser_id(), getAddress1(), getAddress2(), getCity(), getState(), getCountry(), getPin(), getHTelephone(), getHTelephone(), getMobileNo(), getFaxNo(), getEmail1(), getEmail2(), getEmail3(), getOwebsite(), getPwebsite());
        msg = getUpdateInfo();
        return SUCCESS;
    }

    /*Remote Access Start*/
    public String getDEIContactInfo() throws Exception {
        //line = ReadNWriteInTxt.readLin(pPath, src_id);
        //  skey = org.apache.commons.lang.StringUtils.substringBetween(line, ";", ";");
        RemoteContactInfo = DEIRemoteAccessAPI.getContactInfo(user_id, src_id, universityCode);
        xmlFile = xfPath + user_id + ".xml";
        /*Read XML File and get TagValues*/
        try {
            File fXmlFile = new File(xmlFile);
            if (fXmlFile.exists()) {
                readContactXML(fXmlFile);
            } else {
                msg = "File not found! at " + fXmlFile;
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        if (createKeyedHash(skey, randomNumber, hashCode).equals(true)) {
            return SUCCESS;
        } else {
            msg = "Hash Code Doesn't Matched.";
            return ERROR;
        }
    }

    public String EditDEIContactInfo() throws Exception {
        xmlFile = xfPath + user_id + ".xml";
        /*Read XML File and get TagValues*/
        try {
            File fXmlFile = new File(xmlFile);
            if (fXmlFile.exists()) {
                readContactXML(fXmlFile);
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

    public String UpdateDEIContactInfo() throws Exception {
//        line = ReadNWriteInTxt.readLin(pPath, src_id);
//        skey = org.apache.commons.lang.StringUtils.substringBetween(line, ";", ";");
        if (createKeyedHash(skey, randomNumber, hashCode).equals(true)) {
            /* Update Permanent Address   */
            StdMasterModel.setAddressKey("per");
            StdMasterModel.setAddressLineOne(PAaddress);
            StdMasterModel.setCity(PAcity);
            StdMasterModel.setState(PAstate);
            StdMasterModel.setPinCode(PApinCode);
            StdMasterModel.setOfficePhone(PAofficePhone);
            StdMasterModel.setHomePhone(PAhomePhone);
            StdMasterModel.setOtherPhone(PAotherPhone);
            StdMasterModel.setFax(PAfax);
            DEIRemoteAccessAPI.updateContactInfo(StdMasterModel, src_id, user_id, universityCode);
            /* Update Correspondence Address */
            StdMasterModel.setAddressKey("cor");
            StdMasterModel.setAddressLineOne(PAaddress);
            StdMasterModel.setCity(PAcity);
            StdMasterModel.setState(PAstate);
            StdMasterModel.setPinCode(PApinCode);
            StdMasterModel.setOfficePhone(PAofficePhone);
            StdMasterModel.setHomePhone(PAhomePhone);
            StdMasterModel.setOtherPhone(PAotherPhone);
            StdMasterModel.setFax(PAfax);
            DEIRemoteAccessAPI.updateContactInfo(StdMasterModel, src_id, user_id, universityCode);
            return SUCCESS;
        } else {
            msg = "Hash Code Doesn't Matched.";
            return ERROR;
        }
    }

    private String readContactXML(File fXmlFile) throws Exception {
        Document document = (Document) builder.build(fXmlFile);
        Element rootNode = document.getRootElement();
        List PAlist = rootNode.getChildren("PermanentAddress");
        for (int i = 0; i < PAlist.size(); i++) {
            Element PAnode = (Element) PAlist.get(i);
            PAaddress = PAnode.getChildText("address");
            PAcity = PAnode.getChildText("city");
            PAstate = PAnode.getChildText("state");
            PAofficePhone = PAnode.getChildText("officePhone");
            PApinCode = PAnode.getChildText("pinCode");
            PAhomePhone = PAnode.getChildText("homePhone");
            PAotherPhone = PAnode.getChildText("otherPhone");
            PAfax = PAnode.getChildText("fax");
        }
        List CAlist = rootNode.getChildren("CorrespondenceAddress");
        for (int i = 0; i < CAlist.size(); i++) {
            Element CAnode = (Element) CAlist.get(i);
            CAaddress = CAnode.getChildText("address");
            CAcity = CAnode.getChildText("city");
            CAstate = CAnode.getChildText("state");
            CAofficePhone = CAnode.getChildText("officePhone");
            CApinCode = CAnode.getChildText("pinCode");
            CAhomePhone = CAnode.getChildText("homePhone");
            CAotherPhone = CAnode.getChildText("otherPhone");
            CAfax = CAnode.getChildText("fax");
        }
        hashCode = rootNode.getChildText("hashCode");
        randomNumber = rootNode.getChildText("randomPassword");
        return SUCCESS;
    }

    /*Create HashCode and Math with existing HasCode in XML file.*/
    private Boolean createKeyedHash(String skey, String randPassword, String hashCode) {
        String ePortfolioHashCode = EncrptDecrpt.keyedHash(src_id, randPassword, skey);
        if (ePortfolioHashCode.contentEquals(hashCode)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    /*Remote Access End*/

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
    public ContactDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ContactDao dao) {
        this.dao = dao;
    }

    /**
     * @return the ProfileContact
     */
    public ProfileContact getProfileContact() {
        return ProfileContact;
    }

    /**
     * @param ProfileContact the ProfileContact to set
     */
    public void setProfileContact(ProfileContact ProfileContact) {
        this.ProfileContact = ProfileContact;
    }

    /**
     * @return the contactListList
     */
    public List<ProfileContact> getContactListList() {
        return contactListList;
    }

    /**
     * @param contactListList the contactListList to set
     */
    public void setContactListList(List<ProfileContact> contactListList) {
        this.contactListList = contactListList;
    }

    /**
     * @return the contactInfoId
     */
    public long getContactInfoId() {
        return contactInfoId;
    }

    /**
     * @param contactInfoId the contactInfoId to set
     */
    public void setContactInfoId(long contactInfoId) {
        this.contactInfoId = contactInfoId;
    }

    /**
     * @return the HTelephone
     */
    public Long getHTelephone() {
        return HTelephone;
    }

    /**
     * @param HTelephone the HTelephone to set
     */
    public void setHTelephone(Long HTelephone) {
        this.HTelephone = HTelephone;
    }

    /**
     * @return the OTelephone
     */
    public Long getOTelephone() {
        return OTelephone;
    }

    /**
     * @param OTelephone the OTelephone to set
     */
    public void setOTelephone(Long OTelephone) {
        this.OTelephone = OTelephone;
    }

    /**
     * @return the mobileNo
     */
    public Long getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the faxNo
     */
    public Long getFaxNo() {
        return faxNo;
    }

    /**
     * @param faxNo the faxNo to set
     */
    public void setFaxNo(Long faxNo) {
        this.faxNo = faxNo;
    }

    /**
     * @return the pin
     */
    public Integer getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(Integer pin) {
        this.pin = pin;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
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
     * @return the email1
     */
    public String getEmail1() {
        return email1;
    }

    /**
     * @param email1 the email1 to set
     */
    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    /**
     * @return the email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2 the email2 to set
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return the email3
     */
    public String getEmail3() {
        return email3;
    }

    /**
     * @param email3 the email3 to set
     */
    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    /**
     * @return the owebsite
     */
    public String getOwebsite() {
        return owebsite;
    }

    /**
     * @param owebsite the owebsite to set
     */
    public void setOwebsite(String owebsite) {
        this.owebsite = owebsite;
    }

    /**
     * @return the pwebsite
     */
    public String getPwebsite() {
        return pwebsite;
    }

    /**
     * @param pwebsite the pwebsite to set
     */
    public void setPwebsite(String pwebsite) {
        this.pwebsite = pwebsite;
    }

    /**
     * @return the deleteInfo
     */
    public String getDeleteInfo() {
        return deleteInfo;
    }

    /**
     * @param deleteInfo the deleteInfo to set
     */
    public void setDeleteInfo(String deleteInfo) {
        this.deleteInfo = deleteInfo;
    }

    /**
     * @return the updateInfo
     */
    public String getUpdateInfo() {
        return updateInfo;
    }

    /**
     * @param updateInfo the updateInfo to set
     */
    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    /**
     * @return the skey
     */
    public String getSkey() {
        return skey;
    }

    /**
     * @param skey the skey to set
     */
    public void setSkey(String skey) {
        this.skey = skey;
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
     * @return the xfPath
     */
    public String getXfPath() {
        return xfPath;
    }

    /**
     * @param xfPath the xfPath to set
     */
    public void setXfPath(String xfPath) {
        this.xfPath = xfPath;
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
     * @return the RemoteContactInfo
     */
    public String getRemoteContactInfo() {
        return RemoteContactInfo;
    }

    /**
     * @param RemoteContactInfo the RemoteContactInfo to set
     */
    public void setRemoteContactInfo(String RemoteContactInfo) {
        this.RemoteContactInfo = RemoteContactInfo;
    }

    /**
     * @return the PAaddress
     */
    public String getPAaddress() {
        return PAaddress;
    }

    /**
     * @param PAaddress the PAaddress to set
     */
    public void setPAaddress(String PAaddress) {
        this.PAaddress = PAaddress;
    }

    /**
     * @return the PAcity
     */
    public String getPAcity() {
        return PAcity;
    }

    /**
     * @param PAcity the PAcity to set
     */
    public void setPAcity(String PAcity) {
        this.PAcity = PAcity;
    }

    /**
     * @return the PAstate
     */
    public String getPAstate() {
        return PAstate;
    }

    /**
     * @param PAstate the PAstate to set
     */
    public void setPAstate(String PAstate) {
        this.PAstate = PAstate;
    }

    /**
     * @return the PApinCode
     */
    public String getPApinCode() {
        return PApinCode;
    }

    /**
     * @param PApinCode the PApinCode to set
     */
    public void setPApinCode(String PApinCode) {
        this.PApinCode = PApinCode;
    }

    /**
     * @return the PAofficePhone
     */
    public String getPAofficePhone() {
        return PAofficePhone;
    }

    /**
     * @param PAofficePhone the PAofficePhone to set
     */
    public void setPAofficePhone(String PAofficePhone) {
        this.PAofficePhone = PAofficePhone;
    }

    /**
     * @return the PAhomePhone
     */
    public String getPAhomePhone() {
        return PAhomePhone;
    }

    /**
     * @param PAhomePhone the PAhomePhone to set
     */
    public void setPAhomePhone(String PAhomePhone) {
        this.PAhomePhone = PAhomePhone;
    }

    /**
     * @return the PAotherPhone
     */
    public String getPAotherPhone() {
        return PAotherPhone;
    }

    /**
     * @param PAotherPhone the PAotherPhone to set
     */
    public void setPAotherPhone(String PAotherPhone) {
        this.PAotherPhone = PAotherPhone;
    }

    /**
     * @return the PAfax
     */
    public String getPAfax() {
        return PAfax;
    }

    /**
     * @param PAfax the PAfax to set
     */
    public void setPAfax(String PAfax) {
        this.PAfax = PAfax;
    }

    /**
     * @return the CAaddress
     */
    public String getCAaddress() {
        return CAaddress;
    }

    /**
     * @param CAaddress the CAaddress to set
     */
    public void setCAaddress(String CAaddress) {
        this.CAaddress = CAaddress;
    }

    /**
     * @return the CAcity
     */
    public String getCAcity() {
        return CAcity;
    }

    /**
     * @param CAcity the CAcity to set
     */
    public void setCAcity(String CAcity) {
        this.CAcity = CAcity;
    }

    /**
     * @return the CAstate
     */
    public String getCAstate() {
        return CAstate;
    }

    /**
     * @param CAstate the CAstate to set
     */
    public void setCAstate(String CAstate) {
        this.CAstate = CAstate;
    }

    /**
     * @return the CApinCode
     */
    public String getCApinCode() {
        return CApinCode;
    }

    /**
     * @param CApinCode the CApinCode to set
     */
    public void setCApinCode(String CApinCode) {
        this.CApinCode = CApinCode;
    }

    /**
     * @return the CAofficePhone
     */
    public String getCAofficePhone() {
        return CAofficePhone;
    }

    /**
     * @param CAofficePhone the CAofficePhone to set
     */
    public void setCAofficePhone(String CAofficePhone) {
        this.CAofficePhone = CAofficePhone;
    }

    /**
     * @return the CAhomePhone
     */
    public String getCAhomePhone() {
        return CAhomePhone;
    }

    /**
     * @param CAhomePhone the CAhomePhone to set
     */
    public void setCAhomePhone(String CAhomePhone) {
        this.CAhomePhone = CAhomePhone;
    }

    /**
     * @return the CAotherPhone
     */
    public String getCAotherPhone() {
        return CAotherPhone;
    }

    /**
     * @param CAotherPhone the CAotherPhone to set
     */
    public void setCAotherPhone(String CAotherPhone) {
        this.CAotherPhone = CAotherPhone;
    }

    /**
     * @return the CAfax
     */
    public String getCAfax() {
        return CAfax;
    }

    /**
     * @param CAfax the CAfax to set
     */
    public void setCAfax(String CAfax) {
        this.CAfax = CAfax;
    }

    /**
     * @return the StdMasterModel
     */
    public StudentMasterBeanAPI getStdMasterModel() {
        return StdMasterModel;
    }

    /**
     * @param StdMasterModel the StdMasterModel to set
     */
    public void setStdMasterModel(StudentMasterBeanAPI StdMasterModel) {
        this.StdMasterModel = StdMasterModel;
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
