/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.DAO.JournalDao;
import org.IGNOU.ePortfolio.Model.Journal;
import org.IGNOU.ePortfolio.Model.JournalAuthor;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 27-02-2012
 */
public class JournalInfoAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private Journal j = new Journal();
    private JournalDao dao = new JournalDao();
    private long journalId;
    private String userId;
    private String confType;
    private String researchArea;
    private String asssProject;
    private String projectName;
    private String paperTitle;
    private Integer noCoauthor;
    private String journalName;
    private Integer volumeNo;
    private Integer serialNo;
    private String issnNo;
    private Integer pfrom;
    private Integer pto;
    private String date;
    private String impactFactor;
    private String avgCitagtionIndex;
    private String scopus;
    private String language;
    private String affiliation;
    private String url;
    private String summary;
    private String key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String key6;
    private Set<JournalAuthor> journalAuthors = new HashSet<JournalAuthor>(0);
    private ArrayList<String> fname;
    private ArrayList<String> lname;
    private List<Journal> JListList;
    private MyProfileDAO bdao = new MyProfileDAO();
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public JournalInfoAction() {
    }

    public String ShowJournalInfo() throws Exception {
        setJListList(getDao().JournalListByUserId(getUser_id()));
        if (getJListList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditJournalInfo() throws Exception {
        setJListList(getDao().JournalListByJournalId(getJournalId()));
        return SUCCESS;
    }

    public String UpdateJournalInfo() throws Exception {
        getDao().JournalUpdate(getJournalId(), getUserId(), getConfType(), getResearchArea(), getAsssProject(), getProjectName(), getPaperTitle(), getNoCoauthor(), getJournalName(), getVolumeNo(), getSerialNo(), getIssnNo(), getPfrom(), getPto(), getDate(), getImpactFactor(), getAvgCitagtionIndex(), getScopus(), getLanguage(), getAffiliation(), getUrl(), getSummary(), getKey1(), getKey2(), getKey3(), getKey4(), getKey5(), getKey6(), getJournalAuthors(), getFname(), getLname());
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteJournalInfo() throws Exception {
        getDao().JournalDeleteByJournalId(getJournalId());
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
     * @return the j
     */
    public Journal getJ() {
        return j;
    }

    /**
     * @param j the j to set
     */
    public void setJ(Journal j) {
        this.j = j;
    }

    /**
     * @return the dao
     */
    public JournalDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(JournalDao dao) {
        this.dao = dao;
    }

    /**
     * @return the journalId
     */
    public long getJournalId() {
        return journalId;
    }

    /**
     * @param journalId the journalId to set
     */
    public void setJournalId(long journalId) {
        this.journalId = journalId;
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
     * @return the asssProject
     */
    public String getAsssProject() {
        return asssProject;
    }

    /**
     * @param asssProject the asssProject to set
     */
    public void setAsssProject(String asssProject) {
        this.asssProject = asssProject;
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
     * @return the journalName
     */
    public String getJournalName() {
        return journalName;
    }

    /**
     * @param journalName the journalName to set
     */
    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    /**
     * @return the volumeNo
     */
    public Integer getVolumeNo() {
        return volumeNo;
    }

    /**
     * @param volumeNo the volumeNo to set
     */
    public void setVolumeNo(Integer volumeNo) {
        this.volumeNo = volumeNo;
    }

    /**
     * @return the serialNo
     */
    public Integer getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return the issnNo
     */
    public String getIssnNo() {
        return issnNo;
    }

    /**
     * @param issnNo the issnNo to set
     */
    public void setIssnNo(String issnNo) {
        this.issnNo = issnNo;
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the impactFactor
     */
    public String getImpactFactor() {
        return impactFactor;
    }

    /**
     * @param impactFactor the impactFactor to set
     */
    public void setImpactFactor(String impactFactor) {
        this.impactFactor = impactFactor;
    }

    /**
     * @return the avgCitagtionIndex
     */
    public String getAvgCitagtionIndex() {
        return avgCitagtionIndex;
    }

    /**
     * @param avgCitagtionIndex the avgCitagtionIndex to set
     */
    public void setAvgCitagtionIndex(String avgCitagtionIndex) {
        this.avgCitagtionIndex = avgCitagtionIndex;
    }

    /**
     * @return the scopus
     */
    public String getScopus() {
        return scopus;
    }

    /**
     * @param scopus the scopus to set
     */
    public void setScopus(String scopus) {
        this.scopus = scopus;
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
     * @return the journalAuthors
     */
    public Set<JournalAuthor> getJournalAuthors() {
        return journalAuthors;
    }

    /**
     * @param journalAuthors the journalAuthors to set
     */
    public void setJournalAuthors(Set<JournalAuthor> journalAuthors) {
        this.journalAuthors = journalAuthors;
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
     * @return the JListList
     */
    public List<Journal> getJListList() {
        return JListList;
    }

    /**
     * @param JListList the JListList to set
     */
    public void setJListList(List<Journal> JListList) {
        this.JListList = JListList;
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
