/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ExtraActivitiesDao;
import org.IGNOU.ePortfolio.Model.ExtraActivities;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 29-02-2012
 */
public class ExtraActivitiesAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private ExtraActivities ext = new ExtraActivities();
    private ExtraActivitiesDao dao = new ExtraActivitiesDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public ExtraActivitiesAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.saveInfo(ext);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getExt().setUserId(getUser_id());
        return getExt();
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
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }
}
