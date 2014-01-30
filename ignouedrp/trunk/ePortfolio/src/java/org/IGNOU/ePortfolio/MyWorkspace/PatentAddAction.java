/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.PatentDao;
import org.IGNOU.ePortfolio.Model.Patent;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class PatentAddAction extends ActionSupport implements Serializable, ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private Patent Pat = new Patent();
    private PatentDao dao = new PatentDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public PatentAddAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.PatentSave(Pat);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getPat().setUserId(user_id);
        return getPat();
    }

    /**
     * @return the Pat
     */
    public Patent getPat() {
        return Pat;
    }

    /**
     * @param Pat the Pat to set
     */
    public void setPat(Patent Pat) {
        this.Pat = Pat;
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
