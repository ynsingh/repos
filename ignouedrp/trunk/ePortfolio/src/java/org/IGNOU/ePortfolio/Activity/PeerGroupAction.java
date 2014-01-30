/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Activity;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ActivitiesDao;
import org.IGNOU.ePortfolio.Model.ActivitiesAnnounce;
import org.IGNOU.ePortfolio.Model.ActivitiesSubmission;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 01-06-2012
 */
public class PeerGroupAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ActivitiesDao dao = new ActivitiesDao();
    private Integer instituteId;
    private Integer programmeId;
    private Integer courseId;
    private ActivitiesAnnounce evi = new ActivitiesAnnounce();
    private int evidenceId;
    private int gradeScale;
    private String facultyId;
    private List<ActivitiesAnnounce> PeerEviList;
    private ActivitiesSubmission eviSub = new ActivitiesSubmission();
    private int submissionId;
    private String userId;
    private List<ActivitiesSubmission> PeerEviSubList;

    public PeerGroupAction() {
    }

    public String PeerGroupEviList() throws Exception {
        PeerEviList = dao.EvidenceListByInstituteIdProgrammeId(instituteId, programmeId);
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
    public ActivitiesDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ActivitiesDao dao) {
        this.dao = dao;
    }

    /**
     * @return the instituteId
     */
    public Integer getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
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

    /**
     * @return the evi
     */
    public ActivitiesAnnounce getEvi() {
        return evi;
    }

    /**
     * @param evi the evi to set
     */
    public void setEvi(ActivitiesAnnounce evi) {
        this.evi = evi;
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
     * @return the gradeScale
     */
    public int getGradeScale() {
        return gradeScale;
    }

    /**
     * @param gradeScale the gradeScale to set
     */
    public void setGradeScale(int gradeScale) {
        this.gradeScale = gradeScale;
    }

    /**
     * @return the facultyId
     */
    public String getFacultyId() {
        return facultyId;
    }

    /**
     * @param facultyId the facultyId to set
     */
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * @return the EvidenceListByInstituteIdProgrammeId
     */
    public List<ActivitiesAnnounce> getPeerEviList() {
        return PeerEviList;
    }

    /**
     * @param EvidenceListByInstituteIdProgrammeId the
     * EvidenceListByInstituteIdProgrammeId to set
     */
    public void setPeerEviList(List<ActivitiesAnnounce> PeerEviList) {
        this.PeerEviList = PeerEviList;
    }

    /**
     * @return the eviSub
     */
    public ActivitiesSubmission getEviSub() {
        return eviSub;
    }

    /**
     * @param eviSub the eviSub to set
     */
    public void setEviSub(ActivitiesSubmission eviSub) {
        this.eviSub = eviSub;
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
     * @return the PeerEviSubList
     */
    public List<ActivitiesSubmission> getPeerEviSubList() {
        return PeerEviSubList;
    }

    /**
     * @param PeerEviSubList the PeerEviSubList to set
     */
    public void setPeerEviSubList(List<ActivitiesSubmission> PeerEviSubList) {
        this.PeerEviSubList = PeerEviSubList;
    }
}
