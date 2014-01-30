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
import org.IGNOU.ePortfolio.DAO.GradeTypeDao;
import org.IGNOU.ePortfolio.Model.GradeTypeMaster;

/**
 *
 * @author vinay
 */
public class GradeMasterListAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = -2223948287805083119L;
    private Map<String, String> gradeL;
    private GradeTypeDao gradeDao = new GradeTypeDao();
    private List<GradeTypeMaster> gradeMasterList;
    private String GradeNotFound = getText("recordNotFound");

    @Override
    public String execute() throws Exception {
        gradeMasterList = gradeDao.GradeTypeMasterList();
        gradeL = new HashMap<String, String>();
        if (gradeMasterList.isEmpty()) {
            gradeL.put("NULL", getGradeNotFound());
        } else {
            for (int i = 0; i < gradeMasterList.size(); i++) {
                gradeL.put(gradeMasterList.get(i).getGtId() + "", gradeMasterList.get(i).getTitle());
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
    public List<GradeTypeMaster> getGradeMasterList() {
        return gradeMasterList;
    }

    /**
     * @return the gradeL
     */
    public Map<String, String> getGradeL() {
        return gradeL;
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
}
