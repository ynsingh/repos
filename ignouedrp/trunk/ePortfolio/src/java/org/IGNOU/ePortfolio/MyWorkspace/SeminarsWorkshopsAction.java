
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.SeminarsWorkshopsDao;
import org.IGNOU.ePortfolio.Model.SeminarsWorkshops;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 24-02-2012
 */
public class SeminarsWorkshopsAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private SeminarsWorkshops sw = new SeminarsWorkshops();
    private SeminarsWorkshopsDao dao = new SeminarsWorkshopsDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public SeminarsWorkshopsAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.saveSW(getSw());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getSw().setUserId(getUser_id());
        return getSw();
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
     * @return the sw
     */
    public SeminarsWorkshops getSw() {
        return sw;
    }

    /**
     * @param sw the sw to set
     */
    public void setSw(SeminarsWorkshops sw) {
        this.sw = sw;
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
