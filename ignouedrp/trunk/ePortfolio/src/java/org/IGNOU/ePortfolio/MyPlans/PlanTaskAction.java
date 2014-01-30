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
package org.IGNOU.ePortfolio.MyPlans;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.PlanTaskDAO;
import org.IGNOU.ePortfolio.Model.UserPlan;
import org.IGNOU.ePortfolio.Model.UserPlanTask;

/**
 *
 * @author IGNOU Team
 * @version 2 Last Modified on 12-07-2012 by IGNOU Team
 */
public class PlanTaskAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserPlanTask myplantasklist;
    private PlanTaskDAO dao = new PlanTaskDAO();
    private List<UserPlanTask> tasklist;
    private List<UserPlanTask> EditTask;
    private UserPlan myplanlist;
    private List<UserPlan> planlist;
    private long planId;
    private long taskId;
    private String TTitle;
    private String TDescription;
    private String TDate;
    private Integer status;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    @Override
    public String execute() throws Exception {
        setTasklist(getDao().UserPlanTaskListByPlanId(planId));
        return SUCCESS;
    }

    /**
     * Added on 24 Aug, 2011
     */
    public String EditTask() {
        setEditTask(getDao().UserPlanTaskEdit(taskId));
        return SUCCESS;
    }

    /**
     * Calculating System Current Date End
     */
    public String UpdateTask() {
        getDao().UserPlanTaskUpdate(taskId, TTitle, TDescription, new Date().toString(), status);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteTask() {
        getDao().UserPlanTaskDelete(taskId);
        msg = infoDeleted;
        return SUCCESS;
    }

    /**
     * @return the myplantasklist
     */
    public UserPlanTask getMyplantasklist() {
        return myplantasklist;
    }

    /**
     * @param myplantasklist the myplantasklist to set
     */
    public void setMyplantasklist(UserPlanTask myplantasklist) {
        this.myplantasklist = myplantasklist;
    }

    /**
     * @return the dao
     */
    public PlanTaskDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(PlanTaskDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the tasklist
     */
    public List<UserPlanTask> getTasklist() {
        return tasklist;
    }

    /**
     * @param tasklist the tasklist to set
     */
    public void setTasklist(List<UserPlanTask> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * @return the EditTask
     */
    public List<UserPlanTask> getEditTask() {
        return EditTask;
    }

    /**
     * @param EditTask the EditTask to set
     */
    public void setEditTask(List<UserPlanTask> EditTask) {
        this.EditTask = EditTask;
    }

    /**
     * @return the myplanlist
     */
    public UserPlan getMyplanlist() {
        return myplanlist;
    }

    /**
     * @param myplanlist the myplanlist to set
     */
    public void setMyplanlist(UserPlan myplanlist) {
        this.myplanlist = myplanlist;
    }

    /**
     * @return the planlist
     */
    public List<UserPlan> getPlanlist() {
        return planlist;
    }

    /**
     * @param planlist the planlist to set
     */
    public void setPlanlist(List<UserPlan> planlist) {
        this.planlist = planlist;
    }

    /**
     * @return the planId
     */
    public long getPlanId() {
        return planId;
    }

    /**
     * @param planId the planId to set
     */
    public void setPlanId(long planId) {
        this.planId = planId;
    }

    /**
     * @return the taskId
     */
    public long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the TTitle
     */
    public String getTTitle() {
        return TTitle;
    }

    /**
     * @param TTitle the TTitle to set
     */
    public void setTTitle(String TTitle) {
        this.TTitle = TTitle;
    }

    /**
     * @return the TDescription
     */
    public String getTDescription() {
        return TDescription;
    }

    /**
     * @param TDescription the TDescription to set
     */
    public void setTDescription(String TDescription) {
        this.TDescription = TDescription;
    }

    /**
     * @return the TDate
     */
    public String getTDate() {
        return TDate;
    }

    /**
     * @param TDate the TDate to set
     */
    public void setTDate(String TDate) {
        this.TDate = TDate;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * @return the infoDeleted
     */
    public String getInfoDeleted() {
        return infoDeleted;
    }

    /**
     * @param infoDeleted the infoDeleted to set
     */
    public void setInfoDeleted(String infoDeleted) {
        this.infoDeleted = infoDeleted;
    }

    /**
     * @return the infoUpdated
     */
    public String getInfoUpdated() {
        return infoUpdated;
    }

    /**
     * @param infoUpdated the infoUpdated to set
     */
    public void setInfoUpdated(String infoUpdated) {
        this.infoUpdated = infoUpdated;
    }
}