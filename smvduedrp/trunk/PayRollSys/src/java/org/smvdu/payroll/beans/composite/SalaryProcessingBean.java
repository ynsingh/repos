/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import org.smvdu.payroll.beans.setup.SalaryHead;
import org.smvdu.payroll.beans.*;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

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




public class SalaryProcessingBean {    


    private int typeCode=-1;
    private String empCode;
    private UIData incomeGrid;
    private UIData deductGrid;
    private Employee employee;
    private ArrayList<SalaryHead> incomeHeads;
    private ArrayList<SalaryHead> deductHeads;

    
    public ArrayList<SalaryHead> getDeductHeads() {
        deductHeads = new SalaryHeadDB().loadSelectedDeductionHeads(typeCode);
        return deductHeads;
    }
    public void setDeductHeads(ArrayList<SalaryHead> deductHeads) {
        this.deductHeads = deductHeads;
    }
    public ArrayList<SalaryHead> getIncomeHeads() {
        incomeHeads = new SalaryHeadDB().loadSelectedIncomeHeads(typeCode);
        return incomeHeads;
    }
    public void setIncomeHeads(ArrayList<SalaryHead> incomeHeads) {
        this.incomeHeads = incomeHeads;
    }
    public String getEmpCode() {
        return empCode;
    }
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    public void loadProfile()  {
        employee = new EmployeeDB().loadProfile(empCode,1);
        if(employee!=null)
        {
            typeCode = employee.getType();
        }
    }
    public Employee getEmployee() {
        
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public int getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }   
    public UIData getDeductGrid() {
        System.out.println(" Get Deduct Data");
        return deductGrid;
    }
    public void setDeductGrid(UIData deductGrid) {
        System.out.println(" set Deduct Data");
        this.deductGrid = deductGrid;
    }
    public UIData getIncomeGrid() {
        return incomeGrid;
    }
    public void setIncomeGrid(UIData incomeGrid) {
        this.incomeGrid = incomeGrid;
    }
    public void save(ArrayList<SalaryHead> heads)    {
        new SalaryHeadDB().updateDefault(heads, typeCode);
    }

    public void updateData()
    {
        System.out.println("Updating Salary data ...."+empCode);
        //FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary data Updated", ""));
        
        System.out.println("Code :"+empCode);

        System.out.println("Def Size :"+incomeHeads.size());
        ArrayList<SalaryHead> incomes = (ArrayList<SalaryHead>)incomeGrid.getValue();
        System.out.println("Size : "+incomes.size());
        for(SalaryHead sh : incomes)
        {
            System.out.println("Income Head : "+sh.getName()+", Amount : "+sh.getDefaultValue());
        }
        incomes = (ArrayList<SalaryHead>)deductGrid.getValue();
        System.out.println("Deduct Size : "+incomes.size());
        for(SalaryHead sh : incomes)
        {
            System.out.println("Head : "+sh.getName()+", Amount : "+sh.getDefaultValue());
        }
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary data Updated for "+empCode, ""));
     }
        
    
}
