/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Dropdown;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.DAO.InstituteDao;
import org.IGNOU.ePortfolio.Model.Course;

/**
 *
 * @author vinay
 */
public class CourseAction extends ActionSupport {

    private static final long serialVersionUID = -2223948287805083119L;
    private Map<String, String> courseL;
    private String programmeId;
    private InstituteDao instDao = new InstituteDao();
    private List<Course> CourseList;
    private String CourseNotFound = getText("msg.courseNotFound");

    @Override
    public String execute() throws Exception {
        CourseList = instDao.CourseListByProgrammeId(Integer.valueOf(programmeId));
        courseL = new HashMap<String, String>();
        if (CourseList.isEmpty()) {
            courseL.put("NULL", getCourseNotFound());
        } else {
            for (int i = 0; i < CourseList.size(); i++) {
                courseL.put(CourseList.get(i).getCourseId()+ "", CourseList.get(i).getCourseName());
            }
        }
        return SUCCESS;
    }

    public String getJSON() throws Exception {
        return execute();
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @return the CourseListByProgrammeId
     */
    public List<Course> getCourseList() {
        return CourseList;
    }

    /**
     * @return the CourseNotFound
     */
    public String getCourseNotFound() {
        return CourseNotFound;
    }

    /**
     * @param CourseNotFound the CourseNotFound to set
     */
    public void setCourseNotFound(String CourseNotFound) {
        this.CourseNotFound = CourseNotFound;
    }

    /**
     * @return the courseL
     */
    public Map<String, String> getCourseL() {
        return courseL;
    }
}
