/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.FeedbackDao;
import org.IGNOU.ePortfolio.Model.Feedback;

/**
 *
 * @author IGNOU Team
 */
public class FeedBackArchiveAction extends ActionSupport {
  private FeedbackDao fbDao = new FeedbackDao();
    private Feedback fback;
    private List<Feedback> fbList;   
    private  long feedbackId;
    private Boolean archive;

   
    
     public String MoveFbsToArchive() throws Exception {
        Feedback ArchiveFeedback = getFbDao().ArchiveFeedback(getFeedbackId());
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
        this.fbList = fbList;
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
     * @return the archive
     */
    public Boolean getArchive() {
        return archive;
    }

    /**
     * @param archive the archive to set
     */
    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

   

}
