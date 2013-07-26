/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.DAO.MediaPublicationDao;
import org.IGNOU.ePortfolio.Model.MediaPublication;

/**
 *
 * @author IGNOU Team
 */
public class MediaInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private MediaPublication mp = new MediaPublication();
    private MediaPublicationDao dao = new MediaPublicationDao();
    private long mpId;
    private String userId;
    private String typeOfMedia;
    private String titleOfMedia;
    private String titleOfArticle;
    private String pubDate;
    private String url;
    private String summary;
    private Integer api;
    private List<MediaPublication> MPubList = null;
    private MyProfileDAO bdao = new MyProfileDAO();
    private String fname, mname, lname;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public MediaInfoAction() {
    }

    public String ShowMedia() throws Exception {

        MPubList = getDao().MediaPublicationListByUserId(getUser_id());
        if (MPubList.isEmpty() /*&& basicListList.isEmpty()*/) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditMedia() throws Exception {
        MPubList = dao.MediaPublicationListByMPId(mpId);
        return SUCCESS;
    }

    public String UpdateMedia() throws Exception {
        dao.MediaPublicationUpdate(mpId, userId, typeOfMedia, titleOfMedia, titleOfArticle, pubDate, url, summary, api);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteMedia() throws Exception {
        dao.MediaPublicationDelete(mpId);
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
     * @return the mp
     */
    public MediaPublication getMp() {
        return mp;
    }

    /**
     * @param mp the mp to set
     */
    public void setMp(MediaPublication mp) {
        this.mp = mp;
    }

    /**
     * @return the dao
     */
    public MediaPublicationDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(MediaPublicationDao dao) {
        this.dao = dao;
    }

    /**
     * @return the mpId
     */
    public long getMpId() {
        return mpId;
    }

    /**
     * @param mpId the mpId to set
     */
    public void setMpId(long mpId) {
        this.mpId = mpId;
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
     * @return the typeOfMedia
     */
    public String getTypeOfMedia() {
        return typeOfMedia;
    }

    /**
     * @param typeOfMedia the typeOfMedia to set
     */
    public void setTypeOfMedia(String typeOfMedia) {
        this.typeOfMedia = typeOfMedia;
    }

    /**
     * @return the titleOfMedia
     */
    public String getTitleOfMedia() {
        return titleOfMedia;
    }

    /**
     * @param titleOfMedia the titleOfMedia to set
     */
    public void setTitleOfMedia(String titleOfMedia) {
        this.titleOfMedia = titleOfMedia;
    }

    /**
     * @return the titleOfArticle
     */
    public String getTitleOfArticle() {
        return titleOfArticle;
    }

    /**
     * @param titleOfArticle the titleOfArticle to set
     */
    public void setTitleOfArticle(String titleOfArticle) {
        this.titleOfArticle = titleOfArticle;
    }

    /**
     * @return the pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * @param pubDate the pubDate to set
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the api
     */
    public Integer getApi() {
        return api;
    }

    /**
     * @param api the api to set
     */
    public void setApi(Integer api) {
        this.api = api;
    }

    /**
     * @return the MPubList
     */
    public List<MediaPublication> getMPubList() {
        return MPubList;
    }

    /**
     * @param MPubList the MPubList to set
     */
    public void setMPubList(List<MediaPublication> MPubList) {
        this.setMPubList(MPubList);
    }

    /**
     * @return the bdao
     */
    public MyProfileDAO getBdao() {
        return bdao;
    }

    /**
     * @param bdao the bdao to set
     */
    public void setBdao(MyProfileDAO bdao) {
        this.bdao = bdao;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * @param mname the mname to set
     */
    public void setMname(String mname) {
        this.mname = mname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
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
