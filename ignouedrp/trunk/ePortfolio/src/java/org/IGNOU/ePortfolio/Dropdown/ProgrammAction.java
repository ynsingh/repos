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
import org.IGNOU.ePortfolio.Model.Programme;

/**
 *
 * @author Amit
 */
public class ProgrammAction extends ActionSupport {

    private static final long serialVersionUID = -2223948287805083119L;
    private Map<String, String> programmeL;
    private String instituteId;
    private InstituteDao instDao = new InstituteDao();
    private List<Programme> PrograList;
    private String ProgrammeNotFound = getText("msg.programmeNotFound");

    @Override
    public String execute() {
        PrograList = instDao.ProgrammeList(Integer.valueOf(instituteId));
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

    public String getJSON() {
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
}
