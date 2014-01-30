/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.Date;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ProgrammeDao;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 07-11-2012
 */
public class ProgrammeRegsitrationAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ProgrammeDao dao = new ProgrammeDao();
    private String departmentId, instituteId;
    private String programmeName;
    private String programmeCode;
    private Integer duration;
    private String overview;

    public ProgrammeRegsitrationAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.ProgrammeSave(Integer.valueOf(departmentId), Integer.valueOf(instituteId), programmeName, programmeCode, duration, overview, user_id, new Date());
        return SUCCESS;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the instituteId
     */
    public String getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    /**
     * @return the programmeName
     */
    public String getProgrammeName() {
        return programmeName;
    }

    /**
     * @param programmeName the programmeName to set
     */
    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    /**
     * @return the programmeCode
     */
    public String getProgrammeCode() {
        return programmeCode;
    }

    /**
     * @param programmeCode the programmeCode to set
     */
    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    /**
     * @return the duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * @return the overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview the overview to set
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }
}
