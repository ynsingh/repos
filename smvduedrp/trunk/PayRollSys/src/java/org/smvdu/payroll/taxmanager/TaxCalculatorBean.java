/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

/**
 *
 * @author Algox
 */
public class TaxCalculatorBean {

    private String empCode;
    private String investmentHead;
    private int actualAmount;
    private int effectiveAmount;


    private int headCode;
    private int empId;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getHeadCode() {
        return headCode;
    }

    public void setHeadCode(int headCode) {
        this.headCode = headCode;
    }

    private int maxLimitAmount;

    public int getMaxLimitAmount() {
        return maxLimitAmount;
    }

    public void setMaxLimitAmount(int maxLimitAmount) {
        this.maxLimitAmount = maxLimitAmount;
    }
    

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public int getEffectiveAmount() {
        if(actualAmount<maxLimitAmount)
        {
            effectiveAmount = actualAmount;
        }
        else
            effectiveAmount = maxLimitAmount;
        return effectiveAmount;
    }

    public void setEffectiveAmount(int effectiveAmount) {
        this.effectiveAmount = effectiveAmount;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getInvestmentHead() {
        return investmentHead;
    }

    public void setInvestmentHead(String investmentHead) {
        this.investmentHead = investmentHead;
    }
    

}
