/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.InvestmentMaster;
import org.smvdu.payroll.beans.db.InvestmentMasterDB;

/**
 *
 * @author Algox
 */
public class InvestmentController {

     private UIData dataGrid;

    private ArrayList<InvestmentMaster> heads;
    private SelectItem[] asItems;

    public SelectItem[] getAsItems() {
        heads = new InvestmentMasterDB().load();
        asItems = new SelectItem[heads.size()];
        int k=0;
        for(InvestmentMaster im : heads)
        {
            SelectItem si = new SelectItem(im.getId(), im.getName());
            asItems[k] = si;
            k++;
        }
        return asItems;
    }

    public void setAsItems(SelectItem[] asItems) {
        this.asItems = asItems;
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<InvestmentMaster> getHeads() {
        heads = new InvestmentMasterDB().load();
        return heads;
    }

    public void setHeads(ArrayList<InvestmentMaster> heads) {
        this.heads = heads;
    }


}
