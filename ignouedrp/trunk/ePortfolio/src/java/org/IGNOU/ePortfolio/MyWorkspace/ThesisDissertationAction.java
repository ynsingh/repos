/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ThesisDissertationDao;
import org.IGNOU.ePortfolio.Model.ThesisDissertation;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 21-02-2012
 */
public class ThesisDissertationAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private ThesisDissertation TD = new ThesisDissertation();
    private ThesisDissertationDao dao = new ThesisDissertationDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public ThesisDissertationAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.saveTDInfo(getTD());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getTD().setUserId(getUser_id());
        return getTD();
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
     * @return the TD
     */
    public ThesisDissertation getTD() {
        return TD;
    }

    /**
     * @param TD the TD to set
     */
    public void setTD(ThesisDissertation TD) {
        this.TD = TD;
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
