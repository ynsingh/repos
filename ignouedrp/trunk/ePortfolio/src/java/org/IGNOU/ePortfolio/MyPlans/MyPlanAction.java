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
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.PlanTaskDAO;
import org.IGNOU.ePortfolio.Model.UserPlan;

/**
 *
 * @author IGNOU Team
 */
public class MyPlanAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private UserPlan myplanlist;
    private PlanTaskDAO dao = new PlanTaskDAO();
    private List<UserPlan> planlist;
    private List<UserPlan> editPlanList;
    private long planId;
    private String PTitle;
    private String PDescription;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    @Override
    public String execute() {
        planlist = dao.UserPlanListByUserId(getUser_id());
        return SUCCESS;
    }

    /**
     * editPlan This is the function to call DAO to show editable Plan details.
     * Added on 23/08/2011
     *
     * @author IGNOU Team
     */
    public String editPlan() {
        setEditPlanList(getDao().UserPlanlistByPlanId(planId));
        return SUCCESS;
    }

    public String UpdatePlan() throws Exception {
        getDao().UserPlanUpdate(planId, user_id, PTitle, PDescription);
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeletePlan() throws Exception {
        getDao().UserPlanDelete(planId);
        msg = infoDeleted;
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
     * @return the editPlanList
     */
    public List<UserPlan> getEditPlanList() {
        return editPlanList;
    }

    /**
     * @param editPlanList the editPlanList to set
     */
    public void setEditPlanList(List<UserPlan> editPlanList) {
        this.editPlanList = editPlanList;
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
     * @return the PTitle
     */
    public String getPTitle() {
        return PTitle;
    }

    /**
     * @param PTitle the PTitle to set
     */
    public void setPTitle(String PTitle) {
        this.PTitle = PTitle;
    }

    /**
     * @return the PDescription
     */
    public String getPDescription() {
        return PDescription;
    }

    /**
     * @param PDescription the PDescription to set
     */
    public void setPDescription(String PDescription) {
        this.PDescription = PDescription;
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