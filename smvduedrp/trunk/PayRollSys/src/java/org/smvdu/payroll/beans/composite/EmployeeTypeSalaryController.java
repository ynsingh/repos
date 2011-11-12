/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.setup.EmployeeTypeSalaryHead;
import org.smvdu.payroll.beans.db.EmployeeTypeSalaryHeadDB;

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
