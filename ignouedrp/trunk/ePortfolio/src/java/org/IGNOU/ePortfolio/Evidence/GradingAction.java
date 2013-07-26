/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.FileUploadCommon;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EvidenceDao;
import org.IGNOU.ePortfolio.DAO.GradeTypeDao;
import org.IGNOU.ePortfolio.Model.Evidence;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;
import org.IGNOU.ePortfolio.Model.GradeTypeDetailsMaster;
import org.IGNOU.ePortfolio.Model.GradeTypeMaster;
import org.IGNOU.ePortfolio.Model.GradeValue;
import org.apache.commons.lang.StringUtils;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author IGNOU Team
 */
public class GradingAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private EvidenceDao dao = new EvidenceDao();
    private Integer evidenceId;
    private int submissionId;
    private String instructions;
    private String attachment;
    private Date subDate;
    private String userId;
    private Integer courseId;
    private Integer programmeId;
    private Integer instituteId;
    private Boolean submitted;
    private Boolean post;
    private Boolean saveDraft;
    private String gradesObtained;
    private String facultyComment;
    private String facultyAttachment;
    private File stuData, facData;
    private String stuDataFileName, facDataFileName;
    private GradeTypeMaster gtm = new GradeTypeMaster();
    private GradeTypeDao gtdDao = new GradeTypeDao();
    private EvidenceDao EviDao = new EvidenceDao();
    private List<Evidence> EviList;
    private List<EvidenceSubmission> ActivitiesSubmitedList;
    private List<Evidence> evList;
    private List<GradeTypeMaster> gtValList;
    private List<GradeValue> GVList;
    private List<GradeTypeDetailsMaster> gtdmList;
    private ArrayList<String> GObtained = new ArrayList<String>();
    private ArrayList<String> GradeLable = new ArrayList<String>();
    private ArrayList<String> GradeValueRange = new ArrayList<String>();
    private String[] splitValue1;
    private String evFilePath = ReadPropertyFile("Filepath");
    private String msg;
    private String recordNotFound = getText("recordNotFound");
    private String infoSaved = getText("msg.infoSaved");
    private String evTitle, facultyName, title, assAttach, instruction;
    private Date openDate, closeDate;

    public GradingAction() {
    }
    /*Activity Details for Submit Student Activity's Form*/

    public String ActivitiesSubmitedList() throws Exception {
        EviList = EviDao.EvidenceListByEvidenceId(evidenceId);
        if (EviList.isEmpty()) {
            msg = recordNotFound;
            return SUCCESS;
        } else {
            return SUCCESS;
        }
    }
    /*End*/

    public String UserActivitySubmitedList() throws Exception {
        ActivitiesSubmitedList = dao.EvidenceSubmissionListByEvidenceId(getEvidenceId());
        if (ActivitiesSubmitedList.isEmpty()) {
            msg = recordNotFound;
        } else {
            for (int j = 0; j < ActivitiesSubmitedList.size(); j++) {
                if (ActivitiesSubmitedList.get(j).getGradesObtained() != null) {
                    if (ActivitiesSubmitedList.get(j).getGradesObtained().length() > 3) {
                        GObtained.add(StringUtils.substringBefore(ActivitiesSubmitedList.get(j).getGradesObtained(), ":"));
                    } else {
                        GObtained.add(ActivitiesSubmitedList.get(j).getGradesObtained());
                    }
                } else {
                }
            }
            evTitle = ActivitiesSubmitedList.iterator().next().getEvidence().getEvTitle();
            facultyName = ActivitiesSubmitedList.iterator().next().getEvidence().getUser().getLname() + " " + ActivitiesSubmitedList.iterator().next().getEvidence().getUser().getFname();
            title = ActivitiesSubmitedList.iterator().next().getEvidence().getGradeValue().getGradeTypeDetailsMaster().getGradeTypeMaster().getTitle();
            openDate = ActivitiesSubmitedList.iterator().next().getEvidence().getOpenDate();
            closeDate = ActivitiesSubmitedList.iterator().next().getEvidence().getCloseDate();
            instruction = ActivitiesSubmitedList.iterator().next().getEvidence().getInstruction();
        }
        return SUCCESS;
    }

    public String ActivitiesEvaluateList() throws Exception {
        ActivitiesSubmitedList = dao.EvidenceSubmissionListBySubmissionId(getSubmissionId());
        GVList = gtdDao.GradeValueListByGradeTypeIdUserId(user_id, ActivitiesSubmitedList.iterator().next().getEvidence().getGradeValue().getGradeTypeDetailsMaster().getGtdId() /*getGtdmList().iterator().next().getGtdId()*/);
        splitValue1 = GVList.iterator().next().getGradeValue().split(" ");
        for (int i = 0; i < splitValue1.length; i++) {
            GradeLable.add(StringUtils.substringBefore(splitValue1[i], ":"));
            GradeValueRange.add(StringUtils.substringAfter(splitValue1[i], ":"));
        }

        return SUCCESS;
    }

    public String UpdateMarks() throws Exception {
        gtdDao.EvidenceSubmissionMarksUpdate(submissionId, gradesObtained, facultyComment, FacultyFile());
        msg = infoSaved;
        return SUCCESS;
    }

    public String FacultyFile() throws IOException {
        if (getFacData() != null) {
            String filepath = evFilePath + "/" + getUser_id().substring(0, 5) + "/";
            new FileUploadCommon().UploadFile(facData, facDataFileName, filepath);
            facultyAttachment = getFacDataFileName();
            return facultyAttachment;
        } else {

            facultyAttachment = "null";
            return facultyAttachment;
        }
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
     * @return the evidenceId
     */
    public Integer getEvidenceId() {
        return evidenceId;
    }

    /**
     * @param evidenceId the evidenceId to set
     */
    public void setEvidenceId(Integer evidenceId) {
        this.evidenceId = evidenceId;
    }

    /**
     * @return the ActivitiesSubmitedList
     */
    public List<EvidenceSubmission> getActivitiesSubmitedList() {
        return ActivitiesSubmitedList;
    }

    /**
     * @param ActivitiesSubmitedList the ActivitiesSubmitedList to set
     */
    public void setActivitiesSubmitedList(List<EvidenceSubmission> ActivitiesSubmitedList) {
        this.ActivitiesSubmitedList = ActivitiesSubmitedList;
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
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * @param instructions the instructions to set
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * @return the attachment
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * @param attachment the attachment to set
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * @return the subDate
     */
    public Date getSubDate() {
        return subDate;
    }

    /**
     * @param subDate the subDate to set
     */
    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the courseId
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the programmeId
     */
    public Integer getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @return the instituteId
     */
    public Integer getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    /**
     * @return the submitted
     */
    public Boolean getSubmitted() {
        return submitted;
    }

    /**
     * @param submitted the submitted to set
     */
    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    /**
     * @return the post
     */
    public Boolean getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Boolean post) {
        this.post = post;
    }

    /**
     * @return the saveDraft
     */
    public Boolean getSaveDraft() {
        return saveDraft;
    }

    /**
     * @param saveDraft the saveDraft to set
     */
    public void setSaveDraft(Boolean saveDraft) {
        this.saveDraft = saveDraft;
    }

    /**
     * @return the gradesObtained
     */
    public String getGradesObtained() {
        return gradesObtained;
    }

    /**
     * @param gradesObtained the gradesObtained to set
     */
    public void setGradesObtained(String gradesObtained) {
        this.gradesObtained = gradesObtained;
    }

    /**
     * @return the facultyComment
     */
    public String getFacultyComment() {
        return facultyComment;
    }

    /**
     * @param facultyComment the facultyComment to set
     */
    public void setFacultyComment(String facultyComment) {
        this.facultyComment = facultyComment;
    }

    /**
     * @return the facultyAttachment
     */
    public String getFacultyAttachment() {
        return facultyAttachment;
    }

    /**
     * @param facultyAttachment the facultyAttachment to set
     */
    public void setFacultyAttachment(String facultyAttachment) {
        this.facultyAttachment = facultyAttachment;
    }

    /**
     * @return the stuData
     */
    public File getStuData() {
        return stuData;
    }

    /**
     * @param stuData the stuData to set
     */
    public void setStuData(File stuData) {
        this.stuData = stuData;
    }

    /**
     * @return the stuDataFileName
     */
    public String getStuDataFileName() {
        return stuDataFileName;
    }

    /**
     * @param stuDataFileName the stuDataFileName to set
     */
    public void setStuDataFileName(String stuDataFileName) {
        this.stuDataFileName = stuDataFileName;
    }

    /**
     * @return the EviList
     */
    public List<Evidence> getEviList() {
        return EviList;
    }

    /**
     * @param EviList the EviList to set
     */
    public void setEviList(List<Evidence> EviList) {
        this.EviList = EviList;
    }

    /**
     * @return the EviDao
     */
    public EvidenceDao getEviDao() {
        return EviDao;
    }

    /**
     * @param EviDao the EviDao to set
     */
    public void setEviDao(EvidenceDao EviDao) {
        this.EviDao = EviDao;
    }

    /**
     * @return the gtm
     */
    public GradeTypeMaster getGtm() {
        return gtm;
    }

    /**
     * @param gtm the gtm to set
     */
    public void setGtm(GradeTypeMaster gtm) {
        this.gtm = gtm;
    }

    /**
     * @return the gtdDao
     */
    public GradeTypeDao getGtdDao() {
        return gtdDao;
    }

    /**
     * @param gtdDao the gtdDao to set
     */
    public void setGtdDao(GradeTypeDao gtdDao) {
        this.gtdDao = gtdDao;
    }

    /**
     * @return the gtValList
     */
    public List<GradeTypeMaster> getGtValList() {
        return gtValList;
    }

    /**
     * @param gtValList the gtValList to set
     */
    public void setGtValList(List<GradeTypeMaster> gtValList) {
        this.gtValList = gtValList;
    }

    /**
     * @return the GVList
     */
    public List<GradeValue> getGVList() {
        return GVList;
    }

    /**
     * @param GVList the GVList to set
     */
    public void setGVList(List<GradeValue> GVList) {
        this.GVList = GVList;
    }

    /**
     * @return the gtdmList
     */
    public List<GradeTypeDetailsMaster> getGtdmList() {
        return gtdmList;
    }

    /**
     * @param gtdmList the gtdmList to set
     */
    public void setGtdmList(List<GradeTypeDetailsMaster> gtdmList) {
        this.gtdmList = gtdmList;
    }

    /**
     * @return the splitValue1
     */
    public String[] getSplitValue1() {
        return splitValue1;
    }

    /**
     * @param splitValue1 the splitValue1 to set
     */
    public void setSplitValue1(String[] splitValue1) {
        this.splitValue1 = splitValue1;
    }

    /**
     * @return the facData
     */
    public File getFacData() {
        return facData;
    }

    /**
     * @param facData the facData to set
     */
    public void setFacData(File facData) {
        this.facData = facData;
    }

    /**
     * @return the facDataFileName
     */
    public String getFacDataFileName() {
        return facDataFileName;
    }

    /**
     * @param facDataFileName the facDataFileName to set
     */
    public void setFacDataFileName(String facDataFileName) {
        this.facDataFileName = facDataFileName;
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
     * @return the GObtained
     */
    public ArrayList<String> getGObtained() {
        return GObtained;
    }

    /**
     * @param GObtained the GObtained to set
     */
    public void setGObtained(ArrayList<String> GObtained) {
        this.GObtained = GObtained;
    }

    /**
     * @return the GradeLable
     */
    public ArrayList<String> getGradeLable() {
        return GradeLable;
    }

    /**
     * @param GradeLable the GradeLable to set
     */
    public void setGradeLable(ArrayList<String> GradeLable) {
        this.GradeLable = GradeLable;
    }

    /**
     * @return the GradeValueRange
     */
    public ArrayList<String> getGradeValueRange() {
        return GradeValueRange;
    }

    /**
     * @param GradeValueRange the GradeValueRange to set
     */
    public void setGradeValueRange(ArrayList<String> GradeValueRange) {
        this.GradeValueRange = GradeValueRange;
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

    /**
     * @return the evTitle
     */
    public String getEvTitle() {
        return evTitle;
    }

    /**
     * @param evTitle the evTitle to set
     */
    public void setEvTitle(String evTitle) {
        this.evTitle = evTitle;
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
     * @return the assAttach
     */
    public String getAssAttach() {
        return assAttach;
    }

    /**
     * @param assAttach the assAttach to set
     */
    public void setAssAttach(String assAttach) {
        this.assAttach = assAttach;
    }

    /**
     * @return the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * @param instruction the instruction to set
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
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
     * @return the evList
     */
    public List<Evidence> getEvList() {
        return evList;
    }

    /**
     * @param evList the evList to set
     */
    public void setEvList(List<Evidence> evList) {
        this.evList = evList;
    }
}
