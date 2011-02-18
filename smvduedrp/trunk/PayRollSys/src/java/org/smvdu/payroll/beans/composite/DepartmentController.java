/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Department;
import org.smvdu.payroll.beans.db.DepartmentDB;

/**
 *
 * @author Algox
 */
public class DepartmentController {

    public DepartmentController()
    {
        departments = new DepartmentDB().loadDepartments();
    }
    private ArrayList<Department> departments;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }


    public void update()
    {
        ArrayList<Department> data = (ArrayList<Department>) dataGrid.getValue();
        for(Department d: data)
        {
            System.out.println(d.getName());
        }
        new DepartmentDB().update(data);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Departments Updated", ""));
    }
    public void setDepartments(ArrayList<Department> salaryData) {
        this.departments = salaryData;
    }
    

}
