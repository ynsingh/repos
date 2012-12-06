/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ExtraActivitiesDao;
import org.IGNOU.ePortfolio.Model.ExtraActivities;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 01-03-2012
 */
public class ExtraActivitiesInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private ExtraActivities ext = new ExtraActivities();
    private ExtraActivitiesDao dao = new ExtraActivitiesDao();
    private Long activitiesId;
    private String userId;
    private String organizationName;
    private String role;
    private String tfrom;
    private String tto;
    private String description;
    private String cause;
    private List<ExtraActivities> EXTList;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public ExtraActivitiesInfoAction() {
    }

    public String ShowExtInfo() throws Exception {
        setEXTList(getDao().ShowInfo(getUser_id()));
        if (getEXTList().isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditExtInfo() throws Exception {
        setEXTList(getDao().EditInfo(getActivitiesId()));
        return SUCCESS;
    }

    public String UpdateExtInfo() throws Exception {
        getDao().UpdateInfo(getActivitiesId(), getUserId(), getOrganizationName(), getCause(), getRole(), getTfrom(), getTto(), getDescription());
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteExtInfo() throws Exception {
        getDao().DeleteInfo(getActivitiesId());
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
     * @return the ext
     */
    public ExtraActivities getExt() {
        return ext;
    }

    /**
     * @param ext the ext to set
     */
    public void setExt(ExtraActivities ext) {
        this.ext = ext;
    }

    /**
     * @return the dao
     */
    public ExtraActivitiesDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ExtraActivitiesDao dao) {
        this.dao = dao;
    }

    /**
     * @return the activitiesId
     */
    public Long getActivitiesId() {
        return activitiesId;
    }

    /**
     * @param activitiesId the activitiesId to set
     */
    public void setActivitiesId(Long activitiesId) {
        this.activitiesId = activitiesId;
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
     * @return the organizationName
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * @param organizationName the organizationName to set
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
     * @return the tfrom
     */
    public String getTfrom() {
        return tfrom;
    }

    /**
     * @param tfrom the tfrom to set
     */
    public void setTfrom(String tfrom) {
        this.tfrom = tfrom;
    }

    /**
     * @return the tto
     */
    public String getTto() {
        return tto;
    }

    /**
     * @param tto the tto to set
     */
    public void setTto(String tto) {
        this.tto = tto;
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
     * @return the cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * @param cause the cause to set
     */
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * @return the EXTList
     */
    public List<ExtraActivities> getEXTList() {
        return EXTList;
    }

    /**
     * @param EXTList the EXTList to set
     */
    public void setEXTList(List<ExtraActivities> EXTList) {
        this.EXTList = EXTList;
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
