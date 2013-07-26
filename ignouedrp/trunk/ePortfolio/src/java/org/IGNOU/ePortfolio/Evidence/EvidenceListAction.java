/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EvidenceDao;
import org.IGNOU.ePortfolio.Model.Evidence;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;

/**
 *
 * @author IGNOU Team
 */
public class EvidenceListAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private List<Evidence> StdevList;
    private List<Evidence> EvidenceReviewList;
    private List<EvidenceSubmission> EviSubList;
    private EvidenceDao dao = new EvidenceDao();
    private Date openDate;
    private Date closeDate;
    private int evidenceId;
    private String open;
    private String poststatus;
    private Date sDate = new Date();
    private ArrayList<String> oarList = new ArrayList<String>();
    private ArrayList<String> GradeValue;
    private String msg;
    private String recordNotFound = getText("recordNotFound");

    public EvidenceListAction() {
    }

    public String FacultyEvidenceList() throws Exception {
        StdevList = getDao().EvidenceListByFacultyId(user_id);
        if (StdevList.isEmpty()) {
            msg = recordNotFound;
            return SUCCESS;

        } else {
            Date odate;
            for (int i = 0; i < StdevList.size(); i++) {
                odate = StdevList.get(i).getOpenDate();
                if (sDate.compareTo(odate) < 0) {
                    open = "null";
                    oarList.add(open);
                } else {
                    open = "notnull";
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
             msg = recordNotFound;
        } else {
            Date odate;
            for (int i = 0; i < StdevList.size(); i++) {
                odate = StdevList.get(i).getOpenDate();
                if (sDate.compareTo(odate) < 0) {
                    open = "null";
                    oarList.add(open);
                } else {
                    open = "notnull";
                    oarList.add(open);
                }
                oarList = this.oarList;
            }
           
        }
         return SUCCESS;
    }

    public String EvidenceSubmittedList() throws Exception {
       EviSubList=dao.EvidenceSubmissionByUserId(user_id);
       if(EviSubList.isEmpty()){
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
    public List<Evidence> getStdevList() {
        return StdevList;
    }

    /**
     * @param StdevList the StdevList to set
     */
    public void setStdevList(List<Evidence> StdevList) {
        this.StdevList = StdevList;
    }

    /**
     * @return the dao
     */
    public EvidenceDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(EvidenceDao dao) {
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
    public List<Evidence> getEvidenceReviewList() {
        return EvidenceReviewList;
    }

    /**
     * @param EvidenceReviewList the EvidenceReviewList to set
     */
    public void setEvidenceReviewList(List<Evidence> EvidenceReviewList) {
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
    public List<EvidenceSubmission> getEviSubList() {
        return EviSubList;
    }

    /**
     * @param EviSubList the EviSubList to set
     */
    public void setEviSubList(List<EvidenceSubmission> EviSubList) {
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
