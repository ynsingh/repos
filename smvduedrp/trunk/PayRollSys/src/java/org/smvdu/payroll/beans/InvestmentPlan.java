/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.InvestmentPlanDB;

/**
 *
 * @author Algox
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
