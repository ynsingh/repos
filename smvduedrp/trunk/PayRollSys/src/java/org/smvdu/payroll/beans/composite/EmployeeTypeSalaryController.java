/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.EmployeeTypeSalaryHead;
import org.smvdu.payroll.beans.db.EmployeeTypeSalaryHeadDB;

/**
 *
 * @author Algox
 */
public class EmployeeTypeSalaryController {

    private ArrayList<EmployeeTypeSalaryHead> allHeads;
    private UIData datagrid;

    public UIData getDatagrid() {
        return datagrid;
    }

    public void setDatagrid(UIData datagrid) {
        this.datagrid = datagrid;
    }
    private int empType;
    public ArrayList<EmployeeTypeSalaryHead> getAllHeads() {
        allHeads =  new EmployeeTypeSalaryHeadDB().loadHeads(empType);
        datagrid.setValue(allHeads);
        return allHeads;
    }
    public void setAllHeads(ArrayList<EmployeeTypeSalaryHead> allHeads) {
        this.allHeads = allHeads;
    }
    public int getEmpType() {
        return empType;
    }
    public void setEmpType(int empType) {
        this.empType = empType;
    }
    public void populate()  {
        System.err.println("Reloading ... "+empType);
        getAllHeads();
    }

    public void print()
    {
        System.out.println("Printing selection ");
        ArrayList<EmployeeTypeSalaryHead> datacopy = new ArrayList<EmployeeTypeSalaryHead>();
        ArrayList<EmployeeTypeSalaryHead> data = ( ArrayList<EmployeeTypeSalaryHead>)datagrid.getValue();
        for(EmployeeTypeSalaryHead esh : data)
        {
            if(esh.isSelected())
            {
               datacopy.add(esh);
            }            
        }
        new EmployeeTypeSalaryHeadDB().save(datacopy, empType);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selection Updated", ""));
       
    }
}
