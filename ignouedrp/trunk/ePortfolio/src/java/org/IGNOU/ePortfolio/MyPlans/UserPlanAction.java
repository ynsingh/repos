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
import org.IGNOU.ePortfolio.MyPlans.Model.UserPlanModel;
import org.IGNOU.ePortfolio.MyPlans.Dao.PlanTaskDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *
 * @author Vinay
 */
public class UserPlanAction extends ActionSupport implements ModelDriven<UserPlanModel> {
    private static final long serialVersionUID = 1L;

    private PlanTaskDAO hu = new PlanTaskDAO();
    private UserPlanModel upModel;
    private String user_id=new UserSession().getUserInSession();

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String DataInsert() {
        upModel.setUser_id(user_id);
        getHu().saveInfo(getUpModel());
        return SUCCESS;
    }

    @Override
    public UserPlanModel getModel() {
        setUpModel(new UserPlanModel());
        return getUpModel();
    }

    /**
     * @return the upModel
     */
    public UserPlanModel getUpModel() {
        return upModel;
    }

    /**
     * @param upModel the upModel to set
     */
    public void setUpModel(UserPlanModel upModel) {
        this.upModel = upModel;
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
     * @return the hu
     */
    public PlanTaskDAO getHu() {
        return hu;
    }

    /**
     * @param hu the hu to set
     */
    public void setHu(PlanTaskDAO hu) {
        this.hu = hu;
    }
}
