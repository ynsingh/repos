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
import org.IGNOU.ePortfolio.DAO.InterestDao;
import org.IGNOU.ePortfolio.Model.ProfileInterestList;

/**
 *
 * @author IGNOU Team
 */
public class ProfileInterestInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private InterestDao Intdao = new InterestDao();
    private ProfileInterestList IntList;
    private List<ProfileInterestList> IntListList;
    private List<ProfileInterestList> editIntList;
    private long interestId;
    private String userId;
    private String acadInterest;
    private String persInterest;
    private String techInterest;
    private String reserInterst;
    private String myHobbies;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public String ShowInterestInfo() throws Exception {
        IntListList = getIntdao().InterestList(user_id);
        if (IntListList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditInterestInfo() {
        editIntList = Intdao.EditInterest(getInterestId());
        return SUCCESS;
    }

    public String DeleteInterestInfo() throws Exception {
        Intdao.DeleteInterestInfo(interestId);
        msg = infoDeleted;
        return SUCCESS;
    }

    public String UpdateInterestInfo() throws Exception {
        Intdao.UpdateInterest(getInterestId(), getUserId(), acadInterest, persInterest, techInterest, reserInterst, myHobbies);
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
    public ProfileInterestList getIntList() {
        return IntList;
    }

    /**
     * @param IntList the IntList to set
     */
    public void setIntList(ProfileInterestList IntList) {
        this.IntList = IntList;
    }

    /**
     * @return the IntListList
     */
    public List<ProfileInterestList> getIntListList() {
        return IntListList;
    }

    /**
     * @param IntListList the IntListList to set
     */
    public void setIntListList(List<ProfileInterestList> IntListList) {
        this.IntListList = IntListList;
    }

    /**
     * @return the editIntList
     */
    public List<ProfileInterestList> getEditIntList() {
        return editIntList;
    }

    /**
     * @param editIntList the editIntList to set
     */
    public void setEditIntList(List<ProfileInterestList> editIntList) {
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
}
