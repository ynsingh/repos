/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MediaPublicationDao;
import org.IGNOU.ePortfolio.Model.MediaPublication;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 16 February 2012
 */
public class MediaAddAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private MediaPublication mp = new MediaPublication();
    private MediaPublicationDao dao = new MediaPublicationDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public MediaAddAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.MediaPublicationSave(getMp());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getMp().setUserId(getUser_id());
        return getMp();
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
