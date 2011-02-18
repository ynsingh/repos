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
public class DefaultSalaryDataController {

    private int typecode;
    private ArrayList<SalaryHead> heads;
    private UIData grid;

    public UIData getGrid() {
        return grid;
    }

    public void populate()
    {
        heads = new SalaryHeadDB().loadSelectedHeads(typecode);
        grid.setValue(heads);
    }

    public void setGrid(UIData grid) {
        this.grid = grid;
    }

    public ArrayList<SalaryHead> getHeads() {
        return heads;
    }

    public void setHeads(ArrayList<SalaryHead> heads) {
        this.heads = heads;
    }

    public int getTypecode() {
        return typecode;
    }

    public void setTypecode(int typecode) {
        this.typecode = typecode;
        heads = new SalaryHeadDB().loadSelectedHeads(typecode);
    }


    public void update()
    {
        System.err.println("Updating default data... ");
        ArrayList<SalaryHead> hs = (ArrayList<SalaryHead>)grid.getValue();
        for(SalaryHead sh : hs)
        {
            System.err.println("Name : "+sh.getName()+", Value : "+sh.getDefaultValue());
        }
        new SalaryHeadDB().updateDefault(hs, typecode);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Default Values Updated",""));
    }

}
