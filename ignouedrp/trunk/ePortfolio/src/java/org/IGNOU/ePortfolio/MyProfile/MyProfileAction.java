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
import org.IGNOU.ePortfolio.Model.ProfileAcademic;
import org.IGNOU.ePortfolio.Model.ProfileEmployment;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class MyProfileAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private MyProfileDAO dao = new MyProfileDAO();
    private ProfileAcademic ProfileAcademic;
    private List<ProfileAcademic> academicListList;
    private List<ProfileAcademic> editAcademicList;
    private List<Long> academicInfoId;
    private List<String> degree;
    private List<String> university;
    private List<String> location;
    private List<String> fstudy;
    private List<String> pyear;
    private List<Integer> percent;
    private List<String> division;
    /*End*/
    private ProfileEmployment ProfileEmployment;
    private List<ProfileEmployment> employmentListList;
    private List<ProfileEmployment> empListList;
    private long employmentInfoId;
    private String jtitle, orgName, oaddress, ocity, ostate, ocountry, jdate, ldate, description;
    private String userId, fname, mname, lname, gender, dateOfBirth, pbirth, mstatus, aboutMe;
    private long personalInfo;
    private String msg;
    private String deleteInfo = getText("msg.infoDeleted"), updateInfo = getText("msg.infoUpdated"), notFound = getText("recordNotFound");

    /*
     * Show and Update Methods begin....
     */
    public String ShowAcademicInfo() {
        setAcademicListList(getDao().ProfileAcademicListByUserId(getUser_id()));
        if (getAcademicListList().isEmpty()) {
            setMsg(getNotFound());
        } else {
        }
        return SUCCESS;
    }

    public String EditAcademicInfo() {
        setEditAcademicList(getDao().ProfileAcademicEdit(getUser_id()));
        return SUCCESS;
    }

    public String UpdateAcademicInfo() {
        //dao.ProfileAcademicUpdate(getAcademicInfoId(), getUserId(), getUniversity(), getDegree(), getFstudy(), getAdate(), getCdate(), getActivities(), getAdditionalNote());
        /**
         * Added on 04-04-2012 by IGNOU Team
         *
         * @Version 2
         */
        getDao().ProfileAcademicUpdate(getAcademicInfoId(), getDegree(), getDegree(), getUniversity(), getLocation(), getFstudy(), getPyear(), getPercent(), getDivision());
        setMsg(getUpdateInfo());
        return SUCCESS;
    }

    /**
     * Created on 13-Sep-2011
     *
     * @author IGNOU Team
     * @return SUCCESS
     */
    public String ShowEmploymentInfo() {
        setEmploymentListList(getDao().ProfileEmployementListByUserId(getUser_id()));
        if (getEmploymentListList().isEmpty()) {
            setMsg(getNotFound());
        } else {
        }
        return SUCCESS;
    }

    /**
     * Created on 13-Sep-2011
     *
     * @author IGNOU Team
     * @return SUCCESS
     * @throws Exception
     */
    public String EditEmploymentInfo() throws Exception {
        setEmpListList(getDao().ProfileEmploymentlListByEmplymentId(getEmploymentInfoId()));
        return SUCCESS;
    }

    /**
     * Created on 13-Sep-2011
     *
     * @author IGNOU Team
     * @return SUCCESS
     * @throws Exception
     */
    public String UpdateEmploymentInfo() throws Exception {
        getDao().ProfileEmploymentUpdate(getEmploymentInfoId(), getUserId(), getJtitle(), getOrgName(), getOaddress(), getOcity(), getOstate(), getOcountry(), getJdate(), getLdate(), getDescription());
        setMsg(getUpdateInfo());
        return SUCCESS;
    }

    /**
     * Created on 13-Sep-2011
     *
     * @author IGNOU Team
     * @return SUCCESS
     * @throws Exception
     */
    public String DeleteEmploymentInfo() throws Exception {
        getDao().ProfileEmploymentDelete(getEmploymentInfoId());
        setMsg(getDeleteInfo());
        return SUCCESS;
    }

    /*
     * Setter And Getter Methods Begin.....
     */

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
     * @return the ProfileAcademic
     */
    public ProfileAcademic getProfileAcademic() {
        return ProfileAcademic;
    }

    /**
     * @param ProfileAcademic the ProfileAcademic to set
     */
    public void setProfileAcademic(ProfileAcademic ProfileAcademic) {
        this.ProfileAcademic = ProfileAcademic;
    }

    /**
     * @return the academicListList
     */
    public List<ProfileAcademic> getAcademicListList() {
        return academicListList;
    }

    /**
     * @param academicListList the academicListList to set
     */
    public void setAcademicListList(List<ProfileAcademic> academicListList) {
        this.academicListList = academicListList;
    }

    /**
     * @return the editAcademicList
     */
    public List<ProfileAcademic> getEditAcademicList() {
        return editAcademicList;
    }

    /**
     * @param editAcademicList the editAcademicList to set
     */
    public void setEditAcademicList(List<ProfileAcademic> editAcademicList) {
        this.editAcademicList = editAcademicList;
    }

    /**
     * @return the academicInfoId
     */
    public List<Long> getAcademicInfoId() {
        return academicInfoId;
    }

    /**
     * @param academicInfoId the academicInfoId to set
     */
    public void setAcademicInfoId(List<Long> academicInfoId) {
        this.academicInfoId = academicInfoId;
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
     * @return the ProfileEmployment
     */
    public ProfileEmployment getProfileEmployment() {
        return ProfileEmployment;
    }

    /**
     * @param ProfileEmployment the ProfileEmployment to set
     */
    public void setProfileEmployment(ProfileEmployment ProfileEmployment) {
        this.ProfileEmployment = ProfileEmployment;
    }

    /**
     * @return the employmentListList
     */
    public List<ProfileEmployment> getEmploymentListList() {
        return employmentListList;
    }

    /**
     * @param employmentListList the employmentListList to set
     */
    public void setEmploymentListList(List<ProfileEmployment> employmentListList) {
        this.employmentListList = employmentListList;
    }

    /**
     * @return the empListList
     */
    public List<ProfileEmployment> getEmpListList() {
        return empListList;
    }

    /**
     * @param empListList the empListList to set
     */
    public void setEmpListList(List<ProfileEmployment> empListList) {
        this.empListList = empListList;
    }

    /**
     * @return the employmentInfoId
     */
    public long getEmploymentInfoId() {
        return employmentInfoId;
    }

    /**
     * @param employmentInfoId the employmentInfoId to set
     */
    public void setEmploymentInfoId(long employmentInfoId) {
        this.employmentInfoId = employmentInfoId;
    }

    /**
     * @return the jtitle
     */
    public String getJtitle() {
        return jtitle;
    }

    /**
     * @param jtitle the jtitle to set
     */
    public void setJtitle(String jtitle) {
        this.jtitle = jtitle;
    }

    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return the oaddress
     */
    public String getOaddress() {
        return oaddress;
    }

    /**
     * @param oaddress the oaddress to set
     */
    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    /**
     * @return the ocity
     */
    public String getOcity() {
        return ocity;
    }

    /**
     * @param ocity the ocity to set
     */
    public void setOcity(String ocity) {
        this.ocity = ocity;
    }

    /**
     * @return the ostate
     */
    public String getOstate() {
        return ostate;
    }

    /**
     * @param ostate the ostate to set
     */
    public void setOstate(String ostate) {
        this.ostate = ostate;
    }

    /**
     * @return the ocountry
     */
    public String getOcountry() {
        return ocountry;
    }

    /**
     * @param ocountry the ocountry to set
     */
    public void setOcountry(String ocountry) {
        this.ocountry = ocountry;
    }

    /**
     * @return the jdate
     */
    public String getJdate() {
        return jdate;
    }

    /**
     * @param jdate the jdate to set
     */
    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    /**
     * @return the ldate
     */
    public String getLdate() {
        return ldate;
    }

    /**
     * @param ldate the ldate to set
     */
    public void setLdate(String ldate) {
        this.ldate = ldate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * @param mname the mname to set
     */
    public void setMname(String mname) {
        this.mname = mname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the pbirth
     */
    public String getPbirth() {
        return pbirth;
    }

    /**
     * @param pbirth the pbirth to set
     */
    public void setPbirth(String pbirth) {
        this.pbirth = pbirth;
    }

    /**
     * @return the mstatus
     */
    public String getMstatus() {
        return mstatus;
    }

    /**
     * @param mstatus the mstatus to set
     */
    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    /**
     * @return the aboutMe
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * @param aboutMe the aboutMe to set
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     * @return the personalInfo
     */
    public long getPersonalInfo() {
        return personalInfo;
    }

    /**
     * @param personalInfo the personalInfo to set
     */
    public void setPersonalInfo(long personalInfo) {
        this.personalInfo = personalInfo;
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
     * @return the deleteInfo
     */
    public String getDeleteInfo() {
        return deleteInfo;
    }

    /**
     * @param deleteInfo the deleteInfo to set
     */
    public void setDeleteInfo(String deleteInfo) {
        this.deleteInfo = deleteInfo;
    }

    /**
     * @return the updateInfo
     */
    public String getUpdateInfo() {
        return updateInfo;
    }

    /**
     * @param updateInfo the updateInfo to set
     */
    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
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
