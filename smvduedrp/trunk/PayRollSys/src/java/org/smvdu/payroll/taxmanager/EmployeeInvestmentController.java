/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
public class EmployeeInvestmentController {
    
    private UIData dataGrid;
    
    private String empCode;

    public String getEmpCode() {

        LoggedEmployee ec= (LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(ec!=null)
        {
            empCode= ec.getProfile().getCode();
            loadData();
        }
        return empCode;
        
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    
    private ArrayList<EmployeeInvestment> investments;

    
    
    public void loadData()
    {
        System.out.println(empCode);
        if(empCode!=null)
        {
            investments = new EmployeeInvestmentDB().loadInvestments(empCode);
            dataGrid.setValue(investments);
        }
    }
    public UIData getDataGrid() {
       
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<EmployeeInvestment> getInvestments() {
        
        return investments;
    }

    public void setInvestments(ArrayList<EmployeeInvestment> investments) {
        this.investments = investments;
    }
    
    
    public void update()
    {
        ArrayList<EmployeeInvestment> data = (ArrayList<EmployeeInvestment>)dataGrid.getValue();
        for(EmployeeInvestment ei : data)
        {
            System.out.println(ei.getCode()+","+ei.getName()+" : "+ei.getAmount());
        }
        new EmployeeInvestmentDB().update(data, empCode);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Record Updated", ""));
    }
    
    

}
