/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.text.ParseException;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.LikeUnlikeDao;
import org.IGNOU.ePortfolio.Model.LikeDislike;
import org.json.JSONObject;

/**
 *
 * @author Amit
 */
public class UnlikeUndoAction extends ActionSupport{
    
     private String user_id = new UserSession().getUserInSession();
     private LikeDislike ldmodel=new LikeDislike();
     private LikeUnlikeDao ldDao=new LikeUnlikeDao();
     private Integer evidenceSubId;
     private List<LikeDislike> likedislikelist;
     private Integer commentId;
     private Integer likeCount,likeCount1;
     private Integer dislikeCount,dislikeCount1;
     private String userDetail,userDetail1,Ldval;
     private int likeDislikeId;
     private JSONObject jsonlike=new JSONObject();
     private Integer evidenceId;
     private Integer instituteId;
     private Integer programmeId;
     private Integer courseId;
     private String userId;
     
     public String Unlike() throws ParseException{
          likedislikelist=ldDao.LikeDislikeCheck(evidenceSubId, commentId);
          likeCount1=likedislikelist.iterator().next().getLikeCount();
          dislikeCount1=likedislikelist.iterator().next().getDislikeCount();
          userDetail1=likedislikelist.iterator().next().getUserDetail();
         
          jsonlike=new JSONObject(userDetail1);
          jsonlike.remove(user_id);
          userDetail=jsonlike.toString();
          likeCount=likeCount1-1;
          dislikeCount=dislikeCount1;
          ldDao.UpdateLikeDislike(likeDislikeId, evidenceSubId, commentId, likeCount, dislikeCount, userDetail);
       return SUCCESS;
     }    
      
     public String UndoDislike() throws ParseException{
          likedislikelist=ldDao.LikeDislikeCheck(evidenceSubId, commentId);
          likeCount1=likedislikelist.iterator().next().getLikeCount();
          dislikeCount1=likedislikelist.iterator().next().getDislikeCount();
          userDetail1=likedislikelist.iterator().next().getUserDetail();
          jsonlike=new JSONObject(userDetail1);
         
          jsonlike.remove(user_id);
          userDetail=jsonlike.toString();
          dislikeCount=dislikeCount1-1;
         likeCount=likeCount1;
          ldDao.UpdateLikeDislike(likeDislikeId, evidenceSubId, commentId, likeCount, dislikeCount, userDetail);
    
     
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
     * @return the ldmodel
     */
    public LikeDislike getLdmodel() {
        return ldmodel;
    }

    /**
     * @param ldmodel the ldmodel to set
     */
    public void setLdmodel(LikeDislike ldmodel) {
        this.ldmodel = ldmodel;
    }

    /**
     * @return the ldDao
     */
    public LikeUnlikeDao getLdDao() {
        return ldDao;
    }

    /**
     * @param ldDao the ldDao to set
     */
    public void setLdDao(LikeUnlikeDao ldDao) {
        this.ldDao = ldDao;
    }

    /**
     * @return the evidenceSubId
     */
    public Integer getEvidenceSubId() {
        return evidenceSubId;
    }

    /**
     * @param evidenceSubId the evidenceSubId to set
     */
    public void setEvidenceSubId(Integer evidenceSubId) {
        this.evidenceSubId = evidenceSubId;
    }

    /**
     * @return the likedislikelist
     */
    public List<LikeDislike> getLikedislikelist() {
        return likedislikelist;
    }

    /**
     * @param likedislikelist the likedislikelist to set
     */
    public void setLikedislikelist(List<LikeDislike> likedislikelist) {
        this.likedislikelist = likedislikelist;
    }

    /**
     * @return the commentId
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * @return the likeCount
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount the likeCount to set
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return the likeCount1
     */
    public Integer getLikeCount1() {
        return likeCount1;
    }

    /**
     * @param likeCount1 the likeCount1 to set
     */
    public void setLikeCount1(Integer likeCount1) {
        this.likeCount1 = likeCount1;
    }

    /**
     * @return the dislikeCount
     */
    public Integer getDislikeCount() {
        return dislikeCount;
    }

    /**
     * @param dislikeCount the dislikeCount to set
     */
    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * @return the dislikeCount1
     */
    public Integer getDislikeCount1() {
        return dislikeCount1;
    }

    /**
     * @param dislikeCount1 the dislikeCount1 to set
     */
    public void setDislikeCount1(Integer dislikeCount1) {
        this.dislikeCount1 = dislikeCount1;
    }

    /**
     * @return the userDetail
     */
    public String getUserDetail() {
        return userDetail;
    }

    /**
     * @param userDetail the userDetail to set
     */
    public void setUserDetail(String userDetail) {
        this.userDetail = userDetail;
    }

    /**
     * @return the userDetail1
     */
    public String getUserDetail1() {
        return userDetail1;
    }

    /**
     * @param userDetail1 the userDetail1 to set
     */
    public void setUserDetail1(String userDetail1) {
        this.userDetail1 = userDetail1;
    }

    /**
     * @return the Ldval
     */
    public String getLdval() {
        return Ldval;
    }

    /**
     * @param Ldval the Ldval to set
     */
    public void setLdval(String Ldval) {
        this.Ldval = Ldval;
    }

    /**
     * @return the likeDislikeId
     */
    public int getLikeDislikeId() {
        return likeDislikeId;
    }

    /**
     * @param likeDislikeId the likeDislikeId to set
     */
    public void setLikeDislikeId(int likeDislikeId) {
        this.likeDislikeId = likeDislikeId;
    }

    /**
     * @return the jsonlike
     */
    public JSONObject getJsonlike() {
        return jsonlike;
    }

    /**
     * @param jsonlike the jsonlike to set
     */
    public void setJsonlike(JSONObject jsonlike) {
        this.jsonlike = jsonlike;
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
     * @return the courseId
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
