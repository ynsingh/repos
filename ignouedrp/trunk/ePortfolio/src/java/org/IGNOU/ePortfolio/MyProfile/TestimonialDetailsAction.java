/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.PrintHtmlPdf;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.Action.sendMail;
import org.IGNOU.ePortfolio.DAO.TestimonialDao;
import org.IGNOU.ePortfolio.Model.Testimonials;
import org.apache.log4j.Logger;

/**
 *
 * @author Vinay
 */
public class TestimonialDetailsAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private TestimonialDao dao = new TestimonialDao();
    private Testimonials testiModel = new Testimonials();
    private List<Testimonials> ReqsentList;
    private Integer testiReqId;
    private String msg, norecordFound = getText("recordNotFound");
    private String saveEmail = getText("msg.testimonial.saveEmail"), update = getText("msg.testimonial.updateEmail"), export = getText("msg.testimonial.export");
    private String title, sentFtitle = getText("title.testimonial.sent.faculty");
    private String sentStitle = getText("title.testimonial.sent.student");
    private String drafttitle = getText("title.testimonial.draft.faculty");
    private String DraftEdit = getText("title.testimonial.draft.edit.faculty");
    private String mailSubject = getText("mail.Subject.Testimonial");
    private String CFile = ReadPropertyFile("Filepath");
    private InputStream fps;
    private String FileToCreate;
    private PrintHtmlPdf phpdf = new PrintHtmlPdf();
    private sendMail sendmailAction = new sendMail();
    private String ButtonName;
    private String requestorId, testiForEmail, testiReqCc, testiReqBcc, createDate, report;

    public TestimonialDetailsAction() {
    }

    public String TestimonialSentListforStudent() throws Exception {
        ReqsentList = dao.STestimonialSentToConcerned(user_id);
        if (ReqsentList.isEmpty() || ReqsentList == null) {
            msg = norecordFound;
        }
        title = sentStitle;
        return SUCCESS;
    }

    public String TestimonialSentListforFaculty() throws Exception {
        ReqsentList = dao.FTestimonialSentToConcerned(user_id);
        if (ReqsentList.isEmpty() || ReqsentList == null) {
            msg = norecordFound;
        }
        title = sentFtitle;
        return SUCCESS;
    }

    public String TestimonialDraftforFaculty() throws Exception {
        ReqsentList = dao.FTestimonialSaved(user_id);
        if (ReqsentList.isEmpty() || ReqsentList == null) {
            msg = norecordFound;
        }
        title = drafttitle;
        return SUCCESS;
    }

    public String EditDraftFaculty() throws Exception {
        ReqsentList = dao.FTestimonialEdit(testiReqId);
        if (ReqsentList.isEmpty() || ReqsentList == null) {
            msg = norecordFound;
        }
        title = DraftEdit;
        return SUCCESS;
    }

    public String UpdateTestimonial() throws Exception {
        if ("Email".equals(getButtonName())) {
            String tempCc[] = testiReqCc.split(",");
            String tempBcc[] = testiReqBcc.split(",");
            try {
                sendmailAction.SendMailToCcBcc(user_id, testiForEmail, tempCc, tempBcc, mailSubject, getReport());
            } catch (Exception e) {
                msg = e.getMessage();
            }
            msg = saveEmail;
            return SUCCESS;
        }
        if ("Save".equals(getButtonName())) {
            dao.UpdateTestimonialbyFaculty(testiReqId, Boolean.TRUE, testiForEmail, testiReqCc, testiReqBcc, report);
            msg = update;
            return SUCCESS;
        }
        if ("Export".equals(getButtonName())) {
            CFile = CFile + "/" + user_id + "/" + "UserTesti.pdf";
            setReport(getReport().replace("<br>", "<br/>"));
            setReport(getReport().replaceAll("\"", "\\\""));
            FileToCreate = phpdf.HtmlToPdf(getReport(), CFile);
            fps = new FileInputStream(new File(FileToCreate));
            msg = export;
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
     * @return the sentFtitle
     */
    public String getSentFtitle() {
        return sentFtitle;
    }

    /**
     * @param sentFtitle the sentFtitle to set
     */
    public void setSentFtitle(String sentFtitle) {
        this.sentFtitle = sentFtitle;
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
     * @return the sentStitle
     */
    public String getSentStitle() {
        return sentStitle;
    }

    /**
     * @param sentStitle the sentStitle to set
     */
    public void setSentStitle(String sentStitle) {
        this.sentStitle = sentStitle;
    }

    /**
     * @return the testiReqId
     */
    public Integer getTestiReqId() {
        return testiReqId;
    }

    /**
     * @param testiReqId the testiReqId to set
     */
    public void setTestiReqId(Integer testiReqId) {
        this.testiReqId = testiReqId;
    }

    /**
     * @return the DraftEdit
     */
    public String getDraftEdit() {
        return DraftEdit;
    }

    /**
     * @param DraftEdit the DraftEdit to set
     */
    public void setDraftEdit(String DraftEdit) {
        this.DraftEdit = DraftEdit;
    }

    /**
     * @return the report
     */
    public String getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(String report) {
        this.report = report;
    }

    /**
     * @return the ButtonName
     */
    public String getButtonName() {
        return ButtonName;
    }

    /**
     * @param ButtonName the ButtonName to set
     */
    public void setButtonName(String ButtonName) {
        this.ButtonName = ButtonName;
    }

    /**
     * @return the mailSubject
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * @param mailSubject the mailSubject to set
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * @return the CFile
     */
    public String getCFile() {
        return CFile;
    }

    /**
     * @param CFile the CFile to set
     */
    public void setCFile(String CFile) {
        this.CFile = CFile;
    }

    /**
     * @return the fps
     */
    public InputStream getFps() {
        return fps;
    }

    /**
     * @param fps the fps to set
     */
    public void setFps(InputStream fps) {
        this.fps = fps;
    }

    /**
     * @return the FileToCreate
     */
    public String getFileToCreate() {
        return FileToCreate;
    }

    /**
     * @param FileToCreate the FileToCreate to set
     */
    public void setFileToCreate(String FileToCreate) {
        this.FileToCreate = FileToCreate;
    }

    /**
     * @return the phpdf
     */
    public PrintHtmlPdf getPhpdf() {
        return phpdf;
    }

    /**
     * @param phpdf the phpdf to set
     */
    public void setPhpdf(PrintHtmlPdf phpdf) {
        this.phpdf = phpdf;
    }

    /**
     * @return the sendmailAction
     */
    public sendMail getSendmailAction() {
        return sendmailAction;
    }

    /**
     * @param sendmailAction the sendmailAction to set
     */
    public void setSendmailAction(sendMail sendmailAction) {
        this.sendmailAction = sendmailAction;
    }

    /**
     * @return the testiForEmail
     */
    public String getTestiForEmail() {
        return testiForEmail;
    }

    /**
     * @param testiForEmail the testiForEmail to set
     */
    public void setTestiForEmail(String testiForEmail) {
        this.testiForEmail = testiForEmail;
    }

    /**
     * @return the testiReqCc
     */
    public String getTestiReqCc() {
        return testiReqCc;
    }

    /**
     * @param testiReqCc the testiReqCc to set
     */
    public void setTestiReqCc(String testiReqCc) {
        this.testiReqCc = testiReqCc;
    }

    /**
     * @return the testiReqBcc
     */
    public String getTestiReqBcc() {
        return testiReqBcc;
    }

    /**
     * @param testiReqBcc the testiReqBcc to set
     */
    public void setTestiReqBcc(String testiReqBcc) {
        this.testiReqBcc = testiReqBcc;
    }

    /**
     * @return the requestorId
     */
    public String getRequestorId() {
        return requestorId;
    }

    /**
     * @param requestorId the requestorId to set
     */
    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the saveEmail
     */
    public String getSaveEmail() {
        return saveEmail;
    }

    /**
     * @param saveEmail the saveEmail to set
     */
    public void setSaveEmail(String saveEmail) {
        this.saveEmail = saveEmail;
    }

    /**
     * @return the update
     */
    public String getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(String update) {
        this.update = update;
    }

    /**
     * @return the export
     */
    public String getExport() {
        return export;
    }

    /**
     * @param export the export to set
     */
    public void setExport(String export) {
        this.export = export;
    }
}
