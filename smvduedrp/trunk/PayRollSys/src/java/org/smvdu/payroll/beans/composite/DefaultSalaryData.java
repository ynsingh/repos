/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.SalaryHead;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
 */
public class DefaultSalaryData {

    private int grade;
    private ArrayList<SalaryHead> salaryData;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    public void print()
    {
       ArrayList<SalaryHead> data = (ArrayList<SalaryHead>) dataGrid.getValue();
       for(SalaryHead sd : data)
       {
           System.out.println(sd.getName()+" : "+sd.getNumber()+" Value : "+sd.getDefaultValue());
       }
       System.out.println("Saving Data ...");
       new SalaryHeadDB().updateDefault(data, grade);
       FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "DefaultSalary Updated", "Default Salary Updated"));
    }

    public void populate()
    {
        salaryData = new SalaryHeadDB().loadSelectedHeads(grade);
        System.err.println(salaryData.size());
    }
    

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
        System.err.println("New Grade : "+grade);
    }

    public ArrayList<SalaryHead> getSalaryData() {
        return salaryData;
    }

    public void setSalaryData(ArrayList<SalaryHead> salaryData) {
        this.salaryData = salaryData;
    }

}
