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
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.AddInfoDao;
import org.IGNOU.ePortfolio.Model.ProfileReferences;

/**
 * Created on 11-Oct-2011 Version 1.1
 *
 * @author IGNOU Team
 */
public class ReferenceAddAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private AddInfoDao dao = new AddInfoDao();
    private ProfileReferences Ref = new ProfileReferences();
    private String user_id = new UserSession().getUserInSession();
    private String name;
    private String designation;
    private String orgUniv;
    private String place;
    private String city;
    private String state;
    private String country;
    private Long phoneno;
    private Long mobileno;
    private String emailId;
    private String website;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public ReferenceAddAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.ProfileReferenceSave(Ref);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getRef().setUserId(user_id);
        return getRef();
    }

    /**
     * @return the Ref
     */
    public ProfileReferences getRef() {
        return Ref;
    }

    /**
     * @param Ref the Ref to set
     */
    public void setRef(ProfileReferences Ref) {
        this.Ref = Ref;
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
    public Long getPhoneno() {
        return phoneno;
    }

    /**
     * @param phoneno the phoneno to set
     */
    public void setPhoneno(Long phoneno) {
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
