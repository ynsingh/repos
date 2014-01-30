/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Activity;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ActivitiesDao;
import org.IGNOU.ePortfolio.Model.ActivitiesAnnounce;
import org.IGNOU.ePortfolio.Model.ActivitiesSubmission;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class TaskSubmittedListAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ActivitiesSubmission eviSubModel = new ActivitiesSubmission();
    private ActivitiesDao eviDao = new ActivitiesDao();
    private Integer evidenceId;
    private List<ActivitiesSubmission> EviStdList;
    private List<ActivitiesAnnounce> eviList;

    public TaskSubmittedListAction() {
    }

    public String TaskSubStudentList() throws Exception {
        eviList = getEviDao().EvidenceListByEvidenceId(getEvidenceId());
        EviStdList = getEviDao().EvidenceSubmissionPeerListByEvidenceIdUserId(getEvidenceId(), getUser_id());
        return SUCCESS;
    }

    /**
     * @return the eviSubModel
     */
    public ActivitiesSubmission getEviSubModel() {
        return eviSubModel;
    }

    /**
     * @param eviSubModel the eviSubModel to set
     */
    public void setEviSubModel(ActivitiesSubmission eviSubModel) {
        this.eviSubModel = eviSubModel;
    }

    /**
     * @return the eviDao
     */
    public ActivitiesDao getEviDao() {
        return eviDao;
    }

    /**
     * @param eviDao the eviDao to set
     */
    public void setEviDao(ActivitiesDao eviDao) {
        this.eviDao = eviDao;
    }

    /**
     * @return the EviStdList
     */
    public List<ActivitiesSubmission> getEviStdList() {
        return EviStdList;
    }

    /**
     * @param EviStdList the EviStdList to set
     */
    public void setEviStdList(List<ActivitiesSubmission> EviStdList) {
        this.EviStdList = EviStdList;
    }

    /**
     * @return the eviList
     */
    public List<ActivitiesAnnounce> getEviList() {
        return eviList;
    }

    /**
     * @param eviList the eviList to set
     */
    public void setEviList(List<ActivitiesAnnounce> eviList) {
        this.eviList = eviList;
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
     * @return the evidenceId
     */
    public Integer getEvidenceId() {
        return evidenceId;
    }

    /**
     * @param evidenceId the evidenceId to set
     */
    public void setEvidenceId(Integer evidenceId) {
        this.evidenceId = evidenceId;
    }
}
