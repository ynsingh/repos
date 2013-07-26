/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author IGNOU Team
 */
public class DownloadAction extends ActionSupport {

    private String evFilePath = ReadPropertyFile("Filepath");
    private int evidenceId;
    private String facultyId;
    private String assAttach;
    private InputStream fis;
    private String userId;
    private String attachment;
    private String commentorId;
    private String commentorFilePath;
    private String filetype;
    private String outputdownload;

    public String downloadAssignment() {
        try {

            outputdownload = evFilePath + "/" + facultyId + "/" + assAttach;
            filetype = assAttach.substring(assAttach.lastIndexOf("."), assAttach.length());
            fis = new FileInputStream(new File(outputdownload));
        } catch (Exception e) {
            System.out.println("Exp" + e);
        }
        return SUCCESS;
    }

    public String downloadStuAtt() {
        try {

            outputdownload = evFilePath + "/" + userId + "/" + attachment;
            filetype = attachment.substring(attachment.lastIndexOf("."), attachment.length());
            fis = new FileInputStream(new File(outputdownload));
        } catch (Exception e) {
            System.out.println("Exp" + e);

        }
        return SUCCESS;
    }

    public String downloadCmtAttachment() {
        try {

            outputdownload = evFilePath + "/" + commentorId + "/" + commentorFilePath;
            filetype = commentorFilePath.substring(commentorFilePath.lastIndexOf("."), commentorFilePath.length());
            fis = new FileInputStream(new File(outputdownload));
        } catch (Exception e) {
            System.out.println("Exp" + e);

        }
        return SUCCESS;
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
     * @return the facultyId
     */
    public String getFacultyId() {
        return facultyId;
    }

    /**
     * @param facultyId the facultyId to set
     */
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
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
     * @return the fis
     */
    public InputStream getFis() {
        return fis;
    }

    /**
     * @param fis the fis to set
     */
    public void setFis(InputStream fis) {
        this.fis = fis;
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
     * @return the commentorId
     */
    public String getCommentorId() {
        return commentorId;
    }

    /**
     * @param commentorId the commentorId to set
     */
    public void setCommentorId(String commentorId) {
        this.commentorId = commentorId;
    }

    /**
     * @return the commentorFilePath
     */
    public String getCommentorFilePath() {
        return commentorFilePath;
    }

    /**
     * @param commentorFilePath the commentorFilePath to set
     */
    public void setCommentorFilePath(String commentorFilePath) {
        this.commentorFilePath = commentorFilePath;
    }

    /**
     * @return the filetype
     */
    public String getFiletype() {
        return filetype;
    }

    /**
     * @param filetype the filetype to set
     */
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    /**
     * @return the outputdownload
     */
    public String getOutputdownload() {
        return outputdownload;
    }

    /**
     * @param outputdownload the outputdownload to set
     */
    public void setOutputdownload(String outputdownload) {
        this.outputdownload = outputdownload;
    }
}
