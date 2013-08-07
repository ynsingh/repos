/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.CourseDao;
import org.IGNOU.ePortfolio.DAO.ProgrammeDao;
import org.IGNOU.ePortfolio.DAO.GradeTypeDao;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.GradeTypeDetailsMaster;
import org.IGNOU.ePortfolio.Model.GradeTypeMaster;
import org.IGNOU.ePortfolio.Model.GradeValue;

/**
 *
 * @author IGNOU Team
 */
public class GradeTypeAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private GradeTypeDao dao = new GradeTypeDao();
    /*Grade Type*/
    private int gtId;
    private String title;
    private String description;
    private Date createDate;
    private List<GradeTypeMaster> GTList;
    private int gtdId;
    private GradeTypeMaster gradeTypeMaster;
    private String details;
    private List<GradeTypeDetailsMaster> GTDList;
    private String[] splitDetails;
    private String[] splitDetails2;
    private String str = new String();
    /*Check Value*/
    private List<GradeValue> gvList;
    /*Institute, Programme, Course     */
    private Integer instituteId;
    private String instituteName;
    private int programmeId;
    private int courseId;
    private List<Course> CourseListList;
    private CourseDao cDao = new CourseDao();
    private int gradeValId;
    private String msg, formNotfilled = getText("msg.formNotFilled");
    private String Course;

    public GradeTypeAction() {
    }

    public String GradeType() throws Exception {
        setGTList(getDao().GradeTypeMasterList());
        return SUCCESS;
    }

    public String GradeTypeDetails() throws Exception {
        CourseListList = cDao.CourseListByCourseId(courseId);
        if (CourseListList.isEmpty()) {
            msg = formNotfilled;
            return INPUT;
        } else {
            setGTDList(getDao().GradeTypeDetailsMasterByGradeTypeId(getGtId()));
            setDetails(getGTDList().iterator().next().getDetails());
            setSplitDetails(getDetails().split(","));
            for (int i = 0; i < getSplitDetails().length; i++) {
                setStr(getStr() + getSplitDetails()[i]);
            }
            setSplitDetails2(getStr().split(": "));
            return SUCCESS;
        }
    }

    public String GradeTypeEditDetails() throws Exception {
        GTDList = getDao().GradeTypeDetailsMasterByGradeTypeId(getGtId());
        details = getGTDList().iterator().next().getDetails();
        splitDetails = getDetails().split(",");
        for (int i = 0; i < getSplitDetails().length; i++) {
            str = getStr() + getSplitDetails()[i];
        }
        setSplitDetails2(getStr().split(": "));
        return SUCCESS;
    }

    /**
     * @return the dao
     */
    public GradeTypeDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(GradeTypeDao dao) {
        this.dao = dao;
    }

    /**
     * @return the gtId
     */
    public int getGtId() {
        return gtId;
    }

    /**
     * @param gtId the gtId to set
     */
    public void setGtId(int gtId) {
        this.gtId = gtId;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the GradeTypeMasterListByGradeTypeId
     */
    public List<GradeTypeMaster> getGTList() {
        return GTList;
    }

    /**
     * @param GradeTypeMasterListByGradeTypeId the
     * GradeTypeMasterListByGradeTypeId to set
     */
    public void setGTList(List<GradeTypeMaster> GTList) {
        this.GTList = GTList;
    }

    /**
     * @return the gtdId
     */
    public int getGtdId() {
        return gtdId;
    }

    /**
     * @param gtdId the gtdId to set
     */
    public void setGtdId(int gtdId) {
        this.gtdId = gtdId;
    }

    /**
     * @return the gradeTypeMaster
     */
    public GradeTypeMaster getGradeTypeMaster() {
        return gradeTypeMaster;
    }

    /**
     * @param gradeTypeMaster the gradeTypeMaster to set
     */
    public void setGradeTypeMaster(GradeTypeMaster gradeTypeMaster) {
        this.gradeTypeMaster = gradeTypeMaster;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return the GTDList
     */
    public List<GradeTypeDetailsMaster> getGTDList() {
        return GTDList;
    }

    /**
     * @param GTDList the GTDList to set
     */
    public void setGTDList(List<GradeTypeDetailsMaster> GTDList) {
        this.GTDList = GTDList;
    }

    /**
     * @return the splitDetails
     */
    public String[] getSplitDetails() {
        return splitDetails;
    }

    /**
     * @param splitDetails the splitDetails to set
     */
    public void setSplitDetails(String[] splitDetails) {
        this.splitDetails = splitDetails;
    }

    /**
     * @return the splitDetails2
     */
    public String[] getSplitDetails2() {
        return splitDetails2;
    }

    /**
     * @param splitDetails2 the splitDetails2 to set
     */
    public void setSplitDetails2(String[] splitDetails2) {
        this.splitDetails2 = splitDetails2;
    }

    /**
     * @return the str
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) {
        this.str = str;
    }

    /**
     * @return the gvList
     */
    public List<GradeValue> getGvList() {
        return gvList;
    }

    /**
     * @param gvList the gvList to set
     */
    public void setGvList(List<GradeValue> gvList) {
        this.gvList = gvList;
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
     * @return the instituteName
     */
    public String getInstituteName() {
        return instituteName;
    }

    /**
     * @param instituteName the instituteName to set
     */
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    /**
     * @return the programmeId
     */
    public int getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(int programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @return the courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the CourseListList
     */
    public List<Course> getCourseListList() {
        return CourseListList;
    }

    /**
     * @param CourseListList the CourseListList to set
     */
    public void setCourseListList(List<Course> CourseListList) {
        this.CourseListList = CourseListList;
    }

    /**
     * @return the gradeValId
     */
    public int getGradeValId() {
        return gradeValId;
    }

    /**
     * @param gradeValId the gradeValId to set
     */
    public void setGradeValId(int gradeValId) {
        this.gradeValId = gradeValId;
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
     * @return the formNotfilled
     */
    public String getFormNotfilled() {
        return formNotfilled;
    }

    /**
     * @param formNotfilled the formNotfilled to set
     */
    public void setFormNotfilled(String formNotfilled) {
        this.formNotfilled = formNotfilled;
    }

    /**
     * @return the Course
     */
    public String getCourse() {
        return Course;
    }

    /**
     * @param Course the Course to set
     */
    public void setCourse(String Course) {
        this.Course = Course;
    }
}
