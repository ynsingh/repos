/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

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
public class TaxCalculatorBean {

    private String empCode;
    private String investmentHead;
    private int actualAmount;
    private float percentDeduction;

    public float getPercentDeduction() {
        return percentDeduction;
    }

    public void setPercentDeduction(float percentDeduction) {
        this.percentDeduction = percentDeduction;
    }
    private int effectiveAmount;
    private static float totalInvestment;

    public float getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(float totalInvestment) {
        TaxCalculatorBean.totalInvestment = totalInvestment;
    }
    
   
    


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
        System.out.println("SEtting e a = "+effectiveAmount);
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
