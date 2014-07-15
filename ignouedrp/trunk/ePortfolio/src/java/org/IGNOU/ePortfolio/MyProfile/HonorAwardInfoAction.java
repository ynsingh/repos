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
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.HonorAwardDao;
import org.IGNOU.ePortfolio.Model.ProfileHonorAward;
import org.apache.log4j.Logger;

/**
 * @version 1
 * @since 13-Oct-2011 (Created & Functional)
 * @author IGNOU Team
 */
public class HonorAwardInfoAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    private HonorAwardDao dao = new HonorAwardDao();
    private ProfileHonorAward ProHonor = new ProfileHonorAward();
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private long honorAwardId;
    private String userId;
    private String haTitle;
    private String issuer;
    private String haDate;
    private String haDescription;
    private List<ProfileHonorAward> HonorAwardList;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public HonorAwardInfoAction() {
    }

    public String ShowHonorInfo() throws Exception {
        HonorAwardList = dao.ProfileHonorAwardListByUserId(user_id);
        if (HonorAwardList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditHonoprInfo() throws Exception {
        HonorAwardList = dao.ProfileHonorAwardByHonorAwardId(getHonorAwardId());
        return SUCCESS;
    }

    public String UpdateHonorInfo() throws Exception {
        getDao().ProfileHonorAwardUpdate(honorAwardId, userId, haTitle, issuer, haDate, haDescription);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteHonorInfo() throws Exception {
        getDao().ProfileHonorAwardDelete(honorAwardId);
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
     * @return the dao
     */
    public HonorAwardDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(HonorAwardDao dao) {
        this.dao = dao;
    }

    /**
     * @return the ProHonor
     */
    public ProfileHonorAward getProHonor() {
        return ProHonor;
    }

    /**
     * @param ProHonor the ProHonor to set
     */
    public void setProHonor(ProfileHonorAward ProHonor) {
        this.ProHonor = ProHonor;
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
     * @return the honorAwardId
     */
    public long getHonorAwardId() {
        return honorAwardId;
    }

    /**
     * @param honorAwardId the honorAwardId to set
     */
    public void setHonorAwardId(long honorAwardId) {
        this.honorAwardId = honorAwardId;
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
     * @return the haTitle
     */
    public String getHaTitle() {
        return haTitle;
    }

    /**
     * @param haTitle the haTitle to set
     */
    public void setHaTitle(String haTitle) {
        this.haTitle = haTitle;
    }

    /**
     * @return the issuer
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * @param issuer the issuer to set
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * @return the haDate
     */
    public String getHaDate() {
        return haDate;
    }

    /**
     * @param haDate the haDate to set
     */
    public void setHaDate(String haDate) {
        this.haDate = haDate;
    }

    /**
     * @return the haDescription
     */
    public String getHaDescription() {
        return haDescription;
    }

    /**
     * @param haDescription the haDescription to set
     */
    public void setHaDescription(String haDescription) {
        this.haDescription = haDescription;
    }

    /**
     * @return the HonorAwardList
     */
    public List<ProfileHonorAward> getHonorAwardList() {
        return HonorAwardList;
    }

    /**
     * @param HonorAwardList the HonorAwardList to set
     */
    public void setHonorAwardList(List<ProfileHonorAward> HonorAwardList) {
        this.HonorAwardList = HonorAwardList;
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
