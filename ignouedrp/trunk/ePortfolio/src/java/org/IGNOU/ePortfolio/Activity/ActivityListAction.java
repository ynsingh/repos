/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Activity;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ActivitiesDao;
import org.IGNOU.ePortfolio.Model.ActivitiesAnnounce;
import org.IGNOU.ePortfolio.Model.ActivitiesSubmission;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class ActivityListAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private List<ActivitiesAnnounce> StdevList;
    private List<ActivitiesAnnounce> EvidenceReviewList;
    private List<ActivitiesSubmission> EviSubList;
    private ActivitiesDao dao = new ActivitiesDao();
    private Date openDate;
    private Date closeDate;
    private int evidenceId;
    private String open;
    private String poststatus;
    private ArrayList<String> oarList = new ArrayList<String>();
    private ArrayList<String> GradeValue;
    private String msg;
    private String recordNotFound = getText("recordNotFound");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date sDate = new Date();

    public ActivityListAction() {
    }

    public String FacultyEvidenceList() throws Exception {
        StdevList = getDao().EvidenceListByFacultyId(user_id);
        if (StdevList.isEmpty()) {
            setMsg(recordNotFound);
            return SUCCESS;
        } else {
            Date odate, cdate;;
            Date Currentdate = dateFormat.parse(dateFormat.format(sDate));
            for (int i = 0; i < StdevList.size(); i++) {
                odate = dateFormat.parse(dateFormat.format(StdevList.get(i).getOpenDate()));
                cdate = dateFormat.parse(dateFormat.format(StdevList.get(i).getCloseDate()));
                if (Currentdate.after(cdate)) {
                    open = "1";
                    oarList.add(open);
                } else {
                    open = "0";
                    oarList.add(open);
                }
                oarList = this.oarList;
            }
            return SUCCESS;
        }
    }

    public String EvidenceReview() throws Exception {
        EvidenceReviewList = dao.EvidenceListByEvidenceId(evidenceId);
        if (sDate.compareTo(EvidenceReviewList.iterator().next().getOpenDate()) <= 0) {
            poststatus = "false";
        } else {
            poststatus = "true";
        }
        return SUCCESS;
    }

    public String StudentEvidenceList() throws Exception {
        StdevList = getDao().EvidenceNotSubmitedListByUserId(user_id);
        if (StdevList.isEmpty()) {
            setMsg(recordNotFound);
        } else {
            Date odate, cdate;;
            Date Currentdate = dateFormat.parse(dateFormat.format(sDate));
            for (int i = 0; i < StdevList.size(); i++) {
                odate = dateFormat.parse(dateFormat.format(StdevList.get(i).getOpenDate()));
                cdate = dateFormat.parse(dateFormat.format(StdevList.get(i).getCloseDate()));
                if (Currentdate.after(odate) && Currentdate.before(cdate) || cdate.equals(Currentdate) || odate.equals(Currentdate)) {
                    open = "1";
                    oarList.add(open);
                } else {
                    open = "0";
                    oarList.add(open);
                    //  msg = NotOpen;
                }
                oarList = this.oarList;
            }
        }
        return SUCCESS;
    }

    public String EvidenceSubmittedList() throws Exception {
        EviSubList = dao.EvidenceSubmissionByUserId(user_id);
        if (EviSubList.isEmpty()) {
            msg = recordNotFound;
        }
        return SUCCESS;
    }

    public String StudentEvidenceDraftList() throws Exception {
        StdevList = getDao().EvidenceDraftListbyFacultyId(user_id);
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
     * @return the StdevList
     */
    public List<ActivitiesAnnounce> getStdevList() {
        return StdevList;
    }

    /**
     * @param StdevList the StdevList to set
     */
    public void setStdevList(List<ActivitiesAnnounce> StdevList) {
        this.StdevList = StdevList;
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
     * @return the openDate
     */
    public Date getOpenDate() {

        return openDate;
    }

    /**
     * @param openDate the openDate to set
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * @return the closeDate
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate the closeDate to set
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
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
     * @return the EvidenceReviewList
     */
    public List<ActivitiesAnnounce> getEvidenceReviewList() {
        return EvidenceReviewList;
    }

    /**
     * @param EvidenceReviewList the EvidenceReviewList to set
     */
    public void setEvidenceReviewList(List<ActivitiesAnnounce> EvidenceReviewList) {
        this.EvidenceReviewList = EvidenceReviewList;
    }

    /**
     * @return the open
     */
    public String getOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(String open) {
        this.open = open;
    }

    /**
     * @return the sDate
     */
    public Date getsDate() {
        return sDate;
    }

    /**
     * @param sDate the sDate to set
     */
    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    /**
     * @return the oarList
     */
    public ArrayList<String> getOarList() {
        return oarList;
    }

    /**
     * @param oarList the oarList to set
     */
    public void setOarList(ArrayList<String> oarList) {
        this.oarList = oarList;
    }

    /**
     * @return the EviSubList
     */
    public List<ActivitiesSubmission> getEviSubList() {
        return EviSubList;
    }

    /**
     * @param EviSubList the EviSubList to set
     */
    public void setEviSubList(List<ActivitiesSubmission> EviSubList) {
        this.EviSubList = EviSubList;
    }

    /**
     * @return the GradeValue
     */
    public ArrayList<String> getGradeValue() {
        return GradeValue;
    }

    /**
     * @param GradeValue the GradeValue to set
     */
    public void setGradeValue(ArrayList<String> GradeValue) {
        this.GradeValue = GradeValue;
    }

    /**
     * @return the poststatus
     */
    public String getPoststatus() {
        return poststatus;
    }

    /**
     * @param poststatus the poststatus to set
     */
    public void setPoststatus(String poststatus) {
        this.poststatus = poststatus;
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
     * @return the recordNotFound
     */
    public String getRecordNotFound() {
        return recordNotFound;
    }

    /**
     * @param recordNotFound the recordNotFound to set
     */
    public void setRecordNotFound(String recordNotFound) {
        this.recordNotFound = recordNotFound;
    }
}
