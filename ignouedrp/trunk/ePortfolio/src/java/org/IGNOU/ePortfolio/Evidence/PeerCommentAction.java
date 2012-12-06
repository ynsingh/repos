/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EvidenceDao;
import org.IGNOU.ePortfolio.Model.ActivitiesComments;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 28-05-2012 Last Modified on 03-06-2012 by IGNOU Team
 */
public class PeerCommentAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private EvidenceDao eviDao = new EvidenceDao();
    private Integer evidenceId;
    private int submissionId;
    private List<ActivitiesComments> CommentList;
    private String msg, recordNotFound = getText("recordNotFound");
    private double avgStarRating;
    private double temval;
    private String userId;
    private List<EvidenceSubmission> EvidenceInfo;

    public PeerCommentAction() {
    }

    public String ShowComments() throws Exception {
        CommentList = eviDao.CommentList(user_id, evidenceId);
        EvidenceInfo = eviDao.MySubInfo(evidenceId, user_id);
        if (CommentList.isEmpty()) {
            msg = recordNotFound;
        } else {
            for (int i = 0; i < CommentList.size(); i++) {
                temval = temval + CommentList.get(i).getRating();
            }
            avgStarRating = temval / CommentList.size();
            /*End Calculation*/
        }
        return SUCCESS;
    }
    public String ShowViewComments() throws Exception {
        CommentList = eviDao.CommentList(userId, evidenceId);
        EvidenceInfo = eviDao.MySubInfo(evidenceId, userId);
        if (CommentList.isEmpty()) {
            msg = recordNotFound;
        } else {
            for (int i = 0; i < CommentList.size(); i++) {
                temval = temval + CommentList.get(i).getRating();
            }
            avgStarRating = temval / CommentList.size();
            /*End Calculation*/
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
     * @return the CommentList
     */
    public List<ActivitiesComments> getCommentList() {
        return CommentList;
    }

    /**
     * @param CommentList the CommentList to set
     */
    public void setCommentList(List<ActivitiesComments> CommentList) {
        this.CommentList = CommentList;
    }

    /**
     * @return the avgStarRating
     */
    public double getAvgStarRating() {
        return avgStarRating;
    }

    /**
     * @param avgStarRating the avgStarRating to set
     */
    public void setAvgStarRating(double avgStarRating) {
        this.avgStarRating = avgStarRating;
    }

    /**
     * @return the temval
     */
    public double getTemval() {
        return temval;
    }

    /**
     * @param temval the temval to set
     */
    public void setTemval(double temval) {
        this.temval = temval;
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
     * @return the recordNotFound
     */
    public String getRecordNotFound() {
        return recordNotFound;
    }

    /**
     * @param recordNotFound the recordNotFound to set
     */
    public void setRecordNotFound(String recordNotFound) {
        this.recordNotFound = recordNotFound;
    }

    /**
     * @return the EvidenceInfo
     */
    public List<EvidenceSubmission> getEvidenceInfo() {
        return EvidenceInfo;
    }

    /**
     * @param EvidenceInfo the EvidenceInfo to set
     */
    public void setEvidenceInfo(List<EvidenceSubmission> EvidenceInfo) {
        this.EvidenceInfo = EvidenceInfo;
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

   
}
