/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.GovernanceDao;
import org.IGNOU.ePortfolio.Model.Governance;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 25 December 2011
 */
public class GovernanceInfoAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private GovernanceDao dao = new GovernanceDao();
    private Governance GovernanceModel = new Governance();
    private long governanceId;
    private String userId, nameCommittee, nameUniversity, durationFrom, durationTo, responsibility, url, summary;
    private List<Governance> GovernanceListList = null;
    private String msg;
    private String address;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public GovernanceInfoAction() {
    }

    public String ShowGovernanceInfo() throws Exception {
        setGovernanceListList(getDao().GovernanceListByUserId(user_id));
        if (GovernanceListList == null || getGovernanceListList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String DeleteGovernanceInfo() throws Exception {
        dao.GovernanceDelete(governanceId);
        msg = infoDeleted;
        return SUCCESS;
    }

    public String EditGovernanceInfo() throws Exception {
        setGovernanceListList(getDao().GovernanceListByGovernanceId(governanceId));
        return SUCCESS;
    }

    public String UpdateGovernanceInfo() throws Exception {
        getDao().GovernanceUpdate(governanceId, userId, nameCommittee, nameUniversity, durationFrom, durationTo, responsibility, url, summary, address);
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
    public GovernanceDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(GovernanceDao dao) {
        this.dao = dao;
    }

    /**
     * @return the GovernanceModel
     */
    public Governance getGovernanceModel() {
        return GovernanceModel;
    }

    /**
     * @param GovernanceModel the GovernanceModel to set
     */
    public void setGovernanceModel(Governance GovernanceModel) {
        this.GovernanceModel = GovernanceModel;
    }

    /**
     * @return the governanceId
     */
    public long getGovernanceId() {
        return governanceId;
    }

    /**
     * @param governanceId the governanceId to set
     */
    public void setGovernanceId(long governanceId) {
        this.governanceId = governanceId;
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
     * @return the nameCommittee
     */
    public String getNameCommittee() {
        return nameCommittee;
    }

    /**
     * @param nameCommittee the nameCommittee to set
     */
    public void setNameCommittee(String nameCommittee) {
        this.nameCommittee = nameCommittee;
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
     * @return the responsibility
     */
    public String getResponsibility() {
        return responsibility;
    }

    /**
     * @param responsibility the responsibility to set
     */
    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
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
     * @return the GovernanceListList
     */
    public List<Governance> getGovernanceListList() {
        return GovernanceListList;
    }

    /**
     * @param GovernanceListList the GovernanceListList to set
     */
    public void setGovernanceListList(List<Governance> GovernanceListList) {
        this.GovernanceListList = GovernanceListList;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
