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

    public float getNetTax() {
        netTax = ((netIncome-netSaving)*taxPercent)/100;
        return netTax;
    }

    public void setNetTax(float netTax) {
        this.netTax = netTax;
    }
    
    
    private float netSaving=0;

    public float getTaxPercent() {
        taxPercent = new TaxCalculatorDB().getTaxPercent();
        return taxPercent;
    }

    public void setTaxPercent(float taxPercent) {
        this.taxPercent = taxPercent;
    }

    
    public float getNetSaving() {
        if(taxBeans==null)
        {            
            taxBeans = new TaxCalculatorDB().loadAnnualStat(empId);
        }
        TaxCalculatorBean tcb = taxBeans.get(taxBeans.size()-1);
        netSaving = tcb.getPercentDeduction();
        return netSaving;
    }

    public void setNetSaving(float netSaving) {
        this.netSaving = netSaving;
    }
    

    public float getNetIncome() {
        
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
        taxBeans = new TaxCalculatorDB().loadAnnualStat(empId);
        netIncome = new TaxCalculatorDB().getNetIncome(empId);
        return taxBeans;
    }

    public void setTaxBeans(ArrayList<TaxCalculatorBean> taxBeans) {
        this.taxBeans = taxBeans;
    }

    
    public void save()
    {
        System.out.println("Saving Tax Statement ...");
        EmployeeTax et = new EmployeeTax();
        et.setName(empId);
        et.setAmount(netTax);
        boolean b = new EmployeeTaxDB().save(et);
        if(b)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tax Information saved", ""));
        }
    }



}
