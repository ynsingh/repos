/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ProfileContactDao;
import org.IGNOU.ePortfolio.Model.ProfileContact;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class ProfileContactAction extends ActionSupport implements Serializable, ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ProfileContact PCMOdel = new ProfileContact();
    private ProfileContactDao dao = new ProfileContactDao();
    private String address1, address2, city, state, country;
    private Integer pin;
    private Long htelephone, otelephone, mobile, fax;
    private String email1, email2, email3;
    private String owebsite, pwebsite;
    private long contactInfoId;
    private String userId;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    @Override
    public Object getModel() {
        PCMOdel.setUserId(getUser_id());
        return PCMOdel;
    }

    @Override
    public String execute() throws Exception {
        dao.ContactInfoSave(PCMOdel);
        msg = infoSaved;
        return SUCCESS;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the PCMOdel
     */
    public ProfileContact getPCMOdel() {
        return PCMOdel;
    }

    /**
     * @param PCMOdel the PCMOdel to set
     */
    public void setPCMOdel(ProfileContact PCMOdel) {
        this.PCMOdel = PCMOdel;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
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
     * @return the htelephone
     */
    public Long getHtelephone() {
        return htelephone;
    }

    /**
     * @param htelephone the htelephone to set
     */
    public void setHtelephone(Long htelephone) {
        this.htelephone = htelephone;
    }

    /**
     * @return the otelephone
     */
    public Long getOtelephone() {
        return otelephone;
    }

    /**
     * @param otelephone the otelephone to set
     */
    public void setOtelephone(Long otelephone) {
        this.otelephone = otelephone;
    }

    /**
     * @return the mobile
     */
    public Long getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the fax
     */
    public Long getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(Long fax) {
        this.fax = fax;
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
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }
}
