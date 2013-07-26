/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.CommentDao;
import org.IGNOU.ePortfolio.Model.Allowcomment;

/**
 *
 * @author IGNOU Team
 */
public class AllowCommentAction extends ActionSupport 
{

    private String user_id = new UserSession().getUserInSession();
    private CommentDao cDao = new CommentDao();
    private Allowcomment allcom = new Allowcomment();
    private int submissionId;
    private Boolean canComment;
    private String listStudent;
    private List<String> stSelList;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");
    private int  evidenceId;
   

    public String AddStudentListToEvSub() {
        cDao.EvidenceSubmissionUpdateWithAllowedStudent(submissionId, canComment, listStudent);
        msg = infoSaved;
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
     * @return the cDao
     */
    public CommentDao getcDao() {
        return cDao;
    }

    /**
     * @param cDao the cDao to set
     */
    public void setcDao(CommentDao cDao) {
        this.cDao = cDao;
    }

    /**
     * @return the allcom
     */
    public Allowcomment getAllcom() {
        return allcom;
    }

    /**
     * @param allcom the allcom to set
     */
    public void setAllcom(Allowcomment allcom) {
        this.allcom = allcom;
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
     * @return the canComment
     */
    public Boolean getCanComment() {
        return canComment;
    }

    /**
     * @param canComment the canComment to set
     */
    public void setCanComment(Boolean canComment) {
        this.canComment = canComment;
    }

    /**
     * @return the listStudent
     */
    public String getListStudent() {
        return listStudent;
    }

    /**
     * @param listStudent the listStudent to set
     */
    public void setListStudent(String listStudent) {
        this.listStudent = listStudent;
    }

    /**
     * @return the stSelList
     */
    public List<String> getStSelList() {
        return stSelList;
    }

    /**
     * @param stSelList the stSelList to set
     */
    public void setStSelList(List<String> stSelList) {
        this.stSelList = stSelList;
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
