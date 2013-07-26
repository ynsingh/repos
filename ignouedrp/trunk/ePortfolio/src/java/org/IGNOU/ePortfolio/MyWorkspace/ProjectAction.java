/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 
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
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ProjectDao;
import org.IGNOU.ePortfolio.Model.Projects;

/**
 *
 * @author IGNOU Team
 * @version 1
 */
public class ProjectAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private ProjectDao dao = new ProjectDao();
    private Projects p = new Projects();
    private String user_id = new UserSession().getUserInSession();
    private String proName;
    private Integer teamSize;
    private String role;
    private String proUrl;
    private String startDate;
    private String endDate;
    private String description;
    private String agency;
    private Long budget;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    @Override
    public String execute() throws Exception {
        dao.ProjectsSave(p);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        p.setUserId(user_id);
        return getP();
    }

    /**
     * @return the p
     */
    public Projects getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Projects p) {
        this.p = p;
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

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }
}