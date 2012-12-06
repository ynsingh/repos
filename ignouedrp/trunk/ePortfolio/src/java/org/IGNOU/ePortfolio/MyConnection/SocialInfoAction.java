/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
package org.IGNOU.ePortfolio.MyConnection
;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MySocialInfoDao;
import org.IGNOU.ePortfolio.Model.ProfileSocial;

/**
 *
 * @author IGNOU Team
 */
public class SocialInfoAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private String user_id = new UserSession().getUserInSession();
    private MySocialInfoDao dao = new MySocialInfoDao();
    private ProfileSocial ProfileSocial;
    private List<ProfileSocial> socialListList;
    private String UserId;
    private long socialInfoId;
    private String gtalk, skype, msn, aim, yahoo, facebook, orkut, twitter, blog;

    public SocialInfoAction() {
    }

    public String ShowSocialInfo() {
        setSocialListList(getDao().SocialList(getUser_id()));
        if (getSocialListList().isEmpty()) {
            // return SUCCESS;
            return INPUT;
        } else {
            //return ERROR;
            return SUCCESS;
        }
    }

    public String UpdateSocialInfo() {
        getDao().UpdateSocial(getSocialInfoId(), getUserId(), getGtalk(), getSkype(), getMsn(), getAim(), getYahoo(), getFacebook(), getOrkut(), getTwitter(), getBlog());
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
     * @return the dao
     */
    public MySocialInfoDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(MySocialInfoDao dao) {
        this.dao = dao;
    }

    /**
     * @return the ProfileSocial
     */
    public ProfileSocial getProfileSocial() {
        return ProfileSocial;
    }

    /**
     * @param ProfileSocial the ProfileSocial to set
     */
    public void setProfileSocial(ProfileSocial ProfileSocial) {
        this.ProfileSocial = ProfileSocial;
    }

    /**
     * @return the socialListList
     */
    public List<ProfileSocial> getSocialListList() {
        return socialListList;
    }

    /**
     * @param socialListList the socialListList to set
     */
    public void setSocialListList(List<ProfileSocial> socialListList) {
        this.socialListList = socialListList;
    }

    /**
     * @return the socialInfoId
     */
    public long getSocialInfoId() {
        return socialInfoId;
    }

    /**
     * @param socialInfoId the socialInfoId to set
     */
    public void setSocialInfoId(long socialInfoId) {
        this.socialInfoId = socialInfoId;
    }

    /**
     * @return the gtalk
     */
    public String getGtalk() {
        return gtalk;
    }

    /**
     * @param gtalk the gtalk to set
     */
    public void setGtalk(String gtalk) {
        this.gtalk = gtalk;
    }

    /**
     * @return the skype
     */
    public String getSkype() {
        return skype;
    }

    /**
     * @param skype the skype to set
     */
    public void setSkype(String skype) {
        this.skype = skype;
    }

    /**
     * @return the msn
     */
    public String getMsn() {
        return msn;
    }

    /**
     * @param msn the msn to set
     */
    public void setMsn(String msn) {
        this.msn = msn;
    }

    /**
     * @return the aim
     */
    public String getAim() {
        return aim;
    }

    /**
     * @param aim the aim to set
     */
    public void setAim(String aim) {
        this.aim = aim;
    }

    /**
     * @return the yahoo
     */
    public String getYahoo() {
        return yahoo;
    }

    /**
     * @param yahoo the yahoo to set
     */
    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the orkut
     */
    public String getOrkut() {
        return orkut;
    }

    /**
     * @param orkut the orkut to set
     */
    public void setOrkut(String orkut) {
        this.orkut = orkut;
    }

    /**
     * @return the twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return the blog
     */
    public String getBlog() {
        return blog;
    }

    /**
     * @param blog the blog to set
     */
    public void setBlog(String blog) {
        this.blog = blog;
    }

    /**
     * @return the UserId
     */
    public String getUserId() {
        return UserId;
    }

    /**
     * @param UserId the UserId to set
     */
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
}
