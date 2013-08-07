/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import org.IGNOU.ePortfolio.Action.PrintHtmlPdf;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.Action.sendMail;
import org.IGNOU.ePortfolio.DAO.TestimonialDao;
import org.json.JSONObject;

/**
 *
 * @author Vinay
 */
public class TestimonialCreateAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private TestimonialDao dao = new TestimonialDao();
    private int testiReqId;
    /*Used in Academic*/
    //Student
    private String stdName, univRegNo, programe, enrollYear, passingYear, profileContacts, ContactNo, emailId, passportNo;
    //Testimonial
    private String purpose, genCharactor, attitude, RelationwithOthers, teamWork, personalIntegrity, reliability, calmness, competence, ambition, overallPerformance, qualificationAttained, rating, gradePercentage, comments;
    //Faculty
    private String facultyName, facultyDesignation, facultyOrg, facultyAddress, facultyPhoneNo, facultyEmailId;
    //Destination
    private String DestinationName, DestinationAddress;
    /*Extra Used in Industry*/
    private String empName, EmpId, designation, joiningDate, leavingDate, panNo, whyLeaving, reEmployment;
    //Mailing Variables
    private String DestinationEmailId, testiForEmail, testiReqCc, testiReqBcc, testiReqMessage;
    private String msg;
    private String saveEmail = getText("msg.testimonial.saveEmail"), update = getText("msg.testimonial.updateEmail"), export = getText("msg.testimonial.export"), draft = getText("msg.testimonial.draft");
    private Date todayDate = new Date();
    private String JSONTestimonial;
    private String report, ButtonName;
    private sendMail sendmailAction = new sendMail();
    private String mailSubject = getText("mail.Subject.Testimonial");
    private String CFile = ReadPropertyFile("Filepath");
    private InputStream fps;
    private String FileToCreate;
    private PrintHtmlPdf phpdf = new PrintHtmlPdf();

    public TestimonialCreateAction() {
    }

    public String PriviewTestimonial() throws Exception {
        JSONObject TestimonialJsonObject = new JSONObject();
        TestimonialJsonObject.put("stdName", stdName);
        TestimonialJsonObject.put("univRegNo", univRegNo);
        TestimonialJsonObject.put("programe", programe);
        TestimonialJsonObject.put("enrollYear", enrollYear);
        TestimonialJsonObject.put("passingYear", passingYear);
        TestimonialJsonObject.put("profileContacts", profileContacts);
        TestimonialJsonObject.put("ContactNo", ContactNo);
        TestimonialJsonObject.put("emailId", emailId);
        TestimonialJsonObject.put("passportNo", passportNo);
        TestimonialJsonObject.put("panNo", panNo);
        JSONTestimonial = TestimonialJsonObject.toString();
        return SUCCESS;
    }

    public String SaveTestimonialLetter() throws Exception {
        if ("Email".equals(getButtonName())) {
            String tempCc[] = testiReqCc.split(",");
            String tempBcc[] = testiReqBcc.split(",");
            try {
                dao.SaveTestimonialReport(testiReqId, new Date(), new Date(), Boolean.TRUE, report);
                sendmailAction.SendMailToCcBcc(user_id, DestinationEmailId, tempCc, tempBcc, mailSubject, report);
            } catch (Exception e) {
                msg = e.getMessage();
            }
            msg = saveEmail;
            return SUCCESS;
        }
        if ("Save".equals(getButtonName())) {
            dao.SaveTestimonialReport(testiReqId, new Date(), null, Boolean.FALSE, report);
            msg = draft;
            return SUCCESS;
        }
        if ("Export".equals(getButtonName())) {
            CFile = CFile + "/" + user_id + "/" + "UserTesti.pdf";
            report = report.replace("<br>", "<br/>");
            report = report.replaceAll("\"", "\\\"");
            FileToCreate = phpdf.HtmlToPdf(report, CFile);
            fps = new FileInputStream(new File(FileToCreate));

            msg = export;
        }
        return INPUT;
    }

    public String SendMailtoConcerned() throws Exception {
        String tempCc[] = testiReqCc.split(",");
        String tempBcc[] = testiReqBcc.split(",");
        try {
            dao.SaveTestimonialReport(testiReqId, new Date(), new Date(), Boolean.TRUE, testiReqMessage);
            sendmailAction.SendMailToCcBcc(user_id, testiForEmail, tempCc, tempBcc, mailSubject, testiReqMessage);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        msg = saveEmail;
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
     * @return the stdName
     */
    public String getStdName() {
        return stdName;
    }

    /**
     * @param stdName the stdName to set
     */
    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    /**
     * @return the univRegNo
     */
    public String getUnivRegNo() {
        return univRegNo;
    }

    /**
     * @param univRegNo the univRegNo to set
     */
    public void setUnivRegNo(String univRegNo) {
        this.univRegNo = univRegNo;
    }

    /**
     * @return the programe
     */
    public String getPrograme() {
        return programe;
    }

    /**
     * @param programe the programe to set
     */
    public void setPrograme(String programe) {
        this.programe = programe;
    }

    /**
     * @return the enrollYear
     */
    public String getEnrollYear() {
        return enrollYear;
    }

    /**
     * @param enrollYear the enrollYear to set
     */
    public void setEnrollYear(String enrollYear) {
        this.enrollYear = enrollYear;
    }

    /**
     * @return the passingYear
     */
    public String getPassingYear() {
        return passingYear;
    }

    /**
     * @param passingYear the passingYear to set
     */
    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    /**
     * @return the profileContacts
     */
    public String getProfileContacts() {
        return profileContacts;
    }

    /**
     * @param profileContacts the profileContacts to set
     */
    public void setProfileContacts(String profileContacts) {
        this.profileContacts = profileContacts;
    }

    /**
     * @return the ContactNo
     */
    public String getContactNo() {
        return ContactNo;
    }

    /**
     * @param ContactNo the ContactNo to set
     */
    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
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
     * @return the passportNo
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @param passportNo the passportNo to set
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return the purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose the purpose to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * @return the genCharactor
     */
    public String getGenCharactor() {
        return genCharactor;
    }

    /**
     * @param genCharactor the genCharactor to set
     */
    public void setGenCharactor(String genCharactor) {
        this.genCharactor = genCharactor;
    }

    /**
     * @return the attitude
     */
    public String getAttitude() {
        return attitude;
    }

    /**
     * @param attitude the attitude to set
     */
    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    /**
     * @return the RelationwithOthers
     */
    public String getRelationwithOthers() {
        return RelationwithOthers;
    }

    /**
     * @param RelationwithOthers the RelationwithOthers to set
     */
    public void setRelationwithOthers(String RelationwithOthers) {
        this.RelationwithOthers = RelationwithOthers;
    }

    /**
     * @return the teamWork
     */
    public String getTeamWork() {
        return teamWork;
    }

    /**
     * @param teamWork the teamWork to set
     */
    public void setTeamWork(String teamWork) {
        this.teamWork = teamWork;
    }

    /**
     * @return the personalIntegrity
     */
    public String getPersonalIntegrity() {
        return personalIntegrity;
    }

    /**
     * @param personalIntegrity the personalIntegrity to set
     */
    public void setPersonalIntegrity(String personalIntegrity) {
        this.personalIntegrity = personalIntegrity;
    }

    /**
     * @return the reliability
     */
    public String getReliability() {
        return reliability;
    }

    /**
     * @param reliability the reliability to set
     */
    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    /**
     * @return the calmness
     */
    public String getCalmness() {
        return calmness;
    }

    /**
     * @param calmness the calmness to set
     */
    public void setCalmness(String calmness) {
        this.calmness = calmness;
    }

    /**
     * @return the competence
     */
    public String getCompetence() {
        return competence;
    }

    /**
     * @param competence the competence to set
     */
    public void setCompetence(String competence) {
        this.competence = competence;
    }

    /**
     * @return the ambition
     */
    public String getAmbition() {
        return ambition;
    }

    /**
     * @param ambition the ambition to set
     */
    public void setAmbition(String ambition) {
        this.ambition = ambition;
    }

    /**
     * @return the overallPerformance
     */
    public String getOverallPerformance() {
        return overallPerformance;
    }

    /**
     * @param overallPerformance the overallPerformance to set
     */
    public void setOverallPerformance(String overallPerformance) {
        this.overallPerformance = overallPerformance;
    }

    /**
     * @return the qualificationAttained
     */
    public String getQualificationAttained() {
        return qualificationAttained;
    }

    /**
     * @param qualificationAttained the qualificationAttained to set
     */
    public void setQualificationAttained(String qualificationAttained) {
        this.qualificationAttained = qualificationAttained;
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * @return the gradePercentage
     */
    public String getGradePercentage() {
        return gradePercentage;
    }

    /**
     * @param gradePercentage the gradePercentage to set
     */
    public void setGradePercentage(String gradePercentage) {
        this.gradePercentage = gradePercentage;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the facultyName
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * @param facultyName the facultyName to set
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * @return the facultyDesignation
     */
    public String getFacultyDesignation() {
        return facultyDesignation;
    }

    /**
     * @param facultyDesignation the facultyDesignation to set
     */
    public void setFacultyDesignation(String facultyDesignation) {
        this.facultyDesignation = facultyDesignation;
    }

    /**
     * @return the facultyOrg
     */
    public String getFacultyOrg() {
        return facultyOrg;
    }

    /**
     * @param facultyOrg the facultyOrg to set
     */
    public void setFacultyOrg(String facultyOrg) {
        this.facultyOrg = facultyOrg;
    }

    /**
     * @return the facultyAddress
     */
    public String getFacultyAddress() {
        return facultyAddress;
    }

    /**
     * @param facultyAddress the facultyAddress to set
     */
    public void setFacultyAddress(String facultyAddress) {
        this.facultyAddress = facultyAddress;
    }

    /**
     * @return the facultyPhoneNo
     */
    public String getFacultyPhoneNo() {
        return facultyPhoneNo;
    }

    /**
     * @param facultyPhoneNo the facultyPhoneNo to set
     */
    public void setFacultyPhoneNo(String facultyPhoneNo) {
        this.facultyPhoneNo = facultyPhoneNo;
    }

    /**
     * @return the facultyEmailId
     */
    public String getFacultyEmailId() {
        return facultyEmailId;
    }

    /**
     * @param facultyEmailId the facultyEmailId to set
     */
    public void setFacultyEmailId(String facultyEmailId) {
        this.facultyEmailId = facultyEmailId;
    }

    /**
     * @return the DestinationName
     */
    public String getDestinationName() {
        return DestinationName;
    }

    /**
     * @param DestinationName the DestinationName to set
     */
    public void setDestinationName(String DestinationName) {
        this.DestinationName = DestinationName;
    }

    /**
     * @return the DestinationEmailId
     */
    public String getDestinationEmailId() {
        return DestinationEmailId;
    }

    /**
     * @param DestinationEmailId the DestinationEmailId to set
     */
    public void setDestinationEmailId(String DestinationEmailId) {
        this.DestinationEmailId = DestinationEmailId;
    }

    /**
     * @return the DestinationAddress
     */
    public String getDestinationAddress() {
        return DestinationAddress;
    }

    /**
     * @param DestinationAddress the DestinationAddress to set
     */
    public void setDestinationAddress(String DestinationAddress) {
        this.DestinationAddress = DestinationAddress;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return the EmpId
     */
    public String getEmpId() {
        return EmpId;
    }

    /**
     * @param EmpId the EmpId to set
     */
    public void setEmpId(String EmpId) {
        this.EmpId = EmpId;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the joiningDate
     */
    public String getJoiningDate() {
        return joiningDate;
    }

    /**
     * @param joiningDate the joiningDate to set
     */
    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    /**
     * @return the leavingDate
     */
    public String getLeavingDate() {
        return leavingDate;
    }

    /**
     * @param leavingDate the leavingDate to set
     */
    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    /**
     * @return the panNo
     */
    public String getPanNo() {
        return panNo;
    }

    /**
     * @param panNo the panNo to set
     */
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    /**
     * @return the whyLeaving
     */
    public String getWhyLeaving() {
        return whyLeaving;
    }

    /**
     * @param whyLeaving the whyLeaving to set
     */
    public void setWhyLeaving(String whyLeaving) {
        this.whyLeaving = whyLeaving;
    }

    /**
     * @return the reEmployment
     */
    public String getReEmployment() {
        return reEmployment;
    }

    /**
     * @param reEmployment the reEmployment to set
     */
    public void setReEmployment(String reEmployment) {
        this.reEmployment = reEmployment;
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
     * @return the todayDate
     */
    public Date getTodayDate() {
        return todayDate;
    }

    /**
     * @param todayDate the todayDate to set
     */
    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    /**
     * @return the JSONTestimonial
     */
    public String getJSONTestimonial() {
        return JSONTestimonial;
    }

    /**
     * @param JSONTestimonial the JSONTestimonial to set
     */
    public void setJSONTestimonial(String JSONTestimonial) {
        this.JSONTestimonial = JSONTestimonial;
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
     * @return the testiReqMessage
     */
    public String getTestiReqMessage() {
        return testiReqMessage;
    }

    /**
     * @param testiReqMessage the testiReqMessage to set
     */
    public void setTestiReqMessage(String testiReqMessage) {
        this.testiReqMessage = testiReqMessage;
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

    /**
     * @return the draft
     */
    public String getDraft() {
        return draft;
    }

    /**
     * @param draft the draft to set
     */
    public void setDraft(String draft) {
        this.draft = draft;
    }
}
