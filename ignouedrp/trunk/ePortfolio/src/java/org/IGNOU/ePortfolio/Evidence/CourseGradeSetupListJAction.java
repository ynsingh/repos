/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Evidence;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EvidenceDao;
import org.IGNOU.ePortfolio.Model.Course;

/**
 *
 * @author vinay
 */
public class CourseGradeSetupListJAction extends ActionSupport {

    private static final long serialVersionUID = -2223948287805083119L;
    private String user_id = new UserSession().getUserInSession();
    private Map<String, String> courseL;
    private EvidenceDao eviDao = new EvidenceDao();
    private List<Course> courceList;
    private String CourseNotFound = getText("msg.CourseGradeSetupNotFound");

    @Override
    public String execute() throws Exception {
        courceList = eviDao.GradeSetUpCourceList(user_id);
        courseL = new HashMap<String, String>();
        if (courceList.isEmpty()) {
            courseL.put(null, getCourseNotFound());
        } else {
            for (int i = 0; i < courceList.size(); i++) {
                courseL.put(courceList.get(i).getCourseId()+"", courceList.get(i).getCourseName() + " (" + courceList.get(i).getCourseCode() + ")");
            }
        }
       // courseL.put("3", "Hello");
         return SUCCESS;
    }

    public String getJSON() throws Exception {
        return execute();
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

    /**
     * @return the courceList
     */
    public List<Course> getCourceList() {
        return courceList;
    }
}
