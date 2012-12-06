/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EvidenceDao;
import org.IGNOU.ePortfolio.Model.Evidence;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;

/**
 *
 * @author IGNOU Team
 */
public class TaskSubmittedListAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private EvidenceSubmission eviSubModel = new EvidenceSubmission();
    private EvidenceDao eviDao = new EvidenceDao();
    private Integer evidenceId;
    private List<EvidenceSubmission> EviStdList;
    private List<Evidence> eviList;

    public TaskSubmittedListAction() {
    }

    public String TaskSubStudentList() throws Exception {
        eviList = getEviDao().EvidenceInfoList(getEvidenceId());
        EviStdList = getEviDao().EviStdSubList(getEvidenceId(), getUser_id());
        return SUCCESS;
    }

    /**
     * @return the eviSubModel
     */
    public EvidenceSubmission getEviSubModel() {
        return eviSubModel;
    }

    /**
     * @param eviSubModel the eviSubModel to set
     */
    public void setEviSubModel(EvidenceSubmission eviSubModel) {
        this.eviSubModel = eviSubModel;
    }

    /**
     * @return the eviDao
     */
    public EvidenceDao getEviDao() {
        return eviDao;
    }

    /**
     * @param eviDao the eviDao to set
     */
    public void setEviDao(EvidenceDao eviDao) {
        this.eviDao = eviDao;
    }

    /**
     * @return the EviStdList
     */
    public List<EvidenceSubmission> getEviStdList() {
        return EviStdList;
    }

    /**
     * @param EviStdList the EviStdList to set
     */
    public void setEviStdList(List<EvidenceSubmission> EviStdList) {
        this.EviStdList = EviStdList;
    }

    /**
     * @return the eviList
     */
    public List<Evidence> getEviList() {
        return eviList;
    }

    /**
     * @param eviList the eviList to set
     */
    public void setEviList(List<Evidence> eviList) {
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
