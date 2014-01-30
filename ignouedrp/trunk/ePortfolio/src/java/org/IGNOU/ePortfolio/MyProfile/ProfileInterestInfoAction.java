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
import org.IGNOU.ePortfolio.DAO.InterestDao;
import org.IGNOU.ePortfolio.Model.ProfileInterest;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class ProfileInterestInfoAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private InterestDao Intdao = new InterestDao();
    private ProfileInterest IntList;
    private List<ProfileInterest> IntListList;
    private List<ProfileInterest> editIntList;
    private long interestId;
    private String userId;
    private String acadInterest;
    private String persInterest;
    private String techInterest;
    private String reserInterst;
    private String myHobbies;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated"), notFound = getText("recordNotFound");

    public String ShowInterestInfo() throws Exception {
        IntListList = getIntdao().ProfileInterestByUserId(user_id);
        if (IntListList.isEmpty()) {
            msg = notFound;
        } else {
        }
        return SUCCESS;
    }

    public String EditInterestInfo() {
        editIntList = Intdao.ProfileInterestByIntrestId(getInterestId());
        return SUCCESS;
    }

    public String DeleteInterestInfo() throws Exception {
        Intdao.ProfileInterestDelete(interestId);
        msg = infoDeleted;
        return SUCCESS;
    }

    public String UpdateInterestInfo() throws Exception {
        Intdao.ProfileInterestUpdate(getInterestId(), getUserId(), acadInterest, persInterest, techInterest, reserInterst, myHobbies);
        msg = infoUpdated;
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
     * @return the IntList
     */
    public ProfileInterest getIntList() {
        return IntList;
    }

    /**
     * @param IntList the IntList to set
     */
    public void setIntList(ProfileInterest IntList) {
        this.IntList = IntList;
    }

    /**
     * @return the IntListList
     */
    public List<ProfileInterest> getIntListList() {
        return IntListList;
    }

    /**
     * @param IntListList the IntListList to set
     */
    public void setIntListList(List<ProfileInterest> IntListList) {
        this.IntListList = IntListList;
    }

    /**
     * @return the editIntList
     */
    public List<ProfileInterest> getEditIntList() {
        return editIntList;
    }

    /**
     * @param editIntList the editIntList to set
     */
    public void setEditIntList(List<ProfileInterest> editIntList) {
        this.editIntList = editIntList;
    }

    /**
     * @return the Intdao
     */
    public InterestDao getIntdao() {
        return Intdao;
    }

    /**
     * @param Intdao the Intdao to set
     */
    public void setIntdao(InterestDao Intdao) {
        this.Intdao = Intdao;
    }

    /**
     * @return the interestId
     */
    public long getInterestId() {
        return interestId;
    }

    /**
     * @param interestId the interestId to set
     */
    public void setInterestId(long interestId) {
        this.interestId = interestId;
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
     * @return the acadInterest
     */
    public String getAcadInterest() {
        return acadInterest;
    }

    /**
     * @param acadInterest the acadInterest to set
     */
    public void setAcadInterest(String acadInterest) {
        this.acadInterest = acadInterest;
    }

    /**
     * @return the persInterest
     */
    public String getPersInterest() {
        return persInterest;
    }

    /**
     * @param persInterest the persInterest to set
     */
    public void setPersInterest(String persInterest) {
        this.persInterest = persInterest;
    }

    /**
     * @return the techInterest
     */
    public String getTechInterest() {
        return techInterest;
    }

    /**
     * @param techInterest the techInterest to set
     */
    public void setTechInterest(String techInterest) {
        this.techInterest = techInterest;
    }

    /**
     * @return the reserInterst
     */
    public String getReserInterst() {
        return reserInterst;
    }

    /**
     * @param reserInterst the reserInterst to set
     */
    public void setReserInterst(String reserInterst) {
        this.reserInterst = reserInterst;
    }

    /**
     * @return the myHobbies
     */
    public String getMyHobbies() {
        return myHobbies;
    }

    /**
     * @param myHobbies the myHobbies to set
     */
    public void setMyHobbies(String myHobbies) {
        this.myHobbies = myHobbies;
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

    /**
     * @return the notFound
     */
    public String getNotFound() {
        return notFound;
    }

    /**
     * @param notFound the notFound to set
     */
    public void setNotFound(String notFound) {
        this.notFound = notFound;
    }
}
