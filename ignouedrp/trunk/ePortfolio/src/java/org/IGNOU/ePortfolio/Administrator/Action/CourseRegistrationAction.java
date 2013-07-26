/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import org.IGNOU.ePortfolio.DAO.ProgrammeCourseDao;

/**
 *
 * @author vinay
 */
public class CourseRegistrationAction extends ActionSupport {

    private ProgrammeCourseDao dao = new ProgrammeCourseDao();
    private int programmeId;
    private String courseCode;
    private String courseName, msg, Saved = getText("msg.infoSaved");

    public CourseRegistrationAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.CourseSave(programmeId, courseCode, courseName);
        msg = Saved;
        return SUCCESS;
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
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
     * @return the Saved
     */
    public String getSaved() {
        return Saved;
    }

    /**
     * @param Saved the Saved to set
     */
    public void setSaved(String Saved) {
        this.Saved = Saved;
    }
}
