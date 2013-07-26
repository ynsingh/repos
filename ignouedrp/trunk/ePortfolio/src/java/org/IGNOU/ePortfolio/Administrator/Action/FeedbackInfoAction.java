/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.sendMail;
import org.IGNOU.ePortfolio.DAO.FeedbackDao;
import org.IGNOU.ePortfolio.Model.Feedback;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author IGNOU Team
 */
public class FeedbackInfoAction extends ActionSupport {

    private FeedbackDao fbDao = new FeedbackDao();
    private Feedback fback;
    private List<Feedback> fbList;
    private long feedbackId;
    private String name;
    private String occupation;
    private String organization;
    private String emailId;
    private Long phone;
    private String comment, FSubject;
    private String to, From, Password, msg, subject;

    public String ShowFeedback() throws Exception {
        fbList = (fbDao.FeedbackList());
        return SUCCESS;
    }
     public String ShowArchiveFeedback() throws Exception {
        fbList = (fbDao.ArchiveFeedbackList());
        return SUCCESS;
    }

    public String DeleteFeedBack() {
        fbDao.FeedbackDeleteByFeedbackId(feedbackId);

        return SUCCESS;
    }

    public String ReplyFeedBack() {
        fbList = fbDao.FeedbackReplyListByFeedbackId(feedbackId);
        return SUCCESS;
    }

    public String DetailFeedback() {
        fbList = fbDao.FeedbackReplyListByFeedbackId(feedbackId);
        return SUCCESS;
    }
             

    public String  ForwardFeedback() {
        fbList = fbDao.FeedbackReplyListByFeedbackId(feedbackId);
        return SUCCESS;
    }

    public String SendFeedbackReply() throws Exception {

        to = getEmailId();
        From = ReadPropertyFile("mailFrom");
        Password = ReadPropertyFile("mailPassword");
        subject =  getFSubject();
        msg = "Dear &nbsp;" + getName() + "<br><br><br>" + getComment();

        new sendMail().SendMail(From, to, subject, msg);

        return SUCCESS;

    }

    /**
     * @return the fbDao
     */
    public FeedbackDao getFbDao() {
        return fbDao;
    }

    /**
     * @param fbDao the fbDao to set
     */
    public void setFbDao(FeedbackDao fbDao) {
        this.fbDao = fbDao;
    }

    /**
     * @return the fback
     */
    public Feedback getFback() {
        return fback;
    }

    /**
     * @param fback the fback to set
     */
    public void setFback(Feedback fback) {
        this.fback = fback;
    }

    /**
     * @return the fbList
     */
    public List<Feedback> getFbList() {
        return fbList;
    }

    /**
     * @param fbList the fbList to set
     */
    public void setFbList(List<Feedback> fbList) {
        this.setFbList(fbList);
    }

    /**
     * @return the feedbackId
     */
    public long getFeedbackId() {
        return feedbackId;
    }

    /**
     * @param feedbackId the feedbackId to set
     */
    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the phone
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the FSubject
     */
    public String getFSubject() {
        return FSubject;
    }

    /**
     * @param FSubject the FSubject to set
     */
    public void setFSubject(String FSubject) {
        this.FSubject = FSubject;
    }
}
