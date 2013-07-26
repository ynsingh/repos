/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.Model.ProfileReferences;

/**
 * Created on 11-Oct-2011
 *
 * @version 1
 * @author IGNOU Team
 */
public class ReferenceAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private MyProfileDAO dao = new MyProfileDAO();
    private ProfileReferences ProRef;
    private String user_id = new UserSession().getUserInSession();
    private List<ProfileReferences> RefList;
    private long referencesId;
    private String userId;
    private String name;
    private String designation;
    private String orgUniv;
    private String place;
    private String city;
    private String state;
    private String country;
    private long phoneno;
    private Long mobileno;
    private String emailId;
    private String website;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public ReferenceAction() {
    }

    public String ShowInfo() throws Exception {
        RefList = dao.ProfileReferencesListByUserId(getUser_id());
        if (RefList.isEmpty()) {
            // return SUCCESS;
            return INPUT;
        } else {
            //return ERROR;
            return SUCCESS;
        }
    }

    public String EditInfo() throws Exception {
        RefList = dao.ProfileReferencesEdit(referencesId);
        return SUCCESS;
    }

    public String UpdateRefInfo() throws Exception {
        dao.ProfileReferencesUpdate(referencesId, user_id, name, designation, orgUniv, place, city, state, country, phoneno, mobileno, emailId, website);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteRefInfo() throws Exception {
        dao.ProfileReferencesDelete(referencesId);
        msg = infoDeleted;
        return SUCCESS;
    }

    /**
     * @return the dao
     */
    public MyProfileDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(MyProfileDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the ProRef
     */
    public ProfileReferences getProRef() {
        return ProRef;
    }

    /**
     * @param ProRef the ProRef to set
     */
    public void setProRef(ProfileReferences ProRef) {
        this.ProRef = ProRef;
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
     * @return the RefList
     */
    public List<ProfileReferences> getRefList() {
        return RefList;
    }

    /**
     * @param RefList the RefList to set
     */
    public void setRefList(List<ProfileReferences> RefList) {
        this.RefList = RefList;
    }

    /**
     * @return the referencesId
     */
    public long getReferencesId() {
        return referencesId;
    }

    /**
     * @param referencesId the referencesId to set
     */
    public void setReferencesId(long referencesId) {
        this.referencesId = referencesId;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the orgUniv
     */
    public String getOrgUniv() {
        return orgUniv;
    }

    /**
     * @param orgUniv the orgUniv to set
     */
    public void setOrgUniv(String orgUniv) {
        this.orgUniv = orgUniv;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
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
     * @return the phoneno
     */
    public long getPhoneno() {
        return phoneno;
    }

    /**
     * @param phoneno the phoneno to set
     */
    public void setPhoneno(long phoneno) {
        this.phoneno = phoneno;
    }

    /**
     * @return the mobileno
     */
    public Long getMobileno() {
        return mobileno;
    }

    /**
     * @param mobileno the mobileno to set
     */
    public void setMobileno(Long mobileno) {
        this.mobileno = mobileno;
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
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
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
     * @return the infoDeleted
     */
    public String getInfoDeleted() {
        return infoDeleted;
    }

    /**
     * @param infoDeleted the infoDeleted to set
     */
    public void setInfoDeleted(String infoDeleted) {
        this.infoDeleted = infoDeleted;
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
}
