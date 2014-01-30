/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ConferenceDao;
import org.IGNOU.ePortfolio.Model.Conference;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 28-02-2011
 */
public class ConferenceAction extends ActionSupport implements Serializable, ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private Conference conf = new Conference();
    private ConferenceDao dao = new ConferenceDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public ConferenceAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.ConferenceSave(getConf());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getConf().setUserId(getUser_id());
        return getConf();
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
     * @return the conf
     */
    public Conference getConf() {
        return conf;
    }

    /**
     * @param conf the conf to set
     */
    public void setConf(Conference conf) {
        this.conf = conf;
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
