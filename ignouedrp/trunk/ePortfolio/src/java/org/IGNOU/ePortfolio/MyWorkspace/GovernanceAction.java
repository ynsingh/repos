/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.GovernanceDao;
import org.IGNOU.ePortfolio.Model.Governance;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 25 December 2011
 */
public class GovernanceAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private GovernanceDao dao = new GovernanceDao();
    private Governance governanceInfo = new Governance();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public GovernanceAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.GovernanceSave(getGovernanceInfo());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        governanceInfo.setUserId(user_id);
        return governanceInfo;
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
     * @return the governanceInfo
     */
    public Governance getGovernanceInfo() {
        return governanceInfo;
    }

    /**
     * @param governanceInfo the governanceInfo to set
     */
    public void setGovernanceInfo(Governance governanceInfo) {
        this.governanceInfo = governanceInfo;
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
