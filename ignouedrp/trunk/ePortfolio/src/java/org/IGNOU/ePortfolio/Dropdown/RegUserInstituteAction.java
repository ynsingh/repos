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
import org.IGNOU.ePortfolio.DAO.InstituteDAO;
import org.IGNOU.ePortfolio.Model.Institute;
import org.apache.log4j.Logger;

/**
 *
 * @author vinay
 */
public class RegUserInstituteAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = -2223948287805083119L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private Map<String, String> univList = null;
    private InstituteDAO instDao = new InstituteDAO();
    private List<Institute> InstList;

    @Override
    public String execute() {
        InstList = instDao.InstituteListByUserId(user_id);
        univList = new HashMap<String, String>();
        for (int i = 0; i < getInstList().size(); i++) {
            univList.put("" + InstList.get(i).getInstituteId(), InstList.get(i).getInstituteName());
        }
        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    /**
     * @return the instDao
     */
    public InstituteDAO getInstDao() {
        return instDao;
    }

    /**
     * @param instDao the instDao to set
     */
    public void setInstDao(InstituteDAO instDao) {
        this.instDao = instDao;
    }

    /**
     * @return the InstList
     */
    public List<Institute> getInstList() {
        return InstList;
    }

    /**
     * @return the univList
     */
    public Map<String, String> getUnivList() {
        return univList;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }
}
