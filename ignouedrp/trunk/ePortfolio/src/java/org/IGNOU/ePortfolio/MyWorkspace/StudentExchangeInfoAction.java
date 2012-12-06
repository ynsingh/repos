/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.StudentExchangeDao;
import org.IGNOU.ePortfolio.Model.StudentExchange;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 28 December 2011
 */
public class StudentExchangeInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private StudentExchangeDao dao = new StudentExchangeDao();
    private StudentExchange ExchangeModel = new StudentExchange();
    private List<StudentExchange> ExchangeProgrammeList = null;
    private long studentExchangeId;
    private String programmeType, nameUniversity, role, programmeTheme, venue, state, country, durationFrom, durationTo, degreeLevel, degraeeName, researchColl, url, description, userId, ifOther;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public String ShowInfo() throws Exception {
        ExchangeProgrammeList = dao.ShowExchangeInfo(user_id);
        if (ExchangeProgrammeList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String DeleteInfo() throws Exception {
        dao.DeleteExchangeInfo(studentExchangeId);
        msg = infoDeleted;
        return SUCCESS;
    }

    public String EditInfo() throws Exception {
        ExchangeProgrammeList = dao.EditExchangeInfo(studentExchangeId);
        return SUCCESS;
    }

    public String UpdateInfo() throws Exception {
        getDao().UpdateExchangeInfo(studentExchangeId, programmeType, nameUniversity, role, programmeTheme, venue, state, country, durationFrom, durationTo, degreeLevel, degraeeName, researchColl, url, description, userId, ifOther);
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
     * @return the dao
     */
    public StudentExchangeDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(StudentExchangeDao dao) {
        this.dao = dao;
    }

    /**
     * @return the ExchangeModel
     */
    public StudentExchange getExchangeModel() {
        return ExchangeModel;
    }

    /**
     * @param ExchangeModel the ExchangeModel to set
     */
    public void setExchangeModel(StudentExchange ExchangeModel) {
        this.ExchangeModel = ExchangeModel;
    }

    /**
     * @return the ExchangeProgrammeList
     */
    public List<StudentExchange> getExchangeProgrammeList() {
        return ExchangeProgrammeList;
    }

    /**
     * @param ExchangeProgrammeList the ExchangeProgrammeList to set
     */
    public void setExchangeProgrammeList(List<StudentExchange> ExchangeProgrammeList) {
        this.ExchangeProgrammeList = ExchangeProgrammeList;
    }

    /**
     * @return the studentExchangeId
     */
    public long getStudentExchangeId() {
        return studentExchangeId;
    }

    /**
     * @param studentExchangeId the studentExchangeId to set
     */
    public void setStudentExchangeId(long studentExchangeId) {
        this.studentExchangeId = studentExchangeId;
    }

    /**
     * @return the programmeType
     */
    public String getProgrammeType() {
        return programmeType;
    }

    /**
     * @param programmeType the programmeType to set
     */
    public void setProgrammeType(String programmeType) {
        this.programmeType = programmeType;
    }

    /**
     * @return the nameUniversity
     */
    public String getNameUniversity() {
        return nameUniversity;
    }

    /**
     * @param nameUniversity the nameUniversity to set
     */
    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
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
     * @return the programmeTheme
     */
    public String getProgrammeTheme() {
        return programmeTheme;
    }

    /**
     * @param programmeTheme the programmeTheme to set
     */
    public void setProgrammeTheme(String programmeTheme) {
        this.programmeTheme = programmeTheme;
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
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
     * @return the durationFrom
     */
    public String getDurationFrom() {
        return durationFrom;
    }

    /**
     * @param durationFrom the durationFrom to set
     */
    public void setDurationFrom(String durationFrom) {
        this.durationFrom = durationFrom;
    }

    /**
     * @return the durationTo
     */
    public String getDurationTo() {
        return durationTo;
    }

    /**
     * @param durationTo the durationTo to set
     */
    public void setDurationTo(String durationTo) {
        this.durationTo = durationTo;
    }

    /**
     * @return the degreeLevel
     */
    public String getDegreeLevel() {
        return degreeLevel;
    }

    /**
     * @param degreeLevel the degreeLevel to set
     */
    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    /**
     * @return the degraeeName
     */
    public String getDegraeeName() {
        return degraeeName;
    }

    /**
     * @param degraeeName the degraeeName to set
     */
    public void setDegraeeName(String degraeeName) {
        this.degraeeName = degraeeName;
    }

    /**
     * @return the researchColl
     */
    public String getResearchColl() {
        return researchColl;
    }

    /**
     * @param researchColl the researchColl to set
     */
    public void setResearchColl(String researchColl) {
        this.researchColl = researchColl;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the ifOther
     */
    public String getIfOther() {
        return ifOther;
    }

    /**
     * @param ifOther the ifOther to set
     */
    public void setIfOther(String ifOther) {
        this.ifOther = ifOther;
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
