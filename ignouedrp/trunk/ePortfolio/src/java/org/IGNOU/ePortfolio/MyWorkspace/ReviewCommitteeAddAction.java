/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ReviewCommitteeDao;
import org.IGNOU.ePortfolio.Model.ReviewCommittee;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 17-02-2012
 */
public class ReviewCommitteeAddAction extends ActionSupport implements Serializable, ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ReviewCommittee rc = new ReviewCommittee();
    private ReviewCommitteeDao dao = new ReviewCommitteeDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public ReviewCommitteeAddAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.saveRCInfo(rc);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        rc.setUserId(user_id);
        return rc;
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
     * @return the rc
     */
    public ReviewCommittee getRc() {
        return rc;
    }

    /**
     * @param rc the rc to set
     */
    public void setRc(ReviewCommittee rc) {
        this.rc = rc;
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
