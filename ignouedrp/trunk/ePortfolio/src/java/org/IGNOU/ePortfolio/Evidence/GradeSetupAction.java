/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ProgrammeDao;
import org.IGNOU.ePortfolio.DAO.GradeTypeDao;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.GradeTypeDetailsMaster;
import org.IGNOU.ePortfolio.Model.GradeTypeMaster;
import org.IGNOU.ePortfolio.Model.GradeValue;
import org.IGNOU.ePortfolio.Model.Institute;
import org.IGNOU.ePortfolio.Model.Programme;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 08-06-2012
 */
public class GradeSetupAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private GradeValue gv = new GradeValue();
    private GradeTypeDetailsMaster gtdm = new GradeTypeDetailsMaster();
    private GradeTypeMaster gtm = new GradeTypeMaster();
    private GradeTypeDao dao = new GradeTypeDao();
    private Integer instituteId;
    private Integer programmeId;
    private Integer courseId;
    private int gradeValId;
    private Integer gtdId;
    //private String gradeValue;
    private Date gradeDate;
    private List<GradeTypeMaster> GTList;
    private List<Institute> InsList;
    private List<Programme> ProgList;
    private List<Course> CourseListList;
    private ProgrammeDao pDao = new ProgrammeDao();
    private String[] gradeValue;
    private String[] gradeValue1;
    private String details;
    private String[] splitDetails;
    private String[] splitDetails2;
    private String str = new String();
    private String str1 = new String();
    private String Course, GradeType;

    public GradeSetupAction() {
    }

    public String EditGradeSetup() throws Exception {
        return SUCCESS;
    }

//    public String GradeValueUpdate() throws Exception {
//        //     CourseProInsList = pDao.CourseDetailByCourseId(courseId);
//        setSplitDetails(getDetails().split(","));
//        for (int i = 0; i < getSplitDetails().length; i++) {
//            setStr1(getStr1() + getSplitDetails()[i]);
//        }
//        setSplitDetails2(getStr1().split(" "));
//        for (int i = 0; i < getSplitDetails2().length; i++) {
//            if (getGradeValue1() == null) {
//                setStr(getStr() + (getSplitDetails2()[i] + getGradeValue()[i]) + " ");
//            } else {
//                setStr(getStr() + (getSplitDetails2()[i] + getGradeValue()[i]) + "-" + getGradeValue1()[i] + " ");
//            }
//        }
//        dao.GradeValueUpdate(gradeValId, instituteId, programmeId, courseId, user_id, gtdId, str, new Date());
//        return SUCCESS;
//    }
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
     * @return the gv
     */
    public GradeValue getGv() {
        return gv;
    }

    /**
     * @param gv the gv to set
     */
    public void setGv(GradeValue gv) {
        this.gv = gv;
    }

    /**
     * @return the gtdm
     */
    public GradeTypeDetailsMaster getGtdm() {
        return gtdm;
    }

    /**
     * @param gtdm the gtdm to set
     */
    public void setGtdm(GradeTypeDetailsMaster gtdm) {
        this.gtdm = gtdm;
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
     * @return the gtdId
     */
    public Integer getGtdId() {
        return gtdId;
    }

    /**
     * @param gtdId the gtdId to set
     */
    public void setGtdId(Integer gtdId) {
        this.gtdId = gtdId;
    }

    /**
     * @return the gradeDate
     */
    public Date getGradeDate() {
        return gradeDate;
    }

    /**
     * @param gradeDate the gradeDate to set
     */
    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    /**
     * @return the GradeTypeMasterListByGradeTypeId
     */
    public List<GradeTypeMaster> getGTList() {
        return GTList;
    }

    /**
     * @param GradeTypeMasterListByGradeTypeId the GradeTypeMasterListByGradeTypeId to set
     */
    public void setGTList(List<GradeTypeMaster> GTList) {
        this.GTList = GTList;
    }

    /**
     * @return the InsList
     */
    public List<Institute> getInsList() {
        return InsList;
    }

    /**
     * @param InsList the InsList to set
     */
    public void setInsList(List<Institute> InsList) {
        this.InsList = InsList;
    }

    /**
     * @return the ProgList
     */
    public List<Programme> getProgList() {
        return ProgList;
    }

    /**
     * @param ProgList the ProgList to set
     */
    public void setProgList(List<Programme> ProgList) {
        this.ProgList = ProgList;
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
     * @return the pDao
     */
    public ProgrammeDao getpDao() {
        return pDao;
    }

    /**
     * @param pDao the pDao to set
     */
    public void setpDao(ProgrammeDao pDao) {
        this.pDao = pDao;
    }

    /**
     * @return the gradeValue
     */
    public String[] getGradeValue() {
        return gradeValue;
    }

    /**
     * @param gradeValue the gradeValue to set
     */
    public void setGradeValue(String[] gradeValue) {
        this.gradeValue = gradeValue;
    }

    /**
     * @return the gradeValue1
     */
    public String[] getGradeValue1() {
        return gradeValue1;
    }

    /**
     * @param gradeValue1 the gradeValue1 to set
     */
    public void setGradeValue1(String[] gradeValue1) {
        this.gradeValue1 = gradeValue1;
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
     * @return the str1
     */
    public String getStr1() {
        return str1;
    }

    /**
     * @param str1 the str1 to set
     */
    public void setStr1(String str1) {
        this.str1 = str1;
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

    /**
     * @return the GradeType
     */
    public String getGradeType() {
        return GradeType;
    }

    /**
     * @param GradeType the GradeType to set
     */
    public void setGradeType(String GradeType) {
        this.GradeType = GradeType;
    }
}
