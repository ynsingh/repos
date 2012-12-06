/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ConsultancyDao;
import org.IGNOU.ePortfolio.Model.Consultancy;

/**
 * @author IGNOU Team
 * @version 1
 * @since 25 December 2011
 */
public class ConsultancyAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private ConsultancyDao dao = new ConsultancyDao();
    private Consultancy consultInfo = new Consultancy();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public ConsultancyAction() {
    }

    @Override
    public Object getModel() {
        getConsultInfo().setUserId(user_id);
        return getConsultInfo();
    }

    @Override
    public String execute() throws Exception {
        dao.saveConsultancyInfo(getConsultInfo());
        msg = infoSaved;
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
     * @return the consultInfo
     */
    public Consultancy getConsultInfo() {
        return consultInfo;
    }

    /**
     * @param consultInfo the consultInfo to set
     */
    public void setConsultInfo(Consultancy consultInfo) {
        this.consultInfo = consultInfo;
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
