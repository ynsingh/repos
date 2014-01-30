/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.PeerGroupDao;
import org.IGNOU.ePortfolio.Model.UserList;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class ResumeBuild extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private String resumeTitle;
    private String resumeObjective;
    private PeerGroupDao bdao = new PeerGroupDao();
    private List<UserList> userDetaillist;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private Date currentDate = new Date();

    public String MakeResume() {
        userDetaillist = bdao.UserListDetailByUserId(user_id);

        return SUCCESS;
    }

    public List<UserList> MakeResumes() {
        userDetaillist = bdao.UserListDetailByUserId(user_id);

        return userDetaillist;
    }

    /**
     * @return the resumeTitle
     */
    public String getResumeTitle() {
        return resumeTitle;
    }

    /**
     * @param resumeTitle the resumeTitle to set
     */
    public void setResumeTitle(String resumeTitle) {
        this.resumeTitle = resumeTitle;
    }

    /**
     * @return the resumeObjective
     */
    public String getResumeObjective() {
        return resumeObjective;
    }

    /**
     * @param resumeObjective the resumeObjective to set
     */
    public void setResumeObjective(String resumeObjective) {
        this.resumeObjective = resumeObjective;
    }

    /**
     * @return the bdao
     */
    public PeerGroupDao getBdao() {
        return bdao;
    }

    /**
     * @param bdao the bdao to set
     */
    public void setBdao(PeerGroupDao bdao) {
        this.bdao = bdao;
    }

    /**
     * @return the userDetaillist
     */
    public List<UserList> getUserDetaillist() {
        return userDetaillist;
    }

    /**
     * @param userDetaillist the userDetaillist to set
     */
    public void setUserDetaillist(List<UserList> userDetaillist) {
        this.userDetaillist = userDetaillist;
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
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
