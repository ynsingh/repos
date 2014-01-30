/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.JournalDao;
import org.IGNOU.ePortfolio.Model.Journal;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 27-02-2012
 */
public class JournalAction extends ActionSupport implements Serializable, ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private Journal j = new Journal();
    private JournalDao dao = new JournalDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public JournalAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.JournalSave(getJ());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getJ().setUserId(getUser_id());
        return getJ();
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
     * @return the j
     */
    public Journal getJ() {
        return j;
    }

    /**
     * @param j the j to set
     */
    public void setJ(Journal j) {
        this.j = j;
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
