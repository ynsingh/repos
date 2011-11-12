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
import org.smvdu.payroll.beans.setup.EmployeeType;
import org.smvdu.payroll.beans.db.EmployeeTypeDB;

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
        dataGrid.setValue(employeeTypes);
        return employeeTypes;
    }


    public void update()
    {
        ArrayList<EmployeeType> data = (ArrayList<EmployeeType>) dataGrid.getValue();
        for(EmployeeType d: data)
        {
            System.out.println(d.getName()+", PF Applies :"+d.isPfApplies());
        }
        new EmployeeTypeDB().update(data);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Types Updated", ""));
    }
    public void setEmployeeTypes(ArrayList<EmployeeType> salaryData) {
        this.employeeTypes = salaryData;
    }
    

}
