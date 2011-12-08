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

import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.MyPlans.Dao.PlanTaskDAO;
import org.IGNOU.ePortfolio.MyPlans.Model.MyPlanList;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author Vinay
 */
public class MyPlanAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private MyPlanList myplanlist;
    private PlanTaskDAO dao = new PlanTaskDAO();
    private List<MyPlanList> planlist;
    private List<MyPlanList> editPlanList;
    private Long plan_id;
    private String p_title;
    private String p_description;
    
    private String user_id=new UserSession().getUserInSession();

    @Override
    public String execute() {
        planlist = dao.Planlist(user_id);
        return SUCCESS;
    }

    /**
     * editPlan This is the function to call DAO to show editable Plan details.
     * Added on 23/08/2011
     * @author Vinay
     */
    public String editPlan() {
        editPlanList = dao.EditPlanlist(plan_id);
        return SUCCESS;
    }

    public String UpdatePlan() throws Exception {
        dao.UpdatePlanInfo(plan_id, p_title, p_description);
        return SUCCESS;
    }

    public String DeletePlan() {
        dao.DeletePlan(plan_id);
        return SUCCESS;
    }

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
     * @return the editPlanList
     */
    public List<MyPlanList> getEditPlanList() {
        return editPlanList;
    }

    /**
     * @param editPlanList the editPlanList to set
     */
    public void setEditPlanList(List<MyPlanList> editPlanList) {
        this.editPlanList = editPlanList;
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
}