/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Activity;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.LikeUnlikeDao;
import org.IGNOU.ePortfolio.Model.LikeDislike;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author Amit
 */
public class CountLikeDislikeAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    private Integer evidenceSubId;
    private LikeUnlikeDao ldDao = new LikeUnlikeDao();
    private List<LikeDislike> likedislist;
    private String likelist, Ldval, userDetail1;
    private Integer commentId;
    private JSONObject jsonlike = new JSONObject();
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ArrayList<String> arrdislist = new ArrayList<String>();
    private ArrayList<String> arrlikelist = new ArrayList<String>();

    public String CountLikeDislike() throws ParseException {
        likedislist = ldDao.LikeDislikeListByEvidenceIdCommentId(evidenceSubId, commentId);
        if (likedislist.isEmpty()) {
            likelist = "null";
            return SUCCESS;
        } else {
            userDetail1 = likedislist.iterator().next().getUserDetail();
            if (userDetail1.contains(user_id)) {
                jsonlike = new JSONObject(userDetail1);

                Ldval = (String) jsonlike.get(user_id);
            } else {
                Ldval = "null";
            }


            for (int i = 0; i < jsonlike.length(); i++) {

                String UserLdVal = jsonlike.getString(jsonlike.names().get(i).toString());
                if (UserLdVal.equals("Like")) {
                    getArrlikelist().add(jsonlike.names().get(i).toString());

                } else {
                    getArrdislist().add(jsonlike.names().get(i).toString());
                }
            }
            likelist = "notnull";
            return SUCCESS;
        }

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
     * @return the likedislist
     */
    public List<LikeDislike> getLikedislist() {
        return likedislist;
    }

    /**
     * @param likedislist the likedislist to set
     */
    public void setLikedislist(List<LikeDislike> likedislist) {
        this.likedislist = likedislist;
    }

    /**
     * @return the likelist
     */
    public String getLikelist() {
        return likelist;
    }

    /**
     * @param likelist the likelist to set
     */
    public void setLikelist(String likelist) {
        this.likelist = likelist;
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
     * @return the arrdislist
     */
    public ArrayList<String> getArrdislist() {
        return arrdislist;
    }

    /**
     * @param arrdislist the arrdislist to set
     */
    public void setArrdislist(ArrayList<String> arrdislist) {
        this.arrdislist = arrdislist;
    }

    /**
     * @return the arrlikelist
     */
    public ArrayList<String> getArrlikelist() {
        return arrlikelist;
    }

    /**
     * @param arrlikelist the arrlikelist to set
     */
    public void setArrlikelist(ArrayList<String> arrlikelist) {
        this.arrlikelist = arrlikelist;
    }
}
