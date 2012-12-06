/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.TalkLectureDao;
import org.IGNOU.ePortfolio.Model.TalkLecture;

/**
 * @author IGNOU Team
 * @version 1
 * @since 17-02-2012
 */
public class TalkLectureInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private TalkLectureDao dao = new TalkLectureDao();
    private TalkLecture tlModel = new TalkLecture();
    private long talkLectureId;
    private String userId;
    private String eventType;
    private String nameUniversity;
    private String address;
    private String nameEvent;
    private String lectureTopic;
    private String deleveredOn;
    private String timeFrom;
    private String timeTo;
    private String level;
    private String url;
    private String description;
    private List<TalkLecture> TLList;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public TalkLectureInfoAction() {
    }

    public String ShowTLInfo() throws Exception {
        setTLList(getDao().ShowTL(getUser_id()));
        if (getTLList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditTLInfo() throws Exception {
        setTLList(getDao().EditTL(getTalkLectureId()));
        return SUCCESS;
    }

    public String UpdateTLInfo() throws Exception {
        getDao().UpdateTL(getTalkLectureId(), getUserId(), getEventType(), getNameUniversity(), getAddress(), getNameEvent(), getLectureTopic(), getDeleveredOn(), getTimeFrom(), getTimeTo(), getLevel(), getUrl(), getDescription());
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteTLInfo() throws Exception {
        getDao().DeleteTL(getTalkLectureId());
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
     * @return the dao
     */
    public TalkLectureDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(TalkLectureDao dao) {
        this.dao = dao;
    }

    /**
     * @return the tlModel
     */
    public TalkLecture getTlModel() {
        return tlModel;
    }

    /**
     * @param tlModel the tlModel to set
     */
    public void setTlModel(TalkLecture tlModel) {
        this.tlModel = tlModel;
    }

    /**
     * @return the talkLectureId
     */
    public long getTalkLectureId() {
        return talkLectureId;
    }

    /**
     * @param talkLectureId the talkLectureId to set
     */
    public void setTalkLectureId(long talkLectureId) {
        this.talkLectureId = talkLectureId;
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
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the nameUniversity
     */
    public String getNameUniversity() {
        return nameUniversity;
    }

    /**
     * @param nameUniversity the nameUniversity to set
     */
    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the nameEvent
     */
    public String getNameEvent() {
        return nameEvent;
    }

    /**
     * @param nameEvent the nameEvent to set
     */
    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    /**
     * @return the lectureTopic
     */
    public String getLectureTopic() {
        return lectureTopic;
    }

    /**
     * @param lectureTopic the lectureTopic to set
     */
    public void setLectureTopic(String lectureTopic) {
        this.lectureTopic = lectureTopic;
    }

    /**
     * @return the deleveredOn
     */
    public String getDeleveredOn() {
        return deleveredOn;
    }

    /**
     * @param deleveredOn the deleveredOn to set
     */
    public void setDeleveredOn(String deleveredOn) {
        this.deleveredOn = deleveredOn;
    }

    /**
     * @return the timeFrom
     */
    public String getTimeFrom() {
        return timeFrom;
    }

    /**
     * @param timeFrom the timeFrom to set
     */
    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    /**
     * @return the timeTo
     */
    public String getTimeTo() {
        return timeTo;
    }

    /**
     * @param timeTo the timeTo to set
     */
    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the TLList
     */
    public List<TalkLecture> getTLList() {
        return TLList;
    }

    /**
     * @param TLList the TLList to set
     */
    public void setTLList(List<TalkLecture> TLList) {
        this.TLList = TLList;
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
