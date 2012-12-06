/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Dropdown;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.InstituteDao;
import org.IGNOU.ePortfolio.Model.Programme;

/**
 *
 * @author vinay
 */
public class ProgrammeInfoAction extends ActionSupport {

    private static long serialVersionUID = -2223948287805083119L;
    private int programmeId;
    private InstituteDao instDao = new InstituteDao();
    private List<Programme> PrograList;
    private String ProgrammeNotFound = getText("msg.programmeNotFound");

    public ProgrammeInfoAction() {
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String execute() throws Exception {
        PrograList = instDao.ProgrammeDetailsList(getProgrammeId());
        return SUCCESS;
    }

    public String getJSON() throws Exception {
        return execute();
    }

    /**
     * @return the programmeId
     */
    public int getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(int programmeId) {
        this.programmeId = programmeId;
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
