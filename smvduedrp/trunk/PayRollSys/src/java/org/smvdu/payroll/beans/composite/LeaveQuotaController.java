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
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
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
