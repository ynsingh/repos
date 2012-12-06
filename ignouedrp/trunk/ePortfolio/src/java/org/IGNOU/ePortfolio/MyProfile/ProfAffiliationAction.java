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
import org.IGNOU.ePortfolio.DAO.ProfAffiliationDao;
import org.IGNOU.ePortfolio.Model.ProfileProAffiliation;
import org.hibernate.HibernateException;

/**
 * @version 1
 * @since 14-Oct-2011
 * @author IGNOU Team
 */
public class ProfAffiliationAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private ProfileProAffiliation ProfAffili = new ProfileProAffiliation();
    private ProfAffiliationDao dao = new ProfAffiliationDao();
    private String role;
    private String orgBody;
    private String vfrom;
    private String place;
    private String country;
    private String summary;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public ProfAffiliationAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            dao.AddAffiliationInfo(ProfAffili);
        } catch (HibernateException HE) {
            return HE.toString();
        }
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getProfAffili().setUserId(user_id);
        return getProfAffili();
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
