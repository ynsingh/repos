/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.MyWorkspace
;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.MyWorkspace.Dao.ProjectDao;
import org.IGNOU.ePortfolio.MyWorkspace.Model.Projects;

/**  
 * @author Vinay
 * @version 2
 * @since 20-Oct-2011
 * Modified by Vinay on 02-Nov-2011
 */
public class ProjectInfoAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private String user_id = new UserSession().getUserInSession();
    private ProjectDao dao = new ProjectDao();
    private Projects pro = new Projects();
    private long projectId;
    private String userId;
    private String proName;
    private Integer teamSize;
    private String role;
    private String agency;
    private Long budget;
    private String proUrl;
    private String startDate;
    private String endDate;
    private String description;
    private List<Projects> ProList = null;

    public ProjectInfoAction() {
    }

    public String ShowProjectInfo() throws Exception {
        ProList = getDao().ShowProjectInfo(user_id);
        if (ProList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditProjectInfo() throws Exception {
        ProList = dao.EditProjectInfo(projectId);
        return SUCCESS;
    }

    public String UpdateProjectInfo() throws Exception {
        dao.UpdateProjectInfo(projectId, userId, proName, teamSize, role, proUrl, startDate, endDate, description, agency, budget);
        return SUCCESS;
    }

    public String DeleteProjectInfo() throws Exception {
        dao.DeleteProjectInfo(projectId);
        return SUCCESS;
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
     * @return the dao
     */
    public ProjectDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ProjectDao dao) {
        this.dao = dao;
    }

    /**
     * @return the pro
     */
    public Projects getPro() {
        return pro;
    }

    /**
     * @param pro the pro to set
     */
    public void setPro(Projects pro) {
        this.pro = pro;
    }

    /**
     * @return the projectId
     */
    public long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the proName
     */
    public String getProName() {
        return proName;
    }

    /**
     * @param proName the proName to set
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * @return the teamSize
     */
    public Integer getTeamSize() {
        return teamSize;
    }

    /**
     * @param teamSize the teamSize to set
     */
    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the proUrl
     */
    public String getProUrl() {
        return proUrl;
    }

    /**
     * @param proUrl the proUrl to set
     */
    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;

    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the ProList
     */
    public List<Projects> getProList() {
        return ProList;
    }

    /**
     * @param ProList the ProList to set
     */
    public void setProList(List<Projects> ProList) {
        this.ProList = ProList;
    }

    /**
     * @return the agency
     */
    public String getAgency() {
        return agency;
    }

    /**
     * @param agency the agency to set
     */
    public void setAgency(String agency) {
        this.agency = agency;
    }

    /**
     * @return the budget
     */
    public Long getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(Long budget) {
        this.budget = budget;
    }
}
