package org.IGNOU.ePortfolio.Model;
// Generated Oct 8, 2012 10:59:52 AM by Hibernate Tools 3.2.1.GA

import java.io.File;
import java.util.Date;

/**
 * Testimonials generated by hbm2java
 */
public class Testimonials implements java.io.Serializable {

    private Integer testiReqId;
    private UserList userByTestiReqTo;
    private UserList userByTestiRequestor;
    private String testiReqCc;
    private String testiReqBcc;
    private String testiType;
    private String testiReqMessage;
    private String testiReqFile;
    private String testiForName;
    private String testiForEmail;
    private String testiForAddress;
    private Date testiReqDate;
    private Boolean draft;
    private Boolean readStatus;
    private Date createDate;
    private Date sentDate;
    private Boolean sent;
    private String report;
    private File SupportingDoc;
    private String SupportingDocFileName;

    public Testimonials() {
    }

    public Testimonials(UserList userByTestiReqTo, UserList userByTestiRequestor, String testiReqCc, String testiReqBcc, String testiType, String testiReqMessage, String testiReqFile, String testiForName, String testiForEmail, String testiForAddress, Date testiReqDate, Boolean draft, Boolean readStatus, Date createDate, Date sentDate, Boolean sent, String report, File SupportingDoc, String SupportingDocFileName) {
        this.userByTestiReqTo = userByTestiReqTo;
        this.userByTestiRequestor = userByTestiRequestor;
        this.testiReqCc = testiReqCc;
        this.testiReqBcc = testiReqBcc;
        this.testiType = testiType;
        this.testiReqMessage = testiReqMessage;
        this.testiReqFile = testiReqFile;
        this.testiForName = testiForName;
        this.testiForEmail = testiForEmail;
        this.testiForAddress = testiForAddress;
        this.testiReqDate = testiReqDate;
        this.draft = draft;
        this.readStatus = readStatus;
        this.createDate = createDate;
        this.sentDate = sentDate;
        this.sent = sent;
        this.report = report;
        this.SupportingDoc = SupportingDoc;
        this.SupportingDocFileName = SupportingDocFileName;
    }

    public Integer getTestiReqId() {
        return this.testiReqId;
    }

    public void setTestiReqId(Integer testiReqId) {
        this.testiReqId = testiReqId;
    }

    public UserList getUserByTestiReqTo() {
        return this.userByTestiReqTo;
    }

    public void setUserByTestiReqTo(UserList userByTestiReqTo) {
        this.userByTestiReqTo = userByTestiReqTo;
    }

    public UserList getUserByTestiRequestor() {
        return this.userByTestiRequestor;
    }

    public void setUserByTestiRequestor(UserList userByTestiRequestor) {
        this.userByTestiRequestor = userByTestiRequestor;
    }

    public String getTestiReqCc() {
        return this.testiReqCc;
    }

    public void setTestiReqCc(String testiReqCc) {
        this.testiReqCc = testiReqCc;
    }

    public String getTestiReqBcc() {
        return this.testiReqBcc;
    }

    public void setTestiReqBcc(String testiReqBcc) {
        this.testiReqBcc = testiReqBcc;
    }

    public String getTestiType() {
        return this.testiType;
    }

    public void setTestiType(String testiType) {
        this.testiType = testiType;
    }

    public String getTestiReqMessage() {
        return this.testiReqMessage;
    }

    public void setTestiReqMessage(String testiReqMessage) {
        this.testiReqMessage = testiReqMessage;
    }

    public String getTestiReqFile() {
        return this.testiReqFile;
    }

    public void setTestiReqFile(String testiReqFile) {
        this.testiReqFile = testiReqFile;
    }

    public String getTestiForName() {
        return this.testiForName;
    }

    public void setTestiForName(String testiForName) {
        this.testiForName = testiForName;
    }

    public String getTestiForEmail() {
        return this.testiForEmail;
    }

    public void setTestiForEmail(String testiForEmail) {
        this.testiForEmail = testiForEmail;
    }

    public String getTestiForAddress() {
        return this.testiForAddress;
    }

    public void setTestiForAddress(String testiForAddress) {
        this.testiForAddress = testiForAddress;
    }

    public Date getTestiReqDate() {
        return this.testiReqDate;
    }

    public void setTestiReqDate(Date testiReqDate) {
        this.testiReqDate = testiReqDate;
    }

    public Boolean getDraft() {
        return this.draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public Boolean getReadStatus() {
        return this.readStatus;
    }

    public void setReadStatus(Boolean readStatus) {
        this.readStatus = readStatus;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSentDate() {
        return this.sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Boolean getSent() {
        return this.sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    /**
     * @return the SupportingDoc
     */
    public File getSupportingDoc() {
        return SupportingDoc;
    }

    /**
     * @param SupportingDoc the SupportingDoc to set
     */
    public void setSupportingDoc(File SupportingDoc) {
        this.SupportingDoc = SupportingDoc;
    }

    /**
     * @return the SupportingDocFileName
     */
    public String getSupportingDocFileName() {
        return SupportingDocFileName;
    }

    /**
     * @param SupportingDocFileName the SupportingDocFileName to set
     */
    public void setSupportingDocFileName(String SupportingDocFileName) {
        this.SupportingDocFileName = SupportingDocFileName;
    }
}