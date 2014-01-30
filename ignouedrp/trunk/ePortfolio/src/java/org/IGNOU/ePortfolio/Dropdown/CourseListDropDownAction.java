/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Dropdown;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.CourseDao;
import org.IGNOU.ePortfolio.Model.Course;
import org.apache.log4j.Logger;

/**
 *
 * @author vinay
 */
public class CourseListDropDownAction extends ActionSupport implements Serializable  {

    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private static final long serialVersionUID = 1L;
    private Map<String, String> courseL;
    private String programmeId;
    private CourseDao instDao = new CourseDao();
    private List<Course> CourseList;
    private String CourseNotFound = getText("msg.courseNotFound");

    @Override
    public String execute() throws Exception {
        CourseList = instDao.CourseListByUserId(user_id);
        courseL = new HashMap<String, String>();
        if (CourseList.isEmpty()) {
            courseL.put("NULL", getCourseNotFound());
        } else {
            for (int i = 0; i < CourseList.size(); i++) {
                courseL.put(CourseList.get(i).getCourseId() + "", CourseList.get(i).getCourseName());
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
