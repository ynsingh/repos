/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.Model.Testimonials;
import org.IGNOU.ePortfolio.Model.UserList;
import org.IGNOU.ePortfolio.DAO.TestimonialDao;

/**
 *
 * @author Vinay Kr. Sharma
 */
public class TestimonialReqInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private TestimonialDao dao = new TestimonialDao();
    private Testimonials testiModel = new Testimonials();
    private String msg, norecordFound = getText("recordNotFound");
    private List<Testimonials> ReqsentList;
    private int testiReqId;
    private String testiRequestor;
    private List<UserList> RequestorList;
    private UserList requestorModel = new UserList();
    private String title, senttitle = getText("title.testimonial.sentReq");
    private String drafttitle = getText("title.testimonial.draft.request");
    private String indextitle = getText("title.testimonial.inbox.faculty");

    public TestimonialReqInfoAction() {
    }

    public String TestimonialSentRequest() throws Exception {
        ReqsentList = dao.SentTestimonialRequest(user_id);
        if (ReqsentList.isEmpty()) {
            msg = norecordFound;
        }
        title = senttitle;
        return SUCCESS;
    }

    public String TestimonialDraftRequest() throws Exception {
        ReqsentList = dao.DraftTestimonialRequest(user_id);
        if (ReqsentList.isEmpty()) {
            msg = norecordFound;
        }
        title = drafttitle;
        return SUCCESS;
    }

    public String FacultyRequestInbox() throws Exception {
        ReqsentList = dao.TestimonialRequestToFaculty(user_id);
        if (ReqsentList.isEmpty()) {
            msg = norecordFound;
        }
        title = indextitle;
        return SUCCESS;
    }

    public String ReadStdRequest() throws Exception {
        ReqsentList = dao.UpdateReadStatus(testiReqId);
        return SUCCESS;
    }

    public String CreateTestiforRequest() throws Exception {
        ReqsentList = dao.CreateTestimonialfor(testiReqId);
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
     * @return the norecordFound
     */
    public String getNorecordFound() {
        return norecordFound;
    }

    /**
     * @param norecordFound the norecordFound to set
     */
    public void setNorecordFound(String norecordFound) {
        this.norecordFound = norecordFound;
    }

    /**
     * @return the ReqsentList
     */
    public List<Testimonials> getReqsentList() {
        return ReqsentList;
    }

    /**
     * @param ReqsentList the ReqsentList to set
     */
    public void setReqsentList(List<Testimonials> ReqsentList) {
        this.ReqsentList = ReqsentList;
    }

    /**
     * @return the testiModel
     */
    public Testimonials getTestiModel() {
        return testiModel;
    }

    /**
     * @param testiModel the testiModel to set
     */
    public void setTestiModel(Testimonials testiModel) {
        this.testiModel = testiModel;
    }

    /**
     * @return the testiReqId
     */
    public int getTestiReqId() {
        return testiReqId;
    }

    /**
     * @param testiReqId the testiReqId to set
     */
    public void setTestiReqId(int testiReqId) {
        this.testiReqId = testiReqId;
    }

    /**
     * @return the testiRequestor
     */
    public String getTestiRequestor() {
        return testiRequestor;
    }

    /**
     * @param testiRequestor the testiRequestor to set
     */
    public void setTestiRequestor(String testiRequestor) {
        this.testiRequestor = testiRequestor;
    }

    /**
     * @return the RequestorList
     */
    public List<UserList> getRequestorList() {
        return RequestorList;
    }

    /**
     * @param RequestorList the RequestorList to set
     */
    public void setRequestorList(List<UserList> RequestorList) {
        this.RequestorList = RequestorList;
    }

    /**
     * @return the requestorModel
     */
    public UserList getRequestorModel() {
        return requestorModel;
    }

    /**
     * @param requestorModel the requestorModel to set
     */
    public void setRequestorModel(UserList requestorModel) {
        this.requestorModel = requestorModel;
    }
//    /**
//     * @return the TestiReportModel
//     */
//    public TestimonialReport getTestiReportModel() {
//        return TestiReportModel;
//    }
//
//    /**
//     * @param TestiReportModel the TestiReportModel to set
//     */
//    public void setTestiReportModel(TestimonialReport TestiReportModel) {
//        this.TestiReportModel = TestiReportModel;
//    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the senttitle
     */
    public String getSenttitle() {
        return senttitle;
    }

    /**
     * @param senttitle the senttitle to set
     */
    public void setSenttitle(String senttitle) {
        this.senttitle = senttitle;
    }

    /**
     * @return the drafttitle
     */
    public String getDrafttitle() {
        return drafttitle;
    }

    /**
     * @param drafttitle the drafttitle to set
     */
    public void setDrafttitle(String drafttitle) {
        this.drafttitle = drafttitle;
    }

    /**
     * @return the indextitle
     */
    public String getIndextitle() {
        return indextitle;
    }

    /**
     * @param indextitle the indextitle to set
     */
    public void setIndextitle(String indextitle) {
        this.indextitle = indextitle;
    }
}
