/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.DAO.ConferenceDao;
import org.IGNOU.ePortfolio.Model.Conference;
import org.IGNOU.ePortfolio.Model.ConferenceAuthors;

import java.util.List;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 28-02-2012
 */
public class ConferenceInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private Conference conf = new Conference();
    private ConferenceDao dao = new ConferenceDao();
    private Long conferenceId;
    private String userId;
    private String confType;
    private String researchArea;
    private String assoProject;
    private String projectName;
    private String role;
    private String presentationType;
    private String paperTitle;
    private Integer noCoauthor;
    private Integer pfrom;
    private Integer pto;
    private String conferenceName;
    private String dfrom;
    private String dto;
    private String orgName;
    private String venue;
    private String state;
    private String country;
    private String language;
    private String url;
    private String affiliation;
    private String abstract_;
    private String key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String key6;
    private Set<ConferenceAuthors> conferenceAuthorses = new HashSet<ConferenceAuthors>(0);
    private ArrayList<String> fname;
    private ArrayList<String> lname;
    private List<Conference> ConfListList;
    private MyProfileDAO bdao = new MyProfileDAO();
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public ConferenceInfoAction() {
    }

    public String ShowConfInfo() throws Exception {
        ConfListList = dao.ConferenceListByUserId(user_id);
        if (ConfListList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditConfInfo() throws Exception {
        setConfListList(getDao().ConferenceListByConferenceId(getConferenceId()));
        return SUCCESS;
    }

    public String UpdateConfInfo() throws Exception {
        getDao().ConferenceUpdate(getConferenceId(), getUserId(), getConfType(), getResearchArea(), getAssoProject(), getProjectName(), getRole(), getPresentationType(), getPaperTitle(), getNoCoauthor(), getPfrom(), getPto(), getConferenceName(), getDfrom(), getDto(), getOrgName(), getVenue(), getState(), getCountry(), getLanguage(), getUrl(), getAffiliation(), getAbstract_(), getKey1(), getKey2(), getKey3(), getKey4(), getKey5(), getKey6(), getConferenceAuthorses(), getFname(), getLname());
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteConfInfo() throws Exception {
        getDao().ConferenceDelete(getConferenceId());
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
     * @return the conf
     */
    public Conference getConf() {
        return conf;
    }

    /**
     * @param conf the conf to set
     */
    public void setConf(Conference conf) {
        this.conf = conf;
    }

    /**
     * @return the dao
     */
    public ConferenceDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ConferenceDao dao) {
        this.dao = dao;
    }

    /**
     * @return the conferenceId
     */
    public Long getConferenceId() {
        return conferenceId;
    }

    /**
     * @param conferenceId the conferenceId to set
     */
    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
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
     * @return the confType
     */
    public String getConfType() {
        return confType;
    }

    /**
     * @param confType the confType to set
     */
    public void setConfType(String confType) {
        this.confType = confType;
    }

    /**
     * @return the researchArea
     */
    public String getResearchArea() {
        return researchArea;
    }

    /**
     * @param researchArea the researchArea to set
     */
    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    /**
     * @return the assoProject
     */
    public String getAssoProject() {
        return assoProject;
    }

    /**
     * @param assoProject the assoProject to set
     */
    public void setAssoProject(String assoProject) {
        this.assoProject = assoProject;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
     * @return the presentationType
     */
    public String getPresentationType() {
        return presentationType;
    }

    /**
     * @param presentationType the presentationType to set
     */
    public void setPresentationType(String presentationType) {
        this.presentationType = presentationType;
    }

    /**
     * @return the paperTitle
     */
    public String getPaperTitle() {
        return paperTitle;
    }

    /**
     * @param paperTitle the paperTitle to set
     */
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    /**
     * @return the noCoauthor
     */
    public Integer getNoCoauthor() {
        return noCoauthor;
    }

    /**
     * @param noCoauthor the noCoauthor to set
     */
    public void setNoCoauthor(Integer noCoauthor) {
        this.noCoauthor = noCoauthor;
    }

    /**
     * @return the pfrom
     */
    public Integer getPfrom() {
        return pfrom;
    }

    /**
     * @param pfrom the pfrom to set
     */
    public void setPfrom(Integer pfrom) {
        this.pfrom = pfrom;
    }

    /**
     * @return the pto
     */
    public Integer getPto() {
        return pto;
    }

    /**
     * @param pto the pto to set
     */
    public void setPto(Integer pto) {
        this.pto = pto;
    }

    /**
     * @return the conferenceName
     */
    public String getConferenceName() {
        return conferenceName;
    }

    /**
     * @param conferenceName the conferenceName to set
     */
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    /**
     * @return the dfrom
     */
    public String getDfrom() {
        return dfrom;
    }

    /**
     * @param dfrom the dfrom to set
     */
    public void setDfrom(String dfrom) {
        this.dfrom = dfrom;
    }

    /**
     * @return the dto
     */
    public String getDto() {
        return dto;
    }

    /**
     * @param dto the dto to set
     */
    public void setDto(String dto) {
        this.dto = dto;
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
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
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
     * @return the affiliation
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * @param affiliation the affiliation to set
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * @return the abstract_
     */
    public String getAbstract_() {
        return abstract_;
    }

    /**
     * @param abstract_ the abstract_ to set
     */
    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    /**
     * @return the key1
     */
    public String getKey1() {
        return key1;
    }

    /**
     * @param key1 the key1 to set
     */
    public void setKey1(String key1) {
        this.key1 = key1;
    }

    /**
     * @return the key2
     */
    public String getKey2() {
        return key2;
    }

    /**
     * @param key2 the key2 to set
     */
    public void setKey2(String key2) {
        this.key2 = key2;
    }

    /**
     * @return the key3
     */
    public String getKey3() {
        return key3;
    }

    /**
     * @param key3 the key3 to set
     */
    public void setKey3(String key3) {
        this.key3 = key3;
    }

    /**
     * @return the key4
     */
    public String getKey4() {
        return key4;
    }

    /**
     * @param key4 the key4 to set
     */
    public void setKey4(String key4) {
        this.key4 = key4;
    }

    /**
     * @return the key5
     */
    public String getKey5() {
        return key5;
    }

    /**
     * @param key5 the key5 to set
     */
    public void setKey5(String key5) {
        this.key5 = key5;
    }

    /**
     * @return the key6
     */
    public String getKey6() {
        return key6;
    }

    /**
     * @param key6 the key6 to set
     */
    public void setKey6(String key6) {
        this.key6 = key6;
    }

    /**
     * @return the conferenceAuthorses
     */
    public Set<ConferenceAuthors> getConferenceAuthorses() {
        return conferenceAuthorses;
    }

    /**
     * @param conferenceAuthorses the conferenceAuthorses to set
     */
    public void setConferenceAuthorses(Set<ConferenceAuthors> conferenceAuthorses) {
        this.conferenceAuthorses = conferenceAuthorses;
    }

    /**
     * @return the fname
     */
    public ArrayList<String> getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(ArrayList<String> fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public ArrayList<String> getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(ArrayList<String> lname) {
        this.lname = lname;
    }

    /**
     * @return the ConfListList
     */
    public List<Conference> getConfListList() {
        return ConfListList;
    }

    /**
     * @param ConfListList the ConfListList to set
     */
    public void setConfListList(List<Conference> ConfListList) {
        this.ConfListList = ConfListList;
    }

    /**
     * @return the bdao
     */
    public MyProfileDAO getBdao() {
        return bdao;
    }

    /**
     * @param bdao the bdao to set
     */
    public void setBdao(MyProfileDAO bdao) {
        this.bdao = bdao;
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
