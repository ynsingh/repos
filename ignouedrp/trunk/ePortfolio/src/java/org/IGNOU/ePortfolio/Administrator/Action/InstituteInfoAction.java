/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ActivitiesDao;
import org.IGNOU.ePortfolio.DAO.InstituteDAO;
import org.IGNOU.ePortfolio.DAO.UserListDao;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.ActivitiesAnnounce;
import org.IGNOU.ePortfolio.Model.Institute;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class InstituteInfoAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    private Institute ins = new Institute();
    private InstituteDAO dao = new InstituteDAO();
    private List<Institute> InsList;
    private List<User> usrList;
    private int instituteId, evidenceId;
    private Integer programmeId;
    private Integer courseId;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private User u = new User();
    private List<User> UListInfo;
    private List<ActivitiesAnnounce> evdInfo;
    private ActivitiesDao evdao = new ActivitiesDao();
    private UserProgrammeDao uiDao = new UserProgrammeDao();
    private UserListDao ulDao = new UserListDao();
    private List<String> stList = new ArrayList<String>();
    private int submissionId;
    private String userId;

    public InstituteInfoAction() {
    }

    public String ShowRegisteredInstitute() throws Exception {
        InsList = dao.InstituteList();
        if (InsList == null || InsList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String InstituteInfo() throws Exception {
        UListInfo = uiDao.UserListByUserId(user_id);
        InsList = dao.InstituteListByInstituteId(UListInfo.iterator().next().getInstituteId());
        return SUCCESS;
    }

    public String StudentList() {
        usrList = ulDao.UserListByProgrammeIdUserId(programmeId, userId);
        for (int i = 0; i < usrList.size(); i++) {
            getStList().add(usrList.get(i).getFname() + "(" + usrList.get(i).getEmailId() + ")");
        }
        return SUCCESS;
    }

    /**
     * @return the ins
     */
    public Institute getIns() {
        return ins;
    }

    /**
     * @param ins the ins to set
     */
    public void setIns(Institute ins) {
        this.ins = ins;
    }

    /**
     * @return the InsList
     */
    public List<Institute> getInsList() {
        return InsList;
    }

    /**
     * @param InsList the InsList to set
     */
    public void setInsList(List<Institute> InsList) {
        this.InsList = InsList;
    }

    /**
     * @return the instituteId
     */
    public int getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(int instituteId) {
        this.instituteId = instituteId;
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
     * @return the u
     */
    public User getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(User u) {
        this.u = u;
    }

    /**
     * @return the UListInfo
     */
    public List<User> getUListInfo() {
        return UListInfo;
    }

    /**
     * @param UListInfo the UListInfo to set
     */
    public void setUListInfo(List<User> UListInfo) {
        this.UListInfo = UListInfo;
    }

    /**
     * @return the usrList
     */
    public List<User> getUsrList() {
        return usrList;
    }

    /**
     * @param usrList the usrList to set
     */
    public void setUsrList(List<User> usrList) {
        this.usrList = usrList;
    }

    /**
     * @return the stList
     */
    public List<String> getStList() {
        return stList;
    }

    /**
     * @param stList the stList to set
     */
    public void setStList(List<String> stList) {
        this.stList = stList;
    }

    /**
     * @return the evidenceId
     */
    public int getEvidenceId() {
        return evidenceId;
    }

    /**
     * @param evidenceId the evidenceId to set
     */
    public void setEvidenceId(int evidenceId) {
        this.evidenceId = evidenceId;
    }

    /**
     * @return the evdInfo
     */
    public List<ActivitiesAnnounce> getEvdInfo() {
        return evdInfo;
    }

    /**
     * @param evdInfo the evdInfo to set
     */
    public void setEvdInfo(List<ActivitiesAnnounce> evdInfo) {
        this.evdInfo = evdInfo;
    }

    /**
     * @return the evdao
     */
    public ActivitiesDao getEvdao() {
        return evdao;
    }

    /**
     * @param evdao the evdao to set
     */
    public void setEvdao(ActivitiesDao evdao) {
        this.evdao = evdao;
    }

    /**
     * @return the submissionId
     */
    public int getSubmissionId() {
        return submissionId;
    }

    /**
     * @param submissionId the submissionId to set
     */
    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the programmeId
     */
    public Integer getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @return the courseId
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
