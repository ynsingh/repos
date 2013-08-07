/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.File;
import java.util.Date;
import org.IGNOU.ePortfolio.Action.FileUploadCommon;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.TestimonialDao;
import org.IGNOU.ePortfolio.Model.Testimonials;
import org.IGNOU.ePortfolio.Model.UserList;

/**
 *
 * @author Vinay
 */
public class TestimonialReqAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private TestimonialDao dao = new TestimonialDao();
    private Testimonials testiModel = new Testimonials();
    private String msg, sentmsg = getText("msg.Request"), draftmsg = getText("msg.draft");
    private String buttonName, testiReqTo;
    private String FilePath = ReadPropertyFile("Filepath");
    private File sDoc;
    private String sDocFileName, sDocPath;
    private FileUploadCommon fupload = new FileUploadCommon();

    public TestimonialReqAction() {
    }

    @Override
    public String execute() throws Exception {
        if ("Submit".equals(getButtonName())) {
            sDoc = testiModel.getSupportingDoc();
            sDocFileName = testiModel.getSupportingDocFileName();
            if (sDoc != null) {
                sDocPath = FilePath + "/" + user_id + "/";
                boolean check = fupload.UploadFile(sDoc, sDocFileName, sDocPath);
                if (check = true) {
                    testiModel.setTestiReqFile(sDocFileName);
                } else {
                    testiModel.setTestiReqFile("error");
                }
            } else {
                testiModel.setTestiReqFile("null");

            }
            testiModel.setDraft(Boolean.FALSE);
            dao.TestimonialSave(testiModel, testiReqTo);
            msg = sentmsg;
        }
        if ("Draft".equals(getButtonName())) {
            sDoc = testiModel.getSupportingDoc();
            sDocFileName = testiModel.getSupportingDocFileName();
            if (sDoc != null) {
                sDocPath = FilePath + "/" + user_id + "/";
                boolean check = fupload.UploadFile(sDoc, sDocFileName, sDocPath);
                if (check = true) {
                    testiModel.setTestiReqFile(sDocFileName);
                } else {
                    testiModel.setTestiReqFile("error");
                }
            } else {
                testiModel.setTestiReqFile("null");

            }
            testiModel.setDraft(Boolean.TRUE);
            dao.TestimonialSave(testiModel, testiReqTo);
            msg = draftmsg;
        }
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        UserList ul = new UserList();
        ul.setEmailId(user_id);
        testiModel.setUserByTestiRequestor(ul);
        testiModel.setTestiReqDate(new Date());
        testiModel.setReadStatus(Boolean.FALSE);
        return testiModel;
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
     * @return the sentmsg
     */
    public String getSentmsg() {
        return sentmsg;
    }

    /**
     * @param sentmsg the sentmsg to set
     */
    public void setSentmsg(String sentmsg) {
        this.sentmsg = sentmsg;
    }

    /**
     * @return the buttonName
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * @param buttonName the buttonName to set
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    /**
     * @return the testiReqTo
     */
    public String getTestiReqTo() {
        return testiReqTo;
    }

    /**
     * @param testiReqTo the testiReqTo to set
     */
    public void setTestiReqTo(String testiReqTo) {
        this.testiReqTo = testiReqTo;
    }

    /**
     * @return the FilePath
     */
    public String getFilePath() {
        return FilePath;
    }

    /**
     * @param FilePath the FilePath to set
     */
    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    /**
     * @return the sDoc
     */
    public File getsDoc() {
        return sDoc;
    }

    /**
     * @param sDoc the sDoc to set
     */
    public void setsDoc(File sDoc) {
        this.sDoc = sDoc;
    }

    /**
     * @return the sDocFileName
     */
    public String getsDocFileName() {
        return sDocFileName;
    }

    /**
     * @param sDocFileName the sDocFileName to set
     */
    public void setsDocFileName(String sDocFileName) {
        this.sDocFileName = sDocFileName;
    }

    /**
     * @return the sDocPath
     */
    public String getsDocPath() {
        return sDocPath;
    }

    /**
     * @param sDocPath the sDocPath to set
     */
    public void setsDocPath(String sDocPath) {
        this.sDocPath = sDocPath;
    }

    /**
     * @return the draftmsg
     */
    public String getDraftmsg() {
        return draftmsg;
    }

    /**
     * @param draftmsg the draftmsg to set
     */
    public void setDraftmsg(String draftmsg) {
        this.draftmsg = draftmsg;
    }
}
