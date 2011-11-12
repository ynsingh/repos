/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import org.smvdu.payroll.beans.setup.InvestmentHead;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.InvestmentPlanDB;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class InvestmentPlan {


    private int planId;
    private int empId;
    private int amount;
    private InvestmentHead headId;
    private int planCode;

    public int getPlanCode() {
        return planCode;
    }

    public void setPlanCode(int planCode) {
        this.planCode = planCode;
    }
    

    private String empname;

    private String empCode;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
    

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public InvestmentHead getHeadId() {
        return headId;
    }

    public void setHeadId(InvestmentHead headId) {
        this.headId = headId;
    }

    public int getPlanId() {
        return planId;
    }


    public void delete()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();        
        Map<String, String> requestParameterMap = context.getRequestParameterMap();
        System.out.println("Delete ID : "+requestParameterMap.get("pid"));
        new InvestmentPlanDB().delete(Integer.parseInt(requestParameterMap.get("pid")));
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Details Deleted", ""));
        
    }


    public void save()
    {
        System.err.println(">>>>>>>>>>>> Saving .... ");
        new InvestmentPlanDB().savePlan(this);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Investment Details saved ", ""));
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }

    private ArrayList<InvestmentPlan> allPlanes;

    public ArrayList<InvestmentPlan> getAllPlanes() {
        return new InvestmentPlanDB().loadPlans(0);
    }

    public void setAllPlanes(ArrayList<InvestmentPlan> allPlanes) {
        this.allPlanes = allPlanes;
    }



}
