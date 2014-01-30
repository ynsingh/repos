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
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.Model.ProfileSkill;
import org.apache.log4j.Logger;

/**
 * Created On 14-Sep-2011.
 *
 * @author IGNOU Team
 */
public class SkillAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
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
    private String msg, permsg, techmsg, othmsg, acdmsg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String recordNotFound = getText("recordNotFound");

    public SkillAction() {
    }

    public String ShowSkillInfo() throws Exception {
        PersonalList = dao.ProfileSkillListByUserIdType(user_id, ptype);
        if (PersonalList.isEmpty()) {
            permsg = recordNotFound;
        }
        AcademicList = dao.ProfileSkillListByUserIdType(user_id, atype);
        if (AcademicList.isEmpty()) {
            acdmsg = recordNotFound;
        }
        TechnicalList = dao.ProfileSkillListByUserIdType(user_id, ttype);
        if (TechnicalList.isEmpty()) {
            techmsg = recordNotFound;
        }
        OtherList = dao.ProfileSkillListByUserIdType(user_id, otype);
        if (OtherList.isEmpty()) {
            othmsg = recordNotFound;
        }
        return SUCCESS;
    }

    /**
     * Added on 15-Sep-2011
     *
     * @return SUCCESS
     * @throws Exception
     * @author IGNOU Team
     *
     */
    public String DeleteSkillInfo() throws Exception {
        dao.ProfileSkillDelete(getSkillId());
        msg = infoDeleted;
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

    /**
     * Added on 15-Sep-2011
     *
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
     * @return the ProfileAcademicListByUserId
     */
    public List<ProfileSkill> getAcademicList() {
        return AcademicList;
    }

    /**
     * @param ProfileAcademicListByUserId the ProfileAcademicListByUserId to set
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
     * @return the recordNotFound
     */
    public String getRecordNotFound() {
        return recordNotFound;
    }

    /**
     * @param recordNotFound the recordNotFound to set
     */
    public void setRecordNotFound(String recordNotFound) {
        this.recordNotFound = recordNotFound;
    }

    /**
     * @return the permsg
     */
    public String getPermsg() {
        return permsg;
    }

    /**
     * @param permsg the permsg to set
     */
    public void setPermsg(String permsg) {
        this.permsg = permsg;
    }

    /**
     * @return the techmsg
     */
    public String getTechmsg() {
        return techmsg;
    }

    /**
     * @param techmsg the techmsg to set
     */
    public void setTechmsg(String techmsg) {
        this.techmsg = techmsg;
    }

    /**
     * @return the othmsg
     */
    public String getOthmsg() {
        return othmsg;
    }

    /**
     * @param othmsg the othmsg to set
     */
    public void setOthmsg(String othmsg) {
        this.othmsg = othmsg;
    }

    /**
     * @return the acdmsg
     */
    public String getAcdmsg() {
        return acdmsg;
    }

    /**
     * @param acdmsg the acdmsg to set
     */
    public void setAcdmsg(String acdmsg) {
        this.acdmsg = acdmsg;
    }
}
