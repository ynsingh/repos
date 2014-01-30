/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

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

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import java.util.Date;
import org.IGNOU.ePortfolio.DAO.PlanTaskDAO;
import org.IGNOU.ePortfolio.Model.UserPlan;
import org.IGNOU.ePortfolio.Model.UserPlanTask;

/**
 *
 * @author IGNOU Team Last Modified on 12-07-2012 by IGNOU Team
 */
public class NewTaskAction extends ActionSupport implements Serializable, ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private UserPlanTask nt = new UserPlanTask();
    private PlanTaskDAO dao = new PlanTaskDAO();
    private UserPlan up = new UserPlan();
    private int planId;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    @Override
    public String execute() {
        up.setPlanId(planId);
        nt.setUserPlan(up);
        dao.UserPlanTaskSave(nt);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        nt.setTDate(new Date().toString());
        nt.setStatus(0);
        return nt;
    }

    /**
     * @return the nt
     */
    public UserPlanTask getNt() {
        return nt;
    }

    /**
     * @param nt the nt to set
     */
    public void setNt(UserPlanTask nt) {
        this.nt = nt;
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
     * @return the up
     */
    public UserPlan getUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(UserPlan up) {
        this.up = up;
    }

    /**
     * @return the planId
     */
    public int getPlanId() {
        return planId;
    }

    /**
     * @param planId the planId to set
     */
    public void setPlanId(int planId) {
        this.planId = planId;
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
