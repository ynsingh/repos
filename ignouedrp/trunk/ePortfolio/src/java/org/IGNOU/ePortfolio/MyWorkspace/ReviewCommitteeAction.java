/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ReviewCommitteeDao;
import org.IGNOU.ePortfolio.Model.ReviewCommittee;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 17-02-2012
 */
public class ReviewCommitteeAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ReviewCommittee rc = new ReviewCommittee();
    private ReviewCommitteeDao dao = new ReviewCommitteeDao();
    private long reviewCommitteeId;
    private String userId;
    private String committeeType;
    private String role;
    private String committeeName;
    private String date;
    private String frequency;
    private String url;
    private String review;
    private byte[] minutesFile;
    private List<ReviewCommittee> RCList;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public ReviewCommitteeAction() {
    }

    public String ShowRCInfo() throws Exception {
        setRCList(getDao().ShowRC(getUser_id()));
        if (getRCList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditRCInfo() throws Exception {
        setRCList(getDao().EditRC(getReviewCommitteeId()));
        return SUCCESS;
    }

    public String UpdateRCInfo() throws Exception {
        dao.UpdateRC(reviewCommitteeId, userId, committeeType, role, committeeName, date, frequency, url, review, minutesFile);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteRCInfo() throws Exception {
        dao.DeleteRC(reviewCommitteeId);
        msg = infoDeleted;
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
     * @return the rc
     */
    public ReviewCommittee getRc() {
        return rc;
    }

    /**
     * @param rc the rc to set
     */
    public void setRc(ReviewCommittee rc) {
        this.rc = rc;
    }

    /**
     * @return the dao
     */
    public ReviewCommitteeDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ReviewCommitteeDao dao) {
        this.dao = dao;
    }

    /**
     * @return the reviewCommitteeId
     */
    public long getReviewCommitteeId() {
        return reviewCommitteeId;
    }

    /**
     * @param reviewCommitteeId the reviewCommitteeId to set
     */
    public void setReviewCommitteeId(long reviewCommitteeId) {
        this.reviewCommitteeId = reviewCommitteeId;
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
     * @return the committeeType
     */
    public String getCommitteeType() {
        return committeeType;
    }

    /**
     * @param committeeType the committeeType to set
     */
    public void setCommitteeType(String committeeType) {
        this.committeeType = committeeType;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the committeeName
     */
    public String getCommitteeName() {
        return committeeName;
    }

    /**
     * @param committeeName the committeeName to set
     */
    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * @return the minutesFile
     */
    public byte[] getMinutesFile() {
        return minutesFile;
    }

    /**
     * @param minutesFile the minutesFile to set
     */
    public void setMinutesFile(byte[] minutesFile) {
        this.minutesFile = minutesFile;
    }

    /**
     * @return the RCList
     */
    public List<ReviewCommittee> getRCList() {
        return RCList;
    }

    /**
     * @param RCList the RCList to set
     */
    public void setRCList(List<ReviewCommittee> RCList) {
        this.RCList = RCList;
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
