/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.IGNOU.ePortfolio.Action.FileUploadCommon;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EvidenceDao;
import org.IGNOU.ePortfolio.Model.Evidence;

/**
 *
 * @author IGNOU Team
 */
public class EvidenceAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private Evidence evd = new Evidence();
    private EvidenceDao evdao = new EvidenceDao();
    private String evTitle, instruction;
    private Date openDate, closeDate, lastAcceptDate;
    private Boolean addDateSchedule, addAnnoOdate, addtoGradebook, saveEvid, cancel;
    private String assAttach, cancelReason, myAttachFileName;
    private File myAttach;
    private int gradeScale, coursesId, gradeId;
    private String evFilePath = getText("evidenceFilePath");

    public String EvidenceSave() throws IOException {
           if (myAttach != null) {
            assAttach = evFilePath + "/" + user_id + "/";
            new FileUploadCommon().UploadFile(myAttach, myAttachFileName, assAttach);
            evdao.saveEvidence(evTitle, instruction, myAttachFileName, user_id, openDate, closeDate, lastAcceptDate, addDateSchedule, addAnnoOdate, addtoGradebook, saveEvid, coursesId, gradeId);
            return SUCCESS;
        } else {
           setAssAttach("null");
            evdao.saveEvidence(evTitle, instruction, assAttach, user_id, openDate, closeDate, lastAcceptDate, addDateSchedule, addAnnoOdate, addtoGradebook, saveEvid, coursesId, gradeId);
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
     * @return the evd
     */
    public Evidence getEvd() {
        return evd;
    }

    /**
     * @param evd the evd to set
     */
    public void setEvd(Evidence evd) {
        this.evd = evd;
    }

    /**
     * @return the evdao
     */
    public EvidenceDao getEvdao() {
        return evdao;
    }

    /**
     * @param evdao the evdao to set
     */
    public void setEvdao(EvidenceDao evdao) {
        this.evdao = evdao;
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
     * @return the myAttachFileName
     */
    public String getMyAttachFileName() {
        return myAttachFileName;
    }

    /**
     * @param myAttachFileName the myAttachFileName to set
     */
    public void setMyAttachFileName(String myAttachFileName) {
        this.myAttachFileName = myAttachFileName;
    }

    /**
     * @return the myAttach
     */
    public File getMyAttach() {
        return myAttach;
    }

    /**
     * @param myAttach the myAttach to set
     */
    public void setMyAttach(File myAttach) {
        this.myAttach = myAttach;
    }

    /**
     * @return the gradeScale
     */
    public int getGradeScale() {
        return gradeScale;
    }

    /**
     * @param gradeScale the gradeScale to set
     */
    public void setGradeScale(int gradeScale) {
        this.gradeScale = gradeScale;
    }

    /**
     * @return the coursesId
     */
    public int getCoursesId() {
        return coursesId;
    }

    /**
     * @param coursesId the coursesId to set
     */
    public void setCoursesId(int coursesId) {
        this.coursesId = coursesId;
    }

    /**
     * @return the gradeId
     */
    public int getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId the gradeId to set
     */
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
}
