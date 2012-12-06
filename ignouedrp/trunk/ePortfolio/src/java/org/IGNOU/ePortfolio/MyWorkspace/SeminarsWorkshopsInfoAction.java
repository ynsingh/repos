/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.DAO.SeminarsWorkshopsDao;
import org.IGNOU.ePortfolio.Model.SeminarsWorkshops;
import org.IGNOU.ePortfolio.Model.SeminarsWorkshopsAuthor;

/**
 *
 * @author IGNOU Team
 * @author 1
 * @since 24-02-2012
 */
public class SeminarsWorkshopsInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private SeminarsWorkshops sw = new SeminarsWorkshops();
    private SeminarsWorkshopsDao dao = new SeminarsWorkshopsDao();
    private Long seminarsWorkshopsId;
    private String userId;
    private String swType;
    private String swName;
    private String DFrom;
    private String DTo;
    private String venue;
    private String state;
    private String country;
    private String swRole;
    private String perType;
    private String paperTitle;
    private Integer noCoauthors;
    private String areaThemeTopic;
    private String sourceFunding;
    private Long amountFunded;
    private String language;
    private String url;
    private String abstract_;
    private Set<SeminarsWorkshopsAuthor> seminarsWorkshopsAuthors = new HashSet<SeminarsWorkshopsAuthor>(0);
    private ArrayList<String> fname;
    private ArrayList<String> lname;
    private List<SeminarsWorkshops> SWListList;
    private MyProfileDAO bdao = new MyProfileDAO();
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public SeminarsWorkshopsInfoAction() {
    }

    public String ShowSWInfo() throws Exception {
        setSWListList(getDao().ShowSW(getUser_id()));
        if (getSWListList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditSWInfo() throws Exception {
        setSWListList(getDao().EditSW(getSeminarsWorkshopsId()));
        return SUCCESS;
    }

    public String UpdateSWInfo() throws Exception {
        getDao().UpdateSW(getSeminarsWorkshopsId(), getUserId(), getSwType(), getSwName(), getDFrom(), getDTo(), getVenue(), getState(), getCountry(), getSwRole(), getPerType(), getPaperTitle(), getNoCoauthors(), getAreaThemeTopic(), getSourceFunding(), getAmountFunded(), getLanguage(), getUrl(), getAbstract_(), getSeminarsWorkshopsAuthors(), getFname(), getLname());
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteSWInfo() throws Exception {
        getDao().DeleteSW(getSeminarsWorkshopsId());
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
     * @return the sw
     */
    public SeminarsWorkshops getSw() {
        return sw;
    }

    /**
     * @param sw the sw to set
     */
    public void setSw(SeminarsWorkshops sw) {
        this.sw = sw;
    }

    /**
     * @return the dao
     */
    public SeminarsWorkshopsDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(SeminarsWorkshopsDao dao) {
        this.dao = dao;
    }

    /**
     * @return the seminarsWorkshopsId
     */
    public Long getSeminarsWorkshopsId() {
        return seminarsWorkshopsId;
    }

    /**
     * @param seminarsWorkshopsId the seminarsWorkshopsId to set
     */
    public void setSeminarsWorkshopsId(Long seminarsWorkshopsId) {
        this.seminarsWorkshopsId = seminarsWorkshopsId;
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
     * @return the swType
     */
    public String getSwType() {
        return swType;
    }

    /**
     * @param swType the swType to set
     */
    public void setSwType(String swType) {
        this.swType = swType;
    }

    /**
     * @return the swName
     */
    public String getSwName() {
        return swName;
    }

    /**
     * @param swName the swName to set
     */
    public void setSwName(String swName) {
        this.swName = swName;
    }

    /**
     * @return the DFrom
     */
    public String getDFrom() {
        return DFrom;
    }

    /**
     * @param DFrom the DFrom to set
     */
    public void setDFrom(String DFrom) {
        this.DFrom = DFrom;
    }

    /**
     * @return the DTo
     */
    public String getDTo() {
        return DTo;
    }

    /**
     * @param DTo the DTo to set
     */
    public void setDTo(String DTo) {
        this.DTo = DTo;
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
     * @return the swRole
     */
    public String getSwRole() {
        return swRole;
    }

    /**
     * @param swRole the swRole to set
     */
    public void setSwRole(String swRole) {
        this.swRole = swRole;
    }

    /**
     * @return the perType
     */
    public String getPerType() {
        return perType;
    }

    /**
     * @param perType the perType to set
     */
    public void setPerType(String perType) {
        this.perType = perType;
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
     * @return the noCoauthors
     */
    public Integer getNoCoauthors() {
        return noCoauthors;
    }

    /**
     * @param noCoauthors the noCoauthors to set
     */
    public void setNoCoauthors(Integer noCoauthors) {
        this.noCoauthors = noCoauthors;
    }

    /**
     * @return the areaThemeTopic
     */
    public String getAreaThemeTopic() {
        return areaThemeTopic;
    }

    /**
     * @param areaThemeTopic the areaThemeTopic to set
     */
    public void setAreaThemeTopic(String areaThemeTopic) {
        this.areaThemeTopic = areaThemeTopic;
    }

    /**
     * @return the sourceFunding
     */
    public String getSourceFunding() {
        return sourceFunding;
    }

    /**
     * @param sourceFunding the sourceFunding to set
     */
    public void setSourceFunding(String sourceFunding) {
        this.sourceFunding = sourceFunding;
    }

    /**
     * @return the amountFunded
     */
    public Long getAmountFunded() {
        return amountFunded;
    }

    /**
     * @param amountFunded the amountFunded to set
     */
    public void setAmountFunded(Long amountFunded) {
        this.amountFunded = amountFunded;
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
     * @return the seminarsWorkshopsAuthors
     */
    public Set<SeminarsWorkshopsAuthor> getSeminarsWorkshopsAuthors() {
        return seminarsWorkshopsAuthors;
    }

    /**
     * @param seminarsWorkshopsAuthors the seminarsWorkshopsAuthors to set
     */
    public void setSeminarsWorkshopsAuthors(Set<SeminarsWorkshopsAuthor> seminarsWorkshopsAuthors) {
        this.seminarsWorkshopsAuthors = seminarsWorkshopsAuthors;
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
     * @return the SWListList
     */
    public List<SeminarsWorkshops> getSWListList() {
        return SWListList;
    }

    /**
     * @param SWListList the SWListList to set
     */
    public void setSWListList(List<SeminarsWorkshops> SWListList) {
        this.SWListList = SWListList;
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
