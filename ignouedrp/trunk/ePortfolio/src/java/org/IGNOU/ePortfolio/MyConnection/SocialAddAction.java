/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 
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
package org.IGNOU.ePortfolio.MyConnection;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.Model.ProfileSocial;
import org.IGNOU.ePortfolio.DAO.MySocialInfoDao;

/**
 * @version 1.1
 * @since 04-Sep-2011
 * @author IGNOU Team
 */
public class SocialAddAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private ProfileSocial PS = new ProfileSocial();
    private MySocialInfoDao dao = new MySocialInfoDao();
    private String gtalk, skype, msn, aim, yahoo, facebook, orkut, twitter, blog;

    public SocialAddAction() {
    }

    @Override
    public Object getModel() {
        getPS().setUserId(user_id);
        return PS;
    }

    @Override
    public String execute() throws Exception {
        dao.ProfileSocialSave(getPS());
        return SUCCESS;
    }

    /**
     * @return the PS
     */
    public ProfileSocial getPS() {
        return PS;
    }

    /**
     * @param PS the PS to set
     */
    public void setPS(ProfileSocial PS) {
        this.PS = PS;
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
}
