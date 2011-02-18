/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.Department;
import org.smvdu.payroll.beans.EmployeeType;
import org.smvdu.payroll.beans.db.DepartmentDB;
import org.smvdu.payroll.beans.db.EmployeeTypeDB;

/**
 *
 * @author Algox
 */
public class EmployeeTypeController {

    public EmployeeTypeController()
    {
        employeeTypes = new EmployeeTypeDB().loadTypes();
    }
    private SelectItem[] asItem;

    public SelectItem[] getAsItem() {
        asItem = new SelectItem[employeeTypes.size()];
        for(int i=0;i<employeeTypes.size();i++)
        {
            SelectItem si = new SelectItem(employeeTypes.get(i).getCode(), employeeTypes.get(i).getName());
            asItem[i] = si;
        }
        return asItem;
    }

    public void setAsItem(SelectItem[] asItem) {
        this.asItem = asItem;
    }

    private ArrayList<EmployeeType> employeeTypes;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<EmployeeType> getEmployeeTypes() {
        return employeeTypes;
    }


    public void update()
    {
        ArrayList<EmployeeType> data = (ArrayList<EmployeeType>) dataGrid.getValue();
        for(EmployeeType d: data)
        {
            System.out.println(d.getName());
        }
        new EmployeeTypeDB().update(data);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Types Updated", ""));
    }
    public void setEmployeeTypes(ArrayList<EmployeeType> salaryData) {
        this.employeeTypes = salaryData;
    }
    

}
