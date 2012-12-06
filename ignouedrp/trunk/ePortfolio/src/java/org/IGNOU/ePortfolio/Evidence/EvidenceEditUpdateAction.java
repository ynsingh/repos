/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EvidenceDao;
import org.IGNOU.ePortfolio.Model.Evidence;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 28-05-2012
 */
public class EvidenceEditUpdateAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private Evidence eviModel = new Evidence();
    private EvidenceDao dao = new EvidenceDao();
    private List<Evidence> EvidenceList;
    private int evidenceId;
    private String evTitle;
    private Date openDate, closeDate, lastAcceptDate;
    private String instruction, cancelReason;
    private Boolean addDateSchedule, addAnnoOdate, addtoGradebook, saveEvid, cancel;
    private String assAttach;
    private String facDataFileName;
    private File facData;
    private String evFilePath = getText("evidenceFilePath");
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public EvidenceEditUpdateAction() {
    }

    public String EditEvidenceInfo() throws Exception {
        EvidenceList = dao.EvidenceReviewInfo(evidenceId);
        return SUCCESS;
    }

    public String UpdateEvidence() throws IOException {
        facData = getFacData();
        if (facData != null) {
            assAttach = getEvFilePath() + "/" + getUser_id() + "/";
            File folder = new File(assAttach);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File fileToCreate = new File(assAttach, getFacDataFileName());
            FileUtils.copyFile(facData, fileToCreate);

            assAttach = getFacDataFileName();
            dao.UpdateEvi(evidenceId, evTitle, openDate, closeDate, lastAcceptDate, instruction, addDateSchedule, addAnnoOdate, addtoGradebook, assAttach, saveEvid, cancel, cancelReason);
            msg = infoUpdated;
            return SUCCESS;
        } else {
            assAttach = "null";
            dao.UpdateEvi(evidenceId, evTitle, openDate, closeDate, lastAcceptDate, instruction, addDateSchedule, addAnnoOdate, addtoGradebook, assAttach, saveEvid, cancel, cancelReason);
            msg = infoUpdated;
            return SUCCESS;
        }
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
     * @return the eviModel
     */
    public Evidence getEviModel() {
        return eviModel;
    }

    /**
     * @param eviModel the eviModel to set
     */
    public void setEviModel(Evidence eviModel) {
        this.eviModel = eviModel;
    }

    /**
     * @return the dao
     */
    public EvidenceDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(EvidenceDao dao) {
        this.dao = dao;
    }

    /**
     * @return the EvidenceList
     */
    public List<Evidence> getEvidenceList() {
        return EvidenceList;
    }

    /**
     * @param EvidenceList the EvidenceList to set
     */
    public void setEvidenceList(List<Evidence> EvidenceList) {
        this.EvidenceList = EvidenceList;
    }

    /**
     * @return the evidenceId
     */
    public int getEvidenceId() {
        return evidenceId;
    }

    /**
     * @param evidenceId the evidenceId to set
     */
    public void setEvidenceId(int evidenceId) {
        this.evidenceId = evidenceId;
    }

    /**
     * @return the evTitle
     */
    public String getEvTitle() {
        return evTitle;
    }

    /**
     * @param evTitle the evTitle to set
     */
    public void setEvTitle(String evTitle) {
        this.evTitle = evTitle;
    }

    /**
     * @return the openDate
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * @param openDate the openDate to set
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * @return the closeDate
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate the closeDate to set
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the lastAcceptDate
     */
    public Date getLastAcceptDate() {
        return lastAcceptDate;
    }

    /**
     * @param lastAcceptDate the lastAcceptDate to set
     */
    public void setLastAcceptDate(Date lastAcceptDate) {
        this.lastAcceptDate = lastAcceptDate;
    }

    /**
     * @return the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * @param instruction the instruction to set
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * @return the addDateSchedule
     */
    public Boolean getAddDateSchedule() {
        return addDateSchedule;
    }

    /**
     * @param addDateSchedule the addDateSchedule to set
     */
    public void setAddDateSchedule(Boolean addDateSchedule) {
        this.addDateSchedule = addDateSchedule;
    }

    /**
     * @return the addAnnoOdate
     */
    public Boolean getAddAnnoOdate() {
        return addAnnoOdate;
    }

    /**
     * @param addAnnoOdate the addAnnoOdate to set
     */
    public void setAddAnnoOdate(Boolean addAnnoOdate) {
        this.addAnnoOdate = addAnnoOdate;
    }

    /**
     * @return the addtoGradebook
     */
    public Boolean getAddtoGradebook() {
        return addtoGradebook;
    }

    /**
     * @param addtoGradebook the addtoGradebook to set
     */
    public void setAddtoGradebook(Boolean addtoGradebook) {
        this.addtoGradebook = addtoGradebook;
    }

    /**
     * @return the assAttach
     */
    public String getAssAttach() {
        return assAttach;
    }

    /**
     * @param assAttach the assAttach to set
     */
    public void setAssAttach(String assAttach) {
        this.assAttach = assAttach;
    }

    /**
     * @return the saveEvid
     */
    public Boolean getSaveEvid() {
        return saveEvid;
    }

    /**
     * @param saveEvid the saveEvid to set
     */
    public void setSaveEvid(Boolean saveEvid) {
        this.saveEvid = saveEvid;
    }

    /**
     * @return the evFilePath
     */
    public String getEvFilePath() {
        return evFilePath;
    }

    /**
     * @param evFilePath the evFilePath to set
     */
    public void setEvFilePath(String evFilePath) {
        this.evFilePath = evFilePath;
    }

    /**
     * @return the facDataFileName
     */
    public String getFacDataFileName() {
        return facDataFileName;
    }

    /**
     * @param facDataFileName the facDataFileName to set
     */
    public void setFacDataFileName(String facDataFileName) {
        this.facDataFileName = facDataFileName;
    }

    /**
     * @return the facData
     */
    public File getFacData() {
        return facData;
    }

    /**
     * @param facData the facData to set
     */
    public void setFacData(File facData) {
        this.facData = facData;
    }

    /**
     * @return the cancelReason
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * @param cancelReason the cancelReason to set
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    /**
     * @return the cancel
     */
    public Boolean getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
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
