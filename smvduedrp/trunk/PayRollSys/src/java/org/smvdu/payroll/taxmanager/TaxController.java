/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

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
public class TaxController {


    private UIData datagrid;
    private String empId;
    private float netIncome;
    private float taxPercent;
    private float netTax;
    private float taxableAmount;
    private int effectiveInv = 0;
    private float educess;
    private float actualCalc;

    public float getActualCalc() {
      actualCalc = new TaxCalculatorDB().getActualCalc(this.getEmpId());
        
      //System.out.println("actual: in set "+getActualCalc());
      return actualCalc;
    }

    public void setActualCalc(float actualCalc) {
        this.actualCalc = actualCalc;
    }

    public float getEducess() {
      educess = Math.abs(netTax * 2)/100;
        return educess;
    }

    public void setEducess(float educess) {
        this.educess = educess;
    }


   


    TaxCalculatorDB taxDb = new TaxCalculatorDB();


    public int getEffectiveInv() {
        return effectiveInv;
    }

    public void setEffectiveInv(int effectiveInv) {
        this.effectiveInv = effectiveInv;
    }
    EmployeeTaxDB et = new EmployeeTaxDB();
    EmployeeTax emt = new EmployeeTax();

    public float getTaxableAmount() {
        taxableAmount =this.netIncome-this.netSaving;

        if(taxableAmount < 200000)
        {
            taxableAmount = Math.abs(taxableAmount);
        }
        return taxableAmount;
    }

    public void setTaxableAmount(float taxableAmount) {
        System.out.println("Total Taxable Amount : in set "+taxableAmount);
        this.taxableAmount = taxableAmount;
    }
    public float getNetTax() {
        netTax = taxDb.getTaxValue();
        System.out.println("Tax Value : get : "+netTax);
        //netTax = (Math.abs((netIncome-netSaving)*taxPercent))/100;
        return netTax;
    }

    public void setNetTax(float netTax) {
        this.netTax = netTax;
    }


    private float netSaving=0;

    public float getTaxPercent() {
        taxPercent = taxDb.getTaxPercent((int)this.getTaxableAmount());
        return taxPercent;
    }

    public void setTaxPercent(float taxPercent) {
        System.out.println("Total Taxable Amount : "+taxPercent);

        this.taxPercent = taxPercent;
    }


    public float getNetSaving() {
        if(taxBeans==null)
        {
            taxBeans = new TaxCalculatorDB().loadAnnualStat(empId);
        }
        TaxCalculatorBean tcb = taxBeans.get(taxBeans.size()-1);
        netSaving = tcb.getPercentDeduction();
        System.out.println("Net Saving In Tax : "+netSaving);
        //et.setSaving(netSaving);
        emt.setNetSaving(netSaving);
        emt.setSaving(netSaving);
        emt.setEducesse(educess);
        return netSaving;
    }

    public void setNetSaving(float netSaving) {
        System.out.println("Net Saving In Tax in set : "+netSaving);
        this.netSaving = netSaving;
    }


    public float getNetIncome() {
        
        
        System.out.println("Total NetIncome : "+netIncome);
        return netIncome;
    }

    public void setNetIncome(float netIncome) {
        this.netIncome = netIncome;
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    private ArrayList<TaxCalculatorBean> taxBeans;

    public UIData getDatagrid() {
        return datagrid;
    }

    public void setDatagrid(UIData datagrid) {
        this.datagrid = datagrid;
    }

    public ArrayList<TaxCalculatorBean> getTaxBeans() {
        int e = 0;
        taxBeans = new TaxCalculatorDB().loadAnnualStat(empId);
        for(TaxCalculatorBean ta:taxBeans)
        {
            e = ta.getEffectiveAmount();
        }
        this.setEffectiveInv(e);
        netIncome = new TaxCalculatorDB().getNetIncome(empId);
        return taxBeans;
    }

    public void setTaxBeans(ArrayList<TaxCalculatorBean> taxBeans) {
        this.taxBeans = taxBeans;
    }


    public void save()
    {
        System.out.println("Saving Tax Statement ...");
        emt.setName(empId);
        emt.setAmount(netTax);
        emt.setEffectiveIe(this.getEffectiveInv());
        emt.setEducesse(educess);
         boolean b = et.save(emt,this);
        if(b)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tax Information saved", ""));
        }
 else{

    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tax Information not saved", ""));



 }
    }



}
