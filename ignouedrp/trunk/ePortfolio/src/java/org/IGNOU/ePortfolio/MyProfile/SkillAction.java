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

import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.MyProfile.Dao.MyProfileDAO;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileSkill;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *Created On 14-Sep-2011.
 * @author Vinay
 */
public class SkillAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private MyProfileDAO dao = new MyProfileDAO();
    private ProfileSkill skil;
    private List<ProfileSkill> PersonalList;
    private List<ProfileSkill> AcademicList;
    private List<ProfileSkill> TechnicalList;
    private List<ProfileSkill> OtherList;
    private long skillId;
    private String atype = "Academic";
    private String ttype = "Technical";
    private String ptype = "Personal";
    private String otype = "Others";

    public SkillAction() {
    }

    public String ShowSkillInfo() throws Exception {
        PersonalList = dao.ShowSkill(user_id, ptype);
        AcademicList = dao.ShowSkill(user_id, atype);
        TechnicalList = dao.ShowSkill(user_id, ttype);
        OtherList = dao.ShowSkill(user_id, otype);
        return SUCCESS;
    }

    /**
     * Added on 15-Sep-2011
     *@return  SUCCESS
     * @throws Exception 
     * @author Vinay
     **/
    public String DeleteSkillInfo() throws Exception {
        dao.DeleteSkilInfo(getSkillId());
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
     * @return the skil
     */
    public ProfileSkill getSkil() {
        return skil;
    }

    /**
     * @param skil the skil to set
     */
    public void setSkil(ProfileSkill skil) {
        this.skil = skil;
    }

    /** Added on 15-Sep-2011
     * @return the skillId
     */
    public long getSkillId() {
        return skillId;
    }

    /**
     * @param skillId the skillId to set
     */
    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    /**
     * @return the PersonalList
     */
    public List<ProfileSkill> getPersonalList() {
        return PersonalList;
    }

    /**
     * @param PersonalList the PersonalList to set
     */
    public void setPersonalList(List<ProfileSkill> PersonalList) {
        this.PersonalList = PersonalList;
    }

    /**
     * @return the AcademicList
     */
    public List<ProfileSkill> getAcademicList() {
        return AcademicList;
    }

    /**
     * @param AcademicList the AcademicList to set
     */
    public void setAcademicList(List<ProfileSkill> AcademicList) {
        this.AcademicList = AcademicList;
    }

    /**
     * @return the TechnicalList
     */
    public List<ProfileSkill> getTechnicalList() {
        return TechnicalList;
    }

    /**
     * @param TechnicalList the TechnicalList to set
     */
    public void setTechnicalList(List<ProfileSkill> TechnicalList) {
        this.TechnicalList = TechnicalList;
    }

    /**
     * @return the OtherList
     */
    public List<ProfileSkill> getOtherList() {
        return OtherList;
    }

    /**
     * @param OtherList the OtherList to set
     */
    public void setOtherList(List<ProfileSkill> OtherList) {
        this.OtherList = OtherList;
    }
}
