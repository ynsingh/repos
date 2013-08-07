/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.CourseDao;
import org.IGNOU.ePortfolio.Model.Course;

/**
 *
 * @author vinay
 */
public class CourseInfoAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private CourseDao dao = new CourseDao();
    private List<Course> CourseList;

    public CourseInfoAction() {
    }

    public String MyCoursesforFaculty() throws Exception {
        CourseList = dao.CourseListByUserId(user_id);
        return SUCCESS;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @return the CourseList
     */
    public List<Course> getCourseList() {
        return CourseList;
    }

    /**
     * @param CourseList the CourseList to set
     */
    public void setCourseList(List<Course> CourseList) {
        this.CourseList = CourseList;
    }
}
