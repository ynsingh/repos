/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ProgrammeCourseDao;
import org.IGNOU.ePortfolio.DAO.GradeTypeDao;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.GradeValue;

/**
 *
 * @author IGNOU Team
 */
public class GradeValueAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private GradeValue gv = new GradeValue();
    private GradeTypeDao dao = new GradeTypeDao();
    private Integer gtdId;
    private Integer instituteId;
    private Integer programmeId;
    private Integer courseId;
    private String[] gradeValue;
    private String[] gradeValue1;
    private String details;
    private String[] splitDetails;
    private String[] splitDetails2;
    private String str = new String();
    private String str1 = new String();
    private List<Course> CourseProInsList;
    private ProgrammeCourseDao pDao = new ProgrammeCourseDao();
    private List<GradeValue> GradeSetupList;
    private int gradeValId;
    private String msg;
    private String infoSaved = getText("msg.infoSaved"), recordNotFound = getText("recordNotFound");

    public GradeValueAction() {
    }

    public String AddGradeValue() throws Exception {
        setSplitDetails(getDetails().split(","));
        for (int i = 0; i < getSplitDetails().length; i++) {
            setStr1(getStr1() + getSplitDetails()[i]);
        }
        setSplitDetails2(getStr1().split(" "));
        for (int i = 0; i < getSplitDetails2().length; i++) {
            if (gradeValue1 == null) {
                setStr(getStr() + (getSplitDetails2()[i] + getGradeValue()[i]) + " ");
            } else {
                setStr(getStr() + (getSplitDetails2()[i] + getGradeValue()[i]) + "-" + getGradeValue1()[i] + " ");
            }
        }
        getDao().GradeValueSave(user_id, gtdId, str, getCourseId());
        msg = infoSaved;
        return SUCCESS;
    }

    public String UpdateGradeSetupValue() throws Exception {
        setCourseProInsList(pDao.CourseDetailByCourseId(courseId));
        setSplitDetails(getDetails().split(","));
        for (int i = 0; i < getSplitDetails().length; i++) {
            setStr1(getStr1() + getSplitDetails()[i]);
        }
        setSplitDetails2(getStr1().split(" "));
        for (int i = 0; i < getSplitDetails2().length; i++) {
            if (gradeValue1 == null) {
                setStr(getStr() + (getSplitDetails2()[i] + getGradeValue()[i]) + " ");
            } else {
                setStr(getStr() + (getSplitDetails2()[i] + getGradeValue()[i]) + "-" + getGradeValue1()[i] + " ");
            }
        }
        dao.GradeValueUpdate(gradeValId, courseId, user_id, gtdId, str, new Date());
        return SUCCESS;
    }

    public String GetGradeSetupInfo() throws Exception {
        GradeSetupList = dao.GradeValueListByUserId(user_id);
        if (GradeSetupList.isEmpty()) {
            msg = recordNotFound;
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
     * @return the GradeSetupList
     */
    public List<GradeValue> getGradeSetupList() {
        return GradeSetupList;
    }

    /**
     * @param GradeSetupList the GradeSetupList to set
     */
    public void setGradeSetupList(List<GradeValue> GradeSetupList) {
        this.GradeSetupList = GradeSetupList;
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
     * @return the CourseProInsList
     */
    public List<Course> getCourseProInsList() {
        return CourseProInsList;
    }

    /**
     * @param CourseProInsList the CourseProInsList to set
     */
    public void setCourseProInsList(List<Course> CourseProInsList) {
        this.CourseProInsList = CourseProInsList;
    }
}
