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

package org.IGNOU.ePortfolio.MyPlans;

import org.IGNOU.ePortfolio.MyPlans.Dao.PlanTaskDAO;
import org.IGNOU.ePortfolio.MyPlans.Model.MyPlanList;
import org.IGNOU.ePortfolio.MyPlans.Model.PlanTaskList;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Vinay
 */
public class PlanTaskAction extends ActionSupport {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    private PlanTaskList myplantasklist;
    private PlanTaskDAO dao = new PlanTaskDAO();
    private List<PlanTaskList> tasklist;
    private List<PlanTaskList> EditTask;
    private MyPlanList myplanlist;
    private List<MyPlanList> planlist;
    private Long plan_id;
    private Long task_id;
    private String t_title;
    private String t_description;
    private int status;
    private String p_title;
    private String p_description;

    @Override
    public String execute() throws Exception {
       // planlist=dao.PlanTasklist(plan_id);
        tasklist=dao.TaskList(plan_id);
        return SUCCESS;
    }

    /** Added on 24 Aug, 2011*/
    public String EditTask() {
        setEditTask(getDao().EditTaskList(getTask_id()));
        return SUCCESS;
    }
    /**
     * Calculating System Current Date Start
     */
    private Calendar c_Date = Calendar.getInstance();
    private SimpleDateFormat f = new SimpleDateFormat("dd MMM, yyyy");
    private String t_date = f.format(c_Date.getTime());

    /**
     *  Calculating System Current Date End
     */
    public String UpdateTask() {
        getDao().UpdateTask(getTask_id(), getT_title(), getT_description(), getStatus(), getT_date());
        return SUCCESS;
    }

    public String DeleteTask() {
        getDao().DeletePlanTask(getTask_id());
        return SUCCESS;
    }

    /**
     * @return the myplantasklist
     */
    public PlanTaskList getMyplantasklist() {
        return myplantasklist;
    }

    /**
     * @param myplantasklist the myplantasklist to set
     */
    public void setMyplantasklist(PlanTaskList myplantasklist) {
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
    public List<PlanTaskList> getTasklist() {
        return tasklist;
    }

    /**
     * @param tasklist the tasklist to set
     */
    public void setTasklist(List<PlanTaskList> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * @return the EditTask
     */
    public List<PlanTaskList> getEditTask() {
        return EditTask;
    }

    /**
     * @param EditTask the EditTask to set
     */
    public void setEditTask(List<PlanTaskList> EditTask) {
        this.EditTask = EditTask;
    }

    /**
     * @return the myplanlist
     */
    public MyPlanList getMyplanlist() {
        return myplanlist;
    }

    /**
     * @param myplanlist the myplanlist to set
     */
    public void setMyplanlist(MyPlanList myplanlist) {
        this.myplanlist = myplanlist;
    }

    /**
     * @return the planlist
     */
    public List<MyPlanList> getPlanlist() {
        return planlist;
    }

    /**
     * @param planlist the planlist to set
     */
    public void setPlanlist(List<MyPlanList> planlist) {
        this.planlist = planlist;
    }

    /**
     * @return the plan_id
     */
    public Long getPlan_id() {
        return plan_id;
    }

    /**
     * @param plan_id the plan_id to set
     */
    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }

    /**
     * @return the task_id
     */
    public Long getTask_id() {
        return task_id;
    }

    /**
     * @param task_id the task_id to set
     */
    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    /**
     * @return the t_title
     */
    public String getT_title() {
        return t_title;
    }

    /**
     * @param t_title the t_title to set
     */
    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    /**
     * @return the t_description
     */
    public String getT_description() {
        return t_description;
    }

    /**
     * @param t_description the t_description to set
     */
    public void setT_description(String t_description) {
        this.t_description = t_description;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the p_title
     */
    public String getP_title() {
        return p_title;
    }

    /**
     * @param p_title the p_title to set
     */
    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    /**
     * @return the p_description
     */
    public String getP_description() {
        return p_description;
    }

    /**
     * @param p_description the p_description to set
     */
    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    /**
     * @return the c_Date
     */
    public Calendar getC_Date() {
        return c_Date;
    }

    /**
     * @param c_Date the c_Date to set
     */
    public void setC_Date(Calendar c_Date) {
        this.c_Date = c_Date;
    }

    /**
     * @return the f
     */
    public SimpleDateFormat getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(SimpleDateFormat f) {
        this.f = f;
    }

    /**
     * @return the t_date
     */
    public String getT_date() {
        return t_date;
    }

    /**
     * @param t_date the t_date to set
     */
    public void setT_date(String t_date) {
        this.t_date = t_date;
    }
}