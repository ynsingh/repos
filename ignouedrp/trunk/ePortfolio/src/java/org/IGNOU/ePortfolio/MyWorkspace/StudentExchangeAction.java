/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.StudentExchangeDao;
import org.IGNOU.ePortfolio.Model.StudentExchange;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 27 December 2011
 */
public class StudentExchangeAction extends ActionSupport implements Serializable  , ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private StudentExchangeDao dao = new StudentExchangeDao();
    private StudentExchange StdExchange = new StudentExchange();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public StudentExchangeAction() {
    }

    @Override
    public String execute() throws Exception {
        getDao().StudentExchangeSave(StdExchange);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getStdExchange().setUserId(user_id);
        return StdExchange;
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
    public StudentExchangeDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(StudentExchangeDao dao) {
        this.dao = dao;
    }

    /**
     * @return the StdExchange
     */
    public StudentExchange getStdExchange() {
        return StdExchange;
    }

    /**
     * @param StdExchange the StdExchange to set
     */
    public void setStdExchange(StudentExchange StdExchange) {
        this.StdExchange = StdExchange;
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
