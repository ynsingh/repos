/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Dropdown;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.InstituteDao;
import org.IGNOU.ePortfolio.Model.Programme;

/**
 *
 * @author vinay
 */
public class InstituteDeptProgrammeAction extends ActionSupport {

    private static final long serialVersionUID = -2223948287805083119L;
    private String user_id = new UserSession().getUserInSession();
    private Map<String, String> programmeL;
    private String instituteId, departmentId;
    private InstituteDao instDao = new InstituteDao();
    private List<Programme> PrograList;
    private String ProgrammeNotFound = getText("msg.programmeNotFound");

    public InstituteDeptProgrammeAction() {
    }

    @Override
    public String execute() throws Exception {
        PrograList = instDao.DeptProgrammeList(Integer.valueOf(departmentId), user_id);
        programmeL = new HashMap<String, String>();
        if (PrograList.isEmpty()) {
            programmeL.put("NULL", getProgrammeNotFound());
        } else {
            for (int j = 0; j < PrograList.size(); j++) {
                programmeL.put(PrograList.get(j).getProgrammeId() + "", PrograList.get(j).getProgrammeName());
            }
        }
        
        return SUCCESS;
    }

    public String getJSON() throws Exception {
        return execute();
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    /**
     * @return the instDao
     */
    public InstituteDao getInstDao() {
        return instDao;
    }

    /**
     * @param instDao the instDao to set
     */
    public void setInstDao(InstituteDao instDao) {
        this.instDao = instDao;
    }

    /**
     * @return the PrograList
     */
    public List<Programme> getPrograList() {
        return PrograList;
    }

    /**
     * @return the programmeL
     */
    public Map<String, String> getProgrammeL() {
        return programmeL;
    }

    /**
     * @return the ProgrammeNotFound
     */
    public String getProgrammeNotFound() {
        return ProgrammeNotFound;
    }

    /**
     * @param ProgrammeNotFound the ProgrammeNotFound to set
     */
    public void setProgrammeNotFound(String ProgrammeNotFound) {
        this.ProgrammeNotFound = ProgrammeNotFound;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @return the instituteId
     */
    public String getInstituteId() {
        return instituteId;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }
}
