/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ResumeDao;
import org.IGNOU.ePortfolio.Model.Resume;

/**
 *
 * @author IGNOU Team
 */
public class UploadResumeAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private Resume res = new Resume();
    private ResumeDao rsdao = new ResumeDao();
    private String user_id = new UserSession().getUserInSession();
    private byte[] resume;
    private String resumeName;
    private Long resumeSize;
    private String resumeType;
    private Date uploadDate;
    private File userResume;
    private String userResumeFileName;

    public String UploadResume() {
        try {
            rsdao.ResumeSave(user_id, getResume(), getResumeName(), getResumeSize(), getResumeType(), new Date());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadResumeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * @return the resume
     */
    public byte[] getResume() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(getUserResume());
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        byte[] bf = new byte[1024];
        try {

            for (int readNum; (readNum = fis.read(bf)) != -1;) {
                bs.write(bf, 0, readNum);

            }
        } catch (IOException ex) {
            Logger.getLogger(UploadResumeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        resume = bs.toByteArray();
        return resume;
    }

    /**
     * @param resume the resume to set
     */
    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    /**
     * @return the resumeName
     */
    public String getResumeName() {
        resumeName = getUserResumeFileName();
        return resumeName;
    }

    /**
     * @param resumeName the resumeName to set
     */
    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    /**
     * @return the resumeSize
     */
    public Long getResumeSize() {
        resumeSize = userResume.length() / 1024;
        return resumeSize;
    }

    /**
     * @param resumeSize the resumeSize to set
     */
    public void setResumeSize(Long resumeSize) {
        this.resumeSize = resumeSize;
    }

    /**
     * @return the resumeType
     */
    public String getResumeType() {
        resumeType = getUserResumeFileName().substring(getUserResumeFileName().indexOf(".", 4));
        return resumeType;
    }

    /**
     * @param resumeType the resumeType to set
     */
    public void setResumeType(String resumeType) {
        this.resumeType = resumeType;
    }

    /**
     * @return the uploadDate
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * @return the userResume
     */
    public File getUserResume() {
        return userResume;
    }

    /**
     * @param userResume the userResume to set
     */
    public void setUserResume(File userResume) {
        this.userResume = userResume;
    }

    /**
     * @return the userResumeFileName
     */
    public String getUserResumeFileName() {
        return userResumeFileName;
    }

    /**
     * @param userResumeFileName the userResumeFileName to set
     */
    public void setUserResumeFileName(String userResumeFileName) {
        this.userResumeFileName = userResumeFileName;
    }
}
