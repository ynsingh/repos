/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import org.smvdu.payroll.beans.*;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
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
        employee = new EmployeeDB().loadProfile(empCode);
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
