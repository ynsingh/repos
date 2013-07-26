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
import org.IGNOU.ePortfolio.DAO.AddInfoDao;
import org.IGNOU.ePortfolio.Model.ProfileAcademic;

/**
 * Created on 09-Sep-2011 Edited on 12-Sep-2011
 *
 * @author IGNOU Team
 */
public class AcademicInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private ProfileAcademic PAM = new ProfileAcademic();
    private AddInfoDao dao = new AddInfoDao();
    private List<String> degree;
    private List<String> university;
    private List<String> location;
    private List<String> fstudy;
    private List<String> pyear;
    private List<Integer> percent;
    private List<String> division;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public AcademicInfoAction(List<String> degree, List<String> university, List<String> location, List<String> fstudy, List<String> pyear, List<Integer> percent, List<String> division) {
        this.degree = degree;
        this.university = university;
        this.location = location;
        this.fstudy = fstudy;
        this.pyear = pyear;
        this.percent = percent;
        this.division = division;
    }

    public AcademicInfoAction() {
    }

    @Override
    public String execute() {
        dao.ProfileAcademicSave(user_id, degree, university, location, fstudy, pyear, percent, division);
        msg = infoSaved;
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
     * @return the PAM
     */
    public ProfileAcademic getPAM() {
        return PAM;
    }

    /**
     * @param PAM the PAM to set
     */
    public void setPAM(ProfileAcademic PAM) {
        this.PAM = PAM;
    }

    /**
     * @return the dao
     */
    public AddInfoDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(AddInfoDao dao) {
        this.dao = dao;
    }

    /**
     * @return the degree
     */
    public List<String> getDegree() {
        return degree;
    }

    /**
     * @param degree the degree to set
     */
    public void setDegree(List<String> degree) {
        this.degree = degree;
    }

    /**
     * @return the university
     */
    public List<String> getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(List<String> university) {
        this.university = university;
    }

    /**
     * @return the location
     */
    public List<String> getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(List<String> location) {
        this.location = location;
    }

    /**
     * @return the fstudy
     */
    public List<String> getFstudy() {
        return fstudy;
    }

    /**
     * @param fstudy the fstudy to set
     */
    public void setFstudy(List<String> fstudy) {
        this.fstudy = fstudy;
    }

    /**
     * @return the pyear
     */
    public List<String> getPyear() {
        return pyear;
    }

    /**
     * @param pyear the pyear to set
     */
    public void setPyear(List<String> pyear) {
        this.pyear = pyear;
    }

    /**
     * @return the percent
     */
    public List<Integer> getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(List<Integer> percent) {
        this.percent = percent;
    }

    /**
     * @return the division
     */
    public List<String> getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(List<String> division) {
        this.division = division;
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
