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
import org.IGNOU.ePortfolio.DAO.HonorAwardDao;
import org.IGNOU.ePortfolio.Model.ProfileHonorAward;
import org.hibernate.HibernateException;

/**
 * @version 1.1
 * @author IGNOU Team
 * @since 13-Oct-2011 Modified by IGNOU Team on 14-Oct-2011 XML Validation are
 * implemented.
 */
public class HonorAwardAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private HonorAwardDao dao = new HonorAwardDao();
    private ProfileHonorAward ProHonor = new ProfileHonorAward();
    private String user_id = new UserSession().getUserInSession();
    private String haTitle;
    private String issuer;
    private String haDate;
    private String haDescription;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public HonorAwardAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            dao.AddHonorInfo(ProHonor);
            msg = infoSaved;
        } catch (HibernateException HE) {
            msg = "Data has not been Saved Successfully. The Error is : " + HE.toString();
        }
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getProHonor().setUserId(user_id);
        return getProHonor();
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
