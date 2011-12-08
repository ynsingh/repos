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
package org.IGNOU.ePortfolio.MyPlans.Model;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vinay
 */
@Entity
@Table(name = "user_plan_task")
public class PlanTaskList implements Serializable {

    private Long plan_id;
    @Id
    private Long task_id;
    private String t_title;
    private String t_description;
    private String t_date;
    private int status;
//    private String p_title;
//    private String p_description;

    public PlanTaskList(Long plan_id, Long task_id, String t_title, String t_description, String t_date, int status/*, String p_title, String p_description*/) {
        this.plan_id = plan_id;
        this.task_id = task_id;
        this.t_title = t_title;
        this.t_description = t_description;
        this.t_date = t_date;
        this.status = status;
//        this.p_title = p_title;
//        this.p_description = p_description;
    }

    public PlanTaskList() {
        super();
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


}