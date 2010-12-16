/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import org.smvdu.payroll.beans.db.InvestmentPlanDB;

/**
 *
 * @author Algox
 */
public class InvestmentPlan {


    private int planId;
    private Employee empId;
    private int amount;
    private InvestmentHead headId;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
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
