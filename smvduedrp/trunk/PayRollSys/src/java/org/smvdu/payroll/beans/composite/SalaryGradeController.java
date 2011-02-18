/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.SalaryGrade;
import org.smvdu.payroll.beans.db.SalaryGradeDB;

/**
 *
 * @author Algox
 */
public class SalaryGradeController {


    public SalaryGradeController()
    {
        grades = new SalaryGradeDB().load();
    }

    private UIData dataGrid;
    private ArrayList<SalaryGrade> grades;

    public ArrayList<SalaryGrade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<SalaryGrade> grades) {
        this.grades = grades;
    }
    

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }



    public void save()
    {
        ArrayList<SalaryGrade> grades = (ArrayList<SalaryGrade>)dataGrid.getValue();
        for(SalaryGrade sg : grades)
        {
            System.out.println("Code : "+sg.getCode()+"Name : "+sg.getName()+", Max : "+sg.getMaxValue()+", Min : "+sg.getMinValue());
        }
        new SalaryGradeDB().update(grades);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Grades Updated", ""));
    }

}
