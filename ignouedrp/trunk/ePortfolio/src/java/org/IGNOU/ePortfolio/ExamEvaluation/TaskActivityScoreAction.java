/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.ExamEvaluation;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.TaskActivityDAO;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;

/**
 *
 * @author IGNOU Team
 * @version 1
 */
public class TaskActivityScoreAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private TaskActivityDAO dao = new TaskActivityDAO();
    private List<EvidenceSubmission> StdScrList;
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
     * @return the dao
     */
    public TaskActivityDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(TaskActivityDAO dao) {
        this.dao = dao;
    }
    /**
     * @return the StdScrList
     */
    public List<EvidenceSubmission> getStdScrList() {
        return StdScrList;
    }

    /**
     * @param StdScrList the StdScrList to set
     */
    public void setStdScrList(List<EvidenceSubmission> StdScrList) {
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
