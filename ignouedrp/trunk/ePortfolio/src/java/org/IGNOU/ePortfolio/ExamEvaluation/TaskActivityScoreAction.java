/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.ExamEvaluation;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ActivitiesDao;
import org.IGNOU.ePortfolio.Model.ActivitiesAnnounce;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 */
public class TaskActivityScoreAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ActivitiesDao dao = new ActivitiesDao();
    private List<ActivitiesAnnounce> StdScrList;
    private String notfound = getText("recordNotFound");
    private String msg;

    public TaskActivityScoreAction() {
    }

    public String GetScore() throws Exception {
        StdScrList = dao.EvidenceSubmissionListGradeNotNullByUserId(user_id);
        if (StdScrList.isEmpty()) {
            msg = notfound;
        }
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
     * @return the StdScrList
     */
    public List<ActivitiesAnnounce> getStdScrList() {
        return StdScrList;
    }

    /**
     * @param StdScrList the StdScrList to set
     */
    public void setStdScrList(List<ActivitiesAnnounce> StdScrList) {
        this.StdScrList = StdScrList;
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
     * @return the notfound
     */
    public String getNotfound() {
        return notfound;
    }

    /**
     * @param notfound the notfound to set
     */
    public void setNotfound(String notfound) {
        this.notfound = notfound;
    }
}
