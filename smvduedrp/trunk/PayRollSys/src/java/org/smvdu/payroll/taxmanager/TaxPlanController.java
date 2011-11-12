/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
public class TaxPlanController {
    
    
    private String empCode;
    private float netTax;
    private float installmentAmount;  
    private int factor = 0;
    private float totalPlanned=0;

    public float getTotalPlanned() {
        return totalPlanned;
    }

    public void setTotalPlanned(float totalPlanned) {
        this.totalPlanned = totalPlanned;
    }
    
    
    public void init()
    {
        getExtendedMonths();
    }
   

    public String getEmpCode() {
        LoggedEmployee ec= (LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(ec!=null)
        {
            empCode= ec.getProfile().getCode();
            init();
        }
        return empCode;
    }
    
    
    private UIData datagrid;
    
    
    private ArrayList<ExtendedMonth> extendedMonths;

    public UIData getDatagrid() {
        return datagrid;
    }

    public void setDatagrid(UIData datagrid) {
        this.datagrid = datagrid;
    }

    public ArrayList<ExtendedMonth> getExtendedMonths() {
        ArrayList<ExtendedMonth> emonths = new EmployeeTaxDB().getMarkedMonths(empCode);
        for(ExtendedMonth em : emonths)
        {
            int x = extendedMonths.indexOf(em);
            if(x>0)
            {
                ExtendedMonth eem = extendedMonths.get(x);
                eem.setSelected(true);
                eem.setAmount(em.getAmount());
                extendedMonths.set(x, eem);
                factor++;
            }
        }
        datagrid.setValue(extendedMonths);
        return extendedMonths;
    }
    
    
    
    public void update()
    {
        factor=0;
        ArrayList<ExtendedMonth> data = (ArrayList<ExtendedMonth>)datagrid.getValue();
        for(ExtendedMonth em : data)
        {
            totalPlanned+=em.getBaseAmount();
            System.out.println(em.getName()+","+em.isSelected()+","+em.getAmount());
            if(em.isSelected())
            {
                factor+=em.getAmount();
            }
        }
        totalPlanned = factor;
        boolean b = new EmployeeTaxDB().updateMarkedMonths(data, empCode);               
               
        if(b)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated", ""));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not Updated. try Again", ""));
        }
    }

    public void setExtendedMonths(ArrayList<ExtendedMonth> extendedMonths) {
        this.extendedMonths = extendedMonths;
    }
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public float getInstallmentAmount() {
        if(factor>0)
        {
            installmentAmount = netTax/factor;
        }
        else installmentAmount= 0;
        if(installmentAmount>0)
        new EmployeeTaxDB().saveMonthlyInstallment(empCode, installmentAmount); 
        return installmentAmount;
    }

    public void setInstallmentAmount(float installmentAmount) {
        this.installmentAmount = installmentAmount;
    }


    public float getNetTax() {
        netTax = new EmployeeTaxDB().getTaxAmount(empCode);
        return netTax;
    }

    public void setNetTax(float netTax) {
        this.netTax = netTax;
    }
    
    
    
    public void selectAll()
    {
        for(ExtendedMonth em : extendedMonths)
        {
            em.setSelected(true);
        }
        datagrid.setValue(extendedMonths);
    }
    public void deSelectAll()
    {
        for(ExtendedMonth em : extendedMonths)
        {
            em.setSelected(false);
        }
        datagrid.setValue(extendedMonths);
    }
    
    
    
    public TaxPlanController()  {
        extendedMonths = new ArrayList<ExtendedMonth>();
        
        extendedMonths.add(new ExtendedMonth(3, "April"));
        extendedMonths.add(new ExtendedMonth(4, "May"));
        extendedMonths.add(new ExtendedMonth(5, "june"));
        extendedMonths.add(new ExtendedMonth(6, "july"));
        extendedMonths.add(new ExtendedMonth(7, "August"));
        extendedMonths.add(new ExtendedMonth(8, "September"));
        extendedMonths.add(new ExtendedMonth(9, "October"));
        extendedMonths.add(new ExtendedMonth(10, "November"));
        extendedMonths.add(new ExtendedMonth(11, "December"));
        extendedMonths.add(new ExtendedMonth(0, "january"));
        extendedMonths.add(new ExtendedMonth(1, "February"));
        extendedMonths.add(new ExtendedMonth(2, "March"));
        
    }

}
