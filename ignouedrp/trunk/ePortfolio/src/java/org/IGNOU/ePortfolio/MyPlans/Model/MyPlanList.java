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
@Table (name = "user_plan")
public class MyPlanList implements Serializable
{
    @Id
    private Long plan_id;
     private String user_id;
    private String p_title;
    private String p_description;

    public MyPlanList(Long plan_id, String user_id, String p_title, String p_description) 
    {
        super();
        this.plan_id = plan_id;
        this.user_id = user_id;
        this.p_title = p_title;
        this.p_description = p_description;
    }

    public MyPlanList() 
    {
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
    
}
