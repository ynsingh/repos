/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import org.IGNOU.ePortfolio.Action.FileUploadCommon;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.CommentDao;
import org.IGNOU.ePortfolio.Model.ActivitiesComments;
import org.IGNOU.ePortfolio.Model.UserList;

/**
 *
 * @author IGNOU Team
 */
public class CommentAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private String evFilePath = getText("evidenceFilePath");
    private ActivitiesComments actCom = new ActivitiesComments();
    private CommentDao cDao = new CommentDao();
    private File commentData;
    private int submissionId;
    private Integer evidenceId;
    private String commentorFilePath, commentDataFileName, userId, comment, msg, mesComment = getText("msg.comment");
    private Double rating;
    
    
    public String CommentAdd() throws IOException {
        if (commentData != null) {
            commentorFilePath=(evFilePath + "/" + user_id + "/");
            new FileUploadCommon().UploadFile(commentData, commentDataFileName, commentorFilePath);
            UserList ul = new UserList();
            ul.setEmailId(user_id);
            setCommentorFilePath(commentDataFileName);
            cDao.saveComment(evidenceId, userId, ul, submissionId, getComment(), commentorFilePath, rating);
            msg = mesComment;
            return SUCCESS;
        } else {
            setCommentorFilePath("null");
            UserList ul = new UserList();
            ul.setEmailId(user_id);
            cDao.saveComment(evidenceId, userId, ul, submissionId, getComment(), getCommentorFilePath(), rating);
            msg = mesComment;
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
     * @return the commentDataFileName
     */
    public String getCommentDataFileName() {
        return commentDataFileName;
    }

    /**
     * @param commentDataFileName the commentDataFileName to set
     */
    public void setCommentDataFileName(String commentDataFileName) {
        this.commentDataFileName = commentDataFileName;
    }

    /**
     * @return the commentData
     */
    public File getCommentData() {
        return commentData;
    }

    /**
     * @param commentData the commentData to set
     */
    public void setCommentData(File commentData) {
        this.commentData = commentData;
    }

    /**
     * @return the submissionId
     */
    public int getSubmissionId() {
        return submissionId;
    }

    /**
     * @param submissionId the submissionId to set
     */
    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
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
     * @return the mesComment
     */
    public String getMesComment() {
        return mesComment;
    }

    /**
     * @param mesComment the mesComment to set
     */
    public void setMesComment(String mesComment) {
        this.mesComment = mesComment;
    }

    /**
     * @return the rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
