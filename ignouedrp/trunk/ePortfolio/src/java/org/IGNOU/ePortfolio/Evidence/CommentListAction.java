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
import org.IGNOU.ePortfolio.Model.Evidence;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;

/**
 *
 * @author IGNOU Team
 * @version 1 Last Modified on 07-06-2012 by IGNOU Team
 */
public class CommentListAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private EvidenceDao eviDao = new EvidenceDao();
    private List<EvidenceSubmission> evcommlist;
    private List<Evidence> ComevList;
    private List<ActivitiesComments> ComList;
    private Integer evidenceId;
    private String userId;
    private String allper;
    private double avgStarRating;
    private double temval;
    private String msg;

    public String EvidenceCommentList() {
        evcommlist = getEviDao().MySubInfo(evidenceId, userId);
        String allow = evcommlist.iterator().next().getListStudent();
        Boolean per = evcommlist.iterator().next().getCanComment();
        if (allow.contains(user_id) && per == true) {
            allper = "notnull";
        } else {
            allper = "null";
        }
        ComList = eviDao.PeerCommentInfo(userId, evidenceId);
        /*Calculate Avg.*/
        for (int i = 0; i < ComList.size(); i++) {
            temval = temval + ComList.get(i).getRating();
        }
        avgStarRating = temval / ComList.size();
        return SUCCESS;
    }

    public String ReplyCommentList() {
        evcommlist = getEviDao().MySubInfo(evidenceId, user_id);
        ComList = eviDao.PeerCommentInfo(user_id, evidenceId);
        /*Calculate Avg.*/
        for (int i = 0; i < ComList.size(); i++) {
            temval = temval + ComList.get(i).getRating();
        }
        avgStarRating = temval / ComList.size();

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
     * @return the evcommlist
     */
    public List<EvidenceSubmission> getEvcommlist() {
        return evcommlist;
    }

    /**
     * @param evcommlist the evcommlist to set
     */
    public void setEvcommlist(List<EvidenceSubmission> evcommlist) {
        this.evcommlist = evcommlist;
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
     * @return the ComevList
     */
    public List<Evidence> getComevList() {
        return ComevList;
    }

    /**
     * @param ComevList the ComevList to set
     */
    public void setComevList(List<Evidence> ComevList) {
        this.ComevList = ComevList;
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
     * @return the ComList
     */
    public List<ActivitiesComments> getComList() {
        return ComList;
    }

    /**
     * @param ComList the ComList to set
     */
    public void setComList(List<ActivitiesComments> ComList) {
        this.ComList = ComList;
    }

    /**
     * @return the allper
     */
    public String getAllper() {
        return allper;
    }

    /**
     * @param allper the allper to set
     */
    public void setAllper(String allper) {
        this.allper = allper;
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
}
