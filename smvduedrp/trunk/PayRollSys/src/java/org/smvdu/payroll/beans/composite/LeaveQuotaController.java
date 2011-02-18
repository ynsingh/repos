/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.ext.attendance.LeaveQuota;
import org.smvdu.payroll.beans.ext.attendance.db.LeaveQuotaDB;

/**
 *
 * @author Algox
 */
public class LeaveQuotaController {

    private ArrayList<LeaveQuota> quotas;
    private SelectItem[] itemAsArray;
    private UIData dataGrid;
    private int empType;

    public SelectItem[] getItemAsArray() {
        quotas = new LeaveQuotaDB().getQuota(empType);
        itemAsArray = new SelectItem[quotas.size()];
        LeaveQuota lq = null;
        for(int i=0;i<quotas.size();i++)
        {
           lq = quotas.get(i);
           SelectItem si = new SelectItem(lq.getLeaveType(), lq.getLeaveTypeName());
           itemAsArray[i] = si;
        }
        return itemAsArray;
    }

    public void setItemAsArray(SelectItem[] itemAsArray) {
        this.itemAsArray = itemAsArray;
    }

    

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }
    

     public void populate()
    {
        getQuotas();
    }


    public void update()
    {
        ArrayList<LeaveQuota> data = (ArrayList<LeaveQuota>)dataGrid.getValue();
        for(LeaveQuota lq:data)
        {
            System.out.println("Name : "+lq.getLeaveTypeName()+" Value : "+lq.getCount());
        }
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<LeaveQuota> getQuotas() {
        quotas = new LeaveQuotaDB().getQuota(empType);
        for(LeaveQuota lq:quotas)
        {
            System.out.println(lq.getLeaveTypeName());
        }
        if(quotas!=null&&!quotas.isEmpty())
        {
            dataGrid.setValue(quotas);
        }
        return quotas;
    }

    public void setQuotas(ArrayList<LeaveQuota> quotas) {
        this.quotas = quotas;
    }
}
