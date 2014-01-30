/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.DAO.ThesisDissertationDao;
import org.IGNOU.ePortfolio.Model.ThesisDissertation;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 21-02-2012
 */
public class ThesisDissertationInfoAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ThesisDissertation TD = new ThesisDissertation();
    private ThesisDissertationDao dao = new ThesisDissertationDao();
    private long thesisDissertationId;
    private String userId;
    private String reportType;
    private String programme;
    private String other;
    private String nameUniversity;
    private String department;
    private String cityState;
    private String country;
    private String thesisType;
    private String thesisTitle;
    private String startDate;
    private String endDate;
    private String outcome;
    private String url;
    private String abstract_;
    private List<ThesisDissertation> TDListList;
    private MyProfileDAO bdao = new MyProfileDAO();
    private String fName, mName, lName;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public ThesisDissertationInfoAction() {
    }

    public String ShowInfo() throws Exception {

        TDListList = dao.ThesisDissertationListByUserId(user_id);
        if (TDListList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditInfo() throws Exception {
        TDListList = dao.ThesisDissertationEdit(thesisDissertationId);
        return SUCCESS;
    }

    public String UpdateInfo() throws Exception {
        getDao().ThesisDissertationUpdate(thesisDissertationId, userId, reportType, programme, other, department, nameUniversity, cityState, country, thesisType, thesisTitle, startDate, endDate, outcome, url, abstract_);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteInfo() throws Exception {
        getDao().ThesisDissertationDelete(getThesisDissertationId());
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
     * @return the TD
     */
    public ThesisDissertation getTD() {
        return TD;
    }

    /**
     * @param TD the TD to set
     */
    public void setTD(ThesisDissertation TD) {
        this.TD = TD;
    }

    /**
     * @return the dao
     */
    public ThesisDissertationDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ThesisDissertationDao dao) {
        this.dao = dao;
    }

    /**
     * @return the thesisDissertationId
     */
    public long getThesisDissertationId() {
        return thesisDissertationId;
    }

    /**
     * @param thesisDissertationId the thesisDissertationId to set
     */
    public void setThesisDissertationId(long thesisDissertationId) {
        this.thesisDissertationId = thesisDissertationId;
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
     * @return the reportType
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * @param reportType the reportType to set
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * @return the programme
     */
    public String getProgramme() {
        return programme;
    }

    /**
     * @param programme the programme to set
     */
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    /**
     * @return the other
     */
    public String getOther() {
        return other;
    }

    /**
     * @param other the other to set
     */
    public void setOther(String other) {
        this.other = other;
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
     * @return the cityState
     */
    public String getCityState() {
        return cityState;
    }

    /**
     * @param cityState the cityState to set
     */
    public void setCityState(String cityState) {
        this.cityState = cityState;
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
     * @return the thesisType
     */
    public String getThesisType() {
        return thesisType;
    }

    /**
     * @param thesisType the thesisType to set
     */
    public void setThesisType(String thesisType) {
        this.thesisType = thesisType;
    }

    /**
     * @return the thesisTitle
     */
    public String getThesisTitle() {
        return thesisTitle;
    }

    /**
     * @param thesisTitle the thesisTitle to set
     */
    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the outcome
     */
    public String getOutcome() {
        return outcome;
    }

    /**
     * @param outcome the outcome to set
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
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
     * @return the TDListList
     */
    public List<ThesisDissertation> getTDListList() {
        return TDListList;
    }

    /**
     * @param TDListList the TDListList to set
     */
    public void setTDListList(List<ThesisDissertation> TDListList) {
        this.TDListList = TDListList;
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
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
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
