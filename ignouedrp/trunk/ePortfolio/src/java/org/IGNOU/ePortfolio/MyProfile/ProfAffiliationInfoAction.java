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
import org.IGNOU.ePortfolio.DAO.ProfAffiliationDao;
import org.IGNOU.ePortfolio.Model.ProfileProAffiliation;

/**
 * @version 1
 * @since 14-Oct-2011
 * @author IGNOU Team ID is Modified by IGNOU Team on 17-Oct-2011.
 */
public class ProfAffiliationInfoAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private ProfileProAffiliation ProfAffili = new ProfileProAffiliation();
    private ProfAffiliationDao dao = new ProfAffiliationDao();
    private long proAffiliationId;
    private String userId;
    private String role;
    private String orgBody;
    private String vfrom;
    private String vupto;
    private String place;
    private String country;
    private String summary;
    private List<ProfileProAffiliation> AffiliationList = null;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public ProfAffiliationInfoAction() {
    }

    public String ShowAffiliationInfo() throws Exception {
        AffiliationList = dao.ProfileProAffiliationList(user_id);
        if (AffiliationList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditAffiliationInfo() throws Exception {
        AffiliationList = dao.ProfileProAffiliationEdit(proAffiliationId);
        return SUCCESS;
    }

    public String UpdateAffiliationInfo() throws Exception {
        getDao().ProfileProAffiliationUpdate(proAffiliationId, userId, role, orgBody, vfrom, vupto, place, country, summary);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteAffiliationInfo() throws Exception {
        dao.ProfileProAffiliationDelete(proAffiliationId);
        msg = infoDeleted;
        return SUCCESS;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
     * @return the ProfAffili
     */
    public ProfileProAffiliation getProfAffili() {
        return ProfAffili;
    }

    /**
     * @param ProfAffili the ProfAffili to set
     */
    public void setProfAffili(ProfileProAffiliation ProfAffili) {
        this.ProfAffili = ProfAffili;
    }

    /**
     * @return the dao
     */
    public ProfAffiliationDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ProfAffiliationDao dao) {
        this.dao = dao;
    }

    /**
     * @return the proAffiliationId
     */
    public long getProAffiliationId() {
        return proAffiliationId;
    }

    /**
     * @param proAffiliationId the proAffiliationId to set
     */
    public void setProAffiliationId(long proAffiliationId) {
        this.proAffiliationId = proAffiliationId;
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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the orgBody
     */
    public String getOrgBody() {
        return orgBody;
    }

    /**
     * @param orgBody the orgBody to set
     */
    public void setOrgBody(String orgBody) {
        this.orgBody = orgBody;
    }

    /**
     * @return the vfrom
     */
    public String getVfrom() {
        return vfrom;
    }

    /**
     * @param vfrom the vfrom to set
     */
    public void setVfrom(String vfrom) {
        this.vfrom = vfrom;
    }

    /**
     * @return the vupto
     */
    public String getVupto() {
        return vupto;
    }

    /**
     * @param vupto the vupto to set
     */
    public void setVupto(String vupto) {
        this.vupto = vupto;
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
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the AffiliationList
     */
    public List<ProfileProAffiliation> getAffiliationList() {
        return AffiliationList;
    }

    /**
     * @param AffiliationList the AffiliationList to set
     */
    public void setAffiliationList(List<ProfileProAffiliation> AffiliationList) {
        this.AffiliationList = AffiliationList;
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
