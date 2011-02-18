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
public class SalaryHeadController {

    public SalaryHeadController()
    {
        heads = new SalaryHeadDB().loadAllHeads();
    }

    private ArrayList<SalaryHead> heads;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<SalaryHead> getHeads() {
        return heads;
    }

    public void setHeads(ArrayList<SalaryHead> heads) {
        this.heads = heads;
    }



    public void delete(int id)
    {
        System.out.println("Got delete request for "+id);
    }

    public void update()
    {
        ArrayList<SalaryHead> hs = (ArrayList<SalaryHead>)dataGrid.getValue();
        for(SalaryHead sh : hs)
        {
            System.out.println("Name : "+sh.getName());
        }
        new SalaryHeadDB().update(heads);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary Heads Updated", ""));
    }

}
