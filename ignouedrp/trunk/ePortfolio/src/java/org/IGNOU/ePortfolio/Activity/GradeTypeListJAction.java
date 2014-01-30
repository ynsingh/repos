/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Activity;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.GradeTypeDao;
import org.IGNOU.ePortfolio.Model.GradeValue;
import org.apache.log4j.Logger;

/**
 *
 * @author vinay
 */
public class GradeTypeListJAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = -2223948287805083119L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private Map<Integer, String> gradeMasterL;
    private GradeTypeDao gradeDao = new GradeTypeDao();
    private List<GradeValue> gradeMasterList;
    private String GradeNotFound = getText("recordNotFound");
    private String coursesId;

    @Override
    public String execute() throws Exception {
        gradeMasterList = gradeDao.GradeValueJsonListByCourseIdUserId(Integer.valueOf(coursesId), user_id);
        gradeMasterL = new HashMap<Integer, String>();
        if (gradeMasterList.isEmpty()) {
            gradeMasterL.put(null, getGradeNotFound());
        } else {
            for (int i = 0; i < gradeMasterList.size(); i++) {
                gradeMasterL.put(gradeMasterList.get(i).getGradeValId(), gradeMasterList.get(i).getGradeTypeDetailsMaster().getGradeTypeMaster().getTitle());
            }
        }
        return SUCCESS;
    }

    public String getJSON() throws Exception {
        return execute();
    }

    /**
     * @return the gradeMasterList
     */
    public List<GradeValue> getGradeMasterList() {
        return gradeMasterList;
    }

    /**
     * @return the gradeMasterL
     */
    public Map<Integer, String> getGradeMasterL() {
        return gradeMasterL;
    }

    /**
     * @return the GradeNotFound
     */
    public String getGradeNotFound() {
        return GradeNotFound;
    }

    /**
     * @param GradeNotFound the GradeNotFound to set
     */
    public void setGradeNotFound(String GradeNotFound) {
        this.GradeNotFound = GradeNotFound;
    }

    /**
     * @return the courseId
     */
    public String getCoursesId() {
        return coursesId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCoursesId(String coursesId) {
        this.coursesId = coursesId;
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
}
