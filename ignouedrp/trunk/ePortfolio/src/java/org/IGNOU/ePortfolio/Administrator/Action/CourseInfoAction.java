/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.CourseDao;
import org.IGNOU.ePortfolio.Model.Course;
import org.apache.log4j.Logger;

/**
 *
 * @author vinay
 */
public class CourseInfoAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private CourseDao dao = new CourseDao();
    private List<Course> CourseList;

    public CourseInfoAction() {
    }

    public String MyCourses() throws Exception {
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
