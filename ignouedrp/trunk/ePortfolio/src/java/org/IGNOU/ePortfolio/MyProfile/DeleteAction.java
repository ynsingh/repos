/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.Model.ProfileAcademic;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 2
 * @since 05-04-2012
 */
public class DeleteAction extends ActionSupport implements Serializable  {

    final Logger logger = Logger.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    private ProfileAcademic PAcad = new ProfileAcademic();
    private MyProfileDAO dao = new MyProfileDAO();
    private long academicInfoId;
    private String userId;
    private String degree;
    private String university;
    private String location;
    private String fstudy;
    private String pyear;
    private Integer percent;
    private String division;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");

    public DeleteAction() {
    }

    public String DeleteAcademicInfo() {
        getDao().ProfileAcademicDelete(getAcademicInfoId());
        msg = infoDeleted;
        return SUCCESS;
    }

    /**
     * @return the PAcad
     */
    public ProfileAcademic getPAcad() {
        return PAcad;
    }

    /**
     * @param PAcad the PAcad to set
     */
    public void setPAcad(ProfileAcademic PAcad) {
        this.PAcad = PAcad;
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
     * @return the academicInfoId
     */
    public long getAcademicInfoId() {
        return academicInfoId;
    }

    /**
     * @param academicInfoId the academicInfoId to set
     */
    public void setAcademicInfoId(long academicInfoId) {
        this.academicInfoId = academicInfoId;
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
     * @return the degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * @param degree the degree to set
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * @return the university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the fstudy
     */
    public String getFstudy() {
        return fstudy;
    }

    /**
     * @param fstudy the fstudy to set
     */
    public void setFstudy(String fstudy) {
        this.fstudy = fstudy;
    }

    /**
     * @return the pyear
     */
    public String getPyear() {
        return pyear;
    }

    /**
     * @param pyear the pyear to set
     */
    public void setPyear(String pyear) {
        this.pyear = pyear;
    }

    /**
     * @return the percent
     */
    public Integer getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
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
}
