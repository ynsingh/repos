/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Designation;
import org.smvdu.payroll.beans.db.DesignationDB;

/**
 *
 * @author Algox
 */
public class DesignationController {

    public DesignationController()
    {
        designations = new DesignationDB().loadDesignations();
    }
    private ArrayList<Designation> designations;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<Designation> getDesignations() {
        return designations;
    }


    public void update()
    {
        ArrayList<Designation> data = (ArrayList<Designation>) dataGrid.getValue();
        for(Designation d: data)
        {
            System.out.println(d.getName());
        }
        new DesignationDB().update(data);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Designations Updated", ""));
    }
    public void setDesignations(ArrayList<Designation> salaryData) {
        this.designations = salaryData;
    }
    

}
