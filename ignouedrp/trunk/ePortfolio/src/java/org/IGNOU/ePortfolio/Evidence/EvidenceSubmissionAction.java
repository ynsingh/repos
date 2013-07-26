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
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author IGNOU Team
 */
public class EvidenceSubmissionAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private EvidenceDao evdao = new EvidenceDao();
    private File stuData;
    private String stuDataFileName;
    private String evFilePath = ReadPropertyFile("Filepath");
    private String attachment;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");
    private int courseId;
    private int evidenceId;
    private Integer instituteId;
    private Integer programmeId;
    private String instructions;
    private Date subDate;
    private Boolean submitted;
    private Boolean post;
    private Boolean saveDraft;

    public String EvidSubmissionSave() throws IOException {

        if (stuData != null) {
            attachment = evFilePath + "/" + user_id + "/";
            new FileUploadCommon().UploadFile(stuData, stuDataFileName, attachment);
            setAttachment(getStuDataFileName());
            evdao.EvidenceSubmissionSave(evidenceId, user_id, getInstructions(), attachment, getSubDate(), getSubmitted(), getPost(), getSaveDraft());
            msg = infoSaved;
            return SUCCESS;
        } else {
            setAttachment("null");

            evdao.EvidenceSubmissionSave(evidenceId, user_id, getInstructions(), attachment, getSubDate(), getSubmitted(), getPost(), getSaveDraft());
            msg = infoSaved;
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
     * @return the stuData
     */
    public File getStuData() {
        return stuData;
    }

    /**
     * @param stuData the stuData to set
     */
    public void setStuData(File stuData) {
        this.stuData = stuData;
    }

    /**
     * @return the stuDataFileName
     */
    public String getStuDataFileName() {
        return stuDataFileName;
    }

    /**
     * @param stuDataFileName the stuDataFileName to set
     */
    public void setStuDataFileName(String stuDataFileName) {
        this.stuDataFileName = stuDataFileName;
    }

    /**
     * @return the attachment
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * @param attachment the attachment to set
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }

    /**
     * @return the courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
     * @return the instituteId
     */
    public Integer getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    /**
     * @return the programmeId
     */
    public Integer getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * @param instructions the instructions to set
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * @return the subDate
     */
    public Date getSubDate() {
        return subDate;
    }

    /**
     * @param subDate the subDate to set
     */
    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    /**
     * @return the submitted
     */
    public Boolean getSubmitted() {
        return submitted;
    }

    /**
     * @param submitted the submitted to set
     */
    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    /**
     * @return the post
     */
    public Boolean getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Boolean post) {
        this.post = post;
    }

    /**
     * @return the saveDraft
     */
    public Boolean getSaveDraft() {
        return saveDraft;
    }

    /**
     * @param saveDraft the saveDraft to set
     */
    public void setSaveDraft(Boolean saveDraft) {
        this.saveDraft = saveDraft;
    }
}
